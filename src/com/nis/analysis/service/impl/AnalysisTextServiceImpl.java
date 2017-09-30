package com.nis.analysis.service.impl;

import com.nis.analysis.entity.NyUnanalyzeBc;
import com.nis.analysis.entity.NyUnanalyzeDict;
import com.nis.analysis.entity.Zg007Dict;
import com.nis.analysis.model.b;
import com.nis.analysis.model.d;
import com.nis.analysis.model.e;
import com.nis.analysis.model.f;
import com.nis.analysis.service.AnalysisTextService;
import com.nis.comm.utils.ab;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class AnalysisTextServiceImpl implements AnalysisTextService {
	private static final Logger c = Logger.getLogger(AnalysisTextServiceImpl.class);
	public Map<String, List<Zg007Dict>> bm = new HashMap();
	public Map<String, List<Zg007Dict>> bn = new HashMap();
	public Map<String, List<Zg007Dict>> bo = new HashMap();
	public Map<String, List<Zg007Dict>> bp = new HashMap();
	public Map<String, List<Zg007Dict>> bq = new HashMap();
	public Map<String, List<Zg007Dict>> br = new HashMap();
	public Map<String, NyUnanalyzeBc> bs = new HashMap();
	public Map<String, NyUnanalyzeDict> bt = new HashMap();
	public Map<String, String> aN = new HashMap();

	public boolean f(List<Map> maplist) {
		try {
			this.bm = (Map) maplist.get(0);
			this.bn = (Map) maplist.get(1);
			this.bo = (Map) maplist.get(2);
			this.bp = (Map) maplist.get(3);
			this.bq = (Map) maplist.get(4);
			this.br = (Map) maplist.get(5);
			this.bs = (Map) maplist.get(6);
			this.bt = (Map) maplist.get(7);
			return true;
		} catch (Exception arg2) {
			arg2.printStackTrace();
			return false;
		}
	}

	public boolean a(Map bjStyleMap) {
		try {
			this.aN = bjStyleMap;
			return true;
		} catch (Exception arg2) {
			arg2.printStackTrace();
			return false;
		}
	}

	public f b(String ctname, String cttext) {
		boolean analysisflag = false;
		String bcname = "";
		boolean success = false;
		String bcText = null;
		f rs = new f();
		ArrayList snList = new ArrayList();
		HashMap keyMap = new HashMap();
		rs.setErrMsg("");
		rs.setSuccess(false);
		rs.setAnalysisResultText("");
		Iterator analysisResultStr = rs.getLnt().iterator();

		while (analysisResultStr.hasNext()) {
			e noanalysis = (e) analysisResultStr.next();
			Iterator bdhh = noanalysis.getLnt().iterator();

			while (bdhh.hasNext()) {
				b sjw = (b) bdhh.next();
				sjw.getNdRs().clear();
			}

			noanalysis.getLnt().clear();
		}

		snList.clear();
		keyMap.clear();
		rs.setLnt(snList);
		boolean arg29 = false;

		String arg34;
		String arg35;
		try {
			bcname = ctname;
			analysisflag = true;
			if (ctname != null && !"".equals(ctname)) {
				Iterator arg33 = this.bs.entrySet().iterator();

				while (arg33.hasNext()) {
					Entry arg30 = (Entry) arg33.next();
					if (bcname.indexOf(((NyUnanalyzeBc) arg30.getValue()).getBcName()) > -1) {
						analysisflag = false;
						success = true;
						break;
					}
				}
			}

			if (cttext != null && !"".equals(cttext)) {
				bcText = cttext.replaceAll("/\r\n/", "<br/>");
				bcText = bcText.replaceAll("/\n/", "<br/>");
				bcText = bcText.replaceAll("/\r/", "<br/>");
				bcText = bcText.replaceAll("  ", "");
				bcText = bcText.replaceAll(",", "，");
				bcText = bcText.replaceAll("？", "？∽");
				bcText = bcText.replaceAll("\\?", "?∽");
				bcText = bcText.replaceAll(" ", "~^~");
				bcText = bcText.replaceAll("。", "。∽");
				bcText = bcText.replaceAll("；", "；∽");
				bcText = bcText.replaceAll("！", "！∽");
				bcText = bcText.replaceAll("!", "!∽");
				bcText = bcText.replaceAll("<br/><br/>", "<br/>");
				bcText = bcText.replaceAll("~^~~^~", "~^~");
				String[] arg31 = bcText.split("∽");
				arg34 = "";
				arg35 = "";

				for (int prebdhh = 0; prebdhh < arg31.length; ++prebdhh) {
					arg34 = arg31[prebdhh];
					if (arg34 == null) {
						arg34 = "";
					}

					if (!arg29) {
						Iterator curAnalysisResult = this.bt.entrySet().iterator();

						while (curAnalysisResult.hasNext()) {
							Entry preAnalysisResult = (Entry) curAnalysisResult.next();
							if (arg34.indexOf(((NyUnanalyzeDict) preAnalysisResult.getValue()).getDcName()) != -1) {
								arg29 = true;
								break;
							}
						}
					}

					String[] arg37 = arg34.split("，");
					e arg39 = new e();
					arg39.setContent(arg34);

					for (int preContext = 0; preContext < arg37.length; ++preContext) {
						arg35 = arg37[preContext];
						b firstStr;
						if (!arg29 && analysisflag) {
							firstStr = this.p(arg35);
							arg39.setLeafNodeText(firstStr);
						} else {
							firstStr = new b();
							firstStr.setContent(arg35);
							arg39.setLeafNodeText(firstStr);
						}
					}

					snList.add(arg39);
				}

				success = true;
			} else {
				success = true;
				analysisflag = false;
			}
		} catch (Exception arg28) {
			success = false;
			rs.setErrMsg(arg28.getMessage());
			c.error("exeAnalysis", arg28);
		}

		rs.setSuccess(success);
		rs.setAnalysisflag(analysisflag);
		String arg32 = "";
		arg34 = (String) this.aN.get("8");
		if (arg34 == null) {
			arg34 = "";
		}

		arg35 = "";
		String arg36 = "";
		String arg38 = "";
		String arg40 = "";
		String arg41 = "";
		String arg42 = "";
		String content = "";
		Pattern pattern = Pattern.compile("^[1-9]{1}.[^0-9]{1}$");
		Matcher matcher = null;
		if (snList != null) {
			Iterator arg22 = snList.iterator();

			label148 : while (true) {
				e sn;
				do {
					do {
						if (!arg22.hasNext()) {
							break label148;
						}

						sn = (e) arg22.next();
					} while (sn == null);

					content = sn.getContent();
				} while ("".equals(content));

				arg35 = content.substring(sn.getContent().length() - 1);
				if (content.length() > 2) {
					arg42 = content.substring(0, 3);
				} else {
					arg42 = "abcd";
				}

				matcher = pattern.matcher(arg42);
				Iterator arg24 = sn.getLnt().iterator();

				while (true) {
					b lnt;
					do {
						if (!arg24.hasNext()) {
							arg40 = sn.getAnalysisContent();
							if ("".equals(arg38)) {
								arg32 = arg32 + arg40;
							} else {
								if ("。".equals(arg36) && "：".equals(arg36)) {
									arg32 = arg32 + "<br/>" + arg34 + arg40;
								}

								if (("：".equals(arg36) || "。".equals(arg36)) && matcher.matches()) {
									arg32 = arg32 + "<br/>" + arg34 + arg40;
								} else if ("；".equals(arg36) && "：".equals(arg36)) {
									arg32 = arg32 + "<br/>" + arg34 + arg40;
								} else if (!"：".equals(arg36) && !"！".equals(arg36) && !"!".equals(arg36)
										&& !"；".equals(arg36) && !"：".equals(arg35) && !"；".equals(arg35)) {
									if ("。".equals(arg36) && arg41.length() > 20) {
										arg32 = arg32 + "<br/>" + arg34 + arg40;
									} else {
										arg32 = arg32 + arg40;
									}
								} else {
									arg32 = arg32 + "<br/>" + arg34 + arg40;
								}
							}

							arg36 = arg35;
							arg38 = arg40;
							arg41 = sn.getContent();
							continue label148;
						}

						lnt = (b) arg24.next();
						lnt.setAnalysisResult(this.a(lnt));
					} while (lnt.getFd());

					List list = lnt.getNdRs();
					Iterator arg27 = list.iterator();

					while (arg27.hasNext()) {
						d result = (d) arg27.next();
						if ("3".equals(result.getElementType()) && !"1".equals(result.getBhElement())
								&& !keyMap.containsKey(result.getElementid()) && ab.isNotEmpty(result.getElementid())) {
							keyMap.put(result.getElementid(), result);
						}
					}
				}
			}
		}

		rs.setAnalysisResultText(arg32);
		rs.setKeyMap(keyMap);
		snList = null;
		return rs;
	}

	public String a(b lnt) {
		if (lnt.getNdRs().size() == 0) {
			return lnt.getContent();
		} else {
			int len = lnt.getContent().length();
			String tmpstr = "";
			String fmtStr = "";
			int endPos = 0;
			List list = lnt.getNdRs();
			Iterator arg7 = list.iterator();

			while (true) {
				while (arg7.hasNext()) {
					d rs = (d) arg7.next();
					tmpstr = tmpstr + rs.getPreContent();
					endPos = rs.getStopPos();
					if ("0".equals(rs.getElementType())) {
						tmpstr = tmpstr + rs.getElementname();
					} else if ("1".equals(rs.getElementType())) {
						tmpstr = tmpstr + rs.getElementname();
					} else if ("2".equals(rs.getElementType())) {
						tmpstr = tmpstr + rs.getElementname();
					} else if ("3".equals(rs.getElementType())) {
						if ("1".equals(rs.getBhElement())) {
							fmtStr = (String) this.aN.get("5");
							if (fmtStr != null && !"".equals(fmtStr)) {
								tmpstr = tmpstr + String.format(fmtStr, new Object[]{rs.getElementname()});
							} else {
								tmpstr = tmpstr + rs.getElementname();
							}
						} else if (lnt.getFd()) {
							tmpstr = tmpstr + rs.getElementname();
						} else {
							fmtStr = (String) this.aN.get("3");
							if (fmtStr != null && !"".equals(fmtStr)) {
								tmpstr = tmpstr + String.format(fmtStr, new Object[]{rs.getElementname()});
							} else {
								tmpstr = tmpstr + rs.getElementname();
							}
						}
					} else if ("4".equals(rs.getElementType())) {
						fmtStr = (String) this.aN.get("4");
						if (fmtStr != null && !"".equals(fmtStr)) {
							tmpstr = tmpstr + String.format(fmtStr, new Object[]{rs.getElementname()});
						} else {
							tmpstr = tmpstr + rs.getElementname();
						}
					} else if ("7".equals(rs.getElementType())) {
						fmtStr = (String) this.aN.get("7");
						if (fmtStr != null && !"".equals(fmtStr)) {
							tmpstr = tmpstr + String.format(fmtStr, new Object[]{rs.getElementname()});
						} else {
							tmpstr = tmpstr + rs.getElementname();
						}
					}
				}

				if (endPos != len) {
					tmpstr = tmpstr + lnt.getContent().substring(endPos, len);
				}

				return tmpstr;
			}
		}
	}

	public String a(String analysistext, String elementName, int endPos) {
		int len = analysistext.length();
		int startln = endPos - 1;
		int endln = endPos - 1;
		String tmpstr = "";
		String rsstr = "";

		while (startln > 0) {
			--startln;
			tmpstr = analysistext.substring(startln, startln + 1);
			if ("、".equals(tmpstr) || ";".equals(tmpstr) || "；".equals(tmpstr) || "?".equals(tmpstr)) {
				++startln;
				break;
			}
		}

		if (startln == -1) {
			startln = 0;
		}

		int a = 0;
		int fh = 0;

		while (endln < len && a <= 25) {
			++endln;
			++a;
			tmpstr = analysistext.substring(endln - 1, endln);
			if ("、".equals(tmpstr) || ";".equals(tmpstr) || "；".equals(tmpstr)) {
				break;
			}

			if (":".equals(tmpstr) || "：".equals(tmpstr) || "?".equals(tmpstr)) {
				if (fh == 1) {
					break;
				}

				++fh;
			}

			if (fh > 0 && "2".equals(tmpstr)) {
				break;
			}
		}

		if (endln == len) {
			tmpstr = analysistext.substring(endln - 1, endln);
			if (!"、".equals(tmpstr) && !";".equals(tmpstr) && !"；".equals(tmpstr)) {
				rsstr = analysistext.substring(startln, endln);
			} else {
				rsstr = analysistext.substring(startln, endln - 1);
			}
		} else {
			rsstr = analysistext.substring(startln, endln);
		}

		rsstr = rsstr.replaceAll("~\\^~", " ");
		rsstr = rsstr.replaceAll("<br/>", "");
		rsstr = rsstr.replaceAll("\n", "");
		rsstr = rsstr.replaceAll("\r", "");
		rsstr = rsstr.trim();
		if (rsstr != null && rsstr.length() > 500) {
			rsstr = rsstr.substring(0, 500);
		}

		return rsstr;
	}

	public b p(String analyText) {
		b lfn = new b();
		ArrayList list = new ArrayList();
		int len = analyText.length();
		int prePos = 0;
		int startPos = 0;
		int endPos = 1;
		String firstText = "";
		int index = 0;
		List dictlist = null;

		while (true) {
			while (startPos < len) {
				firstText = analyText.substring(startPos, endPos);
				if (" ".equals(firstText)) {
					++startPos;
					++endPos;
				} else {
					boolean haskey = false;
					d rs;
					if (this.bm.containsKey(firstText) && !haskey) {
						dictlist = (List) this.bm.get(firstText);
						rs = this.a(analyText, len, startPos, "0", dictlist);
						if (rs != null) {
							++index;
							rs.setPreContent(analyText.substring(prePos, startPos));
							rs.setId(index);
							list.add(rs);
							startPos += rs.getElementLength();
							endPos += rs.getElementLength();
							prePos = startPos;
							haskey = true;
							lfn.setFd(true);
							continue;
						}
					}

					if (this.bp.containsKey(firstText) && !haskey) {
						dictlist = (List) this.bp.get(firstText);
						rs = this.a(analyText, len, startPos, "3", dictlist);
						if (rs != null) {
							++index;
							rs.setPreContent(analyText.substring(prePos, startPos));
							rs.setId(index);
							list.add(rs);
							startPos += rs.getElementLength();
							endPos += rs.getElementLength();
							prePos = startPos;
							haskey = true;
							if (!"1".equals(rs.getBhElement())) {
								rs.setOriginalContent(this.a(analyText, rs.getElementname(), endPos));
							}
							continue;
						}
					}

					if (this.bn.containsKey(firstText) && !haskey) {
						dictlist = (List) this.bn.get(firstText);
						rs = this.a(analyText, len, startPos, "1", dictlist);
						if (rs != null) {
							++index;
							rs.setPreContent(analyText.substring(prePos, startPos));
							rs.setId(index);
							list.add(rs);
							startPos += rs.getElementLength();
							endPos += rs.getElementLength();
							prePos = startPos;
							haskey = true;
							continue;
						}
					}

					if (this.bq.containsKey(firstText) && !haskey) {
						dictlist = (List) this.bq.get(firstText);
						rs = this.a(analyText, len, startPos, "4", dictlist);
						if (rs != null) {
							++index;
							rs.setPreContent(analyText.substring(prePos, startPos));
							rs.setId(index);
							list.add(rs);
							startPos += rs.getElementLength();
							endPos += rs.getElementLength();
							prePos = startPos;
							haskey = true;
							continue;
						}
					}

					if (this.br.containsKey(firstText) && !haskey) {
						dictlist = (List) this.br.get(firstText);
						rs = this.a(analyText, len, startPos, "7", dictlist);
						if (rs != null) {
							++index;
							rs.setPreContent(analyText.substring(prePos, startPos));
							rs.setId(index);
							list.add(rs);
							startPos += rs.getElementLength();
							endPos += rs.getElementLength();
							prePos = startPos;
							haskey = true;
							continue;
						}
					}

					if (!haskey) {
						++startPos;
						++endPos;
					}
				}
			}

			lfn.setNdRs(list);
			lfn.setContent(analyText);
			return lfn;
		}
	}

	public d a(String analyText, int analyTextLen, int startPos, String keyType, List<Zg007Dict> dictlist) {
		d rs = null;
		String elementid = "";
		String elementname = "";
		String tempstr = "";
		boolean elementlength = false;
		boolean templength = false;
		Iterator arg12 = dictlist.iterator();

		while (arg12.hasNext()) {
			Zg007Dict d = (Zg007Dict) arg12.next();
			elementname = d.getItemName();
			elementid = d.getElementId();
			int elementlength1 = elementname.length();
			int templength1;
			if (startPos + elementlength1 > analyTextLen) {
				templength1 = analyTextLen - startPos;
			} else {
				templength1 = elementlength1;
			}

			tempstr = analyText.substring(startPos, startPos + templength1);
			if (tempstr.equals(elementname)) {
				rs = new d();
				rs.setElementid(elementid);
				rs.setElementLength(templength1);
				rs.setElementname(elementname);
				rs.setElementType(keyType);
				rs.setBhElement(d.getBhElement());
				rs.setStartPos(startPos);
				rs.setStopPos(startPos + templength1);
				break;
			}
		}

		return rs;
	}
}