package com.example.TreeAssigmnet;


import com.example.TreeAssigmnet.Model.Account;
import com.example.TreeAssigmnet.Model.Statment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
@Transactional
public class DBconnection {

    @Autowired
    private JdbcTemplate template;

    public Account fetchAccount(String accountNum){

        Account account = template.queryForObject("SELECT * FROM account where account_number = ?",new Object[]{accountNum},new int[]{1},
                (rs, rowNum) -> new Account(rs.getInt("ID"), rs.getString("account_type"), rs.getString("account_number")));

        System.out.println(account);

        return account;
    }

    public ArrayList<Statment> fetchStatements(Account account){

        ArrayList<Statment> statements;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);


        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(account.getAccountNumber().getBytes());
        String accountNumberHash = new String(messageDigest.digest());
            statements = (ArrayList<Statment>) template.query("SELECT * from statement where account_id = ? ", new Object[]{account.getID()}, new int[]{1},
                    (rs, rowNum) -> {

                            return new Statment(rs.getInt("ID"), rs.getInt("account_id"), LocalDate.parse(rs.getString("datefield"), formatter), Double.parseDouble(rs.getString("amount")), accountNumberHash);

                    });

        System.out.println(statements);

        return statements;
    }




}
