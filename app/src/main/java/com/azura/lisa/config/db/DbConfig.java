package com.azura.lisa.config.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {
	protected String DB_URL;
	protected String DB_USER;
	protected String DB_PASSWORD;
	protected String DB_DRIVER = "db.driver";
	protected String DB_DIALECT = "db.dialect";
	
	@Autowired
	protected Environment env;
	
	protected HibernateJpaVendorAdapter vendorAdaptor() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabasePlatform(env.getProperty(DB_DIALECT));
		vendorAdapter.setShowSql(true);
		return vendorAdapter;
	}
}
