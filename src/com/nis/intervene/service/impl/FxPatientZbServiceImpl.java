package com.nis.intervene.service.impl;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.z;
import com.nis.intervene.dao.FxPatientZbDao;
import com.nis.intervene.entity.FxPatientZb;
import com.nis.intervene.service.FxPatientZbService;
import com.nis.intervene.service.impl.FxPatientZbServiceImpl.1;
import com.nis.pdca.service.ZlPdcaPlansService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FxPatientZbServiceImpl implements FxPatientZbService {
	@Autowired
	private FxPatientZbDao sL;
	@Autowired
	private ZlPdcaPlansService su;

	public void save(FxPatientZb fxPatientZb) {
		FxPatientZb fpz = this.sL.findByZyidAndZbId(fxPatientZb.getZyId(), fxPatientZb.getZbId(),
				fxPatientZb.getStartDate());
		if (fpz != null) {
			fpz.setZbScore(fxPatientZb.getZbScore());
			this.sL.update(fpz);
		} else {
			fxPatientZb.setPzId(z.a(bg.np));
			this.sL.save(fxPatientZb);
		}

	}

	public List<FxPatientZb> a(FxPatientZb fxPatientZb) {
		return this.sL.findByZyidAndZbIdAndDate(fxPatientZb.getZyId(), fxPatientZb.getZbId(),
				f.formatDate(fxPatientZb.getStartDate()));
	}

	public void delete(String pzId) {
		this.sL.delete(pzId);
	}

	public void update(FxPatientZb fxPatientZb) {
		this.sL.update(fxPatientZb);
	}

	public FxPatientZb get(String pzId) {
		return this.sL.get(pzId);
	}

	public MyPage<FxPatientZb> b(FxPatientZb fxPatientZb) {
		int total = this.sL.findFxPatientZbCount(fxPatientZb);
		List data = null;
		if (total > 0) {
			data = this.sL.findFxPatientZb(fxPatientZb);
		}

		return new MyPage(fxPatientZb.getPage().intValue(), fxPatientZb.getSize().intValue(), total, data);
	}

	public List<FxPatientZb> getAll() {
		return this.sL.getAll();
	}

	@SqlLog(
      p = "风险分析--患者风险详情"
   )
   public List<FxPatientZb> findByzyid(String zyid) {
      List patientZbList = this.sL.findByzyid(zyid);
      List patientZbList2 = this.sL.findGrYjByzyid(zyid);
      List patientZbList3 = this.sL.findQsByzyid(zyid);
      patientZbList.addAll(patientZbList2);
      patientZbList.addAll(patientZbList3);
      Collections.sort(patientZbList, new 1(this));
      Iterator arg5 = patientZbList.iterator();

      while(true) {
         FxPatientZb patientZb;
         do {
            if(!arg5.hasNext()) {
               return patientZbList;
            }

            patientZb = (FxPatientZb)arg5.next();
         } while(!ab.isNotEmpty(patientZb.getZbNames()));

         String[] zbNames = patientZb.getZbNames().split(",");
         ArrayList pzList = new ArrayList();
         String[] arg11 = zbNames;
         int arg10 = zbNames.length;

         for(int arg9 = 0; arg9 < arg10; ++arg9) {
            String zbName = arg11[arg9];
            FxPatientZb pz = new FxPatientZb();
            String[] pzs = zbName.split("!");
            pz.setPzId(pzs[0]);
            pz.setZbName(pzs[1]);
            if(pzs.length > 2) {
               pz.setPzStatus(pzs[2]);
            }

            if(pzs.length > 3) {
               pz.setPdcaStatus(pzs[3]);
            }

            if(pzs.length > 4) {
               pz.setQsStatus(pzs[4]);
            }

            if(pzs.length > 5) {
               pz.setFxColor(pzs[5]);
            }

            pzList.add(pz);
         }

         patientZb.setPzList(pzList);
      }
   }

	public void saveList(List<FxPatientZb> fxPatientList) {
		this.sL.saveList(fxPatientList);
	}
}