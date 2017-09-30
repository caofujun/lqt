package com.nis.analysis.service.impl;

import com.nis.analysis.entity.ZdmxWeight;
import com.nis.analysis.entity.Zg006Zdmx;
import com.nis.analysis.service.AnalysisModelService;
import com.nis.comm.utils.f;
import com.nis.monitor.entity.Bk001Sbk;
import com.nis.monitor.entity.Gr002YsgrMx;
import com.nis.monitor.entity.Gr018Ysgrys;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St005Ssxxb;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JudgeCode {
	@Autowired
	private AnalysisModelService D;

	public void n(List<Gr002YsgrMx> gr002List) {
	}

	public void o(List<Zg006Zdmx> zg006List) {
		boolean JB = true;
		Iterator zdmx = zg006List.iterator();

		while (zdmx.hasNext()) {
			Zg006Zdmx i = (Zg006Zdmx) zdmx.next();
			if ("JB".equals(i.getNodeType2())) {
				JB = false;
			}
		}

		if (JB) {
			for (int arg4 = 0; arg4 < zg006List.size(); ++arg4) {
				Zg006Zdmx arg5 = (Zg006Zdmx) zg006List.get(arg4);
				if ("ALL".equals(arg5.getNodeType2())) {
					zg006List.remove(arg5);
					--arg4;
				}
			}
		}

	}

	public void c(List<Zg006Zdmx> zg006List, St003Cryxxb cryxxb) {
		for (int i = 0; i < zg006List.size(); ++i) {
			Zg006Zdmx zdmx = (Zg006Zdmx) zg006List.get(i);
			if (!"女".equals(cryxxb.getSex()) && (zdmx.getInfectCode().equals("EMET")
					|| zdmx.getInfectCode().equals("EPIS") || zdmx.getInfectCode().equals("VCUF"))) {
				zg006List.remove(zdmx);
				--i;
			}
		}

	}

	public void d(List<Zg006Zdmx> zg006List, St003Cryxxb cryxxb) {
		Double age = Double.valueOf(0.0D);
		if (cryxxb.getBirthDate() != null) {
			f.d(cryxxb.getBirthDate(), (String) null);
			age = Double.valueOf(Double.parseDouble(cryxxb.getAge()));
		}

		for (int i = 0; i < zg006List.size(); ++i) {
			Zg006Zdmx zdmx = (Zg006Zdmx) zg006List.get(i);
			if (age.doubleValue() >= 1.0D) {
				if (zdmx.getInfectCode().equals("PUST")) {
					zg006List.remove(zdmx);
					--i;
				}

				if (zdmx.getInfectCode().equals("CIRC") || zdmx.getInfectCode().equals("UMB")) {
					zg006List.remove(zdmx);
					--i;
				}
			}

			boolean xsr = false;
			Date date = f.a(cryxxb.getBirthDate(), 30);
			if (cryxxb.getInHospAt() != null && date.getTime() > cryxxb.getInHospAt().getTime()) {
				xsr = true;
			}

			if (!xsr && (zdmx.getInfectCode().equals("CIRC") || zdmx.getInfectCode().equals("UMB"))) {
				zg006List.remove(zdmx);
				--i;
			}
		}

	}

	public void c(List<Zg006Zdmx> zg006List, List<String> infectCodeList) {
		boolean TYX = false;
		Iterator zdmx = zg006List.iterator();

		while (zdmx.hasNext()) {
			Zg006Zdmx i = (Zg006Zdmx) zdmx.next();
			if ("OUTI".equals(i.getInfectCode()) && "TYX".equals(i.getNodeType2())) {
				TYX = true;
			}
		}

		if (TYX) {
			for (int arg5 = 0; arg5 < zg006List.size(); ++arg5) {
				Zg006Zdmx arg6 = (Zg006Zdmx) zg006List.get(arg5);
				if ("SUTI".equals(arg6.getInfectCode())) {
					zg006List.remove(arg6);
					--arg5;
				}
			}
		}

	}

	public void d(List<Gr018Ysgrys> gr018List, List<Zg006Zdmx> zg006List) {
		boolean exist = false;
		Iterator arg4 = gr018List.iterator();

		while (arg4.hasNext()) {
			Gr018Ysgrys zdmx = (Gr018Ysgrys) arg4.next();
			if ("X000131".equals(zdmx.getElementId())) {
				exist = true;
			}
		}

		if (exist) {
			arg4 = zg006List.iterator();

			while (arg4.hasNext()) {
				Zg006Zdmx zdmx1 = (Zg006Zdmx) arg4.next();
				if ("JY000239".equals(zdmx1.getElementId()) && "SKIN".equals(zdmx1.getInfectCode())) {
					zdmx1.setWeightValue("0");
				}
			}
		}

	}

	public void p(List<Zg006Zdmx> zg006List) {
		boolean exist = true;
		Iterator zdmx = zg006List.iterator();

		while (zdmx.hasNext()) {
			Zg006Zdmx i = (Zg006Zdmx) zdmx.next();
			if ("PNU2".equals(i.getInfectCode()) && "JY".equals(i.getNodeType2())) {
				exist = false;
			}
		}

		int arg4;
		Zg006Zdmx arg5;
		if (exist) {
			for (arg4 = 0; arg4 < zg006List.size(); ++arg4) {
				arg5 = (Zg006Zdmx) zg006List.get(arg4);
				if ("PNU2".equals(arg5.getInfectCode())) {
					zg006List.remove(arg5);
					--arg4;
				}
			}
		} else {
			for (arg4 = 0; arg4 < zg006List.size(); ++arg4) {
				arg5 = (Zg006Zdmx) zg006List.get(arg4);
				if ("PNU1".equals(arg5.getInfectCode())) {
					zg006List.remove(arg5);
					--arg4;
				}
			}
		}

	}

	public void e(List<Map<String, ZdmxWeight>> infectCodeWeightList, List<Gr002YsgrMx> gr002List) {
		int URWeight = 0;
		int PNU1Weight = 0;
		int PNU2Weight = 0;
		Iterator PNU1Gr = infectCodeWeightList.iterator();

		while (PNU1Gr.hasNext()) {
			Map URGr = (Map) PNU1Gr.next();
			Iterator i = URGr.entrySet().iterator();

			while (i.hasNext()) {
				Entry PNU2Gr = (Entry) i.next();
				if (((String) PNU2Gr.getKey()).equals("PNU1")) {
					PNU1Weight = ((ZdmxWeight) PNU2Gr.getValue()).getWeight().intValue();
				} else if (((String) PNU2Gr.getKey()).equals("PNU2")) {
					PNU2Weight = ((ZdmxWeight) PNU2Gr.getValue()).getWeight().intValue();
				} else if (((String) PNU2Gr.getKey()).equals("UR")) {
					URWeight = ((ZdmxWeight) PNU2Gr.getValue()).getWeight().intValue();
				}
			}
		}

		Gr002YsgrMx arg12 = null;
		Gr002YsgrMx arg13 = null;
		Gr002YsgrMx arg14 = null;
		Iterator map = gr002List.iterator();

		while (map.hasNext()) {
			Gr002YsgrMx arg15 = (Gr002YsgrMx) map.next();
			if ("UR".equals(arg15.getYjInfectCode())) {
				arg12 = arg15;
			} else if ("PNU1".equals(arg15.getYjInfectCode())) {
				arg13 = arg15;
			} else if ("PNU2".equals(arg15.getYjInfectCode())) {
				arg14 = arg15;
			}
		}

		if (arg12 != null && arg12.getSuspectedDegree() != null
				&& arg12.getSuspectedDegree().doubleValue() - (double) URWeight > 0.0D) {
			URWeight = arg12.getSuspectedDegree().intValue();
		}

		if (arg13 != null && arg13.getSuspectedDegree() != null
				&& arg13.getSuspectedDegree().doubleValue() - (double) PNU1Weight > 0.0D) {
			PNU1Weight = arg13.getSuspectedDegree().intValue();
		}

		if (arg14 != null && arg14.getSuspectedDegree() != null
				&& arg14.getSuspectedDegree().doubleValue() - (double) PNU2Weight > 0.0D) {
			PNU2Weight = arg14.getSuspectedDegree().intValue();
		}

		Entry entry;
		Iterator arg11;
		int arg16;
		Map arg17;
		if (URWeight - PNU1Weight > 0) {
			for (arg16 = 0; arg16 < infectCodeWeightList.size(); ++arg16) {
				arg17 = (Map) infectCodeWeightList.get(arg16);
				arg11 = arg17.entrySet().iterator();

				while (arg11.hasNext()) {
					entry = (Entry) arg11.next();
					if (((String) entry.getKey()).equals("PNU1")) {
						infectCodeWeightList.remove(arg17);
						--arg16;
					}
				}
			}
		}

		if (URWeight - PNU2Weight > 0) {
			for (arg16 = 0; arg16 < infectCodeWeightList.size(); ++arg16) {
				arg17 = (Map) infectCodeWeightList.get(arg16);
				arg11 = arg17.entrySet().iterator();

				while (arg11.hasNext()) {
					entry = (Entry) arg11.next();
					if (((String) entry.getKey()).equals("PNU2")) {
						infectCodeWeightList.remove(arg17);
						--arg16;
					}
				}
			}
		}

		if (PNU1Weight - URWeight > 0) {
			for (arg16 = 0; arg16 < infectCodeWeightList.size(); ++arg16) {
				arg17 = (Map) infectCodeWeightList.get(arg16);
				arg11 = arg17.entrySet().iterator();

				while (arg11.hasNext()) {
					entry = (Entry) arg11.next();
					if (((String) entry.getKey()).equals("UR")) {
						infectCodeWeightList.remove(arg17);
						--arg16;
					}
				}
			}
		}

		if (PNU2Weight - URWeight > 0) {
			for (arg16 = 0; arg16 < infectCodeWeightList.size(); ++arg16) {
				arg17 = (Map) infectCodeWeightList.get(arg16);
				arg11 = arg17.entrySet().iterator();

				while (arg11.hasNext()) {
					entry = (Entry) arg11.next();
					if (((String) entry.getKey()).equals("UR")) {
						infectCodeWeightList.remove(arg17);
						--arg16;
					}
				}
			}
		}

		if (PNU1Weight - URWeight == 0) {
			for (arg16 = 0; arg16 < infectCodeWeightList.size(); ++arg16) {
				arg17 = (Map) infectCodeWeightList.get(arg16);
				arg11 = arg17.entrySet().iterator();

				while (arg11.hasNext()) {
					entry = (Entry) arg11.next();
					if (((String) entry.getKey()).equals("UR")) {
						infectCodeWeightList.remove(arg17);
						--arg16;
					}
				}
			}
		}

		if (PNU2Weight - URWeight == 0) {
			for (arg16 = 0; arg16 < infectCodeWeightList.size(); ++arg16) {
				arg17 = (Map) infectCodeWeightList.get(arg16);
				arg11 = arg17.entrySet().iterator();

				while (arg11.hasNext()) {
					entry = (Entry) arg11.next();
					if (((String) entry.getKey()).equals("UR")) {
						infectCodeWeightList.remove(arg17);
						--arg16;
					}
				}
			}
		}

	}

	public void q(List<Map<String, ZdmxWeight>> infectCodeWeightList) {
		int PNU1Weight = 0;
		int PNU2Weight = 0;
		int PNU3Weight = 0;
		Iterator map = infectCodeWeightList.iterator();

		Entry entry;
		Iterator arg7;
		while (map.hasNext()) {
			Map i = (Map) map.next();
			arg7 = i.entrySet().iterator();

			while (arg7.hasNext()) {
				entry = (Entry) arg7.next();
				if (((String) entry.getKey()).equals("PNU1")) {
					PNU1Weight = ((ZdmxWeight) entry.getValue()).getWeight().intValue();
				} else if (((String) entry.getKey()).equals("PNU2")) {
					PNU2Weight = ((ZdmxWeight) entry.getValue()).getWeight().intValue();
				} else if (((String) entry.getKey()).equals("PNU3")) {
					PNU3Weight = ((ZdmxWeight) entry.getValue()).getWeight().intValue();
				}
			}
		}

		if (PNU1Weight >= 80 || PNU2Weight >= 80 || PNU3Weight >= 80) {
			for (int arg8 = 0; arg8 < infectCodeWeightList.size(); ++arg8) {
				Map arg9 = (Map) infectCodeWeightList.get(arg8);
				arg7 = arg9.entrySet().iterator();

				while (arg7.hasNext()) {
					entry = (Entry) arg7.next();
					if (((String) entry.getKey()).equals("BRON")) {
						infectCodeWeightList.remove(arg9);
						--arg8;
					}
				}
			}
		}

	}

	public void r(List<Map<String, ZdmxWeight>> infectCodeWeightList) {
		label42 : for (int i = 0; i < infectCodeWeightList.size(); ++i) {
			Map map = (Map) infectCodeWeightList.get(i);
			Iterator arg4 = map.entrySet().iterator();

			while (true) {
				Entry entry;
				do {
					if (!arg4.hasNext()) {
						continue label42;
					}

					entry = (Entry) arg4.next();
				} while (!((String) entry.getKey()).equals("SUTI"));

				ZdmxWeight zdmxWeight = (ZdmxWeight) entry.getValue();
				List zdmxList = zdmxWeight.getZdmxList();
				boolean isSUTI = true;
				Iterator arg9 = zdmxList.iterator();

				while (arg9.hasNext()) {
					Zg006Zdmx zdmx = (Zg006Zdmx) arg9.next();
					if ("SUTI".equals(zdmx.getInfectCode())) {
						if ("TYX".equals(zdmx.getNodeType2())) {
							isSUTI = false;
						} else if ("JB".equals(zdmx.getNodeType2())) {
							isSUTI = false;
						}
					}
				}

				if (isSUTI) {
					infectCodeWeightList.remove(map);
					--i;
				}
			}
		}

	}

	public void s(List<Map<String, ZdmxWeight>> infectCodeWeightList) {
		boolean isSUTI = false;

		int i;
		Map map;
		Entry entry;
		Iterator arg5;
		for (i = 0; i < infectCodeWeightList.size(); ++i) {
			map = (Map) infectCodeWeightList.get(i);
			arg5 = map.entrySet().iterator();

			while (arg5.hasNext()) {
				entry = (Entry) arg5.next();
				if (((String) entry.getKey()).equals("SUTI")) {
					isSUTI = true;
				}
			}
		}

		if (isSUTI) {
			for (i = 0; i < infectCodeWeightList.size(); ++i) {
				map = (Map) infectCodeWeightList.get(i);
				arg5 = map.entrySet().iterator();

				while (arg5.hasNext()) {
					entry = (Entry) arg5.next();
					if (((String) entry.getKey()).equals("ASB")) {
						infectCodeWeightList.remove(map);
						--i;
					}
				}
			}
		}

	}

	public void a(List<Map<String, ZdmxWeight>> infectCodeWeightList, Integer minWeight) {
		int LCBIWeight = 0;

		int i;
		Map map;
		Entry entry;
		Iterator arg6;
		for (i = 0; i < infectCodeWeightList.size(); ++i) {
			map = (Map) infectCodeWeightList.get(i);
			arg6 = map.entrySet().iterator();

			while (arg6.hasNext()) {
				entry = (Entry) arg6.next();
				if (((String) entry.getKey()).equals("LCBI")) {
					LCBIWeight = ((ZdmxWeight) entry.getValue()).getWeight().intValue();
				}
			}
		}

		if (LCBIWeight >= minWeight.intValue()) {
			for (i = 0; i < infectCodeWeightList.size(); ++i) {
				map = (Map) infectCodeWeightList.get(i);
				arg6 = map.entrySet().iterator();

				while (arg6.hasNext()) {
					entry = (Entry) arg6.next();
					if (((String) entry.getKey()).equals("CSEP")) {
						infectCodeWeightList.remove(map);
						--i;
					}
				}
			}
		}

	}

	public void b(List<Map<String, ZdmxWeight>> infectCodeWeightList, Integer minWeight) {
		int map;
		Map entry;
		Entry entry1;
		Iterator zdmxWeight;
		ZdmxWeight zmdxList;
		for (map = 0; map < infectCodeWeightList.size(); ++map) {
			entry = (Map) infectCodeWeightList.get(map);
			zdmxWeight = entry.entrySet().iterator();

			while (zdmxWeight.hasNext()) {
				entry1 = (Entry) zdmxWeight.next();
				zmdxList = (ZdmxWeight) entry1.getValue();
				if (zmdxList.getWeight().intValue() <= minWeight.intValue()) {
					infectCodeWeightList.remove(entry);
					--map;
				}
			}
		}

		if (infectCodeWeightList.size() > 1) {
			label86 : for (map = 0; map < infectCodeWeightList.size(); ++map) {
				entry = (Map) infectCodeWeightList.get(map);
				zdmxWeight = entry.entrySet().iterator();

				label83 : while (true) {
					do {
						if (!zdmxWeight.hasNext()) {
							continue label86;
						}

						entry1 = (Entry) zdmxWeight.next();
					} while (!((String) entry1.getKey()).equals("BD0101"));

					zmdxList = (ZdmxWeight) entry1.getValue();
					List exist = zmdxList.getZdmxList();
					boolean zdmx = true;
					Iterator arg10 = exist.iterator();

					while (true) {
						Zg006Zdmx zdmx1;
						do {
							if (!arg10.hasNext()) {
								if (zdmx) {
									infectCodeWeightList.remove(entry);
									--map;
								}
								continue label83;
							}

							zdmx1 = (Zg006Zdmx) arg10.next();
						} while (!"JY".equals(zdmx1.getNodeType2()) && !"TYX".equals(zdmx1.getNodeType2())
								&& !"KJ".equals(zdmx1.getNodeType2()));

						zdmx = false;
					}
				}
			}
		} else if (infectCodeWeightList.size() == 1) {
			Map arg12 = (Map) infectCodeWeightList.get(0);
			Iterator arg13 = arg12.entrySet().iterator();

			while (true) {
				Entry arg11;
				do {
					if (!arg13.hasNext()) {
						return;
					}

					arg11 = (Entry) arg13.next();
				} while (!((String) arg11.getKey()).equals("BD0101"));

				ZdmxWeight arg14 = (ZdmxWeight) arg11.getValue();
				List arg15 = arg14.getZdmxList();
				boolean arg16 = false;
				Iterator arg18 = arg15.iterator();

				while (arg18.hasNext()) {
					Zg006Zdmx arg17 = (Zg006Zdmx) arg18.next();
					if ("KJ".equals(arg17.getNodeType2())) {
						arg16 = true;
					} else {
						arg16 = false;
					}
				}

				if (arg16) {
					infectCodeWeightList.remove(arg12);
				}
			}
		}

	}

	public void a(List<Zg006Zdmx> zdmxJyList, boolean isOperate) {
		Zg006Zdmx zdmx;
		Iterator arg3;
		if (isOperate) {
			arg3 = zdmxJyList.iterator();

			while (arg3.hasNext()) {
				zdmx = (Zg006Zdmx) arg3.next();
				if ("JY000237".equals(zdmx.getElementId()) && "IC".equals(zdmx.getInfectCode())) {
					zdmx.setWeightValue("0");
				}
			}

			arg3 = zdmxJyList.iterator();

			while (arg3.hasNext()) {
				zdmx = (Zg006Zdmx) arg3.next();
				if ("JY000237".equals(zdmx.getElementId()) && "MEN".equals(zdmx.getInfectCode())) {
					zdmx.setWeightValue("0");
				}
			}
		} else {
			arg3 = zdmxJyList.iterator();

			while (arg3.hasNext()) {
				zdmx = (Zg006Zdmx) arg3.next();
				if ("JY000237".equals(zdmx.getElementId()) && "Organ".equals(zdmx.getInfectCode())) {
					zdmx.setWeightValue("0");
				}
			}
		}

	}

	public void f(List<St005Ssxxb> st005List, List<Map<String, ZdmxWeight>> infectCodeWeightList) {
		boolean isOperate = false;

		label34 : for (int i = 0; i < infectCodeWeightList.size(); ++i) {
			Map map = (Map) infectCodeWeightList.get(i);
			Iterator arg6 = map.entrySet().iterator();

			while (true) {
				while (true) {
					if (!arg6.hasNext()) {
						continue label34;
					}

					Entry entry = (Entry) arg6.next();
					if (st005List != null && st005List.size() > 0) {
						isOperate = this.a(st005List, ((ZdmxWeight) entry.getValue()).getGrDate());
						if (isOperate && ((String) entry.getKey()).equals("IC")) {
							infectCodeWeightList.remove(map);
							--i;
						}
					} else if (((String) entry.getKey()).equals("Organ")) {
						infectCodeWeightList.remove(map);
						--i;
					}
				}
			}
		}

	}

	public void t(List<Map<String, ZdmxWeight>> infectCodeWeightList) {
		if (infectCodeWeightList.size() > 1) {
			label46 : for (int i = 0; i < infectCodeWeightList.size(); ++i) {
				Map map = (Map) infectCodeWeightList.get(i);
				Iterator arg4 = map.entrySet().iterator();

				while (true) {
					ZdmxWeight zdmxWeight;
					do {
						Entry entry;
						do {
							if (!arg4.hasNext()) {
								continue label46;
							}

							entry = (Entry) arg4.next();
						} while (!((String) entry.getKey()).equals("SIP"));

						zdmxWeight = (ZdmxWeight) entry.getValue();
					} while (zdmxWeight.getWeight().intValue() >= 100);

					int JY000246Weight = 0;
					boolean isJY000246 = false;
					List zdmxList = zdmxWeight.getZdmxList();

					for (int maps = 0; maps < zdmxList.size(); ++maps) {
						Zg006Zdmx zdWeight = (Zg006Zdmx) zdmxList.get(maps);
						if ("JY000246".equals(zdWeight.getElementId())) {
							zdmxList.remove(zdWeight);
							JY000246Weight = Integer.parseInt(zdWeight.getWeightValue());
							--maps;
							isJY000246 = true;
						}
					}

					if (isJY000246) {
						infectCodeWeightList.remove(map);
						HashMap arg11 = new HashMap();
						ZdmxWeight arg12 = new ZdmxWeight();
						arg12.setWeight(Integer.valueOf(zdmxWeight.getWeight().intValue() - JY000246Weight));
						arg12.setZdmxList(zdmxList);
						arg11.put("SIP", arg12);
						infectCodeWeightList.add(arg11);
					}
				}
			}
		}

	}

	public void a(List<Zg006Zdmx> zdmxList, List<Zg006Zdmx> zdmxJyList, boolean isOperate) {
		Zg006Zdmx zdmx;
		Iterator isOrganJB;
		if (isOperate) {
			isOrganJB = zdmxJyList.iterator();

			while (isOrganJB.hasNext()) {
				zdmx = (Zg006Zdmx) isOrganJB.next();
				if ("JY000246".equals(zdmx.getElementId()) && "SKIN".equals(zdmx.getInfectCode())) {
					zdmx.setWeightValue("0");
				}
			}

			boolean zdmx2 = false;
			boolean isOrganJB1 = false;
			Iterator arg6 = zdmxList.iterator();

			Zg006Zdmx zdmx1;
			while (arg6.hasNext()) {
				zdmx1 = (Zg006Zdmx) arg6.next();
				if ("Organ".equals(zdmx1.getInfectCode())) {
					if ("TYX".equals(zdmx1.getNodeType2())) {
						zdmx2 = true;
					} else if ("JB".equals(zdmx1.getNodeType2())) {
						isOrganJB1 = true;
					}
				}
			}

			if (!zdmx2 || !isOrganJB1) {
				arg6 = zdmxJyList.iterator();

				while (arg6.hasNext()) {
					zdmx1 = (Zg006Zdmx) arg6.next();
					if ("JY000246".equals(zdmx1.getElementId()) && "Organ".equals(zdmx1.getInfectCode())) {
						zdmx1.setWeightValue("0");
					}
				}
			}
		} else {
			isOrganJB = zdmxJyList.iterator();

			while (isOrganJB.hasNext()) {
				zdmx = (Zg006Zdmx) isOrganJB.next();
				if ("JY000246".equals(zdmx.getElementId())) {
					zdmx.setWeightValue("0");
				}
			}
		}

	}

	public void g(List<Zg006Zdmx> zg006List, List<Zg006Zdmx> zdmxJyList) {
		boolean isElementId = false;
		Iterator GITWeight = zdmxJyList.iterator();

		while (GITWeight.hasNext()) {
			Zg006Zdmx GEWeight = (Zg006Zdmx) GITWeight.next();
			if ("JY000248".equals(GEWeight.getElementId())) {
				isElementId = true;
			}
		}

		if (isElementId) {
			Integer GEWeight1 = Integer.valueOf(0);
			Integer GITWeight1 = Integer.valueOf(0);
			Integer GETYXWeight = Integer.valueOf(0);
			Integer GITTYXWeight = Integer.valueOf(0);
			Integer GEJBWeight = Integer.valueOf(0);
			Integer GITJBWeight = Integer.valueOf(0);
			Iterator arg10 = zg006List.iterator();

			Zg006Zdmx zdmx;
			while (arg10.hasNext()) {
				zdmx = (Zg006Zdmx) arg10.next();
				if ("GE".equals(zdmx.getInfectCode())) {
					if ("JY".equals(zdmx.getNodeType2())) {
						GETYXWeight = Integer.valueOf(GETYXWeight.intValue() + Integer.parseInt(zdmx.getWeightValue()));
					} else if ("JB".equals(zdmx.getNodeType2())) {
						GEJBWeight = Integer.valueOf(GEJBWeight.intValue() + Integer.parseInt(zdmx.getWeightValue()));
					}
				}

				if ("GIT".equals(zdmx.getInfectCode())) {
					if ("JY".equals(zdmx.getNodeType2())) {
						GITTYXWeight = Integer
								.valueOf(GITTYXWeight.intValue() + Integer.parseInt(zdmx.getWeightValue()));
					} else if ("JB".equals(zdmx.getNodeType2())) {
						GITJBWeight = Integer.valueOf(GITJBWeight.intValue() + Integer.parseInt(zdmx.getWeightValue()));
					}
				}
			}

			if (GETYXWeight.intValue() > GEJBWeight.intValue()) {
				GEWeight1 = GETYXWeight;
			} else {
				GEWeight1 = GEJBWeight;
			}

			if (GITTYXWeight.intValue() > GITJBWeight.intValue()) {
				GITWeight1 = GITTYXWeight;
			} else {
				GITWeight1 = GITJBWeight;
			}

			if (GEWeight1.intValue() > GITWeight1.intValue()) {
				arg10 = zdmxJyList.iterator();

				while (arg10.hasNext()) {
					zdmx = (Zg006Zdmx) arg10.next();
					if ("JY000248".equals(zdmx.getElementId()) && "GIT".equals(zdmx.getInfectCode())) {
						zdmx.setWeightValue("0");
					}
				}
			}

			if (GITWeight1.intValue() > GEWeight1.intValue()) {
				arg10 = zdmxJyList.iterator();

				while (arg10.hasNext()) {
					zdmx = (Zg006Zdmx) arg10.next();
					if ("JY000248".equals(zdmx.getElementId()) && "GE".equals(zdmx.getInfectCode())) {
						zdmx.setWeightValue("0");
					}
				}
			}
		}

	}

	public void h(List<Zg006Zdmx> zg006List, List<Zg006Zdmx> zdmxJyList) {
		boolean isJBTYX = false;
		Iterator URWeight = zg006List.iterator();

		Zg006Zdmx PNU2Weight;
		while (URWeight.hasNext()) {
			PNU2Weight = (Zg006Zdmx) URWeight.next();
			if (!"JB".equals(PNU2Weight.getNodeType2()) && !"TYX".equals(PNU2Weight.getNodeType2())) {
				isJBTYX = true;
			}
		}

		if (isJBTYX) {
			URWeight = zdmxJyList.iterator();

			while (URWeight.hasNext()) {
				PNU2Weight = (Zg006Zdmx) URWeight.next();
				if ("JY000243".equals(PNU2Weight.getNodeType2())) {
					PNU2Weight.setWeightValue("0");
				}
			}
		}

		Integer PNU2Weight1 = Integer.valueOf(0);
		Integer URWeight1 = Integer.valueOf(0);
		Iterator arg6 = zg006List.iterator();

		Zg006Zdmx zdmx;
		int allweight;
		int tyxWeight;
		while (arg6.hasNext()) {
			zdmx = (Zg006Zdmx) arg6.next();
			if ("PNU2".equals(zdmx.getInfectCode())) {
				allweight = 0;
				tyxWeight = 0;
				if ("1".equals(zdmx.getNodeId())) {
					allweight = Integer.parseInt(zdmx.getWeightValue());
				} else if ("99".equals(zdmx.getNodeId())) {
					tyxWeight = Integer.parseInt(zdmx.getWeightValue());
				}

				if (allweight > tyxWeight) {
					PNU2Weight1 = Integer.valueOf(allweight);
				} else {
					PNU2Weight1 = Integer.valueOf(tyxWeight);
				}
			}
		}

		arg6 = zg006List.iterator();

		while (arg6.hasNext()) {
			zdmx = (Zg006Zdmx) arg6.next();
			if ("UR".equals(zdmx.getInfectCode())) {
				allweight = 0;
				tyxWeight = 0;
				if ("1".equals(zdmx.getNodeId())) {
					allweight = Integer.parseInt(zdmx.getWeightValue());
				} else if ("99".equals(zdmx.getNodeId())) {
					tyxWeight = Integer.parseInt(zdmx.getWeightValue());
				}

				if (allweight > tyxWeight) {
					URWeight1 = Integer.valueOf(allweight);
				} else {
					URWeight1 = Integer.valueOf(tyxWeight);
				}
			}
		}

		if (PNU2Weight1.intValue() > URWeight1.intValue()) {
			arg6 = zdmxJyList.iterator();

			while (arg6.hasNext()) {
				zdmx = (Zg006Zdmx) arg6.next();
				if ("JY000243".equals(zdmx.getElementId()) && "UR".equals(zdmx.getInfectCode())) {
					zdmx.setWeightValue("0");
				}
			}
		}

		if (URWeight1.intValue() > PNU2Weight1.intValue()) {
			arg6 = zdmxJyList.iterator();

			while (arg6.hasNext()) {
				zdmx = (Zg006Zdmx) arg6.next();
				if ("JY000243".equals(zdmx.getElementId()) && "PNU2".equals(zdmx.getInfectCode())) {
					zdmx.setWeightValue("0");
				}
			}
		}

	}

	public void i(List<Map<String, ZdmxWeight>> infectCodeWeightList, List<St005Ssxxb> st005List) {
		for (int i = 0; i < infectCodeWeightList.size(); ++i) {
			Map map = (Map) infectCodeWeightList.get(i);
			Iterator arg5 = map.entrySet().iterator();

			while (arg5.hasNext()) {
				Entry entry = (Entry) arg5.next();
				if (((String) entry.getKey()).equals("SIP")) {
					if (!this.a(st005List, ((ZdmxWeight) entry.getValue()).getGrDate())) {
						infectCodeWeightList.remove(map);
						--i;
					}
				} else if (((String) entry.getKey()).equals("SIS")) {
					if (!this.a(st005List, ((ZdmxWeight) entry.getValue()).getGrDate())) {
						infectCodeWeightList.remove(map);
						--i;
					}
				} else if (((String) entry.getKey()).equals("DIP")) {
					if (!this.a(st005List, ((ZdmxWeight) entry.getValue()).getGrDate())) {
						infectCodeWeightList.remove(map);
						--i;
					}
				} else if (((String) entry.getKey()).equals("DIS")) {
					if (!this.a(st005List, ((ZdmxWeight) entry.getValue()).getGrDate())) {
						infectCodeWeightList.remove(map);
						--i;
					}
				} else if (((String) entry.getKey()).equals("Organ")
						&& !this.a(st005List, ((ZdmxWeight) entry.getValue()).getGrDate())) {
					infectCodeWeightList.remove(map);
					--i;
				}
			}
		}

	}

	public void e(List<Map<String, ZdmxWeight>> infectCodeWeightList, St003Cryxxb cryxxb) {
		boolean isSKIN = false;
		if (infectCodeWeightList.size() > 0) {
			int i;
			Map map;
			Entry entry;
			Iterator arg6;
			ZdmxWeight zdmxWeight;
			List zg006ZdmxList;
			Zg006Zdmx zdmx;
			Iterator arg10;
			label90 : for (i = 0; i < infectCodeWeightList.size(); ++i) {
				map = (Map) infectCodeWeightList.get(i);
				arg6 = map.entrySet().iterator();

				while (true) {
					do {
						if (!arg6.hasNext()) {
							continue label90;
						}

						entry = (Entry) arg6.next();
					} while (!((String) entry.getKey()).equals("SIP") && !((String) entry.getKey()).equals("DIP"));

					zdmxWeight = (ZdmxWeight) entry.getValue();
					zg006ZdmxList = zdmxWeight.getZdmxList();
					arg10 = zg006ZdmxList.iterator();

					while (arg10.hasNext()) {
						zdmx = (Zg006Zdmx) arg10.next();
						if ("JY000239".equals(zdmx.getElementId())) {
							isSKIN = true;
						}
					}
				}
			}

			if (isSKIN) {
				label67 : for (i = 0; i < infectCodeWeightList.size(); ++i) {
					map = (Map) infectCodeWeightList.get(i);
					arg6 = map.entrySet().iterator();

					while (true) {
						do {
							if (!arg6.hasNext()) {
								continue label67;
							}

							entry = (Entry) arg6.next();
						} while (!((String) entry.getKey()).equals("SKIN"));

						zdmxWeight = (ZdmxWeight) entry.getValue();
						zg006ZdmxList = zdmxWeight.getZdmxList();
						arg10 = zg006ZdmxList.iterator();

						while (true) {
							while (arg10.hasNext()) {
								zdmx = (Zg006Zdmx) arg10.next();
								if ("JY000239".equals(zdmx.getElementId()) && "SKIN".equals(zdmx.getInfectCode())) {
									zdmx.setWeightValue("0");
								} else if ("JY000239".equals(zdmx.getElementId())
										&& "DECU".equals(zdmx.getInfectCode())) {
									zdmx.setWeightValue("0");
								} else if ("JY000239".equals(zdmx.getElementId())
										&& "BURN".equals(zdmx.getInfectCode())) {
									zdmx.setWeightValue("0");
								}
							}

							zdmxWeight = this.D.b(zg006ZdmxList, cryxxb);
							map.put("SKIN", zdmxWeight);
							break;
						}
					}
				}
			}
		}

	}

	public void b(List<Zg006Zdmx> zdmxList, List<Zg006Zdmx> zdmxJyList, boolean isOperate) {
		Zg006Zdmx isDECUTYX;
		Iterator isBURNTYX;
		if (isOperate) {
			isBURNTYX = zdmxJyList.iterator();

			while (isBURNTYX.hasNext()) {
				isDECUTYX = (Zg006Zdmx) isBURNTYX.next();
				if ("JY000239".equals(isDECUTYX.getElementId()) && "SKIN".equals(isDECUTYX.getInfectCode())) {
					isDECUTYX.setWeightValue("0");
				}

				if ("JY000239".equals(isDECUTYX.getElementId()) && "DECU".equals(isDECUTYX.getInfectCode())) {
					isDECUTYX.setWeightValue("0");
				}

				if ("JY000239".equals(isDECUTYX.getElementId()) && "BURN".equals(isDECUTYX.getInfectCode())) {
					isDECUTYX.setWeightValue("0");
				}
			}
		} else {
			isBURNTYX = zdmxJyList.iterator();

			while (isBURNTYX.hasNext()) {
				isDECUTYX = (Zg006Zdmx) isBURNTYX.next();
				if ("JY000239".equals(isDECUTYX.getElementId()) && "SIP".equals(isDECUTYX.getInfectCode())) {
					isDECUTYX.setWeightValue("0");
				}

				if ("JY000239".equals(isDECUTYX.getElementId()) && "DIP".equals(isDECUTYX.getInfectCode())) {
					isDECUTYX.setWeightValue("0");
				}
			}

			boolean isDECUTYX1 = false;
			boolean isBURNTYX1 = false;
			Iterator arg6 = zdmxList.iterator();

			label118 : while (true) {
				Zg006Zdmx zdmx;
				do {
					do {
						if (!arg6.hasNext()) {
							arg6 = zdmxList.iterator();

							while (true) {
								do {
									do {
										if (!arg6.hasNext()) {
											arg6 = zdmxJyList.iterator();

											while (arg6.hasNext()) {
												zdmx = (Zg006Zdmx) arg6.next();
												if ("JY000239".equals(zdmx.getElementId())
														&& "SKIN".equals(zdmx.getInfectCode()) && isDECUTYX1) {
													zdmx.setWeightValue("0");
												}

												if ("JY000239".equals(zdmx.getElementId())
														&& "DECU".equals(zdmx.getInfectCode()) && !isDECUTYX1) {
													zdmx.setWeightValue("0");
												}

												if ("JY000239".equals(zdmx.getElementId())
														&& "SKIN".equals(zdmx.getInfectCode()) && isBURNTYX1) {
													zdmx.setWeightValue("0");
												}

												if ("JY000239".equals(zdmx.getElementId())
														&& "BURN".equals(zdmx.getInfectCode()) && !isBURNTYX1) {
													zdmx.setWeightValue("0");
												}
											}
											break label118;
										}

										zdmx = (Zg006Zdmx) arg6.next();
									} while (!"BURN".equals(zdmx.getInfectCode()));
								} while (!"TYX".equals(zdmx.getNodeType2()) && !"JB".equals(zdmx.getNodeType2()));

								isBURNTYX1 = true;
							}
						}

						zdmx = (Zg006Zdmx) arg6.next();
					} while (!"DECU".equals(zdmx.getInfectCode()));
				} while (!"TYX".equals(zdmx.getNodeType2()) && !"JB".equals(zdmx.getNodeType2()));

				isDECUTYX1 = true;
			}
		}

	}

	public void j(List<Map<String, ZdmxWeight>> infectCodeWeightList, List<Bk001Sbk> bk001SbkList) {
		Bk001Sbk PNU1bk = null;
		Bk001Sbk PNU2bk = null;
		Iterator map = bk001SbkList.iterator();

		while (map.hasNext()) {
			Bk001Sbk i = (Bk001Sbk) map.next();
			if ("PNU1".equals(i.getInfectDiagnId())) {
				PNU1bk = i;
			} else if ("PNU2".equals(i.getInfectDiagnId())) {
				PNU2bk = i;
			}
		}

		Entry entry;
		Iterator arg7;
		int arg8;
		Map arg9;
		if (PNU1bk != null) {
			if (("0".equals(PNU1bk.getIsOk()) || "1".equals(PNU1bk.getIsOk())) && infectCodeWeightList.size() > 0) {
				for (arg8 = 0; arg8 < infectCodeWeightList.size(); ++arg8) {
					arg9 = (Map) infectCodeWeightList.get(arg8);
					arg7 = arg9.entrySet().iterator();

					while (arg7.hasNext()) {
						entry = (Entry) arg7.next();
						if (((String) entry.getKey()).equals("UR")) {
							infectCodeWeightList.remove(arg9);
							--arg8;
						}
					}
				}
			}
		} else if (PNU2bk != null && ("0".equals(PNU2bk.getIsOk()) || "1".equals(PNU2bk.getIsOk()))
				&& infectCodeWeightList.size() > 0) {
			for (arg8 = 0; arg8 < infectCodeWeightList.size(); ++arg8) {
				arg9 = (Map) infectCodeWeightList.get(arg8);
				arg7 = arg9.entrySet().iterator();

				while (arg7.hasNext()) {
					entry = (Entry) arg7.next();
					if (((String) entry.getKey()).equals("UR")) {
						infectCodeWeightList.remove(arg9);
						--arg8;
					}
				}
			}
		}

	}

	public boolean a(List<St005Ssxxb> st005List, Date grDate) {
		boolean isOperate = false;
		Iterator arg4 = st005List.iterator();

		while (arg4.hasNext()) {
			St005Ssxxb ssxxb = (St005Ssxxb) arg4.next();
			if (ssxxb.getOperAt() != null && grDate != null && grDate.getTime() >= ssxxb.getOperAt().getTime()) {
				isOperate = true;
			}
		}

		return isOperate;
	}
}