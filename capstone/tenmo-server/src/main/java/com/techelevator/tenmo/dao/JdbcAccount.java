package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.techelevator.tenmo.dao.JdbcUserDao;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccount implements AccountDao {

    private final JdbcTemplate jdbcTemplate;



    public JdbcAccount(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT account_id, user_id, balance FROM account";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            accounts.add(mapRowToAccount(results));
        }
        return accounts;
    }

    @Override
    public Account getAccountByUserId(int userId) {

        String sql = "SELECT account_id, user_id, balance FROM account WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            return mapRowToAccount(results);
        } else {
            System.out.println("ERROR: NO ACCOUNT FOUND FOR SPECIFIED USER ID");
         return null;
        }
    }

    @Override
    public BigDecimal getBalance(int userId) {
        //BigDecimal balance = new BigDecimal(0);
        String sql = "SELECT balance FROM account WHERE user_id = ?";
        BigDecimal results = jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);
        /*
        if (results.next()) {
            balance = mapRowToAccount(results).getBalance();
        }

         */

        return results;
    }

    private Account mapRowToAccount(SqlRowSet rowSet) {
        Account account = new Account();
        account.setAccountId(rowSet.getInt("account_id"));
        account.setUserId(rowSet.getInt("user_id"));
        account.setBalance(rowSet.getBigDecimal("balance"));
        return account;
    }


}
