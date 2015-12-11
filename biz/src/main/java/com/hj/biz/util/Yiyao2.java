//package com.hj.biz.util;
//
//import com.mchange.v2.c3p0.ComboPooledDataSource;
//import com.mysql.jdbc.Driver;
//
//import java.beans.PropertyVetoException;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Yiyao2 {
//
//	public static void main(String[] args) throws SQLException {
//		Connection con1 = getPool().getConnection();
//		Statement statement = con1.createStatement();
//		ResultSet rs = statement.executeQuery("select * from cmsb_qy_name");
//		BlockingQueue<String> names = new LinkedBlockingQueue<String>();
//		while (rs.next()) {
//			String name = rs.getString("qyname");
//			names.offer(name);
//			System.out.println(name);
//		}
//		rs.close();
//		statement.close();
//		con1.close();
//		ExecutorService es = Executors.newFixedThreadPool(5);
//		while (true) {
//			final String name = names.poll();
//			if (name == null) {
//				break;
//			}
//			es.execute(new Runnable() {
//
//				@Override
//				public void run() {
//					Connection con = null;
//					try {
//						con = getPool().getConnection();
//						if (isChineseChar(name)) {
//							Statement s1 = con.createStatement();
//							int count = 0;
//							ResultSet r1 = s1.executeQuery("select count(*) from sfda_gcyp where sfda_gcyp.scdw = \"" + name + "\"");
//							while (r1.next()) {
//								count += r1.getInt(1);
//							}
//							r1.close();
//							ResultSet r2 = s1.executeQuery("select count(*) from sfda_jkyp where sfda_jkyp.sccs_ch = \"" + name + "\"");
//							while (r2.next()) {
//								count += r2.getInt(1);
//							}
//							r2.close();
//							s1.close();
//							update(count, name, con);
//						} else {
//							Statement s1 = con.createStatement();
//							int count = 0;
//							ResultSet r1 = s1.executeQuery("select count(*) from sfda_gcyp where sfda_gcyp.scdw = \"" + name + "\"");
//							while (r1.next()) {
//								count += r1.getInt(1);
//							}
//							r1.close();
//							ResultSet r2 = s1.executeQuery("select count(*) from sfda_jkyp where sfda_jkyp.sccs_en = \"" + name + "\"");
//							while (r2.next()) {
//								count += r2.getInt(1);
//							}
//							r2.close();
//							s1.close();
//							update(count, name, con);
//						}
//					} catch (SQLException e) {
//						e.printStackTrace();
//					} finally {
//						if (con != null) {
//							try {
//								con.close();
//							} catch (SQLException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//					}
//
//				}
//			});
//		}
//
//	}
//
//	private static void update(int count, String name, Connection con) throws SQLException {
//		Statement s2 = con.createStatement();
//		System.out.println(name + "      " + count);
//		s2.executeUpdate("update cmsb_qy_name set cnt = " + count + " where qyname= \"" + name + "\"");
//		s2.close();
//	}
//
//	public static boolean isChineseChar(String str) {
//		boolean temp = false;
//		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
//		Matcher m = p.matcher(str);
//		if (m.find()) {
//			temp = true;
//		}
//		return temp;
//	}
//
//	private static volatile ComboPooledDataSource cpds;
//
//	public static ComboPooledDataSource getPool() {
//		if (cpds == null) {
//			synchronized (Yiyao2.class) {
//				if (cpds == null) {
//					cpds = new ComboPooledDataSource();
//					try {
//						cpds.setDriverClass(Driver.class.getName());
//					} catch (PropertyVetoException e) {
//						throw new IllegalArgumentException("driver cannot found!");
//					}
//					cpds.setJdbcUrl("jdbc:mysql://yanatzhou520.mysql.rds.aliyuncs.com/yiyao?characterEncoding=utf8");
//					cpds.setUser("yanatzhou520");
//					cpds.setPassword("yanatzhou520");
//					cpds.setMaxPoolSize(20);
//					cpds.setMaxStatements(180);
//					cpds.setMaxPoolSize(20);
//				}
//			}
//		}
//		return cpds;
//	}
//
//}
