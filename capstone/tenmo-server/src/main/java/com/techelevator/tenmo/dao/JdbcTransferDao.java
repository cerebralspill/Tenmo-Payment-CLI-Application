package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Transfer> getTransfersByAccountId(int accountId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "FROM transfer " +
                "JOIN account ON account_from = account_id " +
                "WHERE account_from = ? OR account_to = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
        while (results.next()) {
            transfers.add(mapRowToTransfer(results));
        }
        return new ArrayList<>();
    }

    @Override
    public List<Transfer> getAll(int currentUserAccountId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, " +
                "               username AS username_from, tenmo_user.user_id AS user_id_from, amount " +
                "     FROM transfer " +
                "     JOIN account ON account_from = account_id " +
                "     JOIN tenmo_user ON account.user_id = tenmo_user.user_id " +
                "     WHERE account_from = ? OR account_to = ? " +
                "     ORDER BY transfer_id DESC;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, currentUserAccountId, currentUserAccountId);
        while (results.next()) {
            transfers.add(mapRowToTransfer(results));
        }

        // This just adds the username of the person receiving the money into the POJO
        // I didn't know how to get it all in one sql statement
        for (Transfer transfer : transfers) {
            sql = "SELECT username AS username_to FROM transfer " +
                    "JOIN account ON account_to = account_id " +
                    "JOIN tenmo_user ON account.user_id = tenmo_user.user_id " +
                    "WHERE transfer_id = ? ;";
            String accountToUsername = jdbcTemplate.queryForObject(sql, String.class, transfer.getTransferID());
            transfer.setUsernameTo(accountToUsername);
            sql = "SELECT tenmo_user.user_id AS user_id_to FROM transfer " +
                    "JOIN account ON account_to = account_id " +
                    "JOIN tenmo_user ON account.user_id = tenmo_user.user_id " +
                    "WHERE transfer_id = ?;";
            int accountToUserId = jdbcTemplate.queryForObject(sql, Integer.class, transfer.getTransferID());
            transfer.setUserIdTo(accountToUserId);
        }

        return transfers;
    }

    @Override
    public Transfer getById(Integer id) {
        Transfer transfer = new Transfer();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, username AS username_from, tenmo_user.user_id AS user_id_from, amount " +
                "                FROM transfer " +
                "                JOIN account ON account_from = account_id " +
                "                JOIN tenmo_user ON account.user_id = tenmo_user.user_id " +
                "                WHERE transfer_id = ? ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            transfer = mapRowToTransfer(results);
        }

        // This just adds the username of the person receiving the money into the POJO
        // I didn't know how to get it all in one sql statement
        sql = "SELECT username AS username_from FROM transfer " +
                "JOIN account ON account_to = account_id " +
                "JOIN tenmo_user ON account.user_id = tenmo_user.user_id " +
                "WHERE transfer_id = ? ;";
        String accountToUsername = jdbcTemplate.queryForObject(sql, String.class, id);
        transfer.setUsernameTo(accountToUsername);
        sql = "SELECT tenmo_user.user_id AS user_id_to FROM transfer " +
                "JOIN account ON account_to = account_id " +
                "JOIN tenmo_user ON account.user_id = tenmo_user.user_id " +
                "WHERE transfer_id = ?;";
        int accountToUserId = jdbcTemplate.queryForObject(sql, Integer.class, transfer.getTransferID());
        transfer.setUserIdTo(accountToUserId);
        return transfer;
    }

    @Override
    public Transfer send(Transfer transfer) {

        int transferTypeId = transfer.getTransferType();
        int transferStatusId = transfer.getTransferStatus();
        int accountFromId = transfer.getAccountFrom();
        int accountToId = transfer.getAccountTo();
        BigDecimal amount = transfer.getAmount();
        int newTransferId = 0;

        String sql = "INSERT INTO transfer( transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES ( ?, ?, ?, ?, ?) RETURNING transfer_id;";
        try {
            newTransferId = jdbcTemplate.queryForObject(sql, Integer.class, transferTypeId, transferStatusId, accountFromId,
                    accountToId, amount);
        } catch (Exception e) {
            System.out.println("ERROR === NULL CREATED WHEN ATTEMPTING TO INITIATE TRANSFER");
        }

        String sql2 = "UPDATE account " +
                "SET balance = balance - ? " +
                "WHERE account_id = ?";
        jdbcTemplate.update(sql2, amount, accountFromId);

        String sql3 = "UPDATE account " +
                "SET balance = balance + ? " +
                "WHERE account_id = ?";
        jdbcTemplate.update(sql3, amount, accountToId);

        return getById(newTransferId);
    }

    @Override
    public Transfer request(BigDecimal amountToRequest) {
        Transfer transfer = null;
        String sql = "SELECT transfer_id, account_from, account_to, transfer_type_id, transfer_status_id, amount" +
                "FROM transfer WHERE transfer_id=?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, amountToRequest);
        if(results.next()){
            transfer = mapRowToTransfer(results);
        }
        return transfer;
    }

    private Transfer mapRowToTransfer(SqlRowSet rowSet) {
        Transfer transfer = new Transfer();
        transfer.setTransferID(rowSet.getInt("transfer_id"));
        transfer.setTransferType(rowSet.getInt("transfer_type_id"));
        transfer.setTransferStatus(rowSet.getInt("transfer_status_id"));
        transfer.setAccountFrom(rowSet.getInt("account_from"));
        transfer.setAccountTo(rowSet.getInt("account_to"));
        transfer.setUsernameFrom(rowSet.getString("username_from"));
        transfer.setUserIdFrom(rowSet.getInt("user_id_from"));
        transfer.setAmount(rowSet.getBigDecimal("amount"));
        return transfer;
    }

    /*
    List<Transfer> getAll();

    Transfer getById(Integer id);

    Transfer send(BigDecimal amountToTransfer);

    Transfer request(BigDecimal amountToRequest);
     */

}
