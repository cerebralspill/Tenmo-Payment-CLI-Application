package com.techelevator.tenmo;

import com.techelevator.tenmo.model.Authority;
import com.techelevator.tenmo.model.LoginDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class LoginDTOTests {
    LoginDTO loginDTO = new LoginDTO();

    @Test
    public void username_equals_frank_rizzo() {
        loginDTO.setUsername("Frank Rizzo");
        assertEquals(loginDTO.getUsername(), "Frank Rizzo");
    }

    @Test
    public void password_equals_jerky_boys() {
        loginDTO.setPassword("Jerky_Boys");
        assertEquals(loginDTO.getPassword(), "Jerky_Boys");

    }
}
