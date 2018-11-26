package pl.coderslab;

import pl.coderslab.pl.coderslab.utils.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try (Connection connection = DbConnection.getConnection()) {
            UserInterface ui = new UserInterface(connection);
            ui.run();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
