package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    List<Transfer> getAll(int currentUserAccountId);

    Transfer getById(Integer id);

    List<Transfer> getTransfersByAccountId(int accountId);

   // List<Transfer> getAllByTransferId(int transferId);

    Transfer send(Transfer transfer);


    Transfer request(BigDecimal amountToRequest);

}
