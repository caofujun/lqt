package com.nis.comm.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class k {
	public void aq(String batName) {
		try {
			File e = new File(batName);
			if (!e.exists()) {
				return;
			}

			Process ps = Runtime.getRuntime().exec(batName);
			InputStream in = ps.getInputStream();

			while (in.read() != -1) {
				;
			}

			in.close();
			ps.waitFor();
		} catch (IOException arg5) {
			arg5.printStackTrace();
		} catch (InterruptedException arg6) {
			arg6.printStackTrace();
		}

	}

	public static void main(String[] args) {
		k test1 = new k();
		String batName = "C:\\Users\\Administrator\\Desktop\\nyTeleActiveX\\install.bat";
		test1.aq(batName);
		System.out.println("main thread");
	}
}