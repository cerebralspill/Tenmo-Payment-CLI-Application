package com.techelevator.tenmo;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TenmoService;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.List;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private final TenmoService tenmoService = new TenmoService();

    private AuthenticatedUser currentUser;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }

    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        try {
            tenmoService.setAuthToken(currentUser.getToken());
        } catch (Exception e) {
            System.out.println("Could not connect to server");
        }
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

    // ---------------------- Use case 3 ----------------------------
    // Displays current user's account balance

    private void viewCurrentBalance() {
        // TODO Auto-generated method stub
        BigDecimal balance = tenmoService.getBalance();
        if (balance != null) {
            consoleService.printBalance(balance);
        } else {
            System.out.println("Balance ended up null");
        }
    }

    // ------------------------- Use case 5 and Use case 6 -------------------------------
    // STILL NEEDS TO BE PROPERLY FORMATTED IN ConsoleService.listTransfers
    // Should be using String.format

    private void viewTransferHistory() {
        Transfer[] transfers = tenmoService.listTransfers();
        boolean exists = false;
        if (transfers != null) {
            consoleService.printTransfers(transfers);
            int transferId = consoleService.promptForInt("Please enter transfer ID to view details (0 to cancel): ");

            if (transferId != 0) {
                for (Transfer transfer : transfers) {
                    if (transferId == transfer.getTransferID()) {
                        consoleService.printTransfer(transfer);
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    System.out.println("Could not find transfer with matching ID");
                }
            }

        } else {
            consoleService.printErrorMessage();
        }
    }



    private void getById(int transferID) {
    }


    private void viewPendingRequests() {
        // TODO Auto-generated method stub

    }

    // ------------------------- Use case 4 -------------------------------
    private void sendBucks() {
        // TODO Auto-generated method stub
        //create cli format on use case 4, page 2 of read me
        User[] users = tenmoService.listUsers();
        System.out.println("---------------------------------------");
        System.out.println("Users");
        System.out.println("ID\t\tName");
        System.out.println("---------------------------------------");

        //iterate to get every user id and name combo
        for (User user : users) {
            System.out.println(user.getId() + "\t\t" + user.getUsername());
        }
        System.out.println("--------");
        int inputId = consoleService.promptForInt("Enter ID of user you are sending to (0 to cancel)");

        //initiating the users
        if ( inputId == currentUser.getUser().getId()) {
            System.out.println("\nYou cannot send money to yourself.");
        } else if (inputId != 0) {
            User fromUser = tenmoService.getUser(currentUser.getUser().getId());
            User toUser = tenmoService.getUser(inputId);

            //checking for null to initiate transfers
            BigDecimal amount = null;
            if (fromUser != null && toUser != null) {
                amount = consoleService.promptForBigDecimal("Enter amount: ");
            }
            if (amount.equals(0)) {
                System.out.println();
            }

        }









            /*
                //Focusing on just going for an approval to send money
                //fulfilling the details by capturing the sets from Transfer POJO. question is do we set transferId?

                Transfer transfer = new Transfer();
                transfer.setAmount(amountRequest);
                //transfer.setTransferType(2);
                transfer.setAccountFrom(fromUser.getId());
                transfer.setAccountTo(toUser.getId());

                Transfer desiredTransfer = tenmoService.sendAmount(transfer);


                //Copy and paste from ConsoleService printTransfer method.
                if (desiredTransfer.getTransferType() == 2) {
                    System.out.println("Send");
                } else {
                    System.out.println("Request");
                }
                System.out.print("Status: ");
                if (desiredTransfer.getTransferStatus() == 1) {
                    System.out.println("Pending");
                } else if (desiredTransfer.getTransferStatus() == 2) {
                    System.out.println("Approved");
                } else {
                    System.out.println("Rejected");
                }
                System.out.println("Amount: " + DecimalFormat.getCurrencyInstance().format(transfer.getAmount()));
            }*/



    }
        private void requestBucks () {
            // TODO Auto-generated method stub

        }

    }

