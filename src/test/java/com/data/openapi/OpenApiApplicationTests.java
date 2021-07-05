package com.data.openapi;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import com.data.openapi.repository.DefaultRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootTest
class OpenApiApplicationTests {

	private DefaultRepository defaultRepository;

	@Autowired
	private ApplicationContext context;

	@Autowired
	public void setDefaultRepository(DefaultRepository defaultRepository) {
		this.defaultRepository = defaultRepository;
	}

	Logger logger = LoggerFactory.getLogger(OpenApiApplicationTests.class);


	@Test
	void contextLoads() {
	}

	@Test
	void selectUserById() throws JsonMappingException, JsonProcessingException, SQLException {

		DriverManagerDataSource db = new DriverManagerDataSource();
        db.setDriverClassName("org.postgresql.Driver");
        db.setUrl("jdbc:postgresql://localhost:5432/dbtest");
        db.setUsername("postgresql");
        db.setPassword("qwerqwer");

		DataSource ds = (DataSource) context.getBean("dataSource");
		ds = db;
		System.out.println("DS = " + ds);

		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, ds);

		Configuration configuration = new Configuration(environment);
		configuration.setLazyLoadingEnabled(true);

		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

		SqlSessionFactory bean = (SqlSessionFactory) context.getBean("sqlSessionFactory");
		bean = builder.build(configuration);

		// DataSourceTransactionManager manager = (DataSourceTransactionManager) context.getBean("dataSourceTransactionManager");
		// System.out.println("MENAGER = " + manager);
		// System.out.println("DATASOURCE = " + manager.getDataSource());
		// System.out.println("CONN = " + manager.getDataSource().getConnection().toString());

		// manager.setDataSource(ds);

		Map<String, Object> result = defaultRepository.selectById("users", 3);

		//ObjectMapper mapper = new ObjectMapper();
		//Map<String, Object> obj = mapper.readValue(result.toString(), new TypeReference<Map<String, Object>>(){});

		logger.info("Result = " + result);

	}

}
