package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
@PreAuthorize("isAuthenticated()")
public class TenmoController {

    private UserDao userDao;
    private AccountDao accountDao;
    private TransferDao transferDao;

   public TenmoController(UserDao userDao, AccountDao accountDao, TransferDao transferDao) {
       this.userDao = userDao;
       this.transferDao = transferDao;
       this.accountDao = accountDao;
   }

    // ----------------- /balance endpoint ---------------------
    // Gets username from principal and finds userId and account
    // balance from the current logged-in user

    @RequestMapping(path = "/balance", method = RequestMethod.GET)
    public BigDecimal getBalance (Principal principal) {

        int userId = userDao.findIdByUsername(principal.getName());
        return accountDao.getBalance(userId);

    }


    // ------------------------- /transfers endpoint --------------------------------
    // Gets all transfers from the current user using principal to get the account ID

    @RequestMapping(path = "/transfers", method = RequestMethod.GET)
    public List<Transfer> getTransfers(Principal principal) {

        int userId = userDao.findIdByUsername(principal.getName());
        int currentUserAccountId = accountDao.getAccountByUserId(userId).getAccountId();

        return transferDao.getAll(currentUserAccountId);
    }

    // ------------------------- /transfers/{id} endpoint --------------------------------

    @RequestMapping(path = "/transfers/{id}", method = RequestMethod.GET)
    public Transfer getTransferById(@PathVariable int id) {
        Transfer transfer = transferDao.getById(id);
        if (transfer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transfer not found.");
        } else {
            return transfer;
        }
    }


    // ---------------- /users endpoint -----------------------

    @RequestMapping(path ="/users", method = RequestMethod.GET)
    public List<User> findAll() {
        return userDao.findAll();
    }


    // ------------------ /users/{id} endpoint ---------------------

    @RequestMapping(path="/users/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable int id) {
        User user = userDao.getUserById(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        } else {
        return user;
        }
    }


    // ------------------------- /send endpoint --------------------------------

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/send", method = RequestMethod.POST)
    public Transfer send(@RequestBody Transfer transfer) {
       return transferDao.send(transfer);
    }












}

