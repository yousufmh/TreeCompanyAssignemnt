package com.example.TreeAssigmnet;

import com.example.TreeAssigmnet.Model.Account;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.context.annotation.SessionScope;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class TreeAssigmnetApplication {

	@Bean
	@SessionScope
	public HttpSession session(HttpServletRequest request){
		HttpSession session =request.getSession();
		session.setMaxInactiveInterval(5*60);
		return session;
	}

	public static void main(String[] args) {
		SpringApplication.run(TreeAssigmnetApplication.class, args);
	}

}
