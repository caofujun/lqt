package com.nis.comm.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class y {
	public static void main(String[] args) {
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:ZG006.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet e = stmt.executeQuery("SELECT * FROM zg006;");

			while (e.next()) {
				int id = e.getInt("mx_id");
				String name = e.getString("infect_code");
				System.out.println("ID = " + id);
				System.out.println("NAME = " + name);
				System.out.println();
			}

			e.close();
			stmt.close();
			c.close();
		} catch (Exception arg5) {
			System.err.println(arg5.getClass().getName() + ": " + arg5.getMessage());
			System.exit(0);
		}

		System.out.println("Operation done successfully");
	}
}