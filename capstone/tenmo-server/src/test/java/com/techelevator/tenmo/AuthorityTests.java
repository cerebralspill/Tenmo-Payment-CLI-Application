package com.techelevator.tenmo;

import com.techelevator.tenmo.model.Authority;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;




import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AuthorityTests {
    Authority authority = new Authority("Wario");

    @Test
    public void getName_equals_to_null() {
        authority.setName(null);;
        assertEquals(authority.getName(), null);
    }
    @Test
    public void getName_equals_to_Wario() {
        authority.setName("Wario");;
        assertEquals(authority.getName(), "Wario");
    }
}
