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
    private Integer exercise_id;
    private Integer user_id;

    public Solution(String description, int exercise_id, int user_id) {
        this.id = 0;
        this.created = Timestamp.valueOf(LocalDateTime.now());
        this.updated = null;
        this.description = description;
        this.exercise_id = exercise_id;
        this.user_id = user_id;
    }

    public Solution() {
        this.id = 0;
        this.created = Timestamp.valueOf(LocalDateTime.now());
        this.updated = null;
        this.description = null;
        this.exercise_id = null;
        this.user_id = null;
    }

    public int getId() {
        return id;
    }

    public String getCreated() {
        return created.toString();
    }

    public String getUpdated() {
        return updated.toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Integer exercise_id) {
        this.exercise_id = exercise_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public boolean save() {
        try (Connection conn = DbUtil.getConn()) {
            if (this.id == 0) {
                String sql = "INSERT INTO Solutions(description, exercise_id, user_id, created) VALUES (?,?,?,?)";
                PreparedStatement psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                psmt.setString(1, this.description);
                psmt.setInt(2, this.exercise_id);
                psmt.setInt(3, this.user_id);
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
                psmt.setInt(2, this.exercise_id);
                psmt.setInt(3, this.user_id);
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
                loadedSolution.user_id = rs.getInt("user_id");
                loadedSolution.exercise_id = rs.getInt("exercise_id");
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
                loadedSolution.user_id = rs.getInt("user_id");
                loadedSolution.exercise_id = rs.getInt("exercise_id");
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
                loadedSolution.user_id = rs.getInt("user_id");
                loadedSolution.exercise_id = rs.getInt("exercise_id");
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
            String sql = "SELECT * FROM Solutions WHERE user_id=?";
            return loadAllBySQLQuery(id, conn, solutions, sql);
        } catch (SQLException e) {
            System.out.println("Load failed: " + e.getMessage());
            return null;
        }
    }

    public static Solution[] loadAllByExerciseId(int id) {
        try (Connection conn = DbUtil.getConn()) {
            List<Solution> solutions = new ArrayList<>();
            String sql = "SELECT * FROM Solutions WHERE exercise_id=? ORDER BY created;";
            return loadAllBySQLQuery(id, conn, solutions, sql);
        } catch (SQLException e) {
            System.out.println("Load failed: " + e.getMessage());
            return null;
        }
    }

    private static Solution[] loadAllBySQLQuery(int id, Connection conn, List<Solution> solutions, String sql) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.id = rs.getInt("id");
            loadedSolution.description = rs.getString("description");
            loadedSolution.user_id = rs.getInt("user_id");
            loadedSolution.exercise_id = rs.getInt("exercise_id");
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
                .append(", exercise_id=")
                .append(exercise_id)
                .append(", user_id=")
                .append(user_id)
                .append('}')
                .toString();
    }
}
