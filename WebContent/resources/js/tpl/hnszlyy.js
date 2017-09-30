/**
 * 湖南省肿瘤医院模板
 */
var template = {};
/*EGFR Exon18外显子突变检测*/
template.exon18 = {
		enter : function(){
			return ['<div class="dg_panel1"><table class="table_zl" width="100%"><tbody><tr><td><table class="dg_table2" cellpadding="0" cellspacing="0" width="100%" border="0"><tbody><tr><th width="40%" colspan="2">突变名称</th><th width="20%">氨基酸变化</th><th width="20%">碱基序列变化</th><th width="20%" class="last">检测结果</th></tr><tr><td rowspan="4">外显子18突变</td><td>无</td><td>无</td><td>无</td><td class="last"><span id="KJ_10054_wxz18tb"></span></td></tr><tr><td>外显子18突变1</td><td>G7198S</td><td>2155G&gt;A</td><td class="last"><span id="KJ_10054_wxz18tb1"></span></td></tr><tr><td>外显子18突变2</td><td>G719C</td><td>2155G&gt;T</td><td class="last"><span id="KJ_10054_wxz18tb2"></span></td></tr><tr><td>外显子18突变3</td><td>G719A</td><td>2156G&gt;C</td><td class="last"><span id="KJ_10054_wxz18tb3"></span></td></tr></tbody></table></td></tr></tbody></table></div>'].join('');
		}
};


