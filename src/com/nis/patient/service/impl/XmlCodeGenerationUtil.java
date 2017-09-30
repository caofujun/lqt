package com.nis.patient.service.impl;

public class XmlCodeGenerationUtil {
	public static void M(String str, String beanName) {
		String[] strs = str.split(",");
		String[] arg5 = strs;
		int arg4 = strs.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String s = arg5[arg3];
			String outStr = "<when test=\"item==\'" + s.substring(s.indexOf("{") + 1, s.indexOf("}")) + "\'\">"
					+ s.substring(0, s.indexOf("{") + 1) + beanName + "." + s.substring(s.indexOf("{") + 1)
					+ ",</when>";
			System.out.println(outStr);
		}

	}

	public static void main(String[] args) {
		String str = "id=#{id},show_id=#{showId},audit_flag=#{auditFlag},break_start_date=#{breakStartDate},break_end_date=#{breakEndDate},break_count=#{breakCount},create_date=#{createDate},moni_date=#{moniDate},dept_id=#{deptId},break_type=#{breakType},read_flag=#{readFlag},audit_id=#{auditId},audit_date=#{auditDate},line=#{line},observe_line=#{observeLine},mulriple=#{mulriple},observe_day=#{observeDay},is_warn=#{isWarn},audit_name=#{auditName}";
		M(str, "by007Bfjl");
	}
}