package com.nis.comm.service.impl;

import java.io.Serializable;

public class Sequence implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final Long oQ = Long.valueOf(-1L);
	private Long oR = Long.valueOf(-1L);
	private Long oS = Long.valueOf(0L);
	private Long oT = Long.valueOf(100L);

	public Sequence(Long minIndex, Long maxIndex) {
		this.oS = minIndex;
		this.oT = maxIndex;
		this.oR = minIndex;
	}

	public Long getMinIndex() {
		return this.oS;
	}

	public Long getMaxIndex() {
		return this.oT;
	}

	public Long getNextSeq() {
		if (this.oR != null && this.oR.longValue() >= 0L) {
			this.oR = Long.valueOf(this.oR.longValue() + 1L);
			return this.oR.longValue() <= this.oT.longValue() ? this.oR : oQ;
		} else {
			return oQ;
		}
	}
}