/*医疗基本资料(10008)*/
template.yljbzl = {
		enter : function(){
			return ['<div class="dg_panel1"><table class="table_zl">	<tr>		<td width="15%" class="text_right">住院次数：</td>		<td colspan="5"><span id="KJ_10008_zycs"></span>次</td>	</tr>	<tr>		<td class="text_right">住院日期：</td>		<td><span id="KJ_10008_ryrq" class="data-fn" data-fn-type="blur" data-fn="template.yljbzl.getCount()"></span>		<td class="text_right" width="10%">出院日期：</td>		<td><span id="KJ_10008_cyrq" class="data-fn" data-fn-type="blur" data-fn="template.yljbzl.getCount()"></span></td>		<td class="text_right" width="10%">在院天数：</td>		<td><span id="KJ_10008_zyts" class="data-readonly"></span>天</td>			</tr>	<tr>		<td class="text_right">分期类型：</td>		<td colspan="5"><span id="KJ_10008_fqlx"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span id="KJ_10008_fq"></span></td>		</tr>		<tr>			<td class="text_right" >T：</td><td class="text_left" colspan="5"><span id="KJ_10008_t1"></span>N：<span id="KJ_10008_n1"></span>M：<span id="KJ_10008_m1" class="data-fn" data-fn-type="change" data-fn="template.yljbzl.checkM()"></span><span id="KJ_10008_mdy1" class="data-hide"></span></td>		</tr>		<tr>			<td class="text_right">病理类型：</td>			<td colspan="5"><span id="KJ_10008_bxlx"></span></td>		</tr>		<tr>			<td class="text_right">分化程度：</td>			<td colspan="5"><span id="KJ_10008_fhcd"></span></td>			</tr>		<tr>			<td class="text_right">活检方式：</td>			<td colspan="5"><span id="KJ_10008_hjfs"></span></td>			</tr>		<tr>			<td class="text_right">活检部位：</td>			<td colspan="5"><span id="KJ_10008_hjbw"></span></td>			</tr>		<tr>			<td class="text_right">细胞学涂片号：</td>			<td colspan="5"><span id="KJ_10008_xbxh"></span></td>			</tr>		<tr>			<td class="text_right">细胞学获取部位：</td>			<td colspan="5"><span id="KJ_10008_xbxhqbw"></span></td>			</tr>		<tr><td colspan="6" class="title_line"><div class="line2_title">血标本记录情况</div></td></tr>				<tr >			<td class="text_right">取血：</td>			<td colspan="5"><span id="KJ_10008_qcs"></span>次</td>		</tr>			<td class="text_right">血标本编号：</td>			<td colspan="5"><span id="KJ_10008_xbbbh"></span></td>		</tr>				<tr>			<td class="text_right">存放位置：</td>			<td colspan="5"><span id="KJ_10008_cfwz"></span></td>		</tr>				<tr>			<td class="text_right">TR活性：</td>			<td colspan="5"><span id="KJ_10008_hx"></span></td>		</tr>		<tr><td colspan="6" class="title_line"><div class="line2_title">组织病理标本记录情况</div></td></tr>		<tr>			<td class="text_right">取样时间：</td>			<td colspan="5"><span id="KJ_10008_xxbb"></span></td>		</tr>		<tr>			<td class="text_right">标本来源：</td>			<td colspan="5"><span id="KJ_10008_bbdx"></span></td>		</tr>		<tr>			<td class="text_right">取组织标本次数：</td>			<td colspan="5"><span id="KJ_10008_qzzbbcs"></span>次</td>		</tr>		<tr>			<td class="text_right">组织标本编号：</td>			<td colspan="5"><span id="KJ_10008_zzbbbh"></span></td>		</tr>		<tr>			<td class="text_right">病理号：</td>			<td colspan="5"><span id="KJ_10008_blh"></span></td>		</tr>		<tr>			<td class="text_right">蜡块编号：</td>			<td colspan="5"><span id="KJ_10008_lkbh"></span></td>			</tr>				<tr>			<td class="text_right">备注：</td>			<td colspan="5"><span id="KJ_10008_qy"></span></td>			</tr></table></div>'].join('');
		},
		//控制文本框显示
		checkM : function(){
			var Mvalue=$("select[name='KJ_10008_sel_m1_100087']").val();
			if(Mvalue=='1a' || Mvalue=='1b'){
				$("input[name='KJ_10008_inp_mdy1_100088']").show();
			}else{
				$("input[name='KJ_10008_inp_mdy1_100088']").hide();
			}
		},
		//计算天数
		getCount:function(){
			setTimeout(function() {
			    var ryrq = $("input[name='KJ_10008_inp_ryrq_100080']").val();
				var cyrq = $("input[name='KJ_10008_inp_cyrq_100081']").val();
				var reslut="";
				if(ryrq != '' && cyrq!=''){
					var arr1=ryrq.split('-');
				 	var arr2=cyrq.split('-');
				 	var d1=new Date(arr1[0],arr1[1]-1,arr1[2]);
				 	var d2=new Date(arr2[0],arr2[1]-1,arr2[2]);
				 	reslut=(d2.getTime()-d1.getTime())/(1000*3600*24);
				}
				$("input[name='KJ_10008_inp_zyts_100082']").val(reslut);
			}, 500);
		}
};
/*治疗基本情况(10009)*/
template.zljbqk = {
		enter : function(){
			return ['<div class="dg_panel1"><ul><li><div class="fl w120 mt5">此次入院治疗方式：</div><ul class="line3"><li><span id="KJ_10009_ccryzlfs"></span></li></ul><div class="clear"></div></li><li>详情：<span id="KJ_10009_xq"></span></li><li>方案：<span id="KJ_10009_fa"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;周期：<span id="KJ_10009_zq"></span></li><li>治疗开始时间：<span id="KJ_10009_zlkssj"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;治疗结束时间：<span id="KJ_10009_zljssj"></span></li><li class="title_line"><div class="line2_title"><span>用药及剂量</span></div></li><li><table class="dg_table2"><tbody><tr><th>药品</th><th>常用剂量</th><th>标准剂量</th><th>实际剂量</th><th>单位</th><th class="last">频率</th></tr><tr><td><span id="KJ_10009_yp1"></span></td><td><span id="KJ_10009_cyjl1" class="data-fn" data-fn-type="keyup" data-fn="template.zljbqk.computesjjl()"></span></td><td><span id="KJ_10009_bzjl1"></span></td><td><span id="KJ_10009_sjjl1"></span></td><td><span id="KJ_10009_dw1"></span></td><td class="last"><span id="KJ_10009_pl1"></span></td></tr><tr><td><span id="KJ_10009_yp2"></span></td><td><span id="KJ_10009_cyjl2" class="data-fn" data-fn-type="keyup" data-fn="template.zljbqk.computesjjl()"></span></td><td><span id="KJ_10009_bzjl2"></span></td><td><span id="KJ_10009_sjjl2"></span></td><td><span id="KJ_10009_dw2"></span></td><td class="last"><span id="KJ_10009_pl2"></span></td></tr><tr><td><span id="KJ_10009_yp3"></span></td><td><span id="KJ_10009_cyjl3" class="data-fn" data-fn-type="keyup" data-fn="template.zljbqk.computesjjl()"></span></td><td><span id="KJ_10009_bzjl3"></span></td><td><span id="KJ_10009_sjjl3"></span></td><td><span id="KJ_10009_dw3"></span></td><td class="last"><span id="KJ_10009_pl3"></span></td></tr><tr><td><span id="KJ_10009_yp4"></span></td><td><span id="KJ_10009_cyjl4" class="data-fn" data-fn-type="keyup" data-fn="template.zljbqk.computesjjl()"></span></td><td><span id="KJ_10009_bzjl4"></span></td><td><span id="KJ_10009_sjjl4"></span></td><td><span id="KJ_10009_dw4"></span></td><td class="last"><span id="KJ_10009_pl4"></span></td></tr></tbody></table></li></ul></div>'].join('');
		},
		computesjjl: function(){
			var exist = $("#10028_tbmj").length;
			if (exist < 1) {
				$.messager.show({ title: '提示', msg: '身高体重未录入,无法自动计算标准剂量!' });
				return;
			}
			var tbmj = $("#10028_tbmj").val();
			tbmj = $.trim(tbmj);
			var obj = template.obj;
			if(!obj) return;
			var cyjl = $(obj).find('input').val();
			var theValue = '';
			if(tbmj != '' && cyjl!='' && !isNaN(tbmj) && !isNaN(cyjl)){
				theValue = cyjl*tbmj;
				$(obj).parent().next().find('input').val(theValue.toFixed(2));
			}
		}
};
/*治疗前后疗效总结(10010)*/
template.zlqhlxzj = {
		enter : function(){
			return ['<div class="dg_panel1">		<ul>			<li class="title_line"><div class="line2_title"><span>病灶比较(RICIST法)</span></div></li>			<li>				<table class="dg_table2" width="100%">				<tr>					<th width="25%">部位</th>					<th width="25%">治疗前</th>					<th width="25%">治疗后</th>									<th width="25%" class="last">比较值</th>				</tr>				<tr>					<td><span id="KJ_10010_bw1"/></td>					<td><span id="KJ_10010_zlq1" class="data-fn zl_zlq" data-fn-type="keyup" data-fn="template.zlqhlxzj.sumzlq()"/></td>					<td><span id="KJ_10010_zlh1" class="data-fn zl_zlh" data-fn-type="keyup" data-fn="template.zlqhlxzj.sumzlh()"/></td>							<td rowspan="6" class="last" ><span id="KJ_10010_bjz" class="data-readonly"/></td>								</tr>			  				<tr>					<td><span id="KJ_10010_bw2"/></td>					<td><span id="KJ_10010_zlq2" class="data-fn zl_zlq" data-fn-type="keyup" data-fn="template.zlqhlxzj.sumzlq()"/></td>					<td><span id="KJ_10010_zlh2" class="data-fn zl_zlh" data-fn-type="keyup" data-fn="template.zlqhlxzj.sumzlh()"/></td>								</tr>				<tr>					<td><span id="KJ_10010_bw3"/></td>					<td><span id="KJ_10010_zlq3" class="data-fn zl_zlq" data-fn-type="keyup" data-fn="template.zlqhlxzj.sumzlq()"/></td>					<td><span id="KJ_10010_zlh3" class="data-fn zl_zlh" data-fn-type="keyup" data-fn="template.zlqhlxzj.sumzlh()"/></td>									</tr>				<tr>					<td><span id="KJ_10010_bw4"/></td>					<td><span id="KJ_10010_zlq4" class="data-fn zl_zlq" data-fn-type="keyup" data-fn="template.zlqhlxzj.sumzlq()"/></td>					<td><span id="KJ_10010_zlh4" class="data-fn zl_zlh" data-fn-type="keyup" data-fn="template.zlqhlxzj.sumzlh()"/></td>									</tr>				<tr>					<td><span id="KJ_10010_bw5"/></td>					<td><span id="KJ_10010_zlq5" class="data-fn zl_zlq" data-fn-type="keyup" data-fn="template.zlqhlxzj.sumzlq()"/></td>					<td><span id="KJ_10010_zlh5" class="data-fn zl_zlh" data-fn-type="keyup" data-fn="template.zlqhlxzj.sumzlh()"/></td>											</tr>				<tr>					<td>合计</td>					<td><span id="KJ_10010_zlqxj" class="data-readonly"/></td>					<td><span id="KJ_10010_zlhxj" class="data-readonly"/></td>								</tr>				</table>						</li>			<li class="title_line"><div class="line2_title"><span>治疗前后疗效总结</span></div></li>			<li>治疗前后不可测量但可评价病灶变化：<span id="KJ_10010_zlqhbk"/>				<span id="KJ_10010_bh1"/>			</li>			<li>治疗前后主要症状变化：<span id="KJ_10010_zlqhz1"/>				<span id="KJ_10010_bh2"/>			</li>			<li>治疗前PS状态：				<span id="KJ_10010_zlqzt"/>			</li>			<li>治疗后PS状态：				<span id="KJ_10010_zlhzt"/>			</li>			<li>PS状态：				<span id="KJ_10010_pszt"/>			</li>					<li>疗效评价：				<span id="KJ_10010_lxpj" class="data-fn" data-fn-type="change" data-fn="template.zlqhlxzj.hide()" init-mehod="template.zlqhlxzj.init()"/>			</li>	<li>有无空洞：<span id="KJ_10010_ywkd"/></li><li>有无坏死：<span id="KJ_10010_ywhs"/></li><li>有无缩小：<span id="KJ_10010_ywsx"/></li><li>有无增大：<span id="KJ_10010_ywzd" /></li>		<li><span style="display:block; float:left;">备注：</span><span id="KJ_10010_bz"/></li>		</ul>	</div>'].join('');
		},
		//小计
		sumzlq : function(){
			var reslut=0;
			$(".zl_zlq").each(function(){
				var theValue = $(this).find("input").val();
				if(theValue!='' && !isNaN(theValue)){
					reslut=parseFloat(reslut) + parseFloat(theValue);
				}
			});
			
			$("input[name='KJ_10010_inp_zlqxj_100120']").val(reslut);
			
			var hxj=$("input[name='KJ_10010_inp_zlhxj_100121']").val();
			
			if(hxj!==''&& parseFloat(hxj)!==0){
				var bfb =(parseFloat(hxj)/parseFloat(reslut))*100;
				$("input[name='KJ_10010_inp_bjz_100122']").val(bfb.toFixed(2));
			}else{
				$("input[name='KJ_10010_inp_bjz_100122']").val("");
			}
			if(reslut<=0){
				$("input[name='KJ_10010_inp_zlqxj_100120']").val("");
			}
		},
		//小计
		sumzlh : function(){
			var reslut=0;
			$(".zl_zlh").each(function(){
				var theValue = $(this).find("input").val();
				if(theValue!='' && !isNaN(theValue)){
					reslut=parseFloat(reslut) + parseFloat(theValue);
				}
			});
			$("input[name='KJ_10010_inp_zlhxj_100121']").val(reslut);
			
			var qxj=$("input[name='KJ_10010_inp_zlqxj_100120']").val();
			
			if(qxj!==''&& parseFloat(qxj)!==0){
				var bfb =(parseFloat(reslut)/parseFloat(qxj))*100;
				$("input[name='KJ_10010_inp_bjz_100122']").val(bfb.toFixed(2));
			}else{
				$("input[name='KJ_10010_inp_bjz_100122']").val("");
			}
			if(reslut<=0){
				$("input[name='KJ_10010_inp_zlhxj_100121']").val("");
			}
		},
		hide : function(){					
			var selectVal = $("select[name='KJ_10010_sel_lxpj_100130']").val();
			if(selectVal && selectVal=='SD'){
				$('#KJ_10010_ywkd').parent().show();
				$('#KJ_10010_ywhs').parent().show();
				$('#KJ_10010_ywsx').parent().show();
				$('#KJ_10010_ywzd').parent().show();
			}else{
				$('#KJ_10010_ywkd').parent().hide();
				$('#KJ_10010_ywhs').parent().hide();
				$('#KJ_10010_ywsx').parent().hide();
				$('#KJ_10010_ywzd').parent().hide();	
			}
		},
		init:function(){
			var selectVal = $("select[name='KJ_10010_sel_lxpj_100130']").val();
			if(selectVal && selectVal=='SD'){
				$('#KJ_10010_ywkd').parent().show();
				$('#KJ_10010_ywhs').parent().show();
				$('#KJ_10010_ywsx').parent().show();
				$('#KJ_10010_ywzd').parent().show();
			}else{
				$('#KJ_10010_ywkd').parent().hide();
				$('#KJ_10010_ywhs').parent().hide();
				$('#KJ_10010_ywsx').parent().hide();
				$('#KJ_10010_ywzd').parent().hide();	
			}
		}
};
/*肺手术记录(10020)*/
template.fssjl = {
		enter : function(){
			return ['<div class="dg_panel1" style="margin:0 auto;">	<table class="table_zl" width="95%">	<tr><td colspan="4" class="title_line"><div class="line2_title">手术中基本信息</div></td></tr>	<tr><td colspan="2"></td></tr>	<tr>		<td width="100" class="text_right">手术日期：</td><td colspan="3"><span id="KJ_10020_ssrq"></span></td>	</tr>	<tr>						<td class="text_right">手术开始时间：</td><td><span id="KJ_10020_sskss"></span>时<span id="KJ_10020_ssksf"></span>分</td>						<td class="text_right" width="100">手术结束时间：</td><td><span id="KJ_10020_ssjss"></span>时<span id="KJ_10020_ssjsf"></span>分</td>	</tr>	<tr>						<td class="text_right">术前诊断：</td><td><span id="KJ_10020_sqzd"></span></td>		<td class="text_right">术中诊断：</td><td><span id="KJ_10020_szzd"></span></td>					</tr>	<tr>		<td class="text_right">术前新辅助治疗：</td><td colspan="3"><span id="KJ_10020_sqxfzzl"></span></td>			</tr>	<tr>		<td class="text_right">新辅助治疗方案：</td><td colspan="3"><span id="KJ_10020_xfzzlfa"></span></td>	</tr>	<tr>		<td colspan="4" >新辅助治疗至手术间隔时间：<span id="KJ_10020_xfzzlz1"></span>天</td>					</tr>	<tr>						<td class="text_right">手术方式：</td><td colspan="3">		<span id="KJ_10020_ssfs" style="margin-right:30px;"></span>		第<span id="KJ_10020_djlgkx"></span>肋骨开胸</td>	</tr>	<tr>		<td class="text_right">切除范围：</td><td colspan="3">		<span id="KJ_10020_qcfw"></span>			</td>					</tr>	</table>	<table class="table_zl" width="95%"><tr><td width="90" ></td><td></td></tr>		<tr><td colspan="2" class="title_line"><div class="line2_title">术中所见</div></td></tr>		<tr><td colspan="2"></td></tr>		<tr>			<td class="text_right">胸腔粘连：</td>			<td>				<span id="KJ_10020_xqzl"></span>			</td>		</tr>		<tr class="bt_dotted">			<td class="text_right" style="vertical-align:text-top;">胸腔积液：</td>			<td>				<span id="KJ_10020_xqjy" class="data-fn" data-fn-type="click" data-fn="template.fssjl.hide()" init-mehod="template.fssjl.init()"></span>								<table class="no_margin" style="display:;" border="0" cellpadding="0" cellspacing="0"> 				<tr>					<td>积液量：<span id="KJ_10020_jyl1"></span>ml</td>					<td>颜色：<span id="KJ_10020_ys1"></span></td>				</tr>				<tr>					<td>细胞学检查：<span id="KJ_10020_xbxjc1"></span>					<td>细胞学结果：<span id="KJ_10020_xbxjg1"></span></td>				</tr>				</table>			</td>				</tr>		<tr class="bt_dotted">			<td class="text_right" style="vertical-align:text-top;">心包积液：</td>			<td>				<span id="KJ_10020_xbjy" class="data-fn" data-fn-type="click" data-fn="template.fssjl.hide()" init-mehod="template.fssjl.init()"></span>								<table class="no_margin" style="display:;" border="0" cellpadding="0" cellspacing="0"> 				<tr>					<td>积液量：<span id="KJ_10020_jyl2"></span>ml</td>					<td>颜色：<span id="KJ_10020_ys2"></span></td>				</tr>				<tr>					<td>细胞学检查：<span id="KJ_10020_xbxjc2"></span></td>					<td>细胞学结果：<span id="KJ_10020_xbxjg2"></span></td>				</tr>				</table>				</td>				</tr>				<tr class="bt_dotted">			<td rowspan="8" class="text_right" style=" vertical-align:text-top;">原发肿瘤：</td>			<td>部位：<span id="KJ_10020_bw"></span></td>				</tr>	  		<tr>			<td>形状：<span id="KJ_10020_xz"></span></td>				</tr>				<tr>			<td>大小：<span id="KJ_10020_dx1"></span>*				<span id="KJ_10020_dx2"></span>*				<span id="KJ_10020_dx3"></span>CM<sup>3</sup>			</td>				</tr>				<tr>						<td>脏层胸膜受累：				<span id="KJ_10020_zcxmsl"></span>			</td>				</tr>		<tr>						<td>壁层胸膜受累：				<span id="KJ_10020_bcxmsl"></span>			</td>				</tr>		<tr>						<td>胸膜结节：				<span id="KJ_10020_xmjj" class="data-fn" data-fn-type="click" data-fn="template.fssjl.hide()" init-mehod="template.fssjl.init()"></span>								<div style="margin-left:72px; display:;">				结节数量：<span id="KJ_10020_jjsl1"></span>				结节大小：<span id="KJ_10020_jjdx1"></span>				结节是否活检：<span id="KJ_10020_jjsfhj1"></span>	</div>			</td>				</tr>				<tr>						<td>肺内结节：				<span id="KJ_10020_fnjj" class="data-fn" data-fn-type="click" data-fn="template.fssjl.hide()" init-mehod="template.fssjl.init()"></span>								<div style="margin-left:72px; display:;">				结节数量：<span id="KJ_10020_jjsl2"></span>				结节大小：<span id="KJ_10020_jjdx2"></span>				结节是否活检：<span id="KJ_10020_jjsfhj2"></span>				</div>			</td>				</tr>				<tr>						<td>肿瘤累及临近结构：				<span id="KJ_10020_zlljljjg"></span>			</td>				</tr>		<tr class="bt_dotted">			<td class="text_right">阻塞性炎症：</td>			<td>				<span id="KJ_10020_zsxyz"></span>			</td>		</tr>		<tr>			<td class="text_right">阻塞性肺不张：</td>			<td>				<span id="KJ_10020_zsxfbz"></span>			</td>		</tr>		<tr>			<td colspan="2">术中是否先行肿物切除再行根治术：				<span id="KJ_10020_szsfgzs"></span></td>		</tr>			<tr>			<td class="text_right">术中冰冻：</td>			<td>				<span id="KJ_10020_szbd"></span>			</td>		</tr>					<tr class="bt_dotted">			<td class="text_right" style="vertical-align:text-top;">手术性质：</td>			<td>				<span id="KJ_10020_ssxz" class="data-fn" data-fn-type="click" data-fn="template.fssjl.hide()" init-mehod="template.fssjl.init()"></span>								<table class="no_margin" style="display:;" border="0" cellpadding="0" cellspacing="0"> 				<tr>					<td>残留部位：<span id="KJ_10020_clbw"></span></td>					<td>大小：<span id="KJ_10020_dx"></span></td>				</tr>				<tr>					<td>原因：<span id="KJ_10020_yy"></span></td>					<td>是否标记：								<span id="KJ_10020_sfbj"></span></td>				</tr>				</table>			</td>		</tr>				<tr>			<td class="text_right">术中失血量：</td>			<td><span id="KJ_10020_shsxl"></span>ml				<div style="margin-left:30px; display:inline; zoom:1;">是否输血：					<span id="KJ_10020_sfsx" class="data-fn" data-fn-type="click" data-fn="template.fssjl.hide()" init-mehod="template.fssjl.init()"></span>				</div>								<div style="margin-left:30px; display:inline; zoom:1;">输血量：<span id="KJ_10020_sxl"></span>ml</div>			</td>		</tr>					<tr class="bb_dotted">			<td class="text_right">术中并发症：</td>			<td>				<span id="KJ_10020_sszbfz"></span>			</td>		</tr>					</table></div>'].join('');
		},
		hide: function(){
			var obj = template.obj;
			var id = $(obj).attr('id');
			var val = $(obj).find('input:checked').val();
			if(!val) return;
			val = $.trim(val);
			if(id){
				if(id.indexOf('xqjy') > -1){//胸腔积液
					('有'==val)?$(obj).next('.no_margin').show():$(obj).next('.no_margin').hide();
				}else if(id.indexOf('xbjy') > -1){//心包积液
					('有'==val)?$(obj).next('.no_margin').show():$(obj).next('.no_margin').hide();
				}else if(id.indexOf('xmjj') > -1){//胸膜结节
					('有'==val)?$(obj).next('div').show():$(obj).next('div').hide();
				}else if(id.indexOf('fnjj') > -1){//肺内结节
					('有'==val)?$(obj).next('div').show():$(obj).next('div').hide();
				}else if(id.indexOf('ssxz') > -1){//手术性质
					('姑息'==val)?$(obj).next('.no_margin').show():$(obj).next('.no_margin').hide();
				}else if(id.indexOf('sfsx') > -1){//是否输血
					('是'==val)?$(obj).parent().next().css({'display':'inline'}):$(obj).parent().next().css({'display':'none'});
				}
			}
		},
		init:function(){
			var obj = template.obj;
			var id = $(obj).attr('id');
			var val = $(obj).find('input:checked').val();
			if(!val)val='';
			val = $.trim(val);
			if(id){
				if(id.indexOf('xqjy') > -1){//胸腔积液
					('有'==val)?$(obj).next('.no_margin').show():$(obj).next('.no_margin').hide();
				}else if(id.indexOf('xbjy') > -1){//心包积液
					('有'==val)?$(obj).next('.no_margin').show():$(obj).next('.no_margin').hide();
				}else if(id.indexOf('xmjj') > -1){//胸膜结节
					('有'==val)?$(obj).next('div').show():$(obj).next('div').hide();
				}else if(id.indexOf('fnjj') > -1){//肺内结节
					('有'==val)?$(obj).next('div').show():$(obj).next('div').hide();
				}else if(id.indexOf('ssxz') > -1){//手术性质
					('姑息'==val)?$(obj).next('.no_margin').show():$(obj).next('.no_margin').hide();
				}else if(id.indexOf('sfsx') > -1){//是否输血
					('是'==val)?$(obj).parent().next().css({'display':'inline'}):$(obj).parent().next().css({'display':'none'});
				}
			}
		}
		
};
/*淋巴结清扫(10021)*/
template.lbjqs = {
		enter : function(){
			return ['<div class="dg_panel1" style="margin:0 auto; ">	<table class="table_zl" width="95%">		<tr><td class="title_line"><div class="line2_title">淋巴结清扫</div></td></tr>		<tr><td ></td></tr>						<tr>						<td>淋巴结清扫：				<span id="KJ_10021_lbjqs"></span>			</td>				</tr>		<tr class="bt_dotted">						<td style=" text-align:center;"><strong>淋巴清扫范围</strong>			</td>				</tr>		<tr>						<td>				<table class="dg_table2 input_border"  cellpadding="0" cellspacing="0"  width="100%" border="0"><tr><td width="10%"></td><td width="40%"></td><td width="10%"></td><td width="40%"></td></tr>				<tr>					<th colspan="2">左肺</th>					<th colspan="2" class="last">右肺</th>				</tr>				<tr>					<td>1组</td>					<td>						<span id="KJ_10021_zf1z"></span>枚					</td>					<td>1组</td>					<td class="last">						<span id="KJ_10021_yf1z"></span>枚					</td>				</tr>				<tr>					<td>3组</td>					<td>						<span id="KJ_10021_zf3z"></span>枚					</td>					<td>2组</td>					<td class="last">						<span id="KJ_10021_yf2z"></span>枚					</td>				</tr>				<tr>					<td>4组</td>					<td>						<span id="KJ_10021_zf4z"></span>枚					</td>					<td>3组</td>					<td class="last">						<span id="KJ_10021_yf3z"></span>枚					</td>				</tr>			  				<tr>					<td>5组</td>					<td>						<span id="KJ_10021_zf5z"></span>枚					</td>					<td>4组</td>					<td class="last">						<span id="KJ_10021_yf4z"></span>枚					</td>				</tr>			  				<tr>					<td>6组</td>					<td>						<span id="KJ_10021_zf6z"></span>枚					</td>					<td>7组</td>					<td class="last">						<span id="KJ_10021_yf7z"></span>枚					</td>				</tr>			  				<tr>					<td>7组</td>					<td>						<span id="KJ_10021_zf7z"></span>枚					</td>					<td>8组</td>					<td class="last">						<span id="KJ_10021_yf8z"></span>枚					</td>				</tr>			  				<tr>					<td>8组</td>					<td>						<span id="KJ_10021_zf8z"></span>枚					</td>					<td>9组</td>					<td class="last">						<span id="KJ_10021_yf9z"></span>枚					</td>				</tr>			  				<tr>					<td>9组</td>					<td>						<span id="KJ_10021_zf9z"></span>枚					</td>					<td>10组</td>					<td class="last">						<span id="KJ_10021_yf10z"></span>枚					</td>				</tr>			  				<tr>					<td>10组</td>					<td>						<span id="KJ_10021_zf10z"></span>枚					</td>					<td>11组</td>					<td class="last">						<span id="KJ_10021_yf11z"></span>枚					</td>				</tr>			  				<tr>					<td>11组</td>					<td>						<span id="KJ_10021_zf11z"></span>枚					</td>					<td>12组</td>					<td class="last">						<span id="KJ_10021_yf12z"></span>枚					</td>				</tr>			  				<tr>					<td>12组</td>					<td>						<span id="KJ_10021_zf12z"></span>枚					</td>					<td>13组</td>					<td class="last">						<span id="KJ_10021_yf13z"></span>枚					</td>				</tr>			  				<tr>					<td>13组</td>					<td>						<span id="KJ_10021_zf13z"></span>枚					</td>					<td>14组</td>					<td class="last">						<span id="KJ_10021_yf14z"></span>枚					</td>				</tr>			  				<tr>					<td>14组</td>					<td>						<span id="KJ_10021_zf14z"></span>枚					</td>					<td></td>					<td class="last">					</td>				</tr>					 		  				</table>			</td>				</tr>				</tr>				</table></div>'].join('');
		}
};

