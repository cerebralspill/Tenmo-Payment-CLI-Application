package com.techelevator.tenmo;

import com.techelevator.tenmo.model.RegisterUserDto;
import org.junit.Test;


import static org.junit.Assert.*;

public class RegisterUserDTOTests {
    RegisterUserDto registerUserDTO = new RegisterUserDto();

    @Test
    public void username_equals_randall_cunningham() {
        registerUserDTO.setUsername("Randall Cunningham");
        assertEquals(registerUserDTO.getUsername(), "Randall Cunningham");
    }

    @Test
    public void password_equals_randy_moss() {
        registerUserDTO.setPassword("Randy_Moss");
        assertEquals(registerUserDTO.getPassword(), "Randy_Moss");
    }

}
