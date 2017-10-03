package com.nis.organization.units;

import com.nis.organization.entity.Dep;
import java.util.Comparator;

public class a implements Comparator<Dep> {
	public int compare(Dep o1, Dep o2) {
		return o2.getIsHavegrant().compareTo(o1.getIsHavegrant());
	}
}