package pl.coderslab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Solution {
    private int id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Exercise exercise;
    private User user;

    public Solution() {

    }

    public Solution(LocalDateTime created, LocalDateTime updated, Exercise exercise, User user) {
        this.created = created;
        this.updated = updated;
        this.exercise = exercise;
        this.user = user;
    }

    static public Solution[] loadAllSolutions(Connection connection) throws SQLException {
        ArrayList<Solution> solutions = new ArrayList<>();
        String sql = "SELECT * FROM solution";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            solutions.add(getSolutionFromResultSet(resultSet, connection));
        }
        Solution[] sArray = new Solution[solutions.size()];
        sArray = solutions.toArray(sArray);
        return sArray;
    }

    static public Solution loadSolutionById(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM solution where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return getSolutionFromResultSet(resultSet, connection);
        }
        return null;
    }

    public void delete(Connection connection) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM solution WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0;
        }
    }

    public void saveToDB(Connection connection) throws SQLException {
        if (this.id == 0) {
            String sql = "INSERT INTO solution(created, updated, exercise_id, users_id) VALUES (?, ?, ?, ?)";
            String[] generatedColumns = {"ID"};
            PreparedStatement preparedStatement = connection.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, String.join(" ", this.created.toString().split("T")));
            preparedStatement.setString(2, String.join(" ", this.updated.toString().split("T")));
            preparedStatement.setInt(3, this.exercise.getId());
            preparedStatement.setInt(4, this.user.getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } else {
            String sql = "UPDATE solution SET created=?, updated=?, exercise_id=?, users_id=? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.join(" ", this.created.toString().split("T")));
            preparedStatement.setString(2, String.join(" ", this.updated.toString().split("T")));
            preparedStatement.setInt(3, this.exercise.getId());
            preparedStatement.setInt(4, this.user.getId());
            preparedStatement.setInt(5, this.id);
            preparedStatement.executeUpdate();
        }
    }

    private static Solution getSolutionFromResultSet(ResultSet resultSet, Connection connection) throws SQLException {
        Solution loadedSolution = new Solution();
        loadedSolution.id = resultSet.getInt("id");
        loadedSolution.created = LocalDateTime.parse(String.join("T", resultSet.getString("created").split(" ")));
        loadedSolution.updated = LocalDateTime.parse(String.join("T", resultSet.getString("updated").split(" ")));
        loadedSolution.exercise = Exercise.loadExerciseById(connection, resultSet.getInt("exercise_id"));
        loadedSolution.user = User.loadUserById(connection, resultSet.getInt("users_id"));
        return loadedSolution;
    }

    public void updateNow() {
        updated = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", exercise=" + exercise.getId() +
                ", user=" + user.getId() +
                '}';
    }
}
