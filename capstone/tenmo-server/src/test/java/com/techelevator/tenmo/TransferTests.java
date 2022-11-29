package com.techelevator.tenmo;

import com.techelevator.tenmo.model.Authority;
import com.techelevator.tenmo.model.Transfer;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestPropertySource;


import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TransferTests {
    Transfer transfer = new Transfer();

   /* @Test
    public void transferId_equals_two() {
        transfer.setTransferID(2);
    assertEquals(transfer.getTransferID(), 2);
    }*/

    @Test
    public void transferTypeId_equals_one() {
        transfer.setTransferType(1);
        assertEquals(transfer.getTransferType(), 1);
    }

    @Test
    public void transferStatus_equals_two() {
        transfer.setTransferStatus(2);
        assertEquals(transfer.getTransferStatus(), 2);
    }

   /* @Test
    public void accountFrom_equals_two() {
        transfer.setAccountFrom(2);
        assertEquals( 2, transfer.getAccountFrom());
    }

    @Test
    public void accountTo_equals_one() {
        transfer.setAccountFrom(1);
        assertEquals(transfer.getAccountTo(), 1);
    }*/

    @Test
    public void userNameFrom_equals_null() {
        transfer.setUsernameFrom(null);
        assertEquals(transfer.getUsernameFrom(), null);
    }

    @Test
    public void userNameFrom_equals_larissa() {
        transfer.setUsernameFrom("Larissa");
        assertEquals(transfer.getUsernameFrom(), "Larissa");
    }

    @Test
    public void userNameTo_equals_null() {
        transfer.setUsernameTo(null);
        assertEquals(transfer.getUsernameTo(), null);
    }

    @Test
    public void userNameTo_equals_mikey() {
        transfer.setUsernameTo("Mikey");
        assertEquals(transfer.getUsernameTo(), "Mikey");
    }

    @Test
    public void amount_equals_oneThousand() {
        transfer.setAmount(BigDecimal.valueOf(10000));
        assertEquals(transfer.getAmount(), BigDecimal.valueOf(10000));
    }




}
