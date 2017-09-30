package com.nis.comm.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public final class ah {
	public static Document createDocument() {
		try {
			return DocumentHelper.createDocument();
		} catch (Exception arg0) {
			arg0.printStackTrace();
			return null;
		}
	}

	public static Document bg(String fileName) {
		try {
			return (new SAXReader()).read(new File(fileName));
		} catch (Exception arg1) {
			arg1.printStackTrace();
			return null;
		}
	}

	public static Document bh(String docStr) {
		try {
			return DocumentHelper.parseText(docStr.trim());
		} catch (DocumentException arg1) {
			arg1.printStackTrace();
			return null;
		}
	}

	public static Element a(Document doc) {
		return doc.getRootElement();
	}

	public static List<Element> a(Element ele) {
		ArrayList elementList = new ArrayList();
		Iterator i = ele.elementIterator();

		while (i.hasNext()) {
			elementList.add((Element) i.next());
		}

		return elementList;
	}

	public static void a(Document document, String fileName) {
		XMLWriter writer = null;
		OutputFormat format = null;

		try {
			format = OutputFormat.createPrettyPrint();
			writer = new XMLWriter(new FileWriter(fileName), format);
			writer.write(document);
			writer.close();
		} catch (Exception arg4) {
			arg4.printStackTrace();
		}

	}

	public static String e(Map map) {
		return k(map, "node");
	}

	public static String k(Map map, String node) {
		Document document = DocumentHelper.createDocument();
		Element nodeElement = document.addElement(node);
		Iterator arg4 = map.keySet().iterator();

		while (arg4.hasNext()) {
			Object obj = arg4.next();
			Element keyElement = nodeElement.addElement("key");
			keyElement.addAttribute("label", String.valueOf(obj));
			keyElement.setText(String.valueOf(map.get(obj)));
		}

		return b(document);
	}

	public static String y(List list) throws Exception {
		return a(list, "nodes", "node");
	}

	public static String a(List list, String nodes, String node) throws Exception {
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("REQUEST");
		Element nodesElement = rootElement.addElement(nodes);
		int i = 0;

		for (Iterator arg7 = list.iterator(); arg7.hasNext(); ++i) {
			Object o = arg7.next();
			Element nodeElement = nodesElement.addElement(node);
			if (o instanceof Map) {
				Iterator arg10 = ((Map) o).keySet().iterator();

				while (arg10.hasNext()) {
					Object arg12 = arg10.next();
					Element keyElement1 = nodeElement.addElement(String.valueOf(arg12).toUpperCase());
					keyElement1.setText(String.valueOf(((Map) o).get(arg12)));
				}
			} else {
				Element keyElement = nodeElement.addElement("key");
				keyElement.addAttribute("label", String.valueOf(i));
				keyElement.setText(String.valueOf(o));
			}
		}

		return b(document);
	}

	public static Map bi(String xml) {
		try {
			HashMap e = new HashMap();
			Document document = DocumentHelper.parseText(xml);
			Element nodeElement = document.getRootElement();
			List node = nodeElement.elements();

			Element elm;
			for (Iterator it = node.iterator(); it.hasNext(); elm = null) {
				elm = (Element) it.next();
				e.put(elm.attributeValue("label"), elm.getText());
			}

			node = null;
			nodeElement = null;
			document = null;
			return e;
		} catch (Exception arg6) {
			arg6.printStackTrace();
			return null;
		}
	}

	public static List bj(String xml) {
		try {
			ArrayList e = new ArrayList();
			Document document = DocumentHelper.parseText(xml);
			Element nodesElement = document.getRootElement();
			List nodes = nodesElement.elements();

			Map map;
			for (Iterator its = nodes.iterator(); its.hasNext(); map = null) {
				Element nodeElement = (Element) its.next();
				map = bi(nodeElement.asXML());
				e.add(map);
			}

			nodes = null;
			nodesElement = null;
			document = null;
			return e;
		} catch (Exception arg7) {
			arg7.printStackTrace();
			return null;
		}
	}

	public static String b(Document document) {
		String s = "";

		try {
			ByteArrayOutputStream ex = new ByteArrayOutputStream();
			OutputFormat format = new OutputFormat("   ", true, "UTF-8");
			XMLWriter writer = new XMLWriter(ex, format);
			writer.write(document);
			s = ex.toString("UTF-8");
		} catch (Exception arg4) {
			arg4.printStackTrace();
		}

		return s;
	}

	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		HashMap cdcMap1 = new HashMap();
		cdcMap1.put("a", "atext");
		cdcMap1.put("b", "btext");
		cdcMap1.put("c", "ctext");
		list.add(cdcMap1);
		cdcMap1 = new HashMap();
		cdcMap1.put("a", "atext");
		cdcMap1.put("b", "btext");
		cdcMap1.put("c", "ctext");
		list.add(cdcMap1);
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><s:schema xmlns:s=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.zhong.cn\" ><s:HGroup  gap=\"50\" horizontalCenter=\"0\" top=\"10\"><s:Panel width=\"200\" height=\"200\" title=\"Spark Panel\" ></s:Panel></s:HGroup></s:schema>";
		System.out.println(xml);
		Document doc = bh(xml);
		Element root = doc.getRootElement();
		Element element = (Element) root.selectNodes("//s:Panel").get(0);
		Element row = null;
		Iterator arg8 = list.iterator();

		while (arg8.hasNext()) {
			Map map = (Map) arg8.next();
			row = element.addElement("row");
			Iterator arg10 = map.keySet().iterator();

			while (arg10.hasNext()) {
				String str = (String) arg10.next();
				row.addAttribute(str, (String) map.get(str));
			}
		}

		System.out.println(doc.asXML());
	}
}