/*放疗记录(10024)*/
template.fljl = {
		enter : function(){
			return ['<div class="dg_panel1"><table class="table_zl" width="95%"><tbody><tr><td class="text_right" width="110">放疗时间：</td><td colspan="3"><span id="KJ_10024_ksflsj"></span>至<span id="KJ_10024_jsflsj"></span></td></tr><tr><td class="text_right">剂量：</td><td colspan="3">pGTV<span id="KJ_10024_jl"></span>Gy/<span id="KJ_10024_cs"></span>次<span id="KJ_10024_tsp"></span>天&nbsp;&nbsp;&nbsp;&nbsp;pCTV<span id="KJ_10024_jlp"></span>Gy/<span id="KJ_10024_csp"></span>次<span id="KJ_10024_tsp1"></span>天</td></tr><tr><td class="text_right">分割方法：</td><td colspan="3"><span id="KJ_10024_fgff"></span></td></tr><tr><td class="text_right">技术方法：</td><td colspan="3"><span id="KJ_10024_jsff"></span></td></tr><tr><td colspan="4" class="title_line"><div class="line2_title">同期化疗：</div></td></tr><tr><td colspan="4"></td></tr><tr><td class="text_right">方案：</td><td><span id="KJ_10024_hlfa"></span></td><td class="text_right">周期：</td><td><span id="KJ_10024_hlzq"></span></td></tr><tr><td class="text_right">治疗开始时间：</td><td><span id="KJ_10024_hlsj"></span></td><td class="text_right">治疗结束时间：</td><td><span id="KJ_10024_hlzljssj"></span></td></tr><tr><td colspan="4" style="padding-top:3px;"><table class="dg_table2 input_border" cellpadding="0" cellspacing="0" width="100%" border="0"><tbody><tr><th><span>药品</span></th><th>常用剂量</th><th>标准剂量</th><th>实际剂量</th><th>单位</th><th class="last">频率</th></tr><tr style="height:32px;"><td><span id="KJ_10024_hlyp1"></span></td><td><span id="KJ_10024_hlcyjl1" class="data-fn" data-fn-type="keyup" data-fn="template.fljl.computesjjl()"></span></td><td><span id="KJ_10024_hlbzjl1"></span></td><td><span id="KJ_10024_hlsjjl1"></span></td><td><span id="KJ_10024_hldw1"></span></td><td><span id="KJ_10024_hlpl1"></span></td></tr><tr style="height:32px;"><td><span id="KJ_10024_hlyp2"></span></td><td><span id="KJ_10024_hlcyjl2"  class="data-fn" data-fn-type="keyup" data-fn="template.fljl.computesjjl()"></span></td><td><span id="KJ_10024_hlbzjl2"></span></td><td><span id="KJ_10024_hlsjjl2"></span></td><td><span id="KJ_10024_hldw2"></span></td><td><span id="KJ_10024_hlpl2"></span></td></tr><tr style="height:32px;"><td><span id="KJ_10024_hlyp3"></span></td><td><span id="KJ_10024_hlcyjl3" class="data-fn" data-fn-type="keyup" data-fn="template.fljl.computesjjl()"></span></td><td><span id="KJ_10024_hlbzjl3"></span></td><td><span id="KJ_10024_hlsjjl3"></span></td><td><span id="KJ_10024_hldw3"></span></td><td><span id="KJ_10024_hlpl3"></span></td></tr><tr style="height:32px;"><td><span id="KJ_10024_hlyp4"></span></td><td><span id="KJ_10024_hlcyjl4" class="data-fn" data-fn-type="keyup" data-fn="template.fljl.computesjjl()"></span></td><td><span id="KJ_10024_hlbzjl4"></span></td><td><span id="KJ_10024_hlsjjl4"></span></td><td><span id="KJ_10024_hldw4"></span></td><td><span id="KJ_10024_hlpl4"></span></td></tr></tbody></table></td></tr><tr><td colspan="4" class="title_line"><div class="line2_title">同期靶向：</div></td></tr><tr><td colspan="4"></td></tr><tr><td class="text_right">方案：</td><td><span id="KJ_10024_bxfa"></span></td><td class="text_right">周期：</td><td><span id="KJ_10024_bxzq"></span></td></tr><tr><td class="text_right">治疗开始时间：</td><td><span id="KJ_10024_bxzlsj"></span></td><td class="text_right">治疗结束时间：</td><td><span id="KJ_10024_bxzljssj"></span></td></tr><tr><td colspan="4" style="padding-top:3px;"><table class="dg_table2 input_border" cellpadding="0" cellspacing="0" width="100%" border="0"><tbody><tr><th><span>药品</span></th><th>常用剂量</th><th>标准剂量</th><th>实际剂量</th><th>单位</th><th class="last">频率</th></tr><tr style="height:32px;"><td><span id="KJ_10024_bxyp1"></span></td><td><span id="KJ_10024_bxcyjl1" class="data-fn" data-fn-type="keyup" data-fn="template.fljl.computesjjl()"></span></td><td><span id="KJ_10024_bxbzjl1"></span></td><td><span id="KJ_10024_bxsjjl1"></span></td><td><span id="KJ_10024_bxdw1"></span></td><td><span id="KJ_10024_bxpl1"></span></td></tr><tr style="height:32px;"><td><span id="KJ_10024_bxyp2"></span></td><td><span id="KJ_10024_bxcyjl2" class="data-fn" data-fn-type="keyup" data-fn="templat.fljl.computesjjl()"></span></td><td><span id="KJ_10024_bxbzjl2"></span></td><td><span id="KJ_10024_bxsjjl2" ></span></td><td><span id="KJ_10024_bxdw2"></span></td><td><span id="KJ_10024_bxpl2"></span></td></tr><tr style="height:32px;"><td><span id="KJ_10024_bxyp3"></span></td><td><span id="KJ_10024_bxcyjl3" class="data-fn" data-fn-type="keyup" data-fn="template.fljl.computesjjl()"></span></td><td><span id="KJ_10024_bxbzjl3"></span></td><td><span id="KJ_10024_bxsjjl3"></span></td><td><span id="KJ_10024_bxdw3"></span></td><td><span id="KJ_10024_bxpl3"></span></td></tr><tr style="height:32px;"><td><span id="KJ_10024_bxyp4"></span></td><td><span id="KJ_10024_bxcyjl4" class="data-fn" data-fn-type="keyup" data-fn="template.fljl.computesjjl()"></span></td><td><span id="KJ_10024_bxbzjl4"></span></td><td><span id="KJ_10024_bxsjjl4"></span></td><td><span id="KJ_10024_bxdw4"></span></td><td><span id="KJ_10024_bxpl4"></span></td></tr></tbody></table></td></tr><tr><td class="text_right">放疗靶区设计方法：</td><td colspan="2"><span id="KJ_10024_bqsjff"></span></td></tr><tr><td colspan="4" class="title_line"><div class="line2_title">       正常组织剂量评估</div></td></tr><tr><td colspan="4"></td></tr><tr><td class="text_right">心脏：</td><td colspan="3">V30<span id="KJ_10024_xz30"></span>%&nbsp;&nbsp;&nbsp;&nbsp;V40<span id="KJ_10024_xz40"></span>%</tr><tr><td class="text_right">肺：</td><td colspan="3">V5<span id="KJ_10024_fv5"></span>%&nbsp;&nbsp;&nbsp;&nbsp;V20<span id="KJ_10024_fv20"></span>%&nbsp;&nbsp;&nbsp;&nbsp;V30<span id="KJ_10024_fv30"></span>%</td></tr><tr><td class="text_right">脊髓：</td><td colspan="3">Dmax<span id="KJ_10024_dmax"></span>Gy&nbsp;&nbsp;&nbsp;&nbsp;Dmean<span id="KJ_10024_dmean"></span>Gy</td></tr><tr><td class="text_right">其他：</td><td colspan="3"><span id="KJ_10024_qtzzjlpg"></span></td></tr><tr><td colspan="4" class="title_line"><div class="line2_title">治疗副反应</div></td></tr><tr><td colspan="4" height="3"></td></tr><tr><td class="text_right">放射性食管炎：</td><td><span id="KJ_10024_fsxsgy"></span></td><td class="text_right">放射性肺炎：</td><td><span id="KJ_10024_fsxfy"></span></td></tr><tr><td class="text_right">喉：</td><td><span id="KJ_10024_h"></span></td><td class="text_right">上消化道：</td><td><span id="KJ_10024_sxhd"></span></td></tr><tr><td class="text_right">心脏：</td><td><span id="KJ_10024_xzffy"></span></td><td class="text_right">下消化道：</td><td><span id="KJ_10024_xxhd"></span></td></tr><tr><td class="text_right">其他：</td><td colspan="3"><span id="KJ_10024_qtzlffy"></span></td></tr></tbody></table></div>'].join('');
		},
		computesjjl: function(){
			var exist = $("#10028_tbmj").length;
			if (exist < 1) {
				$.messager.show({ title: '提示', msg: '身高体重未录入,无法自动计算标准剂量!' });
				return;
			}
			var tbmj = $("#10028_tbmj").val();
			tbmj = $.trim(tbmj);
			var obj = templateHncszl.obj;
			if(!obj) return;
			var cyjl = $(obj).find('input').val();
			var theValue = '';
			if(tbmj != '' && cyjl!='' && !isNaN(tbmj) && !isNaN(cyjl)){
				theValue = cyjl*tbmj;
				$(obj).parent().next().find('input').val(theValue.toFixed(2));
			}
		}
};
/*肺功能(10023)*/
template.fgn = {
		enter : function(){
			return ['<div class="dg_panel1" style="margin:0 auto;">	<table class="table_zl" width="100%"><tr><td>	<table class="dg_table2"  cellpadding="0" cellspacing="0"  width="100%" border="0">	<tr>		<th width="25%">项目名称</th>		<th width="25%">Accual</th>		<th width="25%">%Pred</th>		<th width="25%" class="last">单位</th>	</tr>	<tr>		<td>FVC</td>		<td><span id="KJ_10023_fvcccual"></span></td>		<td><span id="KJ_10023_fvcpred"></span></td>		<td class="last">L</td>	</tr>	<tr>		<td>FEV1</td>		<td><span id="KJ_10023_fev1accual"></span></td>		<td><span id="KJ_10023_fev1pred"></span></td>		<td class="last">L</td>	</tr>	  	<tr>		<td>FEV1%T</td>		<td><span id="KJ_10023_fev1taccual"></td>		<td><span id="KJ_10023_fev1tpred"></td>		<td class="last">%</td>	</tr>	  	<tr>		<td>MVV</td>		<td><span id="KJ_10023_mvvaccual"></td>		<td><span id="KJ_10023_mvvpred"></td>		<td class="last">L/min</td>	</tr>	  		    			   		  	</table>	</td></tr>	</table></div>'].join('');
		}
};

