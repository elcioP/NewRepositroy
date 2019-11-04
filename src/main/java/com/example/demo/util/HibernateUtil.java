package com.example.demo.util;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.fasterxml.classmate.AnnotationConfiguration;

public class HibernateUtil {

	private static  SessionFactory session;
	private static Session s;

	public static Session getSession() {
		Configuration cfg = new Configuration()
		.setProperty("hibernate.dialect",
				"org.hibernate.dialect.H2Dialect")
		.setProperty("hibernate.connection.datasource",
				"java:comp/env/jdbc/test")
		.setProperty("hibernate.order_updates", "true")
		.setProperty("hibernate.connection.driver_class",
				"org.h2.Driver")
		.setProperty("hibernate.connection.username", "sa")
		.setProperty("hibernate.connection.username", "")
		.setProperty("hibernate.connection.pool_size", "1")
		.setProperty("hibernate.show_sql", "true");
		session = cfg.buildSessionFactory();
		
		 s = session.openSession();
		
		return s;

	}


	public void closeSession() {
		session.close();
	}

}
