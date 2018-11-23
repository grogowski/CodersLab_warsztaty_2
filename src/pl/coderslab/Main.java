package pl.coderslab;

import pl.coderslab.pl.coderslab.utils.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        //User user = new User("Artur", "emailXXX@example.com", "password", new UserGroup("grupa"));
        try (Connection connection = DbConnection.getConnection()){
            User user = User.loadUserById(connection, 4);
            user.delete(connection);
//            System.out.println(user);
//            user.setEmail("example@gmail.com");
//            user.saveToDB(connection);
//            user = User.loadUserById(connection, 3);
//            System.out.println(user);
            /*user.saveToDB(connection);
            User[] array = User.loadAllUsers(connection);
            for (User u : array) {
                System.out.println(u);
            } */
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