/*入我院手术治疗情况*/
template.rwysszlqk = {
		enter : function(){
			return ['<div class="dg_panel1"><table class="table_zl" width="95%"><tr><th>手术时间</th>	<th>手术方案</th>	<th>就诊医院</th>	<th>术前诊断</th>	<th>术后诊断</th>	<th>疗效</th>	</tr>	<tr>	<td>	         	<span id="KJ_10061_sssj"></span>	</td>	<td>	         	<span id="KJ_10061_ssfa"></span>	</td>	<td>	         	<span id="KJ_10061_jzyy"></span>			</td>			<td>			 	<span id="KJ_10061_sqzd"></span>			</td>			<td>			 	<span id="KJ_10061_shzd"></span>			</td>			<td>			 	<span id="KJ_10061_lx"></span>			</td>	</tr>	</table></div>'].join('');
		}
};
/*入我院靶向治疗情况*/
template.rwybxzlqk = {
		enter : function(){
			return ['<div class="dg_panel1"><table class="table_zl" width="95%"><tr><td width="100px;">时间</td><td width="100px;">就诊医院</td><td width="50px;">周期</td><td width="170px;">方案及药物</td><td width="50px;">每周期用药时间点</td><td width="100px;">疗效</td><td width="80px;">最严重毒副反应</td><td width="80px;">是否在临床研究中使用靶向治疗</td></tr><tr><td>     	<span id="KJ_10060_sj"></span></td><td>     	<span id="KJ_10060_jzyy"></span></td><td><span id="KJ_10060_zq"></span>	</td>	<td>	 	<span id="KJ_10060_fajyw"></span>	</td>	<td><span id="KJ_10060_mzqyysjd"></span>	</td>	<td>	 	<span id="KJ_10060_lx"></span>	</td>	<td><span id="KJ_10060_zyzdffy"></span>	</td>	<td>	 	<span id="KJ_10060_lcyj"></span>	</td></tr></table></div>'].join('');
		}
};

