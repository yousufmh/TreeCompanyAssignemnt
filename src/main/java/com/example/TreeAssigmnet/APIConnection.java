package com.example.TreeAssigmnet;


import com.example.TreeAssigmnet.Model.Account;
import com.example.TreeAssigmnet.Model.SessionStatus;
import com.example.TreeAssigmnet.Model.Statment;
import com.example.TreeAssigmnet.Model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class APIConnection {


    @Autowired
    HttpSession session;

    @Autowired
    DBconnection db;

    @RequestMapping(value = "login")
    public SessionStatus login (@RequestParam String username, @RequestParam String password){

        User user = new User(username,password);

        if (!session.isNew()){
           User oldUser= (User) session.getAttribute("User");
           if (oldUser!=null && oldUser.getUsername().equals(username)){
               return new SessionStatus(401,"User already Logged in, Please Logout and login again");
           }

        }

        if (username.equals("admin") && password.equals("admin")){
            session.setAttribute("User",user);
            return new SessionStatus(200,"Admin logged in Successfully ");
        }else if (username.equals("user") && password.equals("user")){
            session.setAttribute("User",user);
            return new SessionStatus(200,"User logged in Successfully ");
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong username or password");
        }
    }

    @RequestMapping(value = "logout")
    public SessionStatus logout () {

        session.invalidate();

        return new SessionStatus(200,"User logged out Successfully ");

    }


        @RequestMapping(value = "fetchStatements",produces = "application/JSON")
    public ArrayList<Statment> fetchStatements(@RequestParam( defaultValue = "") String accountID,
                                    @RequestParam(required = false, defaultValue = "",name = "dateFrom") String dateFrom,
                                    @RequestParam(required = false, defaultValue = "",name = "dateTo") String dateTo,
                                    @RequestParam(required = false, defaultValue = "",name = "amountFrom") String amountFrom,
                                    @RequestParam(required = false, defaultValue = "",name = "amountTo") String amountTo
                                    ){

        if (session.isNew() || session.getAttribute("User")==null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You need to login");
        }



        User user = (User) session.getAttribute("User");



        Account account= db.fetchAccount(accountID);
        ArrayList<Statment> statements = db.fetchStatements(account);
        ArrayList<Statment> finalStatments = new ArrayList<>();

        boolean parameterEmpty =  dateFrom.isBlank() && dateTo.isBlank()
                                 && amountFrom.isBlank() && amountTo.isBlank();



            switch (user.getUsername()) {
                case "user" -> {
                    if (parameterEmpty) {
                        statements.sort(Comparator.comparing(Statment::getDateField));

                        for (int i =statements.size()-1; i>statements.size()-4; i--){
                            finalStatments.add(statements.get(i));
                        }
                        return finalStatments;

                    } else {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authrized");
                    }
                }
                case "admin" -> {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
                    LocalDate dateToD,datefromD;
                    if (!dateFrom.isBlank() && !dateTo.isBlank()) {

                            datefromD = LocalDate.parse(dateFrom,formatter);
                            dateToD = LocalDate.parse(dateTo,formatter);

                        statements.sort(Comparator.comparing(Statment::getDateField));

                         for (int i = 0; i < statements.size() - 1; i++) {

                            if (statements.get(i).getDateField().isAfter(datefromD) && statements.get(i).getDateField().isBefore(dateToD)) {

                                finalStatments.add(statements.get(i));
                            }
                        }
                    }
                    if (!amountFrom.isBlank() && !amountTo.isBlank()) {
                        double amountfromD = 0,amountToD = 0;
                        try {
                            amountfromD = Double.parseDouble(amountFrom);
                            amountToD = Double.parseDouble(amountTo);
                        }catch (NumberFormatException e){
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "date formate incorrect");
                        }
                        statements.sort(Comparator.comparing(Statment::getDateField));

                        for (int i = 0; i < statements.size() - 1; i++) {
                            if (statements.get(i).getAmount()>amountfromD && statements.get(i).getAmount()<amountToD)
                                finalStatments.add(statements.get(i));
                            }
                        }
                    return finalStatments;

                }

                default ->
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authrized");


            }


    }



}
