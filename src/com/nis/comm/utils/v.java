package com.nis.comm.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;

public class v {
	public static void main(String[] args) throws Exception {
	}

	public static String aI(String rtfString) throws Exception {
		RTFEditorKit kit = new RTFEditorKit();
		Document doc = kit.createDefaultDocument();
		ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(rtfString.getBytes());
		InputStreamReader in = new InputStreamReader(tInputStringStream, "utf-8");
		kit.read(in, doc, 0);
		String result = doc.getText(0, doc.getLength());
		return result;
	}
}