package com.mysql;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class ReadFileToMysql {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/rizhao";
        String username = "root";
        String password = "password";

        String csvFilePath = "Reviews-simple.csv";

        int batchSize = 20;

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);

            String sql = "INSERT INTO review (course_name, student_name, timestamp, rating, comment) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText = null;

            int count = 0;

            lineReader.readLine(); // skip header line

            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String courseName = data[0];
                String studentName = data[1];
                String timestamp = data[2];
                String rating = data[3];
                String comment = data.length == 5 ? data[4] : "";

                statement.setString(1, courseName);
                statement.setString(2, studentName);

                Timestamp sqlTimestamp = Timestamp.valueOf(timestamp);
                statement.setTimestamp(3, sqlTimestamp);

                Float fRating = Float.parseFloat(rating);
                statement.setFloat(4, fRating);

                statement.setString(5, comment);

                statement.addBatch();

                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }

            lineReader.close();

            // execute the remaining queries
            statement.executeBatch();

            connection.commit();
            connection.close();

        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
