package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    List<Account> getAll();

    Account getAccountByUserId(int userId);

    BigDecimal getBalance(int userId);

}
