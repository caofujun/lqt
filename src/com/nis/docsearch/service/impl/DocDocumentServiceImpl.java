package com.nis.docsearch.service.impl;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.nis.access.entity.AcAccount;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.ae;
import com.nis.comm.utils.z;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.docsearch.dao.DocDocumentDao;
import com.nis.docsearch.dao.DocDocumentDownloadDao;
import com.nis.docsearch.entity.DocDocument;
import com.nis.docsearch.entity.DocDocumentDownload;
import com.nis.docsearch.service.DocDocumentService;
import com.nis.param.service.SysParamService;




class DocDocumentServiceImpl$1
  implements Runnable
{
	DocDocumentServiceImpl qT;
	DocDocument qU;
	AcAccount qV;
	
  DocDocumentServiceImpl$1(DocDocumentServiceImpl paramDocDocumentServiceImpl, DocDocument paramDocDocument, AcAccount paramAcAccount) {
	  this.qT = paramDocDocumentServiceImpl;
	  this.qU = paramDocDocument;
	  this.qV = paramAcAccount;
  }
  
  public void run()
  {
    boolean opreateArray = false;
    this.qU.setCreateUserid(this.qV.getUserId());
    this.qU.setCreateUsername(this.qV.getRealname());
    this.qU.setCreateTime(com.nis.comm.utils.f.getCurDate());
    this.qU.setUpdateUserid(this.qV.getUserId());
    this.qU.setUpdateUsername(this.qV.getRealname());
    this.qU.setUpdateTime(com.nis.comm.utils.f.getCurDate());
    this.qU.setDocLook(Long.valueOf(0L));
    this.qU.setDocDownload(Long.valueOf(0L));
    this.qU.setDocFlag("1");
    if (opreateArray) {
      this.qU.setDocPreview("1");
    } else {
      this.qU.setDocPreview("2");
    }
    this.qT.a(this.qU);
  }
}

@Component
public class DocDocumentServiceImpl implements DocDocumentService {
	@Autowired
	private DocDocumentDao qS;
	@Autowired
	private SysParamService j;
	@Autowired
	private DocDocumentDownloadDao qR;
	@Autowired
	private SysDictService p;

	public String a(DocDocument docDocument) {
		docDocument.setId(z.a(bg.nW));
		this.qS.save(docDocument);
		return docDocument.getId();
	}

	public void delete(String id) {
		this.qS.delete(id);
	}

	public void update(DocDocument docDocument) {
		this.qS.update(docDocument);
	}

	public DocDocument get(String id) {
		DocDocument docDocument = this.qS.get(id);
		if (docDocument != null) {
			List list = this.p.u("docType", (String) null);
			Iterator arg4 = list.iterator();

			while (arg4.hasNext()) {
				SysDict sysDict = (SysDict) arg4.next();
				if (docDocument.getDocType().equalsIgnoreCase(sysDict.getDictCode())) {
					docDocument.setDocTypeName(sysDict.getDictName());
				}
			}

			if (docDocument.getDocTypeName() == null || docDocument.getDocTypeName().equals("")
					|| docDocument.getDocTypeName() == "") {
				docDocument.setDocTypeName("全部");
			}

			docDocument.setDocDownloadForPeople(Long.valueOf((long) this.qR.findDocDocumentCountGroupByAccountId(id)));
		}

		return docDocument;
	}

	public MyPage<DocDocument> b(DocDocument docDocument) {
		int total = this.qS.findDocDocumentCountNOPrivilege(docDocument);
		List data = null;
		if (total > 0) {
			data = this.qS.findDocDocumentNOPrivilege(docDocument);
		}

		return new MyPage(docDocument.getPage().intValue(), docDocument.getSize().intValue(), total, data);
	}

	public List<DocDocument> getAll() {
		return this.qS.getAll();
	}

	public List<DocDocument> findDocDocument(DocDocument docDocument) {
		return this.qS.findDocDocument(docDocument);
	}

	public Map<String, DocDocument> C(List<String> idIn) {
		HashMap map = new HashMap();
		List list = this.qS.findDocumentIdIn(idIn);
		Iterator arg4 = list.iterator();

		while (arg4.hasNext()) {
			DocDocument document = (DocDocument) arg4.next();
			map.put(document.getId(), document);
		}

		return map;
	}

	public int findDocDocumentCount(DocDocument docDocument) {
		return this.qS.findDocDocumentCount(docDocument);
	}

	public String a(CommonsMultipartFile file, AcAccount acAccount, String newFileName) throws Exception {
		Calendar a = Calendar.getInstance();
		String filePath = this.j.findByParamCode(Param.NIS_DOC_UPLOAD_SAVE_DIR) + File.separator + a.get(1)
				+ File.separator + (a.get(2) + 1) + File.separator + a.get(5) + File.separator
				+ acAccount.getUsername();
		if (newFileName == null) {
			newFileName = file.getOriginalFilename();
		}

		ae.a(file.getInputStream(), newFileName, filePath);
		return filePath + File.separator + newFileName;
	}

	public void a(String fileId, AcAccount acAccount) throws Exception {
		DocDocument docDocument = this.qS.get(fileId);
		Long downCout = docDocument.getDocDownload();
		downCout = Long.valueOf(downCout.longValue() + 1L);
		docDocument.setDocDownload(downCout);
		this.qS.update(docDocument);
		DocDocumentDownload docDocumentDownload = new DocDocumentDownload();
		docDocumentDownload.setDocId(fileId);
		docDocumentDownload.setDocScore(docDocument.getDocScore());
		docDocumentDownload.setCreateUserid(acAccount.getUserId());
		docDocumentDownload.setCreateUsername(acAccount.getUsername());
		docDocumentDownload.setCreateTime(new Date());
		docDocumentDownload.setId(z.a(bg.nX));
		this.qR.save(docDocumentDownload);
	}

	public void a(DocDocument docDocument, AcAccount acAccount) throws Exception {
      (new Thread(new DocDocumentServiceImpl$1(this, docDocument, acAccount))).start();
   }

	public List<DocDocument> findDocDocumentNOPrivilege(DocDocument docDocument) {
		return this.qS.findDocDocumentNOPrivilege(docDocument);
	}

	public int findDocDocumentCountNOPrivilege(DocDocument docDocument) {
		return this.qS.findDocDocumentCountNOPrivilege(docDocument);
	}

	public MyPage<DocDocument> c(DocDocument docDocument) {
		int total = this.qS.findDocDocumentCountNOPrivilegeForSuggest(docDocument);
		List data = null;
		if (total > 0) {
			data = this.qS.findDocDocumentNOPrivilegeForSuggest(docDocument);
		}

		return new MyPage(docDocument.getPage().intValue(), docDocument.getSize().intValue(), total, data);
	}

	public List<DocDocument> findDocDocumentNOPrivilegeForSuggest(DocDocument docDocument) {
		return this.qS.findDocDocumentNOPrivilegeForSuggest(docDocument);
	}

	public int findDocDocumentCountNOPrivilegeForSuggest(DocDocument docDocument) {
		return this.qS.findDocDocumentCountNOPrivilegeForSuggest(docDocument);
	}

	public List<DocDocument> getIndex(DocDocument docDocument) {
		return this.qS.getIndex(docDocument);
	}

	public void w(String userId, String fileId) throws Exception {
	}

	public int getDocCountByDocName(String docName) {
		return this.qS.getDocCountByDocName(docName);
	}
}