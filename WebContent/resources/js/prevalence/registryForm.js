/**
 * 个案调查表填写帮助
 */
var registryHelp = {
	//一般情况
	general : '<p>1、医院感染的定义：</b>医院感染又称医院内获得性感染；即指患者在入院时既不存在、亦不处于潜伏期，而在医院内发生的感染，包括在医院获得而于出院后发病的感染。</br>' +
		'社区感染的定义：</b>患者入院时已经存在或处于潜伏期的感染。本次调查社区病毒性肝炎不统计在社区感染中。</br>' +
		'手术：</b>患者在手术室接受外科医生至少在其皮肤或黏膜上做一个切口，包括腹腔镜，并在患者离开手术室前缝合切口。</br>' +
		'手术后肺炎：</b>患者发生在手术后符合医院感染肺炎诊断标准的肺炎。</p>' +
		'<p>2、编号由各医院调查负责人员决定，可由系统自动生成或在录入系统前统一编写。</p>' +
		'<p>3、科室：</b>可写入本院科室名，也可写入下列标准科室名。在录入系统时科室的录入请按照网站的帮助文件执行。需特别注意的是标准科室中“肿瘤科”是指外科的肿瘤科；如为内科肿瘤科，标准科室归类为“其他内科”；如为放射治疗的肿瘤科，标准科室归类为“其他科”。' +
		'呼吸科  消化科  心血管科  内分泌科  肾病内科  血液科  传染科  神经内科   中医科  其他内科（干部内科，风湿免疫，肿瘤内科、皮肤内科） 普外科（乳腺、甲状腺、肝胆胰、胃肠）  骨科 （脊柱） 泌尿外科  神经外科  胸外科（心外科）  烧伤科 整形科 肿瘤科 其他外科（儿外科、血管外科、肛肠外科） 妇科   产科    儿科    耳鼻喉科    眼科  口腔科  其他五官科  综合ICU（各专科ICU列入各专科统计）  其他科（放疗科）。</p>' +
		'<p>4、诊断：</b>填写患者当前诊断。</p>' +
		'<p>5、手术：</b>调查对象在入院后的手术，都填为是。</p>' +
		'<p>6、切口分类：</b></br>' +
		'I类切口：即清洁切口。手术未进入感染炎症区，未进入呼吸道、消化道、泌尿生殖道及口咽部位；</br>' +
		'II类切口：即清洁-污染切口。手术进入呼吸道、消化道、泌尿生殖道及口咽部位，但不伴有明显污染；</br>' +
		'III类切口：即污染切口。手术进入急性炎症但未化脓区域；开放性创伤手术；胃肠道、尿路、胆道内容物及体液有大量溢出污染；术中有明显污染（如开胸心脏按压）；</br>' +
		'IV类切口：即感染切口。有失活组织的陈旧创伤手术；已有临床感染或脏器穿孔的手术。</p>'+
		'无切口：主要指介入手术、经过腔道实施的内窥镜手术。',
	//感染情况
	infections : '<p>7、感染包括医院感染与社区感染</br>' +
		'无论社区感染还是医院感染，包括手术后肺炎，“存在”包括：1）调查日新发生的感染；2）过去发生的感染，在调查日该感染仍未痊愈的患者或部位。“不存在”指：1）过去发生的感染，在调查日已经痊愈的患者或部位；2）没有感染的患者。' +
		'如调查分次完成，则“存在”和“不存在”指各科室相应调查日期内是否存在感染的状态（包括医院感染与社区感染）。</p>' +
		'<p>8、感染部位：医院感染部位和社区感染部位都按下列分类标准填写；汇总时归类如下：' +
		'上呼吸道，下呼吸道，泌尿道，胃肠道（包括：感染性腹泻，食道、胃、大小肠、直肠感染，抗生素相关性腹泻），腹腔内组织（包括：腹膜炎、腹腔积液感染），表浅切口，深部切口，器官腔隙，血管相关，血液（菌血症、败血症），皮肤软组织（包括：皮肤感染、软组织感染、褥疮感染、乳腺脓肿或乳腺感染、脐炎、新生儿脓疱病、烧伤部位感染），其他[胸膜腔感染，病毒性肝炎（仅指医院感染），细菌性脑膜炎，输血相关感染，非手术后颅内脓肿，无脑膜炎的椎管内感染，心血管系统感染，骨、关节感染，生殖道感染，口腔感染以及以上未包括的感染]。' +
		'如为下呼吸道感染，需判断是否为手术后肺炎。</p>' +
		'<p>9、病原体：指感染部位的病原体。一个感染部位若为混合感染则有多个病原体。对于每一个病原体的耐药情况进行判断，分为MDR（多重耐药菌）、XDR（泛耐药菌）、PDR（全耐药菌），填在病原体后的括号内(MDR、XDR、PDR的判断标准请参看：李春辉，吴安华. MDR、XDR、PDR多重耐药菌暂行标准定义——国际专家建议[J]. 中国感染控制杂志,2014; 13 (1): 62-64)。在感染部位的病原体中特别注意金黄色葡萄球菌、凝固酶阴性葡萄球菌、粪肠球菌、屎肠球菌、肺炎链球菌、大肠埃希菌、肺炎克雷伯菌、铜绿假单胞菌、鲍曼不动杆菌等细菌。</p>',
	//细菌耐药情况
	bacterialDrug : '<p>10、细菌耐药情况：指感染部位病原菌药敏结果，如耐药（包括药敏结果为中介者）在耐药上划“√”，敏感则在敏感上划“√”，未做调查中所列抗菌药物在未做上划“√”。若多次培养出相同细菌，以第一次培养细菌的药敏结果判断。</p>',
	//抗菌药物使用情况
	antimicrobialUse : '<p>11、抗菌药物使用情况：</b>是指相应调查日（分次调查的单位，注意各科相应的调查日是不同的）的抗菌药物的使用情况，调查日之前的不计。不包括抗结核治疗药物；不包括抗菌药物的雾化吸入；不包括抗病毒药物（如无环鸟苷、病毒唑等）；不包括眼科（抗菌药物滴眼）、耳鼻喉科（耳、鼻的滴药）、烧伤科（烧伤部位抗菌药物覆盖）等局部用药；不包括抗真菌药物。</p> ' + 
		'<p>12、抗菌药物名称：</b>调查日使用的抗菌药物名称（如当日有停用和新开抗菌药物，则填写新开抗菌药物）。</p>' + 
		'<p>13、目的：</b>单纯用于治疗者归为治疗用药，单纯用于预防者归为预防用药，若两者兼有则归入预防+治疗。不能确定者，可询问病室主管医生。</p>' + 
		'<p>14、联用：</b>调查当日使用不同抗菌药物的数目。</p>' + 
		'<p>15、细菌培养：</b>凡治疗用药者（包括“预防+治疗”用药者）均必须注明是否送细菌培养。单纯预防用药和未用抗菌药物者不填写。其中明确是否使用抗菌药物前送细菌培养。</p>' + 
		'<p>16、调查注意事项：</b></br>' +
		'1）、注意调查过的患者是否存在转科情况，已经调查过的患者不要重复调查。</br>' +
		'2）、每一调查对象均需填写个案调查表。</br>' +
		'3）、细菌培养只须将治疗用药者（包括“预防+治疗”用药者）注明是否送细菌培养，单纯预防用药和未用抗菌药物者不得计入其中，即细菌培养做和未做的合计数应等于（抗菌药物使用人数减去单纯预防用药人数）。</p>',
	//危险因素
	riskFactor : '<p>17、</b></br> 1）、接受放射治疗指用放射线治疗癌症。</br>'+
		'2）、接受抗肿瘤化学治疗指使用化学药物进行抗肿瘤治疗。'
	
};

