package com.techelevator.tenmo;

import com.techelevator.tenmo.model.Authority;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestPropertySource;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class UserTests {
    User user = new User();

    @Test
    public void userid_equals_to_two () {
        int id = 2;
        user.setId(id);
        assertEquals(user.getId(), id);
    }

    @Test
    public void userid_equals_to_zero () {
        int id = 0;
        user.setId(id);
        assertEquals(user.getId(), id);
    }

    @Test
    public void username_equals_null () {
        user.setUsername(null);
        assertEquals(user.getUsername(), null);
    }
    @Test
    public void username_equals_mike_derookie () {
        user.setUsername("Mike Derookie");
        assertEquals(user.getUsername(), "Mike Derookie");
    }

    @Test
    public void password_equals_jerky_boys () {
        user.setPassword("Jerky Boys");
        assertEquals(user.getPassword(), "Jerky Boys");
    }

    @Test
    public void isActivated_equals_true () {
        user.setActivated(true);
        assertEquals(user.isActivated(), true);
    }

    @Test
    public void isActivated_equals_false () {
        user.setActivated(false);
        assertEquals(user.isActivated(), false);
    }

    @Test
    public void setAuthorities_equals_anthony_orlando() {
        Set<Authority> authority = new HashSet<>();
        Set<Authority> newAuthority = new HashSet<>();

        user.setAuthorities(authority);
        assertEquals(user.getAuthorities(), newAuthority);
    }






}