/*体检检查*/
template.tjjc = {
	//录入模板
	enter : function() {
		return ['<li class="dp_cont"><table class="dg_table" rules="rows"><tbody><tr><th>项目名</th><th>检验结果</th><th>单位</th></tr><tr><td class="dgt_title">收缩压</td><td><span id="KJ_10028_ssy"></span>	</td><td>mmHg</td></tr><tr><td class="dgt_title">舒张压</td><td><span id="KJ_10028_szy"></span>	</td><td>mmHg</td></tr><tr><td class="dgt_title">身高</td><td><span id="KJ_10028_sg"  class="data-fn" data-fn-type="keyup" data-fn="template.tjjc.computedata()"></span>	</td><td>cm</td></tr><tr><td class="dgt_title">体重</td><td><span id="KJ_10028_tz"  class="data-fn" data-fn-type="keyup" data-fn="template.tjjc.computedata()"></span>		</td><td>kg</td></tr><tr><td class="dgt_title">体重指数(BMI)</td><td><span id="KJ_10028_tzzs"></span>		</td><td>kg/㎡</td></tr><tr><td class="dgt_title">体表面积(BSA)</td><td><span id="KJ_10028_tbmj"></span>		</td><td>㎡</td></tr><tr><td class="dgt_title">卡氏评分(KPS)</td><td><span id="KJ_10028_kspf"></span></td><td></td></tr><tr><td class="dgt_title">ECOG-WHO x`PS评分</td><td><span id="KJ_10028_pf"></span></td><td></td></tr><tr><td class="dgt_title">疼痛评分（NRS法）</td><td><span id="KJ_10028_ttpff"></span></td><td></td></tr></tbody></table></li>'].join('');
	},
	computedata : function(){
		var sg=$("input[name='KJ_10028_inp_sg_100342']").val();
		var tz=$("input[name='KJ_10028_inp_tz_100343']").val();
		var theValue='';
		var bmi = '';
		if(sg !='' && tz != '' && !isNaN(sg) && !isNaN(tz)){
			/*bmi = 体重（kg）÷身高^2（m）*/
			bmi = (tz/Math.pow(sg/100,2)).toFixed(2);
			/*修正算法  体表面积计算(BSA)=H^0.725*W^0.425*0.007184*/
			theValue = (Math.pow(sg,0.725)*Math.pow(tz,0.425)*0.007184).toFixed(2);
		}
		$("input[name='KJ_10028_inp_tzzs_100344']").val(bmi);
		$("input[name='KJ_10028_inp_tbmj_100345']").val(theValue);
	}
};
/*入我院放疗治疗情况 */
template.rwyflzlqk = {
		enter : function(){
			return ['<div class="dg_panel1"><table class="dg_table" rules="rows" ><tr>  	<th width="100">检验项目</td>  	<th>检验结果</th></tr><tr>  	<td class="dgt_title">放疗时间</td>  	<td>     	<span id="KJ_10059_flsj" ></span></td></tr><tr>  	<td class="dgt_title">就诊医院</td><td>     	<span id="KJ_10059_jzyy" ></span></td></tr><tr>  	<td class="dgt_title">周期</td>  	<td><span id="KJ_10059_zq" ></span>	</td></tr><tr>  	<td class="dgt_title">剂量和分次</td>  	<td><span id="KJ_10059_jl" ></span>Gy/<span id="KJ_10059_cs" ></span>次<span id="KJ_10059_ts" ></span>天	</td></tr><tr>  	<td class="dgt_title">同期化疗</td>  	<td>	 	<span id="KJ_10059_ywtqhl" ></span>     	<span id="KJ_10059_hlyw" ></span>	</td></tr><tr>  	<td class="dgt_title">疗效</td>  	<td>	 	<span id="KJ_10059_lx" ></span>	</td></tr><tr>  	<td class="dgt_title">最严重毒副反应</td>  	<td><span id="KJ_10059_zyzdffy" ></span>	</td></tr><tr>  	<td class="dgt_title">是否在临床研究中使用放疗治疗</td>  	<td>	 	<span id="KJ_10059_lcyj" ></span>	</td></tr></table></div>'].join('');
		}
};
/*入我院化疗治疗情况 */
template.rwyhlzlqk = {
		enter : function(){
			return ['<div class="dg_panel1"><table class="table_zl" width="100%"><tr><th>化疗时间</th><th>就诊医院</th><th>周期</th><th>方案及药物</th><th>每周期用药时间点</th><th>疗效</th><th>最严重毒副反应</th><th>是否在临床研究中使用化疗治疗</th></tr><tr><td>         	<span id="KJ_10058_hlsj" ></span></td><td>         	<span id="KJ_10058_jzyy" ></span></td><td><span id="KJ_10058_zq" ></span>		</td>		<td>		 	<span id="KJ_10058_fajyw" ></span>		</td>		<td><span id="KJ_10058_mzqyysjd" ></span>		</td>		<td>		 	<span id="KJ_10058_lx" ></span>		</td>		<td><span id="KJ_10058_zyzdffy" ></span>		</td>		<td>		 	<span id="KJ_10058_lcyj" ></span>		</td></tr></table></div>'].join('');
		}
};
/*EGFR Exon 21外显子*/
template.exon21 = {
		enter : function(){
			return ['<div class="dg_panel1"><table class="table_zl" width="100%"><tbody><tr><td><table class="dg_table2" cellpadding="0" cellspacing="0" width="100%" border="0"><tbody><tr><th width="40%" colspan="2">突变名称</th><th width="20%">氨基酸变化</th><th width="20%">碱基序列变化</th><th width="20%" class="last">检测结果</th></tr><tr><td rowspan="4">外显子21突变</td><td>无</td><td>无</td><td>无</td><td class="last"><span id="KJ_10057_wxz21tb"></span></td></tr><tr><td>外显子21突变1</td><td>L858R</td><td>25731&gt;G</td><td class="last"><span id="KJ_10057_wxz21tb1"></span></td></tr><tr><td>外显子21突变2</td><td>L858R</td><td>2573-2574TG&gt;GT</td><td class="last"><span id="KJ_10057_wxz21tb2"></span></td></tr><tr><td>外显子21突变3</td><td>L861Q</td><td>2582T&gt;A</td><td class="last"><span id="KJ_10057_wxz21tb3"></span></td></tr></tbody></table></td></tr></tbody></table></div>'].join('');
		}
};
/*EGFR Exon 20外显子*/
template.exon20 = {
		enter : function(){
			return ['<div class="dg_panel1"><table class="table_zl" width="100%"><tbody><tr><td><table class="dg_table2" cellpadding="0" cellspacing="0" width="100%" border="0"><tbody><tr><th width="40%" colspan="2">突变名称</th><th width="20%">氨基酸变化</th><th width="20%">碱基序列变化</th><th width="20%" class="last">检测结果</th></tr><tr></tr><tr><td rowspan="6">外显子20突变</td><td>无</td><td>无</td><td>无</td><td class="last"><span id="KJ_10056_wxz20tb"></span></td></tr><tr><td>外显子20突变1</td><td>S7681</td><td>2303G&gt;T</td><td class="last"><span id="KJ_10056_wxz20tb1"></span></td></tr><tr><td>外显子20突变2</td><td>T790M</td><td>2369C&gt;T</td><td class="last"><span id="KJ_10056_wxz20tb2"></span></td></tr><tr><td>外显子20突变3</td><td>V769-D770inwASV</td><td>2307-2308 insGCCAGCGTG</td><td class="last"><span id="KJ_10056_wxz20tb3"></span></td></tr><tr><td>外显子20突变4</td><td>11773-V774ins11</td><td>2319-2320insCAC</td><td class="last"><span id="KJ_10056_wxz20tb4"></span></td></tr><tr><td>外显子20突变5</td><td>D770-N771insG</td><td>2310-2311insGGT</td><td class="last"><span id="KJ_10056_wxz20tb5"></span></td></tr></tbody></table></td></tr></tbody></table></div>'].join('');
		}
};

