<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="csm" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CSM-控件实例(select的使用)</title>
<jsp:include page="/WEB-INF/view/core/include.jsp"></jsp:include>
</head>
<body style="padding: 20px;">
	
	<a href="${webroot}/weight.jsp">select的使用</a>
	
	<div>
		<p>下拉输入框</p>
		<input id="_combobox" name="linkName" >
	</div>
	
	<div>
		<p>select根据url加载有等级或没等级结构的信息</p>
		<select id="selectLoad" style="width: 200px;"></select>
	</div>
	
	<div>
		<p>省市区</p>
		<div id="areaPanel"></div>
		<p>省市区（选中省市区，如果只到市，则会选中省市）</p>
		<div id="areaSelpcaPanel"></div>
	</div>
	
	<div>
		<p>医院选择（可以选中自己输入的字符串）</p>
		<input type="text" id="unit1_1" style="width: 200px;"/>
	</div>
	<div>
		<p>医院选择（选中初始值）</p>
		<input type="text" id="unit1_2" style="width: 200px;" value="111"/>
	</div>
	<div>
		<p>医院选择（支持多选）</p>
		<input type="text" id="unit5_1" style="width: 200px;"/>
	</div>
	<div>
		<p>医院选择（支持多个选中初始值）</p>
		<input type="text" id="unit5_2" style="width: 200px;" value="111,10000151"/>
	</div>
	<div>
		<p>医院科室联动</p>
		<input type="text" id="unit2_1" style="width: 200px;"/>
		<input type="text" id="dep2_1" style="width: 200px;"/>
	</div>
	<div>
		<p>医院科室联动（选中初始值）</p>
		<input type="text" id="unit2_2" style="width: 200px;" value="111"/>
		<input type="text" id="dep2_2" style="width: 200px;" value="2"/>
	</div>
	
	<div>
		<p>医院科室医生联动</p>
		<input type="text" id="unit3_1" style="width: 200px;"/>
		<input type="text" id="dep3_1" style="width: 200px;"/>
		<input type="text" id="doc3_1" style="width: 200px;"/>
	</div>
	<div>
		<p>医院科室医生联动（选中初始值）</p>
		<input type="text" id="unit3_2" style="width: 200px;" value="111"/>
		<input type="text" id="dep3_2" style="width: 200px;" value="2"/>
		<input type="text" id="doc3_2" style="width: 200px;" value="2"/>
	</div>
	
	<div>
		<p>根据医院值加载科室</p>
		<input type="hidden" id="unit4_1" value="111"/>
		<input type="text" id="dep4_1" style="width: 200px;"/>
	</div>
	
	<div>
		<p>科室可以多选</p>
		<select id="dep_ck" style="width: 500px;"></select>
	</div>
	
	<div>
		<p>字典下拉框</p>
		<nis:select id="gender" name="gender" dictcode="gender" cssCls="easyui-validatebox" exp="required=\"true\""/>
	</div>

	<script type="text/javascript">
		function linkName(){
			Csm.comboBox.inputBox({
				url : webroot + '/sysDict/f_view/getDict.shtml?dictTypeCode=profession&allowSetValue=1',
				valueField : 'value',
				textField : 'value',
				id : '_combobox',
			});
		}
	
		function testSms(){
			var url = "${webroot}/othsmssend/f_view/comSendSmsPage.shtml?recvUserName=test&patientId=999900000101&mobilenum=13888888888&dgsId=1";
	    	Comm.dialogGlobal({
                url:url,
                title: '短信发送',
                width:900,height:500
            });
		};
		$(function() {
			linkName();
			$('#unit5_1').select2Remote({
				//这里填写空选项时显示的文字
				placeholder: '请输入医院名称',
				//远程加载的url
				url: Csm.url.unitQuery,
				//初始化url
				initUrl: Csm.url.unitGet,
				//设置支持多选
				multiple: true
			});
			$('#unit5_2').select2Remote({
				//这里填写空选项时显示的文字
				placeholder: '请输入医院名称',
				//远程加载的url
				url: Csm.url.unitQuery,
				//初始化url【参考方法写法：/unit/json/get】
				initUrl: Csm.url.unitGet,
				//设置支持多选
				multiple: true
			});
			
			Csm.select.load({
				id: 'selectLoad',
				url: webroot + '/pubcat/f_json/getAll.shtml',
				data: {},
				headerKey: '',
				headerValue: '-- 请选择 --',
				//是否可以多选
				//multiple: true,
				value: '2',
				kcode: 'catid',
				kvalue: 'catname',
				//如果为空则表示没有等级关系
				pid: 'parentid'
			});
			
			Csm.select.area({
				panelId: 'areaPanel',
				valueId: 'area',
				valueName: 'area'
			});
			Csm.select.area({
				panelId: 'areaSelpcaPanel',
				valueId: 'areaSelpca',
				valueName: 'areaSelpca',
				value: '3330'
			});
			
			Csm.select.unit({
				id: 'unit1_1',
				idAddInput: 1
			});
			Csm.select.unit({
				id: 'unit1_2'
			});

			Csm.select.unit({
				id: 'unit2_1',
				depId: 'dep2_1'
			});
			Csm.select.unit({
				id: 'unit2_2',
				depId: 'dep2_2'
			});

			Csm.select.unit({
				id: 'unit3_1',
				depId: 'dep3_1',
				docId: 'doc3_1'
			});
			Csm.select.unit({
				id: 'unit3_2',
				depId: 'dep3_2',
				docId: 'doc3_2'
			});

			Csm.select.dep({
				id: 'dep4_1',
				unitId: 'unit4_1'
			});

			Csm.select.depMultiple({
				id: 'dep_ck',
				unitId: 111,
				values: [2,3]
			});
			/* 用法2：传自定义的url和参数
			Csm.select.depMultiple({
				id: 'dep_ck',
				url: Csm.url.depQuery,
				data: {unitId: 111},
				values: [2,3]
			}); */
		});
	</script>
</body>
</html>