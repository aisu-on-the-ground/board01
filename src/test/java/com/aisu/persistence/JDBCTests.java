package com.aisu.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	static {
		try {
			Class cls = Class.forName("oracle.jdbc.driver.OracleDriver");
			log.info(cls);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() throws Exception {
			
		try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "board_ex", "board_ex")) {
			log.info(conn);
		} catch(Exception e) {
//			e.printStackTrace();
			fail(e.getMessage());	
		} 
	}
}
