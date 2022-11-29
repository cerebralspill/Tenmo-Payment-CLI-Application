package com.techelevator.tenmo.services;

import io.cucumber.java.bs.A;
import org.apiguardian.api.API;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.Account;

import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.model.AuthenticatedUser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TenmoService {

    private final RestTemplate restTemplate = new RestTemplate();
    private String authToken = null;

    private static final String API_BASE_URL = "http://localhost:8080/";

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    //Connecting Balance
    public BigDecimal getBalance() {

        BigDecimal balance = null;

        try {
            ResponseEntity<BigDecimal> response = restTemplate.exchange(API_BASE_URL + "balance", HttpMethod.GET,
                    makeAuthEntity(), BigDecimal.class);
            balance = response.getBody();
        } catch (Exception e) {
            System.out.println("Error retrieving balance");
        }
        // = response.getBody();
        //return account.getBalance();
        if (balance != null) {
            return balance;
        } else {
            throw new NullPointerException("Error: Balance is null");
        }
    }


    //Connecting Transfer List
    public Transfer[] listTransfers() {
        Transfer[] transfers = null;
        try {
            ResponseEntity<Transfer[]> response = restTemplate.exchange(API_BASE_URL + "transfers",
                            HttpMethod.GET, makeAuthEntity(), Transfer[].class);
            transfers = response.getBody();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
        }
        return transfers;
    }

    //Connecting Transfer by Id
    public Transfer getTransfer(int id) {
        Transfer transfer = null;
        try {
            ResponseEntity<Transfer> response =
                    restTemplate.exchange(API_BASE_URL + "transfer/" + id,
                            HttpMethod.GET, makeAuthEntity(), Transfer.class);
            transfer = response.getBody();
        } catch (RestClientException e) {
            System.out.println("Transfer not found.");
        }
        return transfer;
    }



    //Connecting User List
    public User[] listUsers() {
        User[] users = null;
        ResponseEntity<User[]> response =
                restTemplate.exchange(API_BASE_URL + "users",
                        HttpMethod.GET, makeAuthEntity(), User[].class);
        users = response.getBody();
        return users;
    }

    //Connecting single user by id
    public User getUser(int id) {
        User user = null;
        try {
            ResponseEntity<User> response =
                    restTemplate.exchange(API_BASE_URL + "users/" + id,
                            HttpMethod.GET, makeAuthEntity(), User.class);
            user = response.getBody();

        } catch (RestClientException e) {
            System.out.println("User not found");
        }
        return user;
    }


    //Connecting send=amount
    public Transfer sendAmount(Transfer newTransfer) {
        Transfer transferAmount = null;
        try {
            ResponseEntity<Transfer> response =
                    restTemplate.exchange(API_BASE_URL + "send",
                            HttpMethod.POST, makeTransferEntity(newTransfer), Transfer.class);
            transferAmount = response.getBody();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
        }
        return transferAmount;
    }






    private Transfer makeTransfer(String currentUser, String userTo, BigDecimal amount) {
        return new Transfer(currentUser, userTo, amount);
    }

    //Creates a new HttpEntity with the `Authorization: Bearer:` header and a reservation request body
    private HttpEntity<Transfer> makeTransferEntity(Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(transfer, headers);
    }

    //Returns an HttpEntity with the `Authorization: Bearer:` header
    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }



}
