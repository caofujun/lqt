<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>更新文档</title>
<style>
body{background:#fff; font-size:12px; line-height:18px;  margin: 0;padding: 0; color:#666; }
.sm_main{width:800px; margin:10px auto;}
.sm_box{ margin-bottom:30px;}
.sm_title{padding:0px 20px; background-color:#efefef; color:#333; font-size:14px; font-weight:bold; width:100%;line-height:40px;}
.sm_list li{ line-height:30px;}
</style>
</head>

<body>

	<div class="sm_main">
		<div class="sm_box">
			<div class="sm_title">2017-09-22更新说明(7.5.5)</div>
			<ol class="sm_list">
				<li>新增查看sql日志功能，双击电话号码查看</li>
				<li>新增分院功能，可以按院区查询与统计</li>
				<li>报表数据的验证与修复</li>
				<li>职业暴露字典同样用业务字典下面的职业暴露项目，如果曾经在基础字典里面个性化的字典需要手动添加</li>
				<li>64项指标可以直接根据指标穿透到指定报表</li>
				<li>检出菌新增审核功能</li>
				<li>院感直报新增进度条显示</li>
				<li>抗菌药物报表新增了按出院时间统计</li>
				<li>修复现场反馈过来的BUG</li>
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2017-09-10更新说明(7.5.4)</div>
			<ol class="sm_list">
				<li>现患率登记表内容新增手术与医院感染危险因素</li>
				<li>报卡页面新增侵入性操作选择与报表历史查询</li>
				<li>ICU监测按医嘱监测统计</li>
				<li>新增天然耐药与标准病原体字典</li>
				<li>现患率直报导出文件做数据验证</li>
				<li>职业暴露界面调整</li>
				<li>抗菌药物报表按出院人数统计</li>
				<li>修复现场反馈过来的BUG</li>
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2017-08-18更新说明(7.5.3)</div>
			<ol class="sm_list">
				<li>新增现患率导出文件直报</li>
				<li>临床根据参数切换本人所属科室还是全部科室</li>
				<li>手卫生调查录入界面调整</li>
				<li>tomcat性能卡死问题解决</li>
				<li>手术打印功能</li>
				<li>环境卫生学的采用场所与采用标本支持手动输入保存，同时保存到字典</li>
				<li>职业暴露界面调整</li>
				<li>修复现场反馈过来的BUG</li>
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2017-08-04更新说明(7.5.2)</div>
			<ol class="sm_list">
				<li>网络直报与现患率直报，需要在角色管理的系统管理--网络直报授权</li>
				<li>新增农药中毒报卡</li>
				<li>手卫生调查录入界面调整</li>
				<li>职业暴露调整</li>
				<li>传染病直报导出调整</li>
				<li>修复现场反馈过来的BUG</li>
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2017-07-21更新说明(7.5.1)</div>
			<ol class="sm_list">
				<li>感染预警详情页面改版</li>
				<li>手卫生依从性调查改版可以根据字典新增指征，行为和错误原因</li>
				<li>新增资料上传与分享，需要在系统管理--医生资料授权</li>
				<li>手术管理可以手动新增手术。</li>
				<li>不分析标本与不分析标本检查病原体维护，需要在系统管理--数据配置里面授权</li>
				<li>法定传染病报卡增加“利福平耐药”选项</li>
				<li>横断面调查增加统计功能</li>
				<li>报表功能数据验证与修复</li>
				<li>新增自定义报表功能，详情请查询文档</li>
				<li>修复现场反馈过来的BUG</li>
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2017-07-06更新说明(7.5.0)</div>
			<ol class="sm_list">
				<li>新增预警模型计算（病程，影像，检验，培养，抗菌药物），多耐以及三管在院患者计算，可以在定时任务中启动任务。启动新的预警任务前，须停止掉老的exe的预警，三管与多耐计算</li>
				<li>新增预警方案配置，可以调节病程，影像，检验，培养，抗菌药物的权重,授权菜单系统管理--数据匹配--预警方案配置。</li>
				<li>新增预警关键词配置，可以设置预警同义词，授权菜单系统管理--数据匹配--预警关键词配置。</li>
				<li>新建检验预警计算配置，可以配置检验预警的感染因素，授权菜单系统管理--数据匹配--授权菜单系统管理--数据匹配--检验预警计算配置。</li>
				<li>新增加密解密工具，授权菜单系统管理--数据匹配--加密解密工具</li>
				<li>新增企业信息管理与审核信息管理,授权菜单系统管理--医院资料--企业信息管理与审核信息管理</li>
				<li>所有报表功能数据验证与修复</li>
				<li>传染病加入待报功能</li>
				<li>新增临床预警列表接口与消息列表接口，详情见临床嵌入接口文档；</li>
				<li>修复现场反馈过来的BUG</li>
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2017-05-26更新说明(7.4.3)</div>
			<ol class="sm_list">
				<li>新增职业暴露统计。授权菜单 职业暴露--职业暴露统计</li>
				<li>新增报表下的子报表授权。需要大家在角色管理分配报表授权，否则看不到报表。</li>
				<li>修复环境卫生根据模板创建无法生成结果的问题</li>
				<li>细菌统计报表重新整理，请大家验证下</li>
				<li>新增重点菌维护，授权菜单系统管理--数据匹配--重点菌维护</li>
				<li>请大家删除之前所有的文件，不要覆盖的方式替换</li>
				<li>修复现场反馈过来的BUG</li>
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2017-05-16更新说明(7.4.2)</div>
			<ol class="sm_list">
				<li>新增消息盒子悬浮球功能</li>
				<li>手术感染报表数据准确性整理</li>
				<li>临床端新增多耐上报卡嵌入</li>
				<li>报卡页面新增添加抗菌药物功能</li>
				<li>新增手卫生用品监测统计报表。授权菜单 手卫生监测--手卫生用品监测统计</li>
				<li>数据库配置文件中新增了中间库配置，请大家配置下</li>
				<li>本次用了混淆打包的方式，请大家删除之前所有的文件，不要覆盖的方式替换</li>
				<li>修复现场反馈过来的BUG</li>
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2017-04-20更新说明(7.4.1)</div>
			<ol class="sm_list">
				<li>新增插管未评估按科室人员查询表。默认是不开启评估展示的，设置参数插管评估是否预警（nis.hxj.cgpgShow）为0后，在首页暴露上报后面会显示插管未评估个数，点击可穿透</li>
				<li>新增手卫生依从性统计报表。授权菜单 手卫生监测--手卫生依从性统计</li>
				<li>患者病例工作量统计。授权菜单 目标性监测--感染监测--感染病例工作量统计可查看</li>
				<li>检出菌搜索新增隔离医嘱功能</li>
				<li>搜索框大部分做了按拼音码检索不区分大小写</li>
				<li>修复现场反馈过来的BUG</li>
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2017-04-01更新说明(7.4.0)</div>
			<ol class="sm_list">
				<li>临床的患者列表新增我的患者，院感，预警人数分类统计筛选</li>
				<li>新增中暑报卡</li>
				<li>定时任务新增立即执行与查看日志功能</li>
				<li>检验数据匹配里面的微生物、标本、抗菌药物，自动匹配功能</li>
				<li>新增病程能调用外部原始病程功能</li>
				<li>环境卫生学【已出报告】、【未出报告】、【已审核】、【未审核】作为检索条件</li>
				<li>修复现场反馈过来的BUG</li>
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2017-03-17更新说明(7.3.9)</div>
			<ol class="sm_list">
				<li>新增实施工具“基础数据配置”与“数据接口监控”，需要登录admin进行授权使用</li>
				<li>新增报表说明维护，可以查看报表计算公式与统计说明</li>
				<li>新增64项指标中的，细菌报表3个与抗菌药物报表3个</li>
				<li>修复现场反馈过来的BUG</li>
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2017-03-02更新说明(7.3.8)</div>
			<ol class="sm_list">
				<li>新增手卫生依从性调查表</li>
				<li>新增数据接口监控</li>
				<li>修改参数调整更新代码后只需调整参数“NIS http 项目地址”的值http://dev.gklqt.com修改为医院IP+端口例：http://192.168.1.200:8080</li>
				<li>修复现场反馈过来的BUG</li>
				<li>修复报表部分不准确的问题</li>
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2017-02-10更新说明(7.2.8)</div>
			<ol class="sm_list">
				<li>新增NICU统计报表</li>
				<li>新增IMC弹出提示开关参数，配置参数nis.imc.is.show</li>
				<li>修复报表样式与手术感染和抗菌药物报表数据的准确性</li>
				<li>新增公卫报卡中心脑血管报卡</li>
				<li>修复部分现场反馈过来的BUG</li>
				<li>报表样式需要替换文件resources.rar解压后覆盖目录：WebReport\WEB-INF\resources</li>
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2017-01-12更新说明(7.1.8)</div>
			<ol class="sm_list">
				<li>修复暴发预警</li>
				<li>新增评估表功能</li>
				<li>修复多耐和感染报表数据的准确性</li>
				<li>新增按菌统计多耐检出率与发病率报表</li>
				<li>地址：<a href="http://pan.baidu.com/s/1sl3yqhJ" target="_blank">http://pan.baidu.com/s/1sl3yqhJ</a> 密码：x39g</li>				
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2016-12-27更新说明(7.0.8)</div>
			<ol class="sm_list">
				<li>暴发预警改版</li>
				<li>环境卫生学监测合格状态修复</li>
				<li>环境卫生学报表修复</li>
				<li>IMC切换用户修复</li>
				<li>每日检出菌时间调整</li>
				<li>地址：<a href="http://pan.baidu.com/s/1o8i4HYU" target="_blank">http://pan.baidu.com/s/1o8i4HYU</a> 密码：poyw</li>				
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2016-12-21更新说明(7.0.7)</div>
			<ol class="sm_list">
				<li>职业暴露优化</li>
				<li>职业暴露打印修复</li>
				<li>新增食源异常报卡</li>
				<li>新增肿瘤报卡新增完整地址框</li>
				<li>修复报卡页面显示性别和数据库中性别不同的BUG</li>
				<li>地址：<a href="http://pan.baidu.com/s/1dFFqMeH" target="_blank">http://pan.baidu.com/s/1dFFqMeH</a> 密码：lv9d</li>				
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2016-12-16更新说明(7.0.6)</div>
			<ol class="sm_list">
				<li>新增手术用药报表7个</li>
				<li>修复首页待处理统计时间问题</li>
				<li>新增肿瘤报卡</li>
				<li>传染病报卡BUG修复</li>
				<li>IMC参数和地址配置</li>
				<li>地址：<a href="http://pan.baidu.com/s/1slVhdG5" target="_blank">http://pan.baidu.com/s/1slVhdG5</a> 密码：t51x</li>				
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2016-12-13更新说明(7.0.5)</div>
			<ol class="sm_list">
				<li>修复排除社区感染BUG</li>
				<li>新增手术用药维护</li>
				<li>修复报表住院患者人数不一致问题</li>
				<li>新增搜索医生不区分大小写问题</li>
				<li>新增乙肝附卡和性病附卡打印</li>
				<li>调整传染病地址审核后丢失的问题</li>
				<li>调整传染病审核后日期默认为当月</li>
				<li>报卡审核界面显示方式按上报日期或审核日期进行排序</li>
				<li>地址：<a href="http://pan.baidu.com/s/1bp23jkz" target="_blank">http://pan.baidu.com/s/1bp23jkz</a> 密码：ymmy</li>				
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2016-12-08更新说明(7.0.4)</div>
			<ol class="sm_list">
				<li>新增死因报卡临床端是否可以填写根本死因的参数</li>
				<li>新增横断面调查打印</li>
				<li>环境卫生学新增一条显示多条</li>
				<li>新增死因报卡临床端是否可以填写根本死因的参数</li>
				<li>修复传染病报卡上报与字典类型不正确的BUG</li>
				<li>修复抗菌药物使用人数数据不正确的问题</li>
				<li>地址：<a href="http://pan.baidu.com/s/1nuYwvtn" target="_blank">http://pan.baidu.com/s/1nuYwvtn</a> 密码：i3de</li>				
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2016-12-06更新说明(7.0.3)</div>
			<ol class="sm_list">
				<li>新增职业暴露打印</li>
				<li>患者查询IE不兼容，</li>
				<li>环境卫生学采用结果算法修改</li>
				<li>传染病txt输入限制长度</li>
				<li>地址：<a href="http://pan.baidu.com/s/1bpN1551" target="_blank">http://pan.baidu.com/s/1bpN1551</a> 密码：m0gd</li>				
			</ol>		
		</div>
		<div class="sm_box">
			<div class="sm_title">2016-12-03更新说明(7.0.2)</div>
			<ol class="sm_list">
				<li>环境卫生学采用点数修改与临床端查询权限修改</li>
				<li>新增ICU调整感染率报表</li>
				<li>患者综合视图统计范围调整</li>
				<li>地址：<a href="http://pan.baidu.com/s/1dELfklV" target="_blank">http://pan.baidu.com/s/1dELfklV</a> 密码：fclg</li>				
			</ol>		
		</div>	
		<div class="sm_box">
			<div class="sm_title">2016-12-01更新说明(7.0.1)</div>
			<ol class="sm_list">
				<li>赤峰宝山传染病报卡打印个性化</li>
				<li>环境卫生学授权以及字段自动启用</li>
				<li>环境卫生学新增2个报表</li>
				<li>新增医生用户的BUG，首页标本图标统计数据不一致</li>
				<li>地址：<a href="http://pan.baidu.com/s/1geCTy75" target="_blank">http://pan.baidu.com/s/1geCTy75</a> 密码：zmmd</li>				
			</ol>		
		</div>	
	</div>
</body>
</html>
