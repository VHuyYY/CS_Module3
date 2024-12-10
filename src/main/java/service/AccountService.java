package service;

import configuration.ConnectDataBase;
import entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountService implements IAccountService<Account> {
    private final Connection connection;

    public AccountService() {
        this.connection = ConnectDataBase.getConnection();
    }

    @Override
    public Account login(String name, String pass) {
        String sql = "select * from account where account = ? and password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pass);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Account(
                        resultSet.getInt("id"),
                        resultSet.getString("account"),
                        resultSet.getString("password"),
                        resultSet.getInt("role")
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