/*EGFR Exon 19外显子*/
template.exon19 = {
		enter : function(){
			return ['<div class="dg_panel1"><table class="table_zl" width="100%"><tbody><tr><td><table class="dg_table2" cellpadding="0" cellspacing="0" width="100%" border="0"><tbody><tr><th width="40%" colspan="2">突变名称</th><th width="20%">氨基酸变化</th><th width="20%">碱基序列变化</th><th width="20%" class="last">检测结果</th></tr><tr><td rowspan="20">外显子19突变</td><td>无</td><td>无</td><td>无</td><td class="last"><span id="KJ_10055_wxz19tb"></span></td></tr><tr><td>外显子19突变1</td><td>E746-A750 del(1)</td><td>2235-2249 del 15</td><td class="last"><span id="KJ_10055_wxz19tb1"></span></td></tr><tr><td>外显子19突变2</td><td>E746-T751&gt;1</td><td>2235-2252&gt;AAT</td><td class="last"><span id="KJ_10055_wxz19tb2"></span></td></tr><tr><td>外显子19突变3</td><td>E746-T751 del</td><td>2236-2253 del 18</td><td class="last"><span id="KJ_10055_wxz19tb3"></span></td></tr><tr><td>外显子19突变4</td><td>E746-T751&gt;A</td><td>2237-2251 del 15</td><td class="last"><span id="KJ_10055_wxz19tb4"></span></td></tr><tr><td>外显子19突变5</td><td>E746-T752&gt;A</td><td>2237-2254 del 18</td><td class="last"><span id="KJ_10055_wxz19tb5"></span></td></tr><tr><td>外显子19突变6</td><td>E746-S752&gt;V</td><td>2237-2255&gt;T</td><td class="last"><span id="KJ_10055_wxz19tb6"></span></td></tr><tr><td>外显子19突变7</td><td>E746-A750 del(2)</td><td>2236-2250 del 15</td><td class="last"><span id="KJ_10055_wxz19tb7"></span></td></tr><tr><td>外显子19突变8</td><td>E746-S752&gt;D</td><td>2238-2255 del 18</td><td class="last"><span id="KJ_10055_wxz19tb8"></span></td></tr><tr><td>外显子19突变9</td><td>L747-A750&gt;P</td><td>2238-2248&gt;GC</td><td class="last"><span id="KJ_10055_wxz19tb9"></span></td></tr><tr><td>外显子19突变10</td><td>L747-T751&gt;Q</td><td>2238-2252&gt;GCA</td><td class="last"><span id="KJ_10055_wxz19tb10"></span></td></tr><tr><td>外显子19突变11</td><td>L747-E749 del</td><td>2239-2247 del 9</td><td class="last"><span id="KJ_10055_wxz19tb11"></span></td></tr><tr><td>外显子19突变12</td><td>L747-T751 del</td><td>2239-2253 del 15</td><td class="last"><span id="KJ_10055_wxz19tb12"></span></td></tr><tr><td>外显子19突变13</td><td>L747-S752 del</td><td>2239-2256 del 18</td><td class="last"><span id="KJ_10055_wxz19tb13"></span></td></tr><tr><td>外显子19突变14</td><td>L747-A750&gt;P</td><td>2239-2248 TTAAGAGAAG&gt;C</td><td class="last"><span id="KJ_10055_wxz19tb14"></span></td></tr><tr><td>外显子19突变15</td><td>L747-P753&gt;Q</td><td>2239-2258&gt;CA</td><td class="last"><span id="KJ_10055_wxz19tb15"></span></td></tr><tr><td>外显子19突变16</td><td>L747-T751&gt;S</td><td>2240-2251 del 12</td><td class="last"><span id="KJ_10055_wxz19tb16"></span></td></tr><tr><td>外显子19突变17</td><td>L747-P753 del</td><td>2240-2254 del 15</td><td class="last"><span id="KJ_10055_wxz19tb17"></span></td></tr><tr><td>外显子19突变18</td><td>L747-P753&gt;S</td><td>2240-2257 del 18</td><td class="last"><span id="KJ_10055_wxz19tb18"></span></td></tr><tr><td>外显子19突变19</td><td>L747-T751&gt;P</td><td>2239-2251&gt;C</td><td class="last"><span id="KJ_10055_wxz19tb19"></span></td></tr></tbody></table></td></tr></tbody></table></div>'].join('');
		}
};

