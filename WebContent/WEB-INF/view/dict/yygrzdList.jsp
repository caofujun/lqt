<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>医院感染诊断维护</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>

	<body>
		<div id="yygrzdPanel"></div>
		<div id="tb" class="m_search">
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="yygrzd.edit('','新增医院感染诊断')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
			</div>			
		</div>

		<script type="text/javascript">
			var yygrzd = {
				panel : 'yygrzdPanel',
			    //选择
			    getSelected : function() {
			    	var curRow = $('#'+yygrzd.panel).datagrid('getSelected');
					var parentObject = parent.Comm.getObjectCache();
					parent.Comm.dialogClose('${param.dialogId}');
					parentObject.getYygrzd(curRow.infectCode,curRow.infectName);
			    },
				query : function() {
					$('#'+yygrzd.panel).treegrid({
		                url:'${webroot}/yygrzd/f_json/findYygrzdList.shtml',
					});
				},
			    //编辑
			    edit:function(id, title) {
			    	if(id===undefined) id = '';
			        Comm.dialogGlobal({
			        	url:"${webroot}/yygrzd/f_view/toedit.shtml?id=" + id,
			            title: title,
			            width:550,
			            parent:this
			        });
			    },
			    //启用/停用
			    isEnable : function(infectCode, infectName, flag) {
			    	var operat = (flag == 1 ? '停用' : '启用');
			    	$.messager.confirm('提示', '确认' + operat + '【' + infectName + '】?', function (r) {
			        	if (r) {
			            	$.ajax({
			                    url: '${webroot}/yygrzd/f_json/updFlag.shtml',
			                    type: 'post',
			                    data: { infectCode: infectCode, flag : flag },
			                    dataType: 'json',
			                    success : function(json) {
									if(json.result==='success') {
										$('#' + yygrzd.panel).treegrid('update',{
											id: infectCode,
											row: {
												flag : (flag == 1 ? 0 : 1)
											}
										});
				                        $.messager.show({ title: '提示', msg: operat + '成功！' });
					    			} else if(json.result === 'error') {
					    				$.messager.show({ title: '提示', msg: json.msg });
					    			}
								}
			            	});
			        	}
			    	});
			    }
			};
			$(document).ready(function () {
				var _columns = [];
				_columns.push({field:'infectName',title:'诊断名称',sortable:true,width:280});
				_columns.push({field:'infectCode',title:'编码',sortable:true,width:100});
				_columns.push({field:'orderIndex',title:'排序号',sortable:true,width:80});
				_columns.push({field:'flag',title:'启用标识',sortable:true,width:80,align:'center',
	           		formatter:function(value,rec){
	           			return (rec.flag == 1 ? '启用' : '停用');
	           		}
				});
                _columns.push({field:'_operate',title:'操作',width:70,
					formatter:function(value,rec){
						return ['<a href="javascript:yygrzd.edit(\'',$.trim(rec.infectCode),'\')" class="ico_editor" title="修改"></a>',
						        '<a href="javascript:yygrzd.isEnable(\'' + $.trim(rec.infectCode) + '\', \'' + rec.infectName + '\', ' + rec.flag + ')" class="' + (rec.flag == 1 ? 'ico_no_select' : 'ico_select') + '" title="' + (rec.flag == 1 ? '停用' : '启用') + '"></a>'].join('');
					}
				});
				$('#'+yygrzd.panel).treegrid({
	                nowrap: true,
	                autoRowHeight: true,
	                striped: true,
	                fitColumns: true,
	                fit: true,
	                collapsible:true,
	                url:'${webroot}/yygrzd/f_json/findYygrzdList.shtml',
	                remoteSort: false,
	                singleSelect: true,
	                idField:'infectCode',
	                treeField:'infectName',
	                lines: true,
	                border:false,
	                columns:[
	                	_columns
	                ]
			    	,onDblClickRow:function(row){
			    		yygrzd.getSelected();
			        },
	                toolbar:'#tb',
	                onLoadSuccess: function (row, data) {
	                	$.each(data, function (i, val) {
	                		$('#'+yygrzd.panel).treegrid('collapseAll', data[i].id);
	                	});
	                }
	            });
				
			});
	
		</script>
	</body>
</html>