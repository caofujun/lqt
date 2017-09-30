package com.nis.comm.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class n {
	private String pG;
	private int pH = 137;
	private byte[] buffer = new byte[1024];
	private DatagramSocket pI = null;

	public n(String strAddr) throws Exception {
		this.pG = strAddr;
		this.pI = new DatagramSocket();
	}

	protected final DatagramPacket c(byte[] bytes) throws IOException {
		DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(this.pG), this.pH);
		this.pI.send(dp);
		return dp;
	}

	protected final DatagramPacket z() throws Exception {
		DatagramPacket dp = new DatagramPacket(this.buffer, this.buffer.length);
		this.pI.receive(dp);
		return dp;
	}

	protected byte[] A() throws Exception {
		byte[] t_ns = new byte[50];
		t_ns[0] = 0;
		t_ns[1] = 0;
		t_ns[2] = 0;
		t_ns[3] = 16;
		t_ns[4] = 0;
		t_ns[5] = 1;
		t_ns[6] = 0;
		t_ns[7] = 0;
		t_ns[8] = 0;
		t_ns[9] = 0;
		t_ns[10] = 0;
		t_ns[11] = 0;
		t_ns[12] = 32;
		t_ns[13] = 67;
		t_ns[14] = 75;

		for (int i = 15; i < 45; ++i) {
			t_ns[i] = 65;
		}

		t_ns[45] = 0;
		t_ns[46] = 0;
		t_ns[47] = 33;
		t_ns[48] = 0;
		t_ns[49] = 1;
		return t_ns;
	}

	protected final String d(byte[] brevdata) throws Exception {
		int i = brevdata[56] * 18 + 56;
		String sAddr = "";
		StringBuffer sb = new StringBuffer(17);

		for (int j = 1; j < 7; ++j) {
			sAddr = Integer.toHexString(255 & brevdata[i + j]);
			if (sAddr.length() < 2) {
				sb.append(0);
			}

			sb.append(sAddr.toUpperCase());
			if (j < 6) {
				sb.append(':');
			}
		}

		return sb.toString();
	}

	public final void close() {
		try {
			this.pI.close();
		} catch (Exception arg1) {
			arg1.printStackTrace();
		}

	}

	public final String B() throws Exception {
		byte[] bqcmd = this.A();
		this.c(bqcmd);
		DatagramPacket dp = this.z();
		String smac = this.d(dp.getData());
		this.close();
		return smac;
	}
}