/*肿瘤不良反映*/
template.fahzblfyjlb = {
		enter : function(){
			return ['<div class="dg_panel1"><!--肿瘤不良反映 --><table class="table_zl" width="95%"><tr><td width="150" class="text_right"><span class="prompt_d">恶心： <span class="arrow_top"></span><div><dd>Ⅰ级：食欲降低，不伴进食习惯改变<br/>Ⅱ级：经口摄食减少不伴明显的体重下隆、脱水或营养不良<br/>Ⅲ级：经口摄入能量和水分不足，需要鼻饲，全肠外营养或者住院<br/>Ⅳ级：—— </dd></div></span></td><td><span id="KJ_990000000501_ex"></span></td></tr><tr><td class="text_right"><span class="prompt_d">呕吐： <span class="arrow_top"></span><div><dd>Ⅰ级：24小时内1~2次发作（间隔5分钟）<br/>Ⅱ级：24小时内3~5次发作（间隔5分钟）<br/>Ⅲ级：24小时内≥6次发作（间隔5分钟）；需要鼻饲，全肠外营养或住院治疗<br/>Ⅳ级：危及生命；需要紧急治疗 </dd></div></span></td><td><span id="KJ_990000000501_ot"></span></td></tr><tr><td class="text_right"><span class="prompt_d">厌食症： <span class="arrow_top"></span><div><dd>Ⅰ级：食欲减退，但不伴有饮食习惯改变<br/>Ⅱ级：进食改变，但不伴有体重降低或营养不良；需要口服补充营养<br/>Ⅲ级：出现明显体重降低或营养不良症状（例如：经口摄入热量不足）；需要鼻饲或全肠外营养<br/>Ⅳ级：危及生命；需要紧急治疗 </dd></div></span></td><td><span id=KJ_990000000501_ysz></span></td></tr><tr><td class="text_right"><span class="prompt_d">呃逆： <span class="arrow_top"></span><div><dd>Ⅰ级：轻度症状；不需要干预<br/>Ⅱ级：中度症状；需要干预；影响工具性日常生活活动<br/>Ⅲ级：重度症状；影响睡眠；影响个人日常生活活动<br/>Ⅳ级：—— </dd></div></span></td><td><span id="KJ_990000000501_en"></span></td></tr><tr><td class="text_right"><span class="prompt_d">腹痛： <span class="arrow_top"></span><div><dd>Ⅰ级：轻度疼痛<br/>Ⅱ级：中度疼痛；影响工具性日常生活活动<br/>Ⅲ级：重度疼痛；影响个人日常生活活动<br/>Ⅳ级：—— </dd></div></span></td><td><span id="KJ_990000000501_ft"></span></td></tr><tr><td  class="text_right"><span class="prompt_d">腹泻： <span class="arrow_top"></span><div><dd>Ⅰ级：与基线相比，大便次数增加每天＜4次；造瘘口排出物轻度增加<br/>Ⅱ级：与基线相比，大便次数增加每天4~6次，造瘘口排出物中度增加<br/>Ⅲ级：与基线相比，大便次数增加每天≥7次，大便失禁；需要入院治疗；与基线相比，造瘘口排出物重度增加；影响个人日常生活活动<br/>Ⅳ级：危及生命；需要紧急治疗 </dd></div></span></td><td><span id="KJ_990000000501_fx"></span></td></tr><tr><td class="text_right"><span class="prompt_d">便秘： <span class="arrow_top"></span><div><dd>Ⅰ级：偶然或间断性出现；偶尔需要使用粪便软化剂，轻泻药，饮食习惯调整或灌肠<br/>Ⅱ级：持续症状，需要有规律的使用轻泻药或灌肠；影响工具性日常生活活动<br/>Ⅲ级：需要手工疏通的顽固性便秘；影响个人日常生活活动<br/>Ⅳ级：危及生命；需要紧急治疗 </dd></div></span></td><td><span id="KJ_990000000501_bm"></span></td></tr><tr><td  class="text_right"><span class="prompt_d">眩晕： <span class="arrow_top"></span><div><dd>Ⅰ级：轻度不平稳或有移动感<br/>Ⅱ级：中度不平稳的；影响工具性日常生活活动<br/>Ⅲ级：重度不平稳；影响个人日常生活活动<br/>Ⅳ级：—— </dd></div></span></td><td><span id="KJ_990000000501_xy"></span></td></tr><tr><td  class="text_right"><span class="prompt_d">头痛： <span class="arrow_top"></span><div><dd>Ⅰ级：轻度头痛<br/>Ⅱ级：中度头痛；影响工具性日常生活活动<br/>Ⅲ级：重度头痛；影响个人日常生活活动<br/>Ⅳ级：—— </dd></div></span></td><td><span id="KJ_990000000501_tt"></span></td></tr><tr><td  class="text_right"><span class="prompt_d">咳嗽： <span class="arrow_top"></span><div><dd>Ⅰ级：轻度症状；需要非处方药治疗<br/>Ⅱ级：中度症状；需要药物治疗；影响工具性日常生活活动<br/>Ⅲ级：重度症状；影响个人日常生活活动<br/>Ⅳ级：—— </dd></div></span></td><td><span id="KJ_990000000501_ks"></span></td></tr><tr><td  class="text_right"><span class="prompt_d">疲劳： <span class="arrow_top"></span><div><dd>Ⅰ级：疲劳，休息后缓解<br/>Ⅱ级：疲劳，休息后不能缓解；影响工具性日常生活活动；<br/>Ⅲ级：疲劳，休息后不能缓解；影响个人日常生活活动<br/>Ⅳ级：—— </dd></div></span></td><td><span id="KJ_990000000501_pl"></span></td></tr><tr><td  class="text_right"><span class="prompt_d">口腔痛： <span class="arrow_top"></span><div><dd>Ⅰ级：轻度疼痛<br/>Ⅱ级：中度疼痛；影响工具性日常生活活动<br/>Ⅲ级：重度疼痛；影响日常生活自理<br/>Ⅳ级：—— </dd></div></span></td><td><span id="KJ_990000000501_kqt"></span></td></tr><tr><td class="text_right"><span class="prompt_d">疼痛： <span class="arrow_top"></span><div><dd>Ⅰ级：轻度疼痛<br/>Ⅱ级：中度疼痛；影响工具性日常生活活动<br/>Ⅲ级：剧痛；影响个人日常生活活动<br/>Ⅳ级：—— </dd></div></span></td><td><span id="KJ_990000000501_tt2"></span></td></tr><tr><td class="text_right"><span class="prompt_d">流行性感冒样症状： <span class="arrow_top"></span><div><dd>Ⅰ级：轻微流感样症状<br/>Ⅱ级：中度症状；影响工具性日常生活活动<br/>Ⅲ级：严重症状；影响个人日常生活活动<br/>Ⅳ级：—— </dd></div></span></td><td><span id="KJ_990000000501_lxngm"></span></td></tr><tr><td class="text_right"><span class="prompt_d">外周神经病变： <span class="arrow_top"></span><div><dd>Ⅰ级：无症状，仅在临床和诊断中发现，不需要干预<br/>Ⅱ级：中度症状；影响工具性日常生活活动<br/>Ⅲ级：严重症状；影响个人日常生活活动<br/>Ⅳ级：—— </dd></div></span></td><td><span id="KJ_990000000501_wzsjbb"></span></td></tr><tr><td class="text_right"><span class="prompt_d">发热： <span class="arrow_top"></span><div><dd>Ⅰ级：38.0~39.0℃(100.4~102.2℉)<br/>Ⅱ级：>39.0~40.0℃(102.3~104.0℉)<br/>Ⅲ级：>40.0℃(>104.0℉)<=24小时<br/>Ⅳ级：？？？>40.0℃(>104.0℉)超过24小时 </dd></div></span></td><td><span id="KJ_990000000501_fr"></span></td></tr><tr><td  class="text_right"><span class="prompt_d">体重降低： <span class="arrow_top"></span><div><dd>Ⅰ级：参照基线，体重减轻5~10%，无需治疗<br/>Ⅱ级：参照基线，体重减轻10~<20%，需要给予营养支持<br/>Ⅲ级：参照基线，体重减轻>=20%；需要鼻饲或全肠外营养<br/>Ⅳ级：—— </dd></div></span></td><td><span id="KJ_990000000501_tzjd"></span></td></tr><tr><td class="text_right"><span class="prompt_d">痤疮样皮疹： <span class="arrow_top"></span><div><dd>Ⅰ级：丘疹和脓疱小于10%的体表面积，伴有/不伴有瘙痒和敏感<br/>Ⅱ级：丘疹和脓疱10%~30%的体表面积，伴有/不伴有瘙痒和压痛；伴心理影响；影响工具性日常生活活动<br/>Ⅲ级：丘疹和脓疱大于30%的体表面积，伴有/不伴有瘙痒和压痛；影响个人日常生活活动；需要口服抗生素治疗二重感染<br/>Ⅳ级：丘疹和脓疱遍布全身表面，伴有/不伴有瘙痒和敏感；需要静脉给予抗生素治疗广泛的多重感染；危及生命 </dd></div></span></td><td><span id="KJ_990000000501_ccyp"></span></td></tr><tr><td  class="text_right"><span class="prompt_d">白细胞数降低： <span class="arrow_top"></span><div><dd>Ⅰ级：<正常值下限~3000/mm3；<正常值下限~3.0×10e9/L<br/>Ⅱ级：<3000~<2000/mm3；<3.0~2.0×10e9/L<br/>Ⅲ级：<2000~<1000/mm3；<2.0~1.0×10e9/L<br/>Ⅳ级：<1000/mm3;<1.0×10e9/L </dd></div></span></td><td><span id="KJ_990000000501_bxbsjd"></span></td></tr><tr><td  class="text_right"><span class="prompt_d">中性粒细胞计数降低： <span class="arrow_top"></span><div><dd>Ⅰ级：<正常值下限~1500/mm3；<正常值下限~1.5×10e9/L<br/>Ⅱ级：<1500~1000/mm3；<1.5~1.0×10e9/L<br/>Ⅲ级：<1000~500/mm3；<1.0~0.5×10e9/L<br/>Ⅳ级：<500/mm3；<0.5×10e9/L </dd></div></span></td><td><span id="KJ_990000000501_zxlxb"></span></td></tr><tr><td class="text_right"><span class="prompt_d">血小板计数降低： <span class="arrow_top"></span><div><dd>Ⅰ级：<正常值下限~75,000/mm3；<正常值下限~75.0×10e9/L<br/>Ⅱ级：<75,000~50,000/mm3；<75.0~50.0×10e9/L<br/>Ⅲ级：<50,000~25,000/mm3；<50.0~25.0×10e9/L<br/>Ⅳ级：<25,000/mm3；<25.0×10e9/L </dd></div></span></td><td><span id="KJ_990000000501_xxbjs"></span></td></tr><tr><td  class="text_right"><span class="prompt_d">天冬氨酸氨基转移酶增高： <span class="arrow_top"></span><div><dd>Ⅰ级：>正常值上限~3.0倍正常值上限<br/>Ⅱ级：无症状者：>3.0 - 5.0 倍正常值上限；>3 倍正常值上限，伴随以下症状加重：疲劳、恶心、呕吐、右上区疼痛或压痛，发热、皮疹、嗜酸粒细胞增多<br/>Ⅲ级：>5.0 - 20.0 倍正常值上限；持续2周以上，>5倍正常值上限<br/>Ⅳ级：>20.0 倍 正常值上限 </dd></div></span></td><td><span id="KJ_990000000501_gzasaj"></span></td></tr><tr><td  class="text_right"><span class="prompt_d">丙氨酸氨基转移酶增高： <span class="arrow_top"></span><div><dd>Ⅰ级：>正常值上限~3.0倍正常值上限<br/>Ⅱ级：无症状者：>3.0 - 5.0 倍正常值上限；>3 倍正常值上限，伴随以下症状加重：疲劳、恶心、呕吐、右上区疼痛或压痛，发热、皮疹、嗜酸粒细胞增多<br/>Ⅲ级：>5.0 - 20.0 倍正常值上限；持续2周以上，>5倍正常值上限<br/>Ⅳ级：>20.0 倍 正常值上限 </dd></div></span></td><td><span id="KJ_990000000501_basaj"></span></td></tr><tr><td  class="text_right"><span class="prompt_d">肌酐增高： <span class="arrow_top"></span><div><dd>Ⅰ级：>1 - 1.5 倍基线数值；>正常值上限 -1.5 x 正常值上限<br/>Ⅱ级：>1.5 - 3.0倍基线数值；>1.5 -3.0 倍 正常值上限<br/>Ⅲ级：>3.0 基线数值；>3.0 - 6.0 倍正常值上限<br/>Ⅳ级：>6.0 倍 正常值上限 </dd></div></span></td><td><span id="KJ_990000000501_jgzg"></span></td></tr><tr><td  class="text_right">其他：</td><td><span id="KJ_990000000501_qt"></span></td></tr></table></div>'].join('');
		}
};

