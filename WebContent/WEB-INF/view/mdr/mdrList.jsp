<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>检出菌搜索</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
	<script type="text/javascript" src="${webroot}/resources/easyui/upgrade/datagrid-detailview.js${version}"></script>
	<style>
		.easyui-combobox{width:75px;}
		.table_cx .easyui-combobox{width:150px;}
		.messager-button .l-btn{pading:3px 10px;width:auto;min-width: 80px;_min-width: 80px;}
		.window-header .panel-tool .panel-tool-collapse{display: none;}
		.window-header .panel-tool .panel-tool-min{display: none;}
		.window-header .panel-tool .panel-tool-max{display: none;}
	</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',border:false,title:'<c:if test="${empty testOrderNo}">查询条件</c:if>'" style="width:250px;<c:if test="${!empty testOrderNo}">display:none;</c:if>">
		 <div class="easyui-layout" data-options="fit:true">              
	         <div data-options="region:'center',border:false">
	         	<!-- <select style="width:100px" id="patientState"><option value="">病人状态</option><option value="z">在院</option><option value="c">出院</option></select> -->
				<table class="table_cx" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td class="t_title">时间选择：</td>
							<td>
								<select style="width:120px" id="dateType" class="easyui-combobox"><option value="j">检出日期</option><option value="s">送检日期</option></select>
							</td>
						</tr>
						<tr>
							<td class="t_title">开始时间：</td>
							<td>
								<input type="text" id="startDate" value="${startDate}" style="width:108px"  class="Wdate text" onclick="WdatePicker()"  />
							</td>
						</tr>
						<tr>
							<td class="t_title">结束时间：</td>
							<td>
								<input type="text" id="endDate" value="${endDate}" style="width:108px" class="Wdate text" onclick="WdatePicker()" />
							</td>
						</tr>
						<tr><td height="5"></td><td></td></tr>
						<tr>
							<td class="t_title">患者检索：</td>
							<td>
								<input type="text" class="auto-tip" style="width:108px" data-tip="${patientZyTitle}/${patientNoTitle}/姓名" id="searchString" <c:if test="${!empty testOrderNo}">value="${testOrderNo}"</c:if> />
							</td>
						</tr>
						<tr><td height="5"></td><td></td></tr>
						<tr>
							<td class="t_title">科室类型：</td>
							<td>
								<select style="width:120px" id="deptType" class="easyui-combobox"><option value="s">送检科室</option><option value="z" <c:if test="${!empty testOrderNo}">selected="selected"</c:if> >所在科室</option></select>
							</td>
						</tr>
						<tr>
							<td class="t_title">科室名称：</td>
							<td>
							<input type="hidden" id="unit" value="${unitId}"/>
							<div class="select_del"><input id="deptId"  style="width:120px" <c:if test="${deptId!=null}">readonly="readonly"  </c:if>/><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#deptId').combo('clear');"></a></div>
							</td>
						</tr>
						<tr><td height="5"></td><td></td></tr>
						<tr>
							<td class="t_title">检出菌类：</td>
							<td>
							<select style="width:120px" id="key" class="easyui-combobox"><option value="q">全部</option><option value="z">重点菌</option><option value="f">非重点菌</option></select>
							</td>
						</tr>
						<tr id="zdjrow" style="display: none;">
							<td class="t_title">重点菌名称：</td>
							<td>
								<nis:select name="specDescribes" id="specDescribes" cssCls="easyui-combobox" headerKey="" headerValue="重点菌名称" dictcode="spec_describes" exp="style=\"width: 120px;\""/>
							</td>
						</tr>
						<tr>
							<td class="t_title">菌名称：</td>
							<td>
							<input type="text" class="auto-tip" style="width:108px" data-tip="菌名称" id="lispathoName" value="${name}"/>
							</td>
						</tr>
						<tr><td height="5"></td><td></td></tr>
						<tr>
							<td class="t_title">革兰氏分类：</td>
							<td>
							<select style="width:120px" id="rsId" class="easyui-combobox"><option value="">请选择</option><option value="G+">G+</option><option value="G-">G-</option></select>
							</td>
						</tr>
						<tr>
							<td class="t_title">菌属分类：</td>
							<td>
							<div class="select_del"><input id="jszd"  style="width:120px" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#jszd').combo('clear');"></a></div>
							</td>
						</tr>
						<tr>
							<td class="t_title">多耐类型：</td>
							<td>
								<div class="select_del">
									<select id="dnlx"  class="easyui-combobox"></select>
								</div>
							</td>
						</tr>
						<tr><td height="5"></td><td></td></tr>
						<tr>
							<td class="t_title">感染类型：</td>
							<td>
								<nis:select name="infectTypeId"  cssCls="easyui-combobox" id="infectTypeId" headerKey="" headerValue="请选择感染类型" dictcode="gr_type" value=""  exp="style=\"width: 120px;\""/>
							</td>
						</tr>
						<c:if test="${isSh == 1}">
						<tr><td height="5"></td><td></td></tr>
						<tr>
							<td class="t_title">审核状态：</td>
							<td>
							<select style="width:120px" id="authStatus" class="easyui-combobox"><option value="q">全部</option><option value="0">未审核</option><option value="1">审核</option></select>
							</td>
						</tr>
						</c:if>
						<tr><td height="5"></td><td></td></tr>
			<!-- 			<tr>
							<td class="t_title">去重条件：</td>
							<td>
							<input id="jszd"  type="text" style="width:108px" />
							</td>
						</tr> -->
						<tr><td height="5"></td><td></td></tr>
						<tr>			
							<td class="t_title"><input type="checkbox" name="qc" id="qc" value="t"/></td>
							<td>
								<label for="qc">剔除重复</label>
								<span class="ico_help"></span>
							</td>
						</tr>
			<!-- 			<tr>			
							<td class="t_title"><input type="checkbox" name="gl" id="gl" value="1"/></td>
							<td>
							<label for="gl">显示隔离医嘱</label>
							</td>
						</tr> -->
						<tr>
							<td class="t_title"><input type="checkbox" name="gx" id="gx" value="1"/></td>
							<td>
							<label for="gx">显示管辖科室</label>
							</td>
						</tr>
						<!-- <tr>
							<td class="t_title"><input type="checkbox" name="cs" id="cs" value="1"/></td>
							<td>
							<label for="cs">过滤措施完全执行</label>
							</td>
						</tr> -->
					</tbody>
				</table>
	         </div>
	         <div data-options="region:'south',border:false" style="height:50px; border-top-width:1px;">	         		
				<div class="btn_center">
					<div class="n_btn_blue">
						<a href="javascript:;" onclick="viewMdr.query()" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
					</div>
				</div>				
	         </div>
        </div>			
	</div>
	<div data-options="region:'center',border:false" style="border-left-width:1px;">
		<div id="tb" class="m_search">
			<span style="color:red;float:left;">此页面多耐菌列表比实际检出时间延后一天</span>
			<div class="m_search_last">					
				<div class="btn_r">
					<div class="n_btn_grey">
						<a href="javascript:;" onclick="viewMdr.exportExcelMdr()"><i class="icon iconfont">&#xe628;</i><span>导出</span></a>
					</div>				
				</div>		
			</div>
		</div>	
		<div id="viewMdrPanel"></div>
	</div>	
	<script type="text/javascript">
		/**剔除重复规则显示**/
		$(function(){
            $('.ico_help').tooltip({
                position: 'right',
                content: '<p style="color:#333; width:300px; padding:10px;"><b>送检次数：</b>统计时间内，同一科室、同一患者、送检相同标本，只算一次送检；<br/><b>检出次数：</b>统计时间内，同一科室、同一患者、同一送检标本、检出相同细菌，只算一次检出；<br/><b>多耐次数：</b>统计时间内、同一科室、同一患者、同一送检标本、检出相同细菌、且均为多耐，只算最早检出的那一株。</p>',
                onShow: function(){
                    $(this).tooltip('tip').css({
                        backgroundColor: '#fafafa',
                        borderColor: '#ccc'
                    });
                }
            });
		 });
		
		function seturl(testOrderNo,pathogenSn){
			var url="${webroot}/xn011Dclymx/f_view/mdrDetail.shtml?testOrderNo="+testOrderNo+"&pathogenSn="+pathogenSn;
		    $("#viewMdrDetailIframe").attr("src",url);
		}
		function showAdv(){
			if($("#adv").css("display")=="none"){
				$("#adv").show();
				$('#viewMdrPanel').datagrid('resize',{})
			}else{
				$("#adv").hide();
				$('#viewMdrPanel').datagrid('resize',{})
			}
		}
		var emptyDGview = $.extend({},$.fn.datagrid.defaults.view,{
		    onAfterRender:function(target){
		        $.fn.datagrid.defaults.view.onAfterRender.call(this,target);
		        var opts = $(target).datagrid('options');
		        var vc = $(target).datagrid('getPanel').children('div.datagrid-view');
		        vc.children('div.datagrid-empty').remove();
		        if (!$(target).datagrid('getRows').length){
		            var d = $('<div class="datagrid-empty" style="color:red;"></div>').html(opts.emptyMsg || 'no records').appendTo(vc);
		            d.css({
		                position:'absolute',
		                left:0,
		                top:50,
		                width:'100%',
		                textAlign:'center'
		            });
		        }
		    }
		});
		var viewMdr = {
			panel : 'viewMdrPanel',
			dataSections : eval('${dataSections}'),
			load : function(){
				autoTip.clear();
				var resProp = $('#resProp').val();
				if(resProp!=null){
					resProp=resProp.join(",");
				}
				var specDescribes = $('#specDescribes').combogrid('getValue');
				/* if(specDescribes!=null){
					specDescribes=specDescribes.join(",");
				} */
				var infectTypeId = $('#infectTypeId').combogrid('getValue');
				/* if(infectTypeId!=null){
					infectTypeId=infectTypeId.join(",");
				} */
				var esbl = '';
	    		$("input:checkbox[name='esbl']:checked").each(function(){ 
	    			esbl += $(this).val() + ',';
	   			});
	    		var gl = '';
	    		$("input:checkbox[name='gl']:checked").each(function(){ 
	    			gl += $(this).val() + ',';
	   			});
	    		var gx = '';
	    		$("input:checkbox[name='gx']:checked").each(function(){ 
	    			gx += $(this).val() + ',';
	   			});
		        $('#'+viewMdr.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                collapsible:true,
	                url:'${webroot}/xn011Dclymx/f_json/pageQuery.shtml',
	                queryParams: {
	                	/* 'patientState':$('#patientState').val(), */
	                	'dateType':$('#dateType').combogrid('getValue'),
		                'startDate': $('#startDate').val(),
		                'endDate': $('#endDate').val(),
		                'deptType':$('#deptType').combogrid('getValue'),
		                'deptId': $('#deptId').combogrid('getValue'),
		                'isAudited':$('#isAudited').val(),
		                'key':$('#key').combogrid('getValue'),
		                <c:if test="${isSh == 1}">
		                'authStatus' : $('#authStatus').combogrid('getValue'),
		                </c:if>
		               /*  'resProp':resProp, */
		                'specDescribes':specDescribes,
		                'searchString':$('#searchString').val(),
		                'qc':$("input[name='qc']:checked").val(),
		                'infectTypeId':infectTypeId,
		                'jszd': $('#jszd').combogrid('getValue'),
		                'gl':gl,
		                'gx':gx,
		                'lispathoName':$("#lispathoName").val(),
		                'rsId':$('#rsId').combogrid('getValue'),
		               /*  'byt': $('#byt').combogrid('getValue'), */
		                /* 'kjyw': $('#kjyw').combogrid('getValue'), */
		                'esbl':esbl,
		                'dnlx':$("#dnlx").combobox("getValues").toString()
		            },
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
							<c:if test="${isSh == 1}">
							{field:'authStatusMdr',title:'审核状态',sortable:true,width:70,
							 formatter:function(value,r,index){
									if(r.authStatusMdr){
										var auth;
										if(r.authStatusMdr==1){
											auth='已审核';
											return ['<span title="'+r.authUser+'于'+r.authDate.substring(0,10)+'已审核">',auth,'</span>'].join('');
										}else if(r.authStatusMdr==0){
											auth='未审核';
											return auth;
										}
									}	
								}
							 },
							 </c:if>
							 {field:'gl',title:'隔离状态',sortable:true,width:70,
							 formatter:function(value,r,index){
									if(r.gl){
										var agl;
										if(r.gl==2){
											agl='正在隔离';
										}else if(r.gl==1){
											agl='隔离结束';
										}
										return ['<a href="javascript:viewMdr.getYzxx(\'',r.zyid,'\',\'\');">',agl,'</a>'].join('');
									}	
								}
							 },
							{field:'submiAt',title:'送检日期',sortable:true,width:80},
							{field:'surveyDeptName',title:'送检科室',sortable:true,width:100},
							{field:'zyid',title:'${patientZyTitle}',sortable:true,width:100,
								formatter:function(value,r){
									return ['<a href="javascript:viewMdr.showDetail(\'',r.zyid,'\',\'\');">',r.${patientZyValue},'</a>'].join('');
								}	
							},
							{field:'patientName',title:'患者',sortable:true,width:80,
								formatter:function(value,row,index){
								  	  return [(row.patientName+'('+row.sex+','+row.age+row.ageUnit+')')].join('');}	
							},
							{field:'bedNo',title:'床号',sortable:true,width:50},
							{field:'testOrderNo',title:'单号',sortable:true,width:100},
							{field:'lisitemName',title:'标本',sortable:true,width:60},
							{field:'dt',title:'检出日期',sortable:true,width:80},
							{field:'lispathoName',title:'检出菌',sortable:true,width:120},
							{field:'resProp',title:'多耐类型ID',sortable:true,hidden:true},
							{field:'resPropName',title:'多耐类型',sortable:true,width:100,
								formatter:function(value,r){
									var resPropName = '<a class="a_w" href="javascript:viewMdr.showMdr(\''+r.testOrderNo+'\',\''+r.pathogenSn+'\');">'+r.resPropName+'</a>';
									if('hospital'=='${acType}'){
										resPropName = resPropName + '<a href="#" title="cc111" class="easyui-tooltip" id="id_tooltip_' + r.zyid + '_' + r.testOrderNo + '_' + r.orderno + '_' + r.surveyDeptId + '_' + r.normItemId + '_' + r.normOrderno + '"><span class="arrow_down"></span></a>';
									}
									return resPropName;
								}		
							},
							{field:'specDescribes',title:'特殊耐药',sortable:true,align:'center',width:60,
								formatter:function(value,r){
									return ['<a href="javascript:viewMdr.showTsny(\'',r.specDescribes,'\');">',r.specDescribes,'</a>'].join('');
								}		
							},
							{field:'esbl',title:'ESBL',sortable:true,align:'center',width:50},						
							
							{field:'authStatus',title:'authStatus',hidden:true},
							{field:'ifts',title:'感染类型 | 感染时间 | 感染部位',sortable:true,width:300,
								formatter:function(value,r,index){
									var x = (!r.infectTypeName?"":r.infectTypeName)+(!r.grsj?"":" | "+(r.grsj).substring(0,10))+(!r.grbw?"":" | "+r.grbw)+((r.authStatus=="未审核" )?"(<font color='red'>"+r.authStatus+"</font>)":"") ;
									return [('<a href="javascript:void(0);" style="" id="od_tooltip_'+ r.zyid + '_' + r.testOrderNo + '_' + r.orderno + '_' + r.surveyDeptId + '_' + r.normItemId + '_' + r.normOrderno+'" class="easyui-tooltip">' + x + '</a>')].join('');
								}								
							 },
							{field:'opts',title:'操作',width:80,formatter:function(value,r){
								var resPropName = '';
								if('${isSh}'== 1){
									resPropName += '<a href="javascript:viewMdr.check(\''+ r.resPropName + '\',\'' + r.orderno + '\',\'' + r.surveyDeptId + '\',\'' + r.dt +'\');" class="ico_check" title="审核"></a>';
								}
								resPropName += '<a href="#" class="easyui-tooltip" id="id_tooltip_' + r.testOrderNo + '_'+ r.pathoCode+'">干预 &nbsp;&nbsp;<span class="arrow_down"></span></a>';
								return resPropName;
							}}  
		                ]
	                ],	                	
	                pagination:true,
	                pageSize:50,
	                pageList:[50,100,200],
	                rownumbers:true,
	                view: emptyDGview,
	                emptyMsg: '暂无需要报告的多耐菌！',
	                onLoadSuccess: function(data) {
	                	for (var i = 0; i < data.rows.length; i++) {
	            			var row = data.rows[i];
	            			viewMdr.addTooltip('id_tooltip_' + row.zyid + '_' + row.testOrderNo + '_' + row.orderno + '_' + row.surveyDeptId + '_' + row.normItemId + '_' + row.normOrderno, row.zyid, row.testOrderNo, row.orderno, row.surveyDeptId, row.normItemId, row.normOrderno);
	            			var t = (row.dt).replace(/-/g,"/");
	            			var dateM = new Date(t);
	            			var dc = (!row.bedNo?"未知":row.bedNo)+" 床 ["+row.patientName+"]于 "+(dateM.getMonth()+1)+"月"+dateM.getDate()+"日"+" 检出 "+row.lispathoName+" ( "+(!row.resPropName?" ":row.resPropName)+(!row.specDescribes?" ":" , "+row.specDescribes)+") "+(!row.esbl?"":"ESBL("+row.esbl+")")+"，请注意开立隔离医嘱，采取隔离措施！";
	            			viewMdr.addOpts('id_tooltip_' + row.testOrderNo +'_'+row.pathoCode,row.deptId,dc,row.testOrderNo,row.dt,row.zyid,row.surveyDeptId,row.orderno,(!row.infectTypeId?"":row.infectTypeId),(!row.normItemId?"":row.normItemId),(!row.normOrderno?"":row.normOrderno),(!row.resProp?"":row.resProp));
	            			viewMdr.addOdTooltip('od_tooltip_'+ row.zyid + '_' + row.testOrderNo + '_' + row.orderno + '_' + row.surveyDeptId + '_' + row.normItemId + '_' + row.normOrderno,  row.dt,row.zyid,row.surveyDeptId,row.orderno,row.testOrderNo,row.pathogenSn);
	            		}
	            		
	                	//$('#'+viewMdr.panel).datagrid('selectRow', index);
	                	var curRow = $('#'+viewMdr.panel).datagrid('getSelected');
	                	if(curRow!=undefined){
	                		seturl(curRow.testOrderNo,curRow.pathogenSn);
	                	}
	                },
	                onClickRow:function(rowIndex, rowData){	                
	                	seturl(rowData.testOrderNo,rowData.pathogenSn);
			    	},
			    	toolbar:'#tb'
		        });
			},
			//查询
			query : function(index) {
				autoTip.clear();
				var resProp = $('#resProp').val();
				if(resProp!=null){
					resProp=resProp.join(",");
				}
				var specDescribes = $('#specDescribes').combogrid('getValue');
				/* if(specDescribes!=null){
					specDescribes=specDescribes.join(",");
				} */
				var infectTypeId = $('#infectTypeId').combogrid('getValue');
				/* if(infectTypeId!=null){
					infectTypeId=infectTypeId.join(",");
				} */
				var esbl = '';
	    		$("input:checkbox[name='esbl']:checked").each(function(){ 
	    			esbl += $(this).val() + ',';
	   			});
	    		var gl = '';
	    		$("input:checkbox[name='gl']:checked").each(function(){ 
	    			gl += $(this).val() + ',';
	   			});
	    		var gx = '';
	    		$("input:checkbox[name='gx']:checked").each(function(){ 
	    			gx += $(this).val() + ',';
	   			});
		        $('#'+viewMdr.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                collapsible:true,
	                url:'${webroot}/xn011Dclymx/f_json/pageQuery.shtml',
	                queryParams: {
	                	/* 'patientState':$('#patientState').val(), */
	                	'dateType':$('#dateType').combogrid('getValue'),
		                'startDate': $('#startDate').val(),
		                'endDate': $('#endDate').val(),
		                'deptType':$('#deptType').combogrid('getValue'),
		                'deptId': $('#deptId').combogrid('getValue'),
		                'isAudited':$('#isAudited').val(),
		                'key':$('#key').combogrid('getValue'),
		                <c:if test="${isSh == 1}">
		                'authStatus' : $('#authStatus').combogrid('getValue'),
		                </c:if>
		               /*  'resProp':resProp, */
		                'specDescribes':specDescribes,
		                'searchString':$('#searchString').val(),
		                'qc':$("input[name='qc']:checked").val(),
		                'infectTypeId':infectTypeId,
		                'jszd': $('#jszd').combogrid('getValue'),
		                'gl':gl,
		                'gx':gx,
		                'lispathoName':$("#lispathoName").val(),
		                'rsId':$('#rsId').combogrid('getValue'),
		               /*  'byt': $('#byt').combogrid('getValue'), */
		                /* 'kjyw': $('#kjyw').combogrid('getValue'), */
		                'esbl':esbl,
		                'dnlx':$("#dnlx").combobox("getValues").toString()
		            },	           
	                onLoadSuccess: function(data) {
	                	for (var i = 0; i < data.rows.length; i++) {
	            			var row = data.rows[i];
	            			viewMdr.addTooltip('id_tooltip_' + row.zyid + '_' + row.testOrderNo + '_' + row.orderno + '_' + row.surveyDeptId + '_' + row.normItemId + '_' + row.normOrderno, row.zyid, row.testOrderNo, row.orderno, row.surveyDeptId, row.normItemId, row.normOrderno);
	            			var t = (row.dt).replace(/-/g,"/");
	            			var dateM = new Date(t);
	            			var dc = (!row.bedNo?"未知":row.bedNo)+" 床 ["+row.patientName+"]于 "+(dateM.getMonth()+1)+"月"+dateM.getDate()+"日"+" 检出 "+row.lispathoName+" ( "+(!row.resPropName?" ":row.resPropName)+(!row.specDescribes?" ":" , "+row.specDescribes)+") "+(!row.esbl?"":"ESBL("+row.esbl+")")+"，请注意开立隔离医嘱，采取隔离措施！";
	            			viewMdr.addOpts('id_tooltip_' + row.testOrderNo +'_'+row.pathoCode,row.deptId,dc,row.testOrderNo,row.dt,row.zyid,row.surveyDeptId,row.orderno,(!row.infectTypeId?"":row.infectTypeId),(!row.normItemId?"":row.normItemId),(!row.normOrderno?"":row.normOrderno),(!row.resProp?"":row.resProp));
	            			viewMdr.addOdTooltip('od_tooltip_'+ row.zyid + '_' + row.testOrderNo + '_' + row.orderno + '_' + row.surveyDeptId + '_' + row.normItemId + '_' + row.normOrderno,  row.dt,row.zyid,row.surveyDeptId,row.orderno,row.testOrderNo,row.pathogenSn);
	            		}
	            		
	                	$('#'+viewMdr.panel).datagrid('selectRow', index);
	                	var curRow = $('#'+viewMdr.panel).datagrid('getSelected');
	                	if(curRow!=undefined){
	                		seturl(curRow.testOrderNo,curRow.pathogenSn);
	                	}
	                },
	                onClickRow:function(rowIndex, rowData){	                
	                	seturl(rowData.testOrderNo,rowData.pathogenSn);
			    	},
			    	toolbar:'#tb'
		        });
		    },
		    check :  function (resPropName,orderno,surveyDeptId,dt){
		    	 Comm.dialogGlobal({
			        	url:"${webroot}/xn011Dclymx/f_view/mdrCheck.shtml?orderno=" + orderno + "&surveyDeptId=" + surveyDeptId + "&dt="+dt.substring(0,10)+ "&resPropName=" + encodeURI(encodeURI(resPropName)),
			            title: "多耐类型审核确认",
			            width:400,
			            hight:500,
			            parent:this
			     });
		    	
		    },
			//添加Tooltip
			addTooltip : function(divId, zyid, testOrderNo, orderno, surveyDeptId, normItemId, normOrderno) {
				$('#' + divId).tooltip({
	                content: $('<div></div>'),
	                showEvent: 'click',
	                onUpdate: function(content){
	                    content.panel({
	                    	width: 250,
	                    	border: false,
	                        href: '${webroot}/xn011Dclymx/f_view/mdr/type/list.shtml?zyid=' + zyid+'&testOrderNo=' + testOrderNo+'&orderno=' + orderno+'&surveyDeptId=' + surveyDeptId+'&normItemId=' + normItemId+'&normOrderno=' + normOrderno+'&divId=' + divId+''
	                    });
	                },
	                onShow: function(){
	                    var t = $(this);
	                    t.tooltip('tip').unbind().bind('mouseenter', function(){
	                        t.tooltip('show');
	                    }).bind('mouseleave', function(){
	                        t.tooltip('hide');
	                    });
	                }
	            });
			},
			addOdTooltip:function(divId, dt,zyid,surveyDeptId,orderno, testOrderNo,pathoCode){
				$('#' + divId).tooltip({
	                content: $('<div></div>'),
	                showEvent: 'click',
	                onUpdate: function(content){
	                    content.panel({
	                    	width: 300,
	                    	border: false,
	                        href: '${webroot}/xn011Dclymx/f_view/mdr/type/optsDetail.shtml?zyid=' + zyid+'&dt=' + dt+'&orderno=' + orderno+'&surveyDeptId=' + surveyDeptId+'&pathoCode='+pathoCode+'&testOrderNo='+testOrderNo
	                    });
	                },
	                onShow: function(){
	                    var t = $(this);
	                    t.tooltip('tip').unbind().bind('mouseenter', function(){
	                        t.tooltip('show');
	                    }).bind('mouseleave', function(){
	                        t.tooltip('hide');
	                    });
	                }
	            });
		    },
			//添加Tooltip
			addOpts: function(divId, deptId, defContent, testOrderNo,dt,zyid,surveyDeptId,orderno,infectTypeId,normItemId,normOrderno,resprop) {
				var tmpDiv = '<ul class="down_li">';
				tmpDiv += '<li><a href="#" onclick="javascript:sendMessage(\''+zyid+'\',\''+divId+'\',\''+deptId+'\',\''+defContent+'\',\''+testOrderNo+'\')">干预</a></li>';
				if(infectTypeId!=2){
					tmpDiv += '<li><a href="#" onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',\''+dt+'\',\''+zyid+'\',\''+surveyDeptId+'\',\''+orderno+'\',\'1\',\'院感\',\''+normItemId+'\',\''+normOrderno+'\',\''+resprop+'\',\''+defContent+'\');">院感</a></li>';
				}
				if(infectTypeId!=1){
					tmpDiv += '<li><a href="#" onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',\''+dt+'\',\''+zyid+'\',\''+surveyDeptId+'\',\''+orderno+'\',\'2\',\'社感\',\''+normItemId+'\',\''+normOrderno+'\',\''+resprop+'\',\''+defContent+'\');">社感</a></li>';
				}
				if(!infectTypeId || infectTypeId>2){
					tmpDiv += '<li><a href="#" onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',\''+dt+'\',\''+zyid+'\',\''+surveyDeptId+'\',\''+orderno+'\',\'3\',\'定植\',\''+normItemId+'\',\''+normOrderno+'\',\''+resprop+'\',\''+defContent+'\');">定植</a></li>';
					tmpDiv += '<li><a href="#" onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',\''+dt+'\',\''+zyid+'\',\''+surveyDeptId+'\',\''+orderno+'\',\'4\',\'污染\',\''+normItemId+'\',\''+normOrderno+'\',\''+resprop+'\',\''+defContent+'\');">污染</a></li>';
					tmpDiv += '<li><a href="#" onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',\''+dt+'\',\''+zyid+'\',\''+surveyDeptId+'\',\''+orderno+'\',\'5\',\'不确定\',\''+normItemId+'\',\''+normOrderno+'\',\''+resprop+'\',\''+defContent+'\');">不确定</a></li>';
				}
				if( infectTypeId ){
					tmpDiv += '<li><a href="#" onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',\''+dt+'\',\''+zyid+'\',\''+surveyDeptId+'\',\''+orderno+'\',\'0\',\'撤销\',\''+normItemId+'\',\''+normOrderno+'\');">撤销</a></li>';
				}
				tmpDiv += '</ul>';
				
				//tmpDiv += '<li><a href="#" onclick="javascript:sendMessage(\''+divId+'\',\''+deptId+'\',\''+defContent+'\')">干预</a></li>';
				//tmpDiv += '<li><a href="#" '+(infectTypeId==2?"disabled=disabled":"") +' onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',1,\'院感\');">院感</a></li>';
				//tmpDiv += '<li><a href="#" '+(infectTypeId==1?"disabled=disabled":"") +' onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',2,\'社感\');">社感</a></li>';
				//tmpDiv += '<li><a href="#" '+(infectTypeId<=2?"disabled=disabled":"") +' onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',3,\'定值\');">定值</a></li>';
				//tmpDiv += '<li><a href="#" '+(infectTypeId<=2?"disabled=disabled":"") +' onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',4,\'污染\');">污染</a></li>';
				
				$('#' + divId).tooltip({
	                content: $(tmpDiv),
	                showEvent: 'click',
	                onShow: function(){
	                    var t = $(this);
	                    t.tooltip('tip').unbind().bind('mouseenter', function(){
	                        t.tooltip('show');
	                    }).bind('mouseleave', function(){
	                        t.tooltip('hide');
	                    });
	                }
	            });
			},
		    exportExcelMdr:function (){
		    	autoTip.clear();
/* 		    	var resProp = $('#resProp').val();
				if(resProp!=null){
					resProp=resProp.join(",");
				} */
				var specDescribes = $('#specDescribes').combogrid('getValue');
				/* if(specDescribes!=null){
					specDescribes=specDescribes.join(",");
				} */
				var infectTypeId = $('#infectTypeId').combogrid('getValue');
				/* if(infectTypeId!=null){
					infectTypeId=infectTypeId.join(",");
				} */
				var esbl = '';
	    		$("input:checkbox[name='esbl']:checked").each(function(){ 
	    			esbl += $(this).val() + ',';
	   			});
	    		var gl = '';
	    		$("input:checkbox[name='gl']:checked").each(function(){ 
	    			gl += $(this).val() + ',';
	   			});
	    		var gx = '';
	    		$("input:checkbox[name='gx']:checked").each(function(){ 
	    			gx += $(this).val() + ',';
	   			});
				var patientState=$('#patientState').val();
            	var dateType=$('#dateType').combogrid('getValue');
                var startDate= $('#startDate').val();
                var endDate= $('#endDate').val();
                var deptType = $('#deptType').combogrid('getValue');
                var deptId = $('#deptId').combogrid('getValue');
                var isAudited = $('#isAudited').val();
                var key = $("#key").combogrid('getValue');
                var searchString = encodeURIComponent(encodeURIComponent($('#searchString').val()));
                var qc = $("input[name='qc']:checked").val();
                var jszd = $('#jszd').combogrid('getValue');
                var rsId= $('#rsId').combogrid('getValue');
                var lispathoName = $("#lispathoName").val();
                var dnlx = $("#dnlx").combobox("getValues").toString();
/*                 var byt = $('#byt').combogrid('getValue');
                var kjyw = $('#kjyw').combogrid('getValue'); */
				var url = "${webroot}/xn011Dclymx/f_json/exportExcelMdr.shtml?key="+key+"&specDescribes="+specDescribes+"&infectTypeId="+infectTypeId+"&esbl="+esbl+"&patientState="+patientState+"&dateType="
						+dateType+"&startDate="+startDate+"&endDate="+endDate+"&deptType="+deptType+"&deptId="+deptId+"&isAudited="+isAudited+"&searchString="+searchString+"&qc="+qc+"&jszd="+jszd+"&rsId="+rsId+"&gx="+gx+"&gl="+gl+"&lispathoName="+lispathoName+'&dnlx='+dnlx;
		    //	window.location.href = url;
		    
		    var form=$("<form>");//定义一个form表单
			form.attr("style","display:none");
			form.attr("target","");
			form.attr("method","post");
			form.attr("action","${webroot}/xn011Dclymx/f_json/exportExcelMdr.shtml");
			var input1=$("<input>").attr("type","hidden").attr("name","key").attr("value",key);
			var input2=$("<input>").attr("type","hidden").attr("name","specDescribes").attr("value",specDescribes);
			var input3=$("<input>").attr("type","hidden").attr("name","infectTypeId").attr("value",infectTypeId);
			var input4=$("<input>").attr("type","hidden").attr("name","esbl").attr("value",esbl);
			var input5=$("<input>").attr("type","hidden").attr("name","patientState").attr("value",patientState);
			var input6=$("<input>").attr("type","hidden").attr("name","dateType").attr("value",dateType);
			var input7=$("<input>").attr("type","hidden").attr("name","startDate").attr("value",startDate);
			var input8=$("<input>").attr("type","hidden").attr("name","endDate").attr("value",endDate);
			var input9=$("<input>").attr("type","hidden").attr("name","deptType").attr("value",deptType);
			var input0=$("<input>").attr("type","hidden").attr("name","deptId").attr("value",deptId);
			var input10=$("<input>").attr("type","hidden").attr("name","isAudited").attr("value",isAudited);
			var input11=$("<input>").attr("type","hidden").attr("name","searchString").attr("value",searchString);
			var input12=$("<input>").attr("type","hidden").attr("name","qc").attr("value",qc);
			var input13=$("<input>").attr("type","hidden").attr("name","jszd").attr("value",jszd);
			var input14=$("<input>").attr("type","hidden").attr("name","rsId").attr("value",rsId);
			var input15=$("<input>").attr("type","hidden").attr("name","gx").attr("value",gx);
			var input16=$("<input>").attr("type","hidden").attr("name","gl").attr("value",gl);
			var input17=$("<input>").attr("type","hidden").attr("name","lispathoName").attr("value",lispathoName);
			var input17=$("<input>").attr("type","hidden").attr("name","dnlx").attr("value",dnlx);
			$("body").append(form);//将表单放置在web中
			form.append(input1);form.append(input2);form.append(input3);form.append(input4);form.append(input0);
			form.append(input5);form.append(input6);form.append(input7);form.append(input8);form.append(input9);
			form.append(input10);form.append(input11);form.append(input12);form.append(input13);form.append(input14);
			form.append(input15);form.append(input16);form.append(input17);
			
			form.submit();//表单提交
		    
			},
			showMdr:function(testOrderNo,pathogenSn){
				 Comm.dialogGlobal({
			        	url:"${webroot}/xn011Dclymx/f_view/mdrDetail.shtml?testOrderNo="+testOrderNo+"&pathogenSn="+pathogenSn,
			            title: "多耐详情",
			            width:600,
			            height:360,
			            type:"iframe",
			            parent:this
			        });
		    },
		    showTsny:function(specDescribes){
		    	Comm.dialogGlobal({
		    		url:"${webroot}/xn011Dclymx/f_view/mdrTsny.shtml?specDescribes=" + specDescribes ,
		    		title:"特殊耐药",
		    		width:600,
		    		height:320,
		    		type:"iframe",
		    		parent:this
		    	});
		    },
		    getYzxx:function(zyid){
		    	 Comm.dialogGlobal({
			        	url:"${webroot}/st004Yzxxb/f_view/getYzxx.shtml?zyId="+zyid,
			            title: "医嘱信息",
			            width:600,
			            height:400,
			            type:"iframe",
			            parent:this
			        });
		    },
			showDetail:function(zyid){
		    	parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?zyid='+zyid+"&tab=2",true);
		    },
		   
		    //选择感染类型
		    chooseInfectTypeId:function(infectTypeId,zyid,testOrderNo,orderno,surveyDeptId,normItemId,normOrderno){
		    	if(infectTypeId=='1'||infectTypeId=='2'){
		    		parent.menuInfo.clickMenu('报卡操作','/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&zyid='+zyid+"&infectTypeId="+infectTypeId+"&testOrderNo="+testOrderNo+"&normItemId="+normItemId+"&normOrderno="+normOrderno+"&surveyDeptId="+surveyDeptId+"&orderno="+orderno,true);    		
		    	}else{
		    		$.ajax({
	                    url: '${webroot}/xn011Dclymx/f_json/update.shtml',
	                    type: 'post',
	                    data: { zyid: zyid,normItemId:normItemId,normOrderno:normOrderno,testOrderNo:testOrderNo,orderno:orderno,surveyDeptId:surveyDeptId,infectTypeId:infectTypeId,audited:1},
	                    dataType: 'json',
	                    success : function(json) {
	                    	$.messager.show({title : '提示',msg : '操作成功！'});           
						}
	        		}); 
		    	}
		    },
		    //编辑
		    save: function(index, zyid,testOrderNo,orderno,surveyDeptId,normItemId,normOrderno) {
		    	var infectTypeId = $('input[name="infectTypeId'+index+'"]:checked ').val();
		    	var resProp = $('input[name="resProp'+index+'"]:checked ').val();
		    	var inteMode = $('input[name="inteMode'+index+'"]:checked ').val();
		    	 $.ajax({
                    url: '${webroot}/xn011Dclymx/f_json/update.shtml',
                    type: 'post',
                    data: { zyid: zyid,normItemId:normItemId,normOrderno:normOrderno,testOrderNo:testOrderNo,orderno:orderno,surveyDeptId:surveyDeptId,infectTypeId:infectTypeId,inteMode:inteMode,resProp:resProp,audited:1},
                    dataType: 'json',
                    success : function(json) {
                    	$.messager.show({title : '提示',msg : '操作成功！'});
                    	//$('#' + viewMdr.panel).datagrid('collapseRow', index);
                    	viewMdr.query(index);                 
					}
        		}); 
		    },
		    intervOpts:function(testOrderNo,dt,zyid,surveyDeptId,orderno,infectTypeId,infectTypeName,normItemId,normOrderno,resprop,defContent){
		    	if(!infectTypeId || !dt || !zyid || !surveyDeptId || !orderno){
		    		$.messager.show({title : '提示',msg : '必要参数获取失败！'});
		    	}else if(infectTypeId==0){
		    		$.messager.defaults = { ok: "确定", cancel: "取消" ,width:300};
		    		$.messager.confirm("操作提示", "确认撤销感染类型 ？", function (data) {  
		                if (data) {
		                	$.ajax({
		                    	url: '${webroot}/xn011Dclymx/f_json/updateGrlx.shtml',
		                        type: 'POST',
		                        data: { 
		                        	dt: dt,
		                        	zyid:zyid,
		                        	surveyDeptId:surveyDeptId,
		                        	orderno:orderno,
		                        	infectTypeId:infectTypeId,
		                        	infectTypeName:infectTypeName 
								},
		                        dataType: 'json',
		                        success : function(json) {
		                        	//json = eval("("+json+")");
		                        	$.messager.show({title : '提示',msg : json.msg});
		                        	viewMdr.query();
		    					}
		                    });
		                }
		    		});
		    	}else if(infectTypeId>2){
		    		//不是院感、社感
		    		$.messager.defaults = { ok: "确定", cancel: "取消" ,width:300};
		    		$.messager.confirm("操作提示", "确认此细菌为 "+infectTypeName+"？", function (data) {  
		                if (data) {  
		                    $.ajax({
		                    	url: '${webroot}/xn011Dclymx/f_json/updateGrlx.shtml',
		                        type: 'POST',
		                        data: { 
		                        	dt: dt,
		                        	zyid:zyid,
		                        	surveyDeptId:surveyDeptId,
		                        	orderno:orderno,
		                        	infectTypeId:infectTypeId,
		                        	infectTypeName:infectTypeName,
		                        	remindClinic:'YES',
		                        	messageToClinic:defContent
								},
		                        dataType: 'json',
		                        success : function(json) {
		                        	//json = eval("("+json+")");
		                        	$.messager.show({title : '提示',msg : json.msg});
		                        	viewMdr.query();
		    					}
		                    });
		                }  
		            });  
		    	}else if(infectTypeId<=2){
		    		//院感、社感
		    		$.messager.defaults = { ok: "标记为"+infectTypeName+"致病菌,并提醒临床", cancel: "代报致病菌"+infectTypeName+"报卡" ,width:400};
		    		$.messager.confirm("操作提示", "确认此细菌为 "+infectTypeName+"致病菌？", function (data) {  
		    			if (data) {
		    				$.ajax({
		                    	url: '${webroot}/xn011Dclymx/f_json/updateGrlx.shtml',
		                        type: 'POST',
		                        data: { 
		                        	dt: dt,
		                        	zyid:zyid,
		                        	surveyDeptId:surveyDeptId,
		                        	orderno:orderno,
		                        	infectTypeId:infectTypeId,
		                        	infectTypeName:infectTypeName,
		                        	testOrderNo:testOrderNo,
		                        	remindClinic:"YES",
		                        	messageToClinic:defContent
								},
		                        dataType: 'json',
		                        success : function(json) {
		                        	//json = eval("("+json+")");
		                        	$.messager.show({title : '提示',msg : json.msg});
		                        	viewMdr.query();
		    					}
		                    });
		                }else{
		                	bk(zyid,surveyDeptId,orderno,infectTypeId,normItemId,normOrderno,testOrderNo,resprop);
		                }
		            });  
		    	}
		    }
		};
		$(document).ready(function () {
			//$("#specDescribes").next(".combo").hide(); 
			Csm.combogrid.dep({
				//【必传】控件名称
				id: 'deptId',
				hospId:'${unitId}',
				value : '${deptId}',
				ifcaseoffice: '1'
			});
			Csm.combogrid.jszd({
				//【必传】控件名称
				id: 'jszd'
			});
			Csm.combogrid.byt({
				//【必传】控件名称
				id: 'byt'
			});
			Csm.combogrid.kjyw({
				//【必传】控件名称
				id: 'kjyw'
			});
			Csm.select.load({
				id: 'resProp',
				url: webroot + '/sysDict/f_json/getValue.shtml',
				data: {unitId: '${unitId}', dictTypeCode: 'res_prop_code'},
				headerKey: '',
				headerValue: '请选择多耐类型',
				//是否可以多选
				multiple: true,
				value: '${resProp}',
				kcode: 'dictCode',
				kvalue: 'dictName'
			});
			$('#dnlx').combobox({
			    url:'${webroot}/sysDict/f_json/getValue.shtml?dictTypeCode=res_prop_code',
			    valueField:'dictCode',
			    textField:'dictName',
				value: '${resProp}',
			    width:120,
			    multiple:true
			});
			$("#key").combobox({
				onChange: function (n,o) {
					if($("#key").combogrid('getValue')=='z'){
						$("#zdjrow").show();
						$("#specDescribes").next(".combo").show();
					}else{
						$("#zdjrow").hide();
						//$("#specDescribes").next(".combo").hide();
						//$("#lispathoName").show();
					}
				}
			})
			window.setTimeout(function() {
				viewMdr.load();
			}, 500);
		});
		function sendMessage(zyid,divId,deptId,defContent,testOrderNo){
	     	$("#"+divId).tooltip('hide');
			//.replace(/\+/g,"%2B") 转换特殊字符 + 
	     	Comm.dialogGlobal({
		    	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?zyid="+zyid+"&deptId="+deptId+"&msgType=36&testOrderNo="+testOrderNo+"&defContent="+encodeURI(encodeURI(defContent).replace(/\+/g,"%2B")),
		        title: "干预会话",
		        width:870,
		        height:555,
		        type:"iframe"
		    });
		}
		function bk(zyid,surveyDeptId,orderno,infectTypeId,normItemId,normOrderno,testOrderNo,resprop){
			parent.menuInfo.clickMenu('报卡操作','/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&zyid='+zyid+'&infectTypeId='+infectTypeId+'&normItemId='+normItemId+'&normOrderno='+normOrderno+'&testOrderNo='+testOrderNo+'&orderno='+orderno+'&surveyDeptId='+surveyDeptId+'&resprop='+resprop,true);
		}
		</script>
	</body>
</html>
