package vn.edu.iuh.fit.week1;


import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.repositories.DatabaseRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        DatabaseRepository data = new DatabaseRepository();

        //GetAll
        List<Account> list = data.getAll();
        list.forEach(x->System.err.println(x));
        //Add
//        Account acc = new Account("an123","Phan Truong An","123","phanan45@gmail.com","021478563",1);
//        data.addAccount(acc);
        //Delete
//       data.delAccountById("an123");
        //Update
//        data.updateAccount(new Account("an123","Phan Truong An","123","phanan@gmail.com","021478563",1));
        //Find By UserName and Password
//        String acc = data.findAccByUserPass("an123","123");
//        System.err.println(acc);
        //GrandAccess

//        GrantAccess gr = data.getAccountRole("met","123");
//        System.err.println(gr);

    }
}