/*肿瘤腹部CT*/
template.zlfbctbg = {
		enter : function(){
			return ['<div class="dg_panel1"><table class="table_zl"><tr><td width="100"></td><td></td></tr><tr><td class="text_right">肝脏：</td><td><span id="KJ_990000000554_gzzl" class="data-fn" data-fn-type="click" data-fn="template.zlfbctbg.hide()" init-mehod="template.zlfbctbg.init()"></span> <span style="margin-left:10px">数目：<span id="KJ_990000000554_gzsm"></span> 大小：<span id="KJ_990000000554_gzdx"></span></span></td></tr><tr><td class="text_right">肾脏：</td><td><span id="KJ_990000000554_szzl" class="data-fn" data-fn-type="click" data-fn="template.zlfbctbg.hide()" init-mehod="template.zlfbctbg.init()"></span> <span style="margin-left:10px"><span id="KJ_990000000554_zlwz"></span> 数目：<span id="KJ_990000000554_szsm"></span> 大小：<span id="KJ_990000000554_szdx"></span></span></td></tr><tr><td class="text_right">肾上腺：</td><td><span id="KJ_990000000554_ssxzl" class="data-fn" data-fn-type="click" data-fn="template.zlfbctbg.hide()" init-mehod="template.zlfbctbg.init()"></span> <span style="margin-left:10px"><span id="KJ_990000000554_ssxwz"></span> 数目：<span id="KJ_990000000554_ssxsm"></span> 大小：<span id="KJ_990000000554_ssxdx"></span></span></td></tr><tr><td class="text_right">肠道：</td><td><span id="KJ_990000000554_cdzl" class="data-fn" data-fn-type="click" data-fn="template.zlfbctbg.hide()" init-mehod="template.zlfbctbg.init()"></span> <span style="margin-left:10px">部位：<span id="KJ_990000000554_cdbw"></span> 大小：<span id="KJ_990000000554_cddx"></span> 肠壁厚度：<span id="KJ_990000000554_cbhd"></span></span></td></tr><tr><td class="text_right">膜腔淋巴结：</td><td><span id="KJ_990000000554_mqlbj" class="data-fn" data-fn-type="click" data-fn="template.zlfbctbg.hide()" init-mehod="template.zlfbctbg.init()"></span> <span style="margin-left:10px">数目：<span id="KJ_990000000554_fqsm"></span> 大小：<span id="KJ_990000000554_fqdx"></span></span></td></tr><tr><td class="text_right">腹水：</td><td><span id="KJ_990000000554_fs"></span></td></tr><tr><td class="text_right">腹膜淋巴结：</td><td><span id="KJ_990000000554_fmzl" class="data-fn" data-fn-type="click" data-fn="template.zlfbctbg.hide()" init-mehod="template.zlfbctbg.init()"></span> <span style="margin-left:10px">数目：<span id="KJ_990000000554_fmsm"></span> 大小：<span id="KJ_990000000554_fmdx"></span></span></td></tr><tr><td class="text_right">大网膜淋巴结：</td><td><span id="KJ_990000000554_dwm" class="data-fn" data-fn-type="click" data-fn="template.zlfbctbg.hide()" init-mehod="template.zlfbctbg.init()"></span> <span style="margin-left:10px">数目：<span id="KJ_990000000554_dwmsm"></span> 大小：<span id="KJ_990000000554_dwmdx"></span></span></td></tr><tr><td class="text_right">其他：</td><td><span id="KJ_990000000554_qt"></span></td></tr></table></div>'].join('');
		},
		hide: function(){
			var obj = template.obj;
			var id = $(obj).attr('id');
			var val = $(obj).find('input:checked').val();
			if(!val) return;
			val = $.trim(val);
			if(id){
				if(id.indexOf('gzzl') > -1){
					('有'==val)?$(obj).next('span').show():$(obj).next('span').hide();
				}else if(id.indexOf('szzl') > -1){
					('有'==val)?$(obj).next('span').show():$(obj).next('span').hide();
				}else if(id.indexOf('ssxzl') > -1){
					('有'==val)?$(obj).next('span').show():$(obj).next('span').hide();
				}else if(id.indexOf('cdzl') > -1){
					('有'==val)?$(obj).next('span').show():$(obj).next('span').hide();
				}else if(id.indexOf('mqlbj') > -1){
					('有'==val)?$(obj).next('span').show():$(obj).next('span').hide();
				}else if(id.indexOf('fmzl') > -1){
					('有'==val)?$(obj).next('span').show():$(obj).next('span').hide();
				}else if(id.indexOf('_dwm') > -1){
					('有'==val)?$(obj).next('span').show():$(obj).next('span').hide();
				}
			}
		},
		init:function(){
			var obj = template.obj;
			var id = $(obj).attr('id');
			var val = $(obj).find('input:checked').val();
			if(!val)val='';
			val = $.trim(val);
			if(id){
				if(id.indexOf('gzzl') > -1){
					('有'==val)?$(obj).next('span').show():$(obj).next('span').hide();
				}else if(id.indexOf('szzl') > -1){
					('有'==val)?$(obj).next('span').show():$(obj).next('span').hide();
				}else if(id.indexOf('ssxzl') > -1){
					('有'==val)?$(obj).next('span').show():$(obj).next('span').hide();
				}else if(id.indexOf('cdzl') > -1){
					('有'==val)?$(obj).next('span').show():$(obj).next('span').hide();
				}else if(id.indexOf('mqlbj') > -1){
					('有'==val)?$(obj).next('span').show():$(obj).next('span').hide();
				}else if(id.indexOf('fmzl') > -1){
					('有'==val)?$(obj).next('span').show():$(obj).next('span').hide();
				}else if(id.indexOf('_dwm') > -1){
					('有'==val)?$(obj).next('span').show():$(obj).next('span').hide();
				}
			}
		}
};

