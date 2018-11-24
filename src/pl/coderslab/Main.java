package pl.coderslab;

import pl.coderslab.pl.coderslab.utils.DbConnection;
import pl.coderslab.pl.coderslab.utils.TableCreator;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        //User user = new User("Artur", "emailXXX@example.com", "password", new UserGroup("grupa"));
        try (Connection connection = DbConnection.getConnection()) {
            User[] uArray = User.loadAllUsers(connection);
            for (User u : uArray) {
                System.out.println(u);
            }
            UserGroup[] ugArray = UserGroup.loadAllUserGroups(connection);
            for (UserGroup u : ugArray) {
                System.out.println(u);
            }
            Exercise[] eArray = Exercise.loadAllExercises(connection);
            for (Exercise u : eArray) {
                System.out.println(u);
            }
            Solution[] sArray = Solution.loadAllSolutions(connection);
            for (Solution u : sArray) {
                System.out.println(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
