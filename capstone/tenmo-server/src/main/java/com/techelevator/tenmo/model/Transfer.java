package com.techelevator.tenmo.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Transfer {

    private Integer transferID;
    @NotBlank(message = "The transfer type must not be blank.")
    private int transferType;
    private int transferStatus;
    @NotNull(message = "The account_from must not be null.")
    private Integer accountFrom;
    @NotNull(message = "The account_to must not be null.")
    private Integer accountTo;
    //Indicate the start and end points of users. where the money is going.
    private String usernameFrom;
    private String usernameTo;
    private int userIdFrom;
    private int userIdTo;
    //Setting minimum amount to be 0.
    @Min(value=0, message = "The amount cannot be negative.")
    private BigDecimal amount;


    public Transfer (){}

    public Transfer(Integer transferID, int transferType, int transferStatus, Integer accountFrom, Integer accountTo, String usernameFrom, String usernameTo, BigDecimal amouunt) {
        this.transferID = transferID;
        this.transferType = transferType;
        this.transferStatus = transferStatus;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.usernameFrom = usernameFrom;
        this.usernameTo = usernameTo;
        this.amount = amount;
    }

    //Implement the Getters
    public Integer getTransferID() {
        return transferID;
    }

    public int getTransferType() {
        return transferType;
    }

    public int getTransferStatus() {
        return transferStatus;
    }

    public Integer getAccountFrom() {
        return accountFrom;
    }

    public Integer getAccountTo() {
        return accountTo;
    }

    public String getUsernameFrom() {
        return usernameFrom;
    }

    public String getUsernameTo() {
        return usernameTo;
    }

    public int getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(int userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    public int getUserIdTo() {
        return userIdTo;
    }

    public void setUserIdTo(int userIdTo) {
        this.userIdTo = userIdTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }


    //Implement setters
    public void setTransferID(Integer transferID) {
        this.transferID = transferID;
    }

    public void setTransferType(int transferType) {
        this.transferType = transferType;
    }

    public void setTransferStatus(int transferStatus) {
        this.transferStatus = transferStatus;
    }

    public void setAccountFrom(Integer accountFrom) {
        this.accountFrom = accountFrom;
    }

    public void setAccountTo(Integer accountTo) {
        this.accountTo = accountTo;
    }

    public void setUsernameFrom(String usernameFrom) {
        this.usernameFrom = usernameFrom;
    }

    public void setUsernameTo(String usernameTo) {
        this.usernameTo = usernameTo;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }




}
