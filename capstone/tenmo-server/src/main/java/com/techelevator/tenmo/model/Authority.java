package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import java.util.Objects;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.Account;

public class Authority {

   private String name;
   //private int userId;
   //private int accountId;
   //private BigDecimal balance;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Authority(String name) {
      this.name = name;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Authority authority = (Authority) o;
      return name.equals(authority.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name);
   }

   @Override
   public String toString() {
      return "Authority{" +
         "name=" + name +
         '}';
   }
}
