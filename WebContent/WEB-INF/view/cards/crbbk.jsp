<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>传染病上报卡</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/css/cdcCustom.css${version}" />
<style type="text/css">
	table{width: 100%;}
	.mainTable td{padding: 2px;}
	.piece{border-top: 1px solid #000;padding: 5px;width: initial;margin: 0px;margin-bottom: 20px;}
	.pieceTitle{padding: 3px;}
</style>
</head>
<body style="width: auto;">
	<c:if test="${!empty errMsg}">
		<div class="errTip">${errMsg}</div>
	</c:if>
	<div style="border: 1px solid #000;margin: 60px 5%;margin-top:10px; width: 90%;">
		<center><h1>中华人民共和国传染病报告卡</h1></center>
		<div style="width: 100%;">
			<div class="pieceTitle">
				<strong>患者基本信息：</strong>	
			</div>
			<div title="患者基本信息" class="piece">
				<table class="mainTable">
					<tr>
						<td class="rightTextAlign">病例号*：</td>
						<td class=""><input type="text" style="width: 130px;" value="${MZBRXX.patientId}"/></td>
						<td class="rightTextAlign">门诊号/${patientZyTitle}*：</td>
						<td><input type="text" style="width: 130px;" value="${MZBRXX.mzid}"/></td>
						<td class="rightTextAlign">住院次数*：</td>
						<td><input type="text" style="width: 130px;" value="${MZBRXX.visitId}"/></td>
					</tr>
					<tr>
						<td class="rightTextAlign">患者姓名*：</td>
						<td><input type="text" style="width: 130px;" value="${MZBRXX.patientName}"/></td>
						<td class="rightTextAlign">患儿家长姓名：</td>
						<td><input type="text" style="width: 130px;" value="${MZBRXX.parentName}"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="rightTextAlign">证件类型*：</td>
						<td>	
							<select style="width: 142px;">
								<option></option>
							</select>
						</td>
						<td class="rightTextAlign">有效证件号：</td>
						<td colspan="3">
							<input type="text" style="width: 130px; float:left;" value="${MZBRXX.idnumber}" />
							<!-- <input type="button" class="butt" value="提取性别和生日" /> -->
							<a href="javascript:;" class="tqxx" title="提取性别和生日" onclick="getInfoByID();"></a>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="line-height: 25px;">
							（有效证件号不许输入中文；包括：居民身份证号、护照、居民健康卡、社会保障卡、新农合医疗卡。暂无身份证号的
							儿童及残障患者填写监护人有效证件号，特殊信息请在备注中填写）
						</td>
					</tr>
					<tr>
						<td class="rightTextAlign">性别*：</td>
						<td><select style="width: 142px;"><option></option></select></td>
						<td class="rightTextAlign">出生日期*：</td>
						<td><input type="text" style="width: 130px;" value='<fmt:formatDate value="${MZBRXX.birthday}" type="both"/>' class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
						<td class="rightTextAlign">生日不详填年龄*：</td>
						<td>
							<input type="text" style="width: 60px;" value="${MZBRXX.age}"/>
							<select style="width: 60px;" >
								<option value="岁">岁</option>
								<option value="月">月</option>
								<option value="天">天</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="rightTextAlign">患者工作单位：</td>
						<td colspan="5">
							<input type="text" style="width: 93%;" value="${MZBRXX.workUnit}"/>
						</td>
					</tr>
					<tr>
						<td class="rightTextAlign">联系电话*：</td>
						<td>
							<input type="text" style="width: 130px;" value="${MZBRXX.tel}"/>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="rightTextAlign">病人属于*：</td>
						<td>
							<select style="width: 142px;" >
								<option></option>
							</select>
						</td>
						<td class="rightTextAlign">现住地址国标*：</td>
						<td>
							<label id="nowAddrGBCode">111111111</label>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>
							<select style="width: 142px;" >
									<option></option>
							</select>
							省
						</td>
						<td>&nbsp;</td>
						<td>
							<select style="width: 142px;" >
									<option></option>
							</select>
							市
						</td>
						<td>&nbsp;</td>
						<td>
							<select style="width: 142px;" >
									<option></option>
							</select>
							县（区）
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td colspan="2">
							<select style="width: 142px;" >
									<option></option>
							</select>
							乡（镇、街道）
						</td>
						<td>
							<input type="text" style="width: 130px;" />
							村
						</td>
						<td>&nbsp;</td>
						<td>
							<input type="text" style="width: 130px;" />
							门牌号
						</td>
					</tr>
					<tr>
						<td class="rightTextAlign">现住详细地址*：</td>
						<td colspan="5">
							<input type="text" style="width: 93%;" />
						</td>
					</tr>
					<tr>
						<td class="rightTextAlign">人群分类*：</td>
						<td>
							<select style="width: 142px;" >
									<option></option>
							</select>
						</td>
						<td class="rightTextAlign">其他人群分类：</td>
						<td><input type="text" style="width: 130px;" /></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="rightTextAlign">死亡日期：</td>
						<td><input type="text" class="Wdate text" style="width: 130px;" value="${queryStartDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="rightTextAlign">备注：</td>
						<td colspan="5">
							<input type="text" style="width: 93%;" />
						</td>
					</tr>
				</table>
			</div>
			<div class="pieceTitle">
				<strong>传染病信息：</strong>	<input type="button" class="btn_add" value="新增" onclick="" />
			</div>
			<div class="piece" title="传染病信息">
				<jsp:include page="/WEB-INF/view/cards/crbtable.jsp"></jsp:include>
			</div>
			<div class="pieceTitle">
				<strong>附卡信息：</strong>
			</div>
			<div class="piece" title="附卡信息">
				<div style="float:left;font-size: 14px;color:#adadad;">
					<a style="padding: 3px 7px;font-weight: bold;cursor: pointer;" name="xbfk" class="curM" onclick="move(this)">性病附卡</a>
					<a style="padding: 3px 7px;font-weight: bold;cursor: pointer;" name="ygfk" onclick="move(this)">乙肝附卡</a>
					<a style="padding: 3px 7px;font-weight: bold;cursor: pointer;" name="afpfk"onclick="move(this)">AFP附卡</a>
					<div class="move-bg"></div>
				</div>
				<div id="xbfk" class="fkPages">
					<jsp:include page="/WEB-INF/view/cards/additionCards/xbfk.jsp"></jsp:include>
				</div>
				<div id="ygfk" class="fkPages">
					<jsp:include page="/WEB-INF/view/cards/additionCards/ygfk.jsp"></jsp:include>
				</div>
				<div id="afpfk" class="fkPages">
					<jsp:include page="/WEB-INF/view/cards/additionCards/afpfk.jsp"></jsp:include>
				</div>
			</div>
			<div class="pieceTitle">
				<strong>报卡信息：</strong>
			</div>
			<div class="piece" title="报卡信息" style="margin-bottom: 5px;">
				<table class="mainTable">
					<tr>
						<td>报卡类别*：</td>
						<td>
							<select style="width: 142px;" >
									<option>初次报告</option>
									<option>订正报告</option>
									<option>死亡报告</option>
							</select>
						</td>
						<td>上报医生*：</td>
						<td><input type="text" style="width: 130px;" /></td>
						<td>上报科室*：</td>
						<td><input type="text" style="width: 130px;" /></td>
					</tr>
					<tr>
						<td>填卡日期*：</td>
						<td><input type="text" class="Wdate text" style="width: 130px;" value="${queryStartDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div style="position: fixed;bottom: 0px;background-color: gray;border: 1px solid #000; border-bottom:none;left: 5%;padding:10px 0px; width: 90%; ">
		<div style="float: left;padding-left:30px;">
			<input type="button" class="butt" style="width: 100px;" value="稍后再报"/>
		</div>
		<div style="float: right;padding-right:30px;">
			<input type="button" class="butt" style="width: 100px;" value="确定"/>&nbsp;&nbsp;
			<input type="button" class="butt" style="width: 100px;" value="取消"/>
		</div>
	</div>
	<script type="text/javascript">
	$(".move-bg").width($(".curM")[0].offsetWidth);
	$(".move-bg").animate({left:getPos($(".curM")[0])},200);
	$(".curM").trigger("click");
	function move(ele){
		$(".curM").removeClass("curM");
		$(ele).addClass("curM");
		var o = getPos($(".curM")[0]);
		$(".move-bg").width($(".curM").width()+14);
		$(".move-bg").animate({left:o},200);
		//loadDate();
		showMoreCDT();
	}
	function getPos(ele){
		 var p=ele.offsetParent;
		 var left=ele.offsetLeft;
		 while(p){
		  if(window.navigator.userAgent.indexOf("MSIE 8")>-1){
		   left+=p.offsetLeft;
		   }else{
		   left+=p.offsetLeft+p.clientLeft; 
		    }
		    p=p.offsetParent;
		  //left+=p.offsetLeft;
		//  p=p.offsetParent;
		  }
		/*  var obj={};
		 obj.x=left; */
		 return left;
	}
	function showMoreCDT(){
		$(".fkPages").each(function(){
			$(this).hide();
		});
		var ct = $(".curM")[0].name;
		$("#"+ct).show();
	}
	</script>
</body>
</html>