/**
 * 选择病原体处理
 */
var choosePathogens = {
	pnum : '',
	num : '',
	xl2ListName : '',
	//行div开头
	rowDivStart : '<div class="div_row" style="margin: 0px 5px">',
	//需要选择药敏结果的病原体ID
	disPathoid : new Array('01','03', '40', '41', '04', '10', '43', '20', '45'),
	//需要验证的药敏结果ID
	drugStatusIds : new Array(),
	//苯唑西林-头孢西丁              *********凝固酶阴性葡萄球菌/金黄色葡萄球菌
	bytid1 : '',
	//氨苄西林-万古霉素              *********屎肠球菌/粪肠球菌
	bytid2 : '',
	//青霉素G       *********肺炎链球菌
	bytid3 : '',
	//亚胺培南/美罗培南-头孢他啶  左氧氟沙星              *********肺炎克雷伯菌/大肠埃希菌
	bytid4 : '',
	//亚胺培南/美罗培南-哌拉西林/他唑巴坦     头孢他啶-头孢吡肟    阿米卡星-环丙沙星               *********铜绿假单胞菌
	bytid5 : '',
	//亚胺培南/美罗培南-头孢哌酮/舒巴坦              *********鲍曼不动杆菌
	bytid6 : '',
	//病原体ID-药敏结果MAP
	pathoMap : {},
	//初始化信息
	init : function(pathoid) {
		if ($.inArray(pathoid, ['01', '03']) != -1) {
			this.bytid1 = '<div class="drug_results">' + this.rowDivStart + this.getDrugResultsHtml(0, '苯唑西林', '19006', true, true) + this.getDrugResultsHtml(1, '头孢西丁', '19074', false, false) + '</div></div>';
		} else if ($.inArray(pathoid, ['40', '41']) != -1) {
			this.bytid2 = '<div class="drug_results">' + this.rowDivStart + this.getDrugResultsHtml(0, '氨苄西林', '19010', true, true) + this.getDrugResultsHtml(1, '万古霉素', '19105', false, false) + '</div></div>';
		} else if (pathoid == '04') {
			this.bytid3 = '<div class="drug_results">' + this.rowDivStart + this.getDrugResultsHtml(0, '青霉素G', '01001', true, true) + '</div></div>';
		} else if ($.inArray(pathoid, ['10', '43']) != -1) {
			this.bytid4 = '<div class="drug_results">' + this.rowDivStart + this.getDrugResultsHtml(1, '亚胺培南/美罗培南', '03202', true, true) + this.getDrugResultsHtml(0, '头孢他啶', '19058', false, false) + '</div>'
					+ this.rowDivStart + this.getDrugResultsHtml(2, '左氧氟沙星', '19152', true, false) + '</div></div>';
		} else if (pathoid == '20') {
			this.bytid5 = '<div class="drug_results">' + this.rowDivStart + this.getDrugResultsHtml(3, '亚胺培南/美罗培南', '03202', true, true) + this.getDrugResultsHtml(1, '哌拉西林/他唑巴坦', '19022', false, false) + '</div>'
					+ this.rowDivStart + this.getDrugResultsHtml(2, '头孢他啶', '19058', true, false) + this.getDrugResultsHtml(4, '头孢吡肟', '19070', false, false) + '</div>'
					+ this.rowDivStart + this.getDrugResultsHtml(5, '阿米卡星', '19094', true, false) + this.getDrugResultsHtml(0, '环丙沙星', '19143', false, false) + '</div></div>';
		} else if (pathoid == '45') {
			this.bytid6 = '<div class="drug_results">' + this.rowDivStart + this.getDrugResultsHtml(1, '亚胺培南/美罗培南', '03202', true, true) + this.getDrugResultsHtml(0, '头孢哌酮/舒巴坦', '19069', false, false) + '</div></div>';
		}
		this.pathoMap = {'01' : this.bytid1, '03' : this.bytid1, '40' : this.bytid2, '41' : this.bytid2, '04' : this.bytid3, '10' : this.bytid4, '43' : this.bytid4, '20' : this.bytid5, '45' : this.bytid6};
	},
	//获取药敏结果html
	getDrugResultsHtml : function(index, durgName, durgId, isOne, isTitle) {
		var htmlStr = '';
		if (isOne) {
			htmlStr += '<span class="row_title">';
			if (isTitle) {
				htmlStr += '<b>药敏结果</b>';
			}
			htmlStr += '</span>';
		}
		var drugStatusId = 'id_drug_result_' + this.pnum + '_' + this.num + '_' + index;
		htmlStr += '<span style="width: 110px; text-align: right;">' + durgName + '</span><span style="width: 90px;"><input type="hidden" name="' + this.xl2ListName + '[' + this.pnum + '].xl003List[' + this.num + '].xl004List[' + index + '].yjywid" /><input type="hidden" name="' + this.xl2ListName + '[' + this.pnum + '].xl003List[' + this.num + '].xl004List[' + index + '].drugId" value="' + durgId + '" /><input type="hidden" name="' + this.xl2ListName + '[' + this.pnum + '].xl003List[' + this.num + '].xl004List[' + index + '].drugName" value="' + durgName + '" /><select id="' + drugStatusId + '" name="' + this.xl2ListName + '[' + this.pnum + '].xl003List[' + this.num + '].xl004List[' + index + '].status" style="width: 83px;" class="require"><option value=""></option><option value="未做">未做</option><option value="耐药">耐药</option><option value="敏感">敏感</option><option value="中介">中介</option></select></span>';
		this.drugStatusIds.push(drugStatusId);
		return htmlStr; 
	},
	//设置药敏结果
	setDrugResults : function(id, type, pathoid) {
		var reObj = $('#' + id).parent().parent().next();
		if (reObj && reObj.hasClass("drug_results")) {
			reObj.remove();
		}
		//病原体ID存在
		var index = $.inArray(pathoid, this.disPathoid);
		if (index != -1) {
			//清除原来的数据
			this.drugStatusIds.length = 0;
			//插入目标对象
			var object = $('#' + id).parent().parent().parent();
			this.num = object.attr('num');
			this.pnum = object.attr('pnum');
			if ('unit' == type) {
				this.xl2ListName = 'xl2ListU';
			} else {
				this.xl2ListName = 'xl2ListC';
			}
			//初始化变量
			choosePathogens.init(pathoid);
			//获取写入的html
			object.append(this.pathoMap[pathoid]);
			//加入必填验证
			for (var i = 0; i < this.drugStatusIds.length; i++) {
				$('#' + this.drugStatusIds[i]).combobox({
				    required: true,
				    editable : false
				});
			}
		}
	}
};