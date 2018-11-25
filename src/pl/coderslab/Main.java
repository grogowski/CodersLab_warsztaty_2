package pl.coderslab;

import pl.coderslab.pl.coderslab.utils.DbConnection;
import pl.coderslab.pl.coderslab.utils.TableCreator;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        //User user = new User("Artur", "emailXXX@example.com", "password", new UserGroup("grupa"));
        try (Connection connection = DbConnection.getConnection()) {
//            TableCreator.createTables(connection);
//            UserGroup group = new UserGroup("testgroup");
//            group.saveToDB(connection);
//            System.out.println(group);
//            group = UserGroup.loadUserGroupById(connection, 1);
//            group.setName("test_group");
//            group.saveToDB(connection);
//            group = UserGroup.loadUserGroupById(connection, 1);
//            System.out.println(group);
//            User user = new User("testuser", "test@email.com", "password", group);
//            user.saveToDB(connection);
//            user = User.loadUserById(connection,1);
//            System.out.println(user);
//            user.setEmail("newemail@yahoo.com");
//            user.saveToDB(connection);
//            user = User.loadUserById(connection, 1);
//            System.out.println(user);
//            Exercise testExercise = new Exercise("test", "agasgfasdg");
//            testExercise.saveToDB(connection);
//            testExercise = Exercise.loadExerciseById(connection, 1);
//            testExercise.setDescription("new description");
//            testExercise.saveToDB(connection);
//            testExercise = Exercise.loadExerciseById(connection,1);
//            System.out.println(testExercise);
//            Solution solution = new Solution(LocalDateTime.parse("2018-11-20T20:30:00"), LocalDateTime.parse("2018-11-20T20:30:00"), "description", testExercise, user);
//            solution.saveToDB(connection);
//            solution = Solution.loadSolutionById(connection,1);
//            System.out.println(solution);
//            solution.updateNow();
//            solution.saveToDB(connection);
//            solution = Solution.loadSolutionById(connection,1);
//            System.out.println(solution);
            System.out.println("---------");
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
