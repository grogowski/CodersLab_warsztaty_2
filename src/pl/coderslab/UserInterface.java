package pl.coderslab;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class UserInterface {
    private Connection connection;
    private Scanner scanner;
    private User[] users;

    public UserInterface(Connection connection) throws SQLException{
        this.connection = connection;
        this.scanner = new Scanner(System.in);
        this.users = User.loadAllUsers(connection);
    }

    public void run() throws SQLException {
        boolean quit=false;
        while (!quit) {
            displayAllUsers();
            displayUserMenu();
            String choice = getInput();
            if (choice.equals("add")) {
                addUser();
            } else if (choice.equals("edit")) {
                editUser();
            } else if (choice.equals("delete")) {
                deleteUser();
            } else {
                quit = true;
            }
         }
    }

    private void displayAllUsers() throws SQLException {
        for (User u : users) {
            System.out.println(u);
        }
    }

    private void displayUserMenu() {
        System.out.println("--------------------------------------------" +
                "\nWybierz jedną z opcji: " +
                "\nadd – dodanie użytkownika, " +
                "\nedit – edycja użytkownika, " +
                "\ndelete – usunięcie użytkownika, " +
                "\nquit – zakończenie programu.");
    }

    private String getInput() {
        String input = "";
        while (!input.equals("quit")) {
            input = scanner.nextLine();
            if (input.equals("add") || input.equals("edit") || input.equals("delete")) {
                return input;
            } else if (input.equals("quit")) {

            } else {
                System.out.println("Wybierz jedną z dostępnych opcji");
            }
        }
        return input;
    }

    private void addUser() {
        System.out.println("addUser");
    }

    private void editUser() {
        System.out.println("editUser");
    }

    private void deleteUser() {
        System.out.println("deleteUser");
    }

}
