package vn.edu.iuh.fit.repositories;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.GrantAccess;
import vn.edu.iuh.fit.entities.Role;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class AccountRepositories {
    private Connection connection;

    public AccountRepositories() {

        connection = DatabaseRepository.getConnection();
    }

    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * from mydb.account");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Account account = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                list.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Account getAccount(String name, String password) {

        PreparedStatement statement = null;
        Account ac = null;
        try {
            statement = connection.prepareStatement("SELECT mydb.account.* , mydb.grant_access.note FROM mydb.account INNER JOIN mydb.grant_access ON account.account_id = grant_access.account_id  where account.account_id = ? and password = ?");
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ac = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ac;
    }

    public GrantAccess getAccountRole(String username, String password) {
        PreparedStatement statement = null;
        GrantAccess grantAccess = null;
        try {
            statement = connection.prepareStatement("SELECT account.*,role.role_id,role.role_name, grant_access.is_grant " + "FROM mydb.grant_access INNER JOIN mydb.account ON grant_access.account_id = account.account_id \n" + "INNER JOIN mydb.role ON grant_access.role_id = role.role_id where account.account_id = ? and password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                grantAccess = new GrantAccess(new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)), new Role(rs.getString(7), rs.getString(8)), rs.getInt(9));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return grantAccess;
    }

    public List<GrantAccess> getDsAccountRole() {

        PreparedStatement statement = null;
        List<GrantAccess> dsAccount = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT mydb.account.*,mydb.role.role_id,mydb.role.role_name, mydb.grant_access.is_grant FROM mydb.grant_access INNER JOIN mydb.account ON grant_access.account_id = account.account_id \n" + "INNER JOIN mydb.role ON grant_access.role_id = role.role_id where account.account_id = ? and password = ?");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                GrantAccess grantAccess = new GrantAccess(new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)), new Role(rs.getString(7), rs.getString(8)), rs.getInt(9));
                dsAccount.add(grantAccess);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsAccount;
    }

    public Account accountLogin(String name, String pass) {
        Account ac = null;
        try {
            // Truy vấn để tìm tài khoản dựa trên name và pass
            String selectQuery = "SELECT * FROM mydb.account WHERE account_id = ? AND password = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, name);
            selectStatement.setString(2, pass);
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {
                String updateQuery = "UPDATE mydb.account SET status = 1 WHERE account_id = ? AND password = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setString(1, name);
                updateStatement.setString(2, pass);
                updateStatement.executeUpdate();
                ac = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return ac;
    }
    public boolean accountLogout(String userName) {
        try {
                String updateQuery = "UPDATE mydb.account SET status = 0 WHERE account_id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setString(1, userName);
                updateStatement.executeUpdate();
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    }

}

