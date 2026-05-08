package com.quantity.repository;

import com.quantity.entity.QuantityMeasurementEntity;
import com.quantity.exception.DatabaseException;
import com.quantity.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {

    public QuantityMeasurementDatabaseRepository() {

        createTable();
    }

    private void createTable() {

        String sql = """
                CREATE TABLE IF NOT EXISTS measurements (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    operation VARCHAR(100),
                    result DOUBLE
                )
                """;

        try (Connection conn =
                     ConnectionPool.getConnection();

             Statement stmt =
                     conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Table creation failed",
                    e);
        }
    }

    @Override
    public void save(
            QuantityMeasurementEntity entity) {

        String sql =
                "INSERT INTO measurements(operation,result) VALUES(?,?)";

        try (Connection conn =
                     ConnectionPool.getConnection();

             PreparedStatement pstmt =
                     conn.prepareStatement(sql)) {

            pstmt.setString(
                    1,
                    entity.getOperation());

            pstmt.setDouble(
                    2,
                    entity.getResult());

            pstmt.executeUpdate();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Save failed",
                    e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity>
    getAllMeasurements() {

        List<QuantityMeasurementEntity> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM measurements";

        try (Connection conn =
                     ConnectionPool.getConnection();

             Statement stmt =
                     conn.createStatement();

             ResultSet rs =
                     stmt.executeQuery(sql)) {

            while (rs.next()) {

                list.add(
                        new QuantityMeasurementEntity(
                                rs.getString("operation"),
                                rs.getDouble("result")
                        )
                );
            }

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Fetch failed",
                    e);
        }

        return list;
    }

    @Override
    public int getTotalCount() {

        String sql =
                "SELECT COUNT(*) FROM measurements";

        try (Connection conn =
                     ConnectionPool.getConnection();

             Statement stmt =
                     conn.createStatement();

             ResultSet rs =
                     stmt.executeQuery(sql)) {

            rs.next();

            return rs.getInt(1);

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Count failed",
                    e);
        }
    }

    @Override
    public void deleteAll() {

        String sql =
                "DELETE FROM measurements";

        try (Connection conn =
                     ConnectionPool.getConnection();

             Statement stmt =
                     conn.createStatement()) {

            stmt.executeUpdate(sql);

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Delete failed",
                    e);
        }
    }
}