package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.UserCredentials;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printTransferHistoryMenu() {
        System.out.println();
        System.out.println("1: View all transfers");
        System.out.println("2: View single transfer");
        System.out.println("0: Exit");
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
            }
        }
    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printBalance(BigDecimal balance) {
        System.out.println("--------------------------------------------");
        System.out.println("Your current balance is: " + DecimalFormat.getCurrencyInstance().format(balance));
        System.out.println("--------------------------------------------");
    }

    public void printTransfers(Transfer[] transfers) {
        System.out.println("----------------------------------------");
        System.out.println("Transfers");
        System.out.println("ID\t\tFrom/To\t\t\t\t     Amount");
        System.out.println("----------------------------------------");
        for (Transfer transfer : transfers) {
            System.out.print(transfer.getTransferID() + "       ");
            if (transfer.getTransferType() == 2) {
                System.out.print("To: ");
                System.out.print(transfer.getUsernameTo() + DecimalFormat.getCurrencyInstance().format(transfer.getAmount())
                        + "       ");
            } else {
                System.out.print("From: ");
                System.out.print(transfer.getUsernameTo() + DecimalFormat.getCurrencyInstance().format(transfer.getAmount())
                        + "       ");
            }
            System.out.println();
        }
    }

    public void printTransfer(Transfer transfer) {
        System.out.println("----------------------------------------");
        System.out.println("Transfer Details");
        System.out.println("----------------------------------------");
        System.out.println("Id: " + transfer.getTransferID());
        System.out.println("From: " + transfer.getUsernameFrom());
        System.out.println("To: " + transfer.getUsernameTo());
        System.out.print("Type: ");
            if (transfer.getTransferType() == 2) {
                System.out.println("Send");
            } else {
                System.out.println("Request");
            }
        System.out.print("Status: ");
            if (transfer.getTransferStatus() == 1) {
                System.out.println("Pending");
            } else if (transfer.getTransferStatus() == 2) {
                System.out.println("Approved");
            } else {
                System.out.println("Rejected");
            }
        System.out.println("Amount: " + DecimalFormat.getCurrencyInstance().format(transfer.getAmount()));
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

}
