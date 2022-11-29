
package com.techelevator.dao;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccount;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JdbcTransferDaoTest extends BaseDaoTests {

    private static final Transfer TRANSFER_1 = new Transfer(1000, 1, 2, 3,2, "angel","jonathan", new BigDecimal("100"));
    private static final Transfer TRANSFER_2 = new Transfer(2000, 2, 2, 1,2, "mikey","jonathan", new BigDecimal("200"));
    private static final Transfer TRANSFER_3 = new Transfer(3000, 2, 1, 3,1, "angel","mikey", new BigDecimal("300"));

    private JdbcTransferDao sut;
    private Transfer transferTest;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcTransferDao(dataSource);

    }

    @Test
    public void getAll_returns_all_transfers(){
        User user = new User(1,"mikey", "123","mikey");
        List<Transfer> transfers = sut.getAll(1000);

        Assert.assertEquals(3, transfers.size());

        assertTransferMatch(TRANSFER_1, transfers.get(0));
        assertTransferMatch(TRANSFER_2, transfers.get(1));
        assertTransferMatch(TRANSFER_3, transfers.get(2));


    }
    @Test
    public void getTransfer_byId_returns_correct(){
        //Transfer actual = sut.getById(1000);
       // Assert.assertEquals(testTransfer, actual);
        Transfer testTransfer4 = new Transfer();
        testTransfer4 = sut.getById(1000);
        BigDecimal testAmount = testTransfer4.getAmount();
        BigDecimal expected = BigDecimal.valueOf(1000);
        int testAccountTo = testTransfer4.getAccountTo();
        Assert.assertEquals(String.valueOf(expected), testAmount, 0);
        Assert.assertEquals(4,testAccountTo);


    }

   /* @Test
    public void transfer_request_returns_success() {
        BigDecimal fakeAmount = BigDecimal.valueOf(200);
        Transfer fakeTrans = sut.request(fakeAmount);
        Assert.assertEquals(TRANSFER_2, fakeTrans);

    }*/
   @Test
   public void send_correctly_sends_and_udates(){
        List<Transfer> beforeSending = new ArrayList<>();
        List<Transfer> afterSending = new ArrayList<>();

        beforeSending = sut.getAll(200);
        sut.send(TRANSFER_1);
        BigDecimal testAmount = TRANSFER_1.getAmount();
        BigDecimal expected = new BigDecimal("100");

        int testAccountTo = TRANSFER_1.getAccountTo();
   }


    private void assertTransferMatch(Transfer expected, Transfer actual){
        Assert.assertEquals(expected.getTransferID(), actual.getTransferID());
        Assert.assertEquals(expected.getTransferType(), actual.getTransferType());
        Assert.assertEquals(expected.getTransferStatus(), actual.getTransferStatus());
        Assert.assertEquals(expected.getAccountFrom(), actual.getAccountFrom());
        Assert.assertEquals(expected.getAccountTo(), actual.getAccountTo());
        Assert.assertEquals(expected.getUsernameFrom(), actual.getUserIdFrom());
        Assert.assertEquals(expected.getUsernameTo(), actual.getUsernameTo());
        Assert.assertEquals(expected.getAmount(), actual.getAmount());
    }

}

