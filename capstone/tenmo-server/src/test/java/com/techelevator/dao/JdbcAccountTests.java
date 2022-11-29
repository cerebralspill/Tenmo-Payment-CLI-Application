package com.techelevator.dao;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccount;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JdbcAccountTests extends BaseDaoTests {
    private static final Account ACCOUNT_1 = new Account(new BigDecimal("100"), 200, 300);
    private static final Account ACCOUNT_2 = new Account(new BigDecimal("200"), 100, 400);

    private AccountDao sut;
    private Account accountTest;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcAccount(dataSource);

    }

    @Test
    public void getAll_returns_all(){
        List<Account> accounts = sut.getAll();
        Assert.assertEquals(2, accounts.size());

        assertAccountsMatch(ACCOUNT_1, accounts.get(0));
        assertAccountsMatch(ACCOUNT_2, accounts.get(1));
    }

   /* @Test
    public void getBalance_returns_balance() {
        //accountTest = accountTest.getBalance(300);
        BigDecimal actual = sut.getBalance(400);
        BigDecimal expected = new BigDecimal("200");

        Assert.assertEquals();
    }*/

    private void assertAccountsMatch(Account expected, Account actual) {
        Assert.assertEquals(expected.getBalance(), actual.getBalance());
        Assert.assertEquals(expected.getAccountId(), actual.getAccountId());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());

    }


}
