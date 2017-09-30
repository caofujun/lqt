package com.nis.pdca.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class a {
	private Configuration wG = null;

	public a() {
		this.wG = new Configuration();
		this.wG.setDefaultEncoding("UTF-8");
	}

	public static void main(String[] args) {
		a test = new a();
		test.aA();
	}

	public void aA() {
		HashMap dataMap = new HashMap();
		this.f(dataMap);
		this.wG.setClassForTemplateLoading(this.getClass(), "src/main/java/template/");
		Template t = null;

		try {
			t = this.wG.getTemplate("wordmodel.ftl");
		} catch (IOException arg8) {
			arg8.printStackTrace();
		}

		File outFile = new File("D:/outFilessa" + Math.random() * 10000.0D + ".doc");
		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
		} catch (FileNotFoundException arg7) {
			arg7.printStackTrace();
		}

		try {
			t.process(dataMap, out);
		} catch (TemplateException arg5) {
			arg5.printStackTrace();
		} catch (IOException arg6) {
			arg6.printStackTrace();
		}

	}

	private void f(Map<String, Object> dataMap) {
		dataMap.put("title", "标题");
		dataMap.put("year", "2012");
		dataMap.put("month", "2");
		dataMap.put("day", "13");
		dataMap.put("auditor", "唐鑫");
		dataMap.put("phone", "13020265912");
		dataMap.put("weave", "占文涛");
		ArrayList list = new ArrayList();

		for (int i = 0; i < 10; ++i) {
			HashMap map = new HashMap();
			map.put("number", Integer.valueOf(i));
			map.put("content", "内容" + i);
			list.add(map);
		}

		dataMap.put("list", list);
	}
}