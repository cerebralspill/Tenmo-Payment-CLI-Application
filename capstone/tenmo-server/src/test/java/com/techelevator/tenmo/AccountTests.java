package com.techelevator.tenmo;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AccountTests {
    Account account = new Account();

    @Test
    public void get_balance_starting_value() {
        account.setBalance(new BigDecimal("1000.00"));
        BigDecimal expected = new BigDecimal("1000.00");
        assertEquals(expected, account.getBalance());
    }

    @Test
    public void accountId_equal_to_zero() {
        account.setAccountId(0);
        assertEquals(account.getAccountId(), 0);
    }
    @Test
    public void accountId_equal_to_two() {
        account.setAccountId(2);
        assertEquals(account.getAccountId(), 2);
    }

    @Test
    public void userId_equal_to_zero() {
        account.setUserId(0);
        assertEquals(account.getUserId(), 0);
    }

    @Test
    public void userId_equal_to_two() {
        account.setUserId(2);
        assertEquals(account.getUserId(), 2);
    }



}
