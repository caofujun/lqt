<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>64项指标</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
<div class="zb_box">
	<div class="zb_title">64项监测指标情况汇总</div>
	<div class="zb_main">		
		<div class="zb_list zb_list_h">
			<span class="zb_Num">序号</span>
			<span class="zb_Name">指标名称</span>
			<span>操作</span>
			<div class="clear"></div>
		</div>
		<div class="zb_list">
			<span class="zb_Num">1</span>
			<span class="zb_Name">IIHAI-1 医院感染发病率</span>
			<a href="javascript:detail('IIHAI-1 医院感染发病率','/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/YYGRFBL&__bypagesize__=false');"  class="ico_view" title="查看"></a>
			<div class="clear"></div>
		</div>
		<div class="zb_list">
			<span class="zb_Num">2</span>
			<span class="zb_Name">IIHAI-2 医院感染例次发病率</span>
			<a href="javascript:detail('IIHAI-2 医院感染例次发病率','/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/YYGRFBL&__bypagesize__=false');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">3</span>
			<span class="zb_Name">IIHAI-3 千日医院感染发病率</span>
			<a href="javascript:detail('IIHAI-3 千日医院感染发病率','/report/f_view/fineReportGr.shtml?tab=5');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">4</span>
			<span class="zb_Name">IIHAI-4 千日医院感染例次发病率</span>
			<a href="javascript:detail('IIHAI-4 千日医院感染例次发病率','/report/f_view/fineReportGr.shtml?tab=5');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">5</span>
			<span class="zb_Name">IIHAI-5 医院感染现患率</span>
			<a href="javascript:detail('IIHAI-5 医院感染现患率','/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/YYGRXHLDC&__bypagesize__=false');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">6</span>
			<span class="zb_Name">IIHAI-6 医院感染现患例次率</span>
			<a href="javascript:detail('IIHAI-6 医院感染现患例次率','/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/YYGRXHLDC&__bypagesize__=false');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">7</span>
			<span class="zb_Name">IIHAI-7 手术患者手术部位感染率</span>
			<a href="javascript:detail('IIHAI-7 手术患者手术部位感染率','/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/ILQKSSKJYWBWGR&__bypagesize__=false');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">8</span>
			<span class="zb_Name">IIHAI-8 一类切口手术部位感染率</span>
			<a href="javascript:detail('IIHAI-8 一类切口手术部位感染率','/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/ILQKSSKJYWBWGR&__bypagesize__=false&incisionGrade=1');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">9</span>
			<span class="zb_Name">IIHAI-9 一类切口手术甲级愈合率</span>
			<a href="javascript:detail('IIHAI-9 一类切口手术甲级愈合率','/report/f_view/fineReportSs.shtml?tab=10&incisionGrade=1');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">10</span>
			 <span class="zb_Name">IINI-10 尿道插管使用率</span>
			<a href="javascript:detail('IINI-10 尿道插管使用率','/report/f_view/fineReportGr.shtml?tab=10');" class="ico_view" title="查看"></a><div class="clear"></div>		</div>
		<div class="zb_list">
			<span class="zb_Num">11</span>
			<span class="zb_Name">IINI-11 中央血管导管使用率</span>
			<a href="javascript:detail('IINI-11 中央血管导管使用率','/report/f_view/fineReportGr.shtml?tab=8');" class="ico_view" title="查看"></a><div class="clear"></div>		</div>
		<div class="zb_list">
			<span class="zb_Num">12</span>
			 <span class="zb_Name">IINI-12 呼吸机使用率</span>
			<a href="javascript:detail('IINI-12 呼吸机使用率','/report/f_view/fineReportGr.shtml?tab=9');" class="ico_view" title="查看"></a><div class="clear"></div>		</div>
		<div class="zb_list">
			<span class="zb_Num">13</span>
			 <span class="zb_Name">IINI-13 尿道插管相关泌尿道感染例次发病率</span>
			<a href="javascript:detail('IINI-13 尿道插管相关泌尿道感染例次发病率','/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/MLDCGGR&__bypagesize__=false');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">14</span>
			 <span class="zb_Name">IINI-14 血管导管相关血流感染发病率</span>
			<a href="javascript:detail('IINI-14 血管导管相关血流感染发病率','/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/ZXJMCG&__bypagesize__=false');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">15</span>
			 <span class="zb_Name">IINI-15 呼吸机相关肺炎发病率</span>
			<a href="javascript:detail('IINI-15 呼吸机相关肺炎发病率','/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/HXJFY&__bypagesize__=false');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">16</span>
			 <span class="zb_Name">IINI-16 新生儿患者医院感染发生率</span>
			<a href="javascript:detail('IINI-16 新生儿患者医院感染发生率','/report/f_view/fineReportGrtj.shtml?tab=2');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">17</span>
			 <span class="zb_Name">IINI-17 不同体重分组新生儿日感染发病率</span>
			<a href="javascript:detail('IINI-17 不同体重分组新生儿日感染发病率','/report/f_view/fineReportGrtj.shtml?tab=2');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">18</span>
			 <span class="zb_Name">IINI-18 不同体重分组脐或中心静脉插管使用率</span>
			<a href="javascript:detail('IINI-18 不同体重分组脐或中心静脉插管使用率','/report/f_view/fineReportGrtj.shtml?tab=2');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">19</span>
			 <span class="zb_Name">IINI-19 不同体重分组呼吸机使用率</span>
			<a href="javascript:detail('IINI-19不同体重分组呼吸机使用率','/report/f_view/fineReportGrtj.shtml?tab=2');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">20</span>
			 <span class="zb_Name">IINI-20 不同体重分组脐或中心静脉插管血流感染发病率</span>
			<a href="javascript:detail('IINI-20不同体重分组脐或中心静脉插管血流感染发病率','/report/f_view/fineReportGrtj.shtml?tab=2');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">21</span>
			 <span class="zb_Name">IINI-21 不同体重分组呼吸机相关肺炎发病率</span>
			<a href="javascript:detail('IINI-21 不同体重分组呼吸机相关肺炎发病率','/report/f_view/fineReportGrtj.shtml?tab=2');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">22</span>
			 <span class="zb_Name">IINI-22 多重耐药菌检出率</span>
			<a href="javascript:detail('IINI-22 多重耐药菌检出率','/report/f_view/fineReportXj.shtml?tab=2');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">23</span>
			 <span class="zb_Name">IINI-23 多重耐药菌医院感染发生率 </span>
			<a href="javascript:detail('IINI-23 多重耐药菌医院感染发生率 ','/report/f_view/fineReportXj.shtml?tab=6&grType=1');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">24</span>
			 <span class="zb_Name">IINI-24 多重耐药菌医院感染例次发生率 </span>
			<a href="javascript:detail('IINI-24 多重耐药菌医院感染例次发生率','/report/f_view/fineReportXj.shtml?tab=6&grType=1');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">25</span>
			 <span class="zb_Name">IINI-25 千日多重耐药菌医院感染例次发生率 </span>
			<a href="javascript:detail('IINI-25 千日多重耐药菌医院感染例次发生率 ','/report/f_view/fineReportXj.shtml?tab=6&grType=1');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">26</span>
			 <span class="zb_Name">IINI-26 千日多重耐药菌定植例次发生率</span>
			<a href="javascript:detail('IINI-26 千日多重耐药菌定植例次发生率','/report/f_view/fineReportXj.shtml?tab=6&grType=3');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">27</span>
			 <span class="zb_Name">IINI-27 医院感染病原体构成比</span>
			<a href="javascript:detail('IINI-27医院感染病原体构成比','/report/f_view/fineReportGr.shtml?tab=2');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list unfinish">
			<span class="zb_Num">28</span>
			 <span class="zb_Name">IINI-28 革兰氏阳性细菌构成比</span>
			<a href="javascript:detail('IINI-28 革兰氏阳性细菌构成比',28);" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list unfinish">
			<span class="zb_Num">29</span>
			 <span class="zb_Name">IINI-29 革兰氏阴性细菌构成比</span>
			<a href="javascript:detail('IINI-29 革兰氏阴性细菌构成比',29);" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">30</span>
			 <span class="zb_Name">IINI-30 医院感染病原体对抗菌药物的耐药率</span>
			<a href="javascript:detail('IINI-30 医院感染病原体对抗菌药物的耐药率','/report/f_view/fineReportXj.shtml?tab=3&grByt=1');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">31</span>
			 <span class="zb_Name">IINI-31 多重耐药菌医院感染病原体对抗菌药物的耐药率</span>
			<a href="javascript:detail('IINI-31 多重耐药菌医院感染病原体对抗菌药物的耐药率','/report/f_view/fineReportXj.shtml?tab=3&grByt=1');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">32</span>
			 <span class="zb_Name">IINI-32 出院患者抗菌药物使用率 </span>
			<a href="javascript:detail('IINI-32 出院患者抗菌药物使用率 ','/report/f_view/fineReportYw.shtml?tab=1&dateType=2');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">33</span>
			 <span class="zb_Name">IINI-33 住院患者抗菌药物使用率</span>
			<a href="javascript:detail('IINI-33住院患者抗菌药物使用率','/report/f_view/fineReportYw.shtml?tab=1&dateType=1');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">34</span>
			 <span class="zb_Name">IINI-34 抗菌药物预防使用构成比</span>
			<a href="javascript:detail('IINI-34抗菌药物预防使用构成比','/report/f_view/fineReportYw.shtml?tab=3');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">35</span>
			 <span class="zb_Name">IINI-35 抗菌药物治疗使用构成比</span>
			<a href="javascript:detail('IINI-35抗菌药物治疗使用构成比','/report/f_view/fineReportYw.shtml?tab=3');" class="ico_view" title="查看"></a>
			<div class="clear"></div>		</div>
		<div class="zb_list">
			<span class="zb_Num">36</span>
			 <span class="zb_Name">IINI-36 出院患者人均使用抗菌药物品种数</span>
			<a href="javascript:detail('IINI-36出院患者人均使用抗菌药物品种数','/report/f_view/fineReportYw.shtml?tab=4');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">37</span>
			 <span class="zb_Name">IINI-37 出院患者人均使用抗菌药物天数</span>
			<a href="javascript:detail('IINI-37出院患者人均使用抗菌药物天数','/report/f_view/fineReportYw.shtml?tab=4');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">38</span>
			 <span class="zb_Name">IINI-38 出院患者使用抗菌药物病原学送检率</span>
			<a href="javascript:detail('IINI-38 出院患者使用抗菌药物病原学送检率','/report/f_view/fineReportYw.shtml?tab=5&dateType=2');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">39</span>
			 <span class="zb_Name">IINI-39 出院患者治疗性使用抗菌药物病原学送检率</span>
			<a href="javascript:detail('IINI-39出院患者治疗性使用抗菌药物病原学送检率','/report/f_view/fineReportYw.shtml?tab=5&dateType=2&usePurpose=1');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">40</span>
			 <span class="zb_Name">IINI-40 住院患者抗菌药物治疗前病原学送检率</span>
			<a href="javascript:detail('IINI-40住院患者抗菌药物治疗前病原学送检率','/report/f_view/fineReportYw.shtml?tab=5&dateType=1&usePurpose=1');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">41</span>
			 <span class="zb_Name">IINI-41 住院患者限制类抗菌药物治疗性使用前病原学送检率</span>
			<a href="javascript:detail('IINI-41住院患者限制类抗菌药物治疗性使用前病原学送检率','/report/f_view/fineReportYw.shtml?tab=5&dateType=1&usePurpose=1');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">42</span>
			 <span class="zb_Name">IINI-42 住院患者特殊类抗菌药物治疗性使用前病原学送检率 </span>
			<a href="javascript:detail('IINI-42住院患者特殊类抗菌药物治疗性使用前病原学送检率','/report/f_view/fineReportYw.shtml?tab=5&dateType=1&usePurpose=1');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">43</span>
			 <span class="zb_Name">IINI-43 住院患者限制类抗菌药物治疗性使用前血培养送检率</span>
			<a href="javascript:detail('IINI-43住院患者限制类抗菌药物治疗性使用前血培养送检率','/report/f_view/fineReportYw.shtml?tab=5&dateType=1&usePurpose=1&state=2');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">44</span>
			 <span class="zb_Name">IINI-44 住院患者特殊类抗菌药物治疗性使用前血培养送检率 </span>
			<a href="javascript:detail('IINI-44 住院患者特殊类抗菌药物治疗性使用前血培养送检率','/report/f_view/fineReportYw.shtml?tab=5&dateType=1&usePurpose=1&state=2');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">45</span>
			 <span class="zb_Name">IINI-45 住院患者体温异常血培养送检率 </span>
			<a href="javascript:detail('IINI-45住院患者体温异常血培养送检率','/report/f_view/fineReportXj.shtml?tab=9');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">46</span>
			 <span class="zb_Name">IINI-46 一类切口手术抗菌药物预防使用率</span>
			<a href="javascript:detail('IINI-46 一类切口手术抗菌药物预防使用率','/report/f_view/fineReportSsyy.shtml?tab=6&incisionGrade=1&usePurpose=2');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list unfinish">
			<span class="zb_Num">47</span>
			 <span class="zb_Name">IINI-47 一类切口手术预防使用抗菌药物天数</span>
			<a href="javascript:detail('IINI-47 一类切口手术预防使用抗菌药物天数',47);" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">48</span>
			 <span class="zb_Name">IINI-48 一类切口手术术后24小时内抗菌药物停药率</span>
			<a href="javascript:detail('IINI-48一类切口手术术后24小时内抗菌药物停药率','/report/f_view/fineReportSsyy.shtml?tab=5&incisionGrade=1&usePurpose=2');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">49</span>
			 <span class="zb_Name">IINI-49 清洁手术术前0.5-2小时给药百分率</span>
			<a href="javascript:detail('IINI-49 清洁手术术前0.5-2小时给药百分率','/report/f_view/fineReportSsyy.shtml?tab=1&incisionGrade=0&usePurpose=2');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list unfinish">
			<span class="zb_Num">50</span>
			 <span class="zb_Name">IINI-50 手术时间大于3小时的手术术中抗菌药物追加剂执行率</span>
			<a href="javascript:detail('IINI-50手术时间大于3小时的手术术中抗菌药物追加剂执行率',50);" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">51</span>
			 <span class="zb_Name">IINI-51 失血量大于1500ml的手术</span>
			<a href="javascript:detail('IINI-51 失血量大于1500ml的手术','/report/f_view/fineReportSs.shtml?tab=11');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list unfinish">
			<span class="zb_Num">52</span>
			 <span class="zb_Name">IINI-52 医院感染聚集事件上报率</span>
			<a href="javascript:detail('IINI-52 医院感染聚集事件上报率',52);" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">53</span>
			 <span class="zb_Name">IINI-53 医院感染病例漏报率</span>
			<a href="javascript:detail('IINI-53 医院感染病例漏报率','/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/CBLB&__bypagesize__=false');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">54</span>
			 <span class="zb_Name">IINI-54 医院感染例次漏报率</span>
			<a href="javascript:detail('IINI-54 医院感染例次漏报率','/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/CBLB&__bypagesize__=false');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">55</span>
			 <span class="zb_Name">IINI-55 医务人员手卫生依从率</span>
			<a href="javascript:detail('IINI-55 医务人员手卫生依从率','/report/f_view/fineReportSw.shtml?tab=7');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list unfinish">
			<span class="zb_Num">56</span>
			 <span class="zb_Name">IINI-56 多重耐药菌感染核心防控措施执行率 </span>
			<a href="javascript:detail('IINI-56多重耐药菌感染核心防控措施执行率',56);" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list unfinish">
			<span class="zb_Num">57</span>
			 <span class="zb_Name">IINI-57 手术部位感染核心防控措施执行率</span>
			<a href="javascript:detail('IINI-57 手术部位感染核心防控措施执行率',57);" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list unfinish">
			<span class="zb_Num">58</span>
			 <span class="zb_Name">IINI-58 中央血管导管相关血流感染核心防控措施执行率</span>
			<a href="javascript:detail('IINI-58中央血管导管相关血流感染核心防控措施执行率',58);" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list unfinish">
			<span class="zb_Num">59</span>
			 <span class="zb_Name">IINI-59 呼吸机相关肺炎核心防控措施执行率</span>
			<a href="javascript:detail('IINI-59 呼吸机相关肺炎核心防控措施执行率',59);" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list unfinish">
			<span class="zb_Num">60</span>
			 <span class="zb_Name">IINI-60 导尿管相关尿路感染核心防控措施执行率</span>
			<a href="javascript:detail('IINI-60导尿管相关尿路感染核心防控措施执行率',60);" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list unfinish">
			<span class="zb_Num">61</span>
			 <span class="zb_Name">IINI-61 环境物表污染物清除率</span>
			<a href="javascript:detail('IINI-61 环境物表污染物清除率',61);" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list unfinish">
			<span class="zb_Num">62</span>
			 <span class="zb_Name">IINI-62 血液透析相关血液感染发生率</span>
			<a href="javascript:detail('血液透析相关血液感染发生率',62);" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">63</span>
			 <span class="zb_Name">IINI-63 NNIS分级手术部位感染发病率 </span>
			<a href="javascript:detail('IINI-63NNIS分级手术部位感染发病率','/report/f_view/fineReportSs.shtml?tab=8');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>
		<div class="zb_list">
			<span class="zb_Num">64</span>
			 <span class="zb_Name">IINI-64 不同感染风险指数手术部位感染发病率 </span>
			<a href="javascript:detail('IINI-64不同感染风险指数手术部位感染发病率','/report/f_view/fineReportSs.shtml?tab=12');" class="ico_view" title="查看"></a>
			<div class="clear"></div>
			</div>		
	</div>
</div>

</body>
<script type="text/javascript">
	function detail(title,url){
		parent.menuInfo.clickMenu(title,url,true);
	}
</script>
</html>