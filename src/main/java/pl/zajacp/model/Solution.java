package pl.zajacp.model;

import pl.zajacp.utils.DbUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Solution {

    private int id;
    private Timestamp created;
    private Timestamp updated;
    private String description;
    private Exercise exercise;
    private User user;

    public Solution(String description, Exercise exercise, User user) {
        this.id = 0;
        this.created = Timestamp.valueOf(LocalDateTime.now());
        this.updated = null;
        this.description = description;
        this.exercise = exercise;
        this.user = user;
    }

    public Solution() {
        this.id = 0;
        this.created = Timestamp.valueOf(LocalDateTime.now());
        this.updated = null;
        this.description = null;
        this.exercise = null;
        this.user = null;
    }

    public int getId() {
        return id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean save() {
        try (Connection conn = DbUtil.getConn()) {
            if (this.id == 0) {
                String sql = "INSERT INTO Solutions(description, exercise_id, user_id, created) VALUES (?,?,?,?)";
                PreparedStatement psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                psmt.setString(1, this.description);
                psmt.setInt(2, this.exercise.getId());
                psmt.setInt(3, this.user.getId());
                psmt.setTimestamp(4, this.created);
                psmt.executeUpdate();
                ResultSet rs = psmt.getGeneratedKeys();
                if (rs.next()) {
                    this.id = rs.getInt(1);
                }
            } else {
                String sql = "UPDATE Solutions SET description=?, exercise_id=?, user_id=?, updated=? WHERE id=?";
                PreparedStatement psmt = conn.prepareStatement(sql);
                psmt.setString(1, this.description);
                psmt.setInt(2, this.exercise.getId());
                psmt.setInt(3, this.user.getId());
                this.updated = Timestamp.valueOf(LocalDateTime.now());
                psmt.setTimestamp(4, this.updated);
                psmt.setInt(5, this.id);
                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Save failed: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static Solution loadById(int id) {
        try (Connection conn = DbUtil.getConn()) {
            String sql = "SELECT * FROM Solutions where id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Solution loadedSolution = new Solution();
                loadedSolution.id = rs.getInt("id");
                loadedSolution.description = rs.getString("description");
                loadedSolution.user = User.loadById(rs.getInt("user_id"));
                loadedSolution.exercise = Exercise.loadById(rs.getInt("exercise_id"));
                loadedSolution.created = rs.getTimestamp("created");
                loadedSolution.updated = rs.getTimestamp("updated");
                return loadedSolution;
            }
        } catch (SQLException e) {
            System.out.println("Load failed: " + e.getMessage());
            return null;
        }
        System.out.println("Load failed: Record with given ID does not exist.");
        return null;
    }

    public static Solution[] loadAll() {
        try (Connection conn = DbUtil.getConn()) {
            List<Solution> solutions = new ArrayList<>();
            String sql = "SELECT * FROM Solutions";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Solution loadedSolution = new Solution();
                loadedSolution.id = rs.getInt("id");
                loadedSolution.description = rs.getString("description");
                loadedSolution.user = User.loadById(rs.getInt("user_id"));
                loadedSolution.exercise = Exercise.loadById(rs.getInt("exercise_id"));
                loadedSolution.created = rs.getTimestamp("created");
                loadedSolution.updated = rs.getTimestamp("updated");
                solutions.add(loadedSolution);
            }
            Solution[] returnArray = new Solution[solutions.size()];
            return solutions.toArray(returnArray);
        } catch (SQLException e) {
            System.out.println("Load failed: " + e.getMessage());
            return null;
        }
    }

    public static Solution[] loadAll(int number) {
        try (Connection conn = DbUtil.getConn()) {
            List<Solution> solutions = new ArrayList<>();
            String sql = "SELECT * FROM Solutions ORDER BY created DESC LIMIT ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, number);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Solution loadedSolution = new Solution();
                loadedSolution.id = rs.getInt("id");
                loadedSolution.description = rs.getString("description");
                loadedSolution.user = User.loadById(rs.getInt("user_id"));
                loadedSolution.exercise = Exercise.loadById(rs.getInt("exercise_id"));
                loadedSolution.created = rs.getTimestamp("created");
                loadedSolution.updated = rs.getTimestamp("updated");
                solutions.add(loadedSolution);
            }
            Solution[] returnArray = new Solution[solutions.size()];
            return solutions.toArray(returnArray);
        } catch (SQLException e) {
            System.out.println("Load failed: " + e.getMessage());
            return null;
        }
    }

    public boolean delete() {
        try (Connection conn = DbUtil.getConn()) {
            if (this.id != 0) {
                String sql = "DELETE FROM Solutions WHERE id=?";
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setInt(1, this.id);
                pstm.executeUpdate();
                this.id = 0;
            }
        } catch (SQLException e) {
            System.out.println("Delete failed: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static Solution[] loadAllByUserId(int id) {
        try (Connection conn = DbUtil.getConn()) {
            List<Solution> solutions = new ArrayList<>();
            String sql = "SELECT * FROM Solutions WHERE user=?";
            return loadAllSQLQuery(id, conn, solutions, sql);
        } catch (SQLException e) {
            System.out.println("Load failed: " + e.getMessage());
            return null;
        }
    }

    public static Solution[] loadAllByExerciseId(int id) {
        try (Connection conn = DbUtil.getConn()) {
            List<Solution> solutions = new ArrayList<>();
            String sql = "SELECT * FROM Solutions WHERE exercise=? ORDER BY created;";
            return loadAllSQLQuery(id, conn, solutions, sql);
        } catch (SQLException e) {
            System.out.println("Load failed: " + e.getMessage());
            return null;
        }
    }

    private static Solution[] loadAllSQLQuery(int id, Connection conn, List<Solution> solutions, String sql) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.id = rs.getInt("id");
            loadedSolution.description = rs.getString("description");
            loadedSolution.user = User.loadById(rs.getInt("user_id"));
            loadedSolution.exercise = Exercise.loadById(rs.getInt("exercise_id"));
            loadedSolution.created = rs.getTimestamp("created");
            loadedSolution.updated = rs.getTimestamp("updated");
            solutions.add(loadedSolution);
        }
        Solution[] returnArray = new Solution[solutions.size()];
        return solutions.toArray(returnArray);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Solution{")
                .append("id=")
                .append(id)
                .append(", created=")
                .append(created)
                .append(", updated=")
                .append(updated)
                .append(", description='")
                .append(description)
                .append('\'')
                .append(", exercise=")
                .append(exercise)
                .append(", user=")
                .append(user)
                .append('}')
                .toString();
    }
}
