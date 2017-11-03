import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class MySQLTestDataInserter {

    private static final String DB_URL = "jdbc:mysql://172.30.224.27:3306/test_data_extraction?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true&nullCatalogMeansCurrent=true&allowMultiQueries=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "cloudsea1!";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            createTable(conn);
            insertTestData(conn);
            System.out.println("Test data inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection conn) throws SQLException {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS test_table (
                id INT AUTO_INCREMENT PRIMARY KEY,
                tinyint_col TINYINT,
                smallint_col SMALLINT,
                mediumint_col MEDIUMINT,
                int_col INT,
                bigint_col BIGINT,
                float_col FLOAT,
                double_col DOUBLE,
                decimal_col DECIMAL(10, 2),
                date_col DATE,
                datetime_col DATETIME,
                timestamp_col TIMESTAMP,
                time_col TIME,
                year_col YEAR,
                char_col CHAR(10),
                varchar_col VARCHAR(255),
                binary_col BINARY(10),
                varbinary_col VARBINARY(255),
                tinyblob_col TINYBLOB,
                blob_col BLOB,
                mediumblob_col MEDIUMBLOB,
                longblob_col LONGBLOB,
                tinytext_col TINYTEXT,
                text_col TEXT,
                mediumtext_col MEDIUMTEXT,
                longtext_col LONGTEXT,
                enum_col ENUM('value1', 'value2', 'value3'),
                set_col SET('value1', 'value2', 'value3')
            ) ENGINE=InnoDB;
            """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    private static void insertTestData(Connection conn) throws SQLException {
        String insertSQL = """
            INSERT INTO test_table (
                tinyint_col, smallint_col, mediumint_col, int_col, bigint_col,
                float_col, double_col, decimal_col, date_col, datetime_col,
                timestamp_col, time_col, year_col, char_col, varchar_col,
                binary_col, varbinary_col, tinyblob_col, blob_col, mediumblob_col,
                longblob_col, tinytext_col, text_col, mediumtext_col, longtext_col,
                enum_col, set_col
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
            """;

        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            Random rand = new Random();
            for (int i = 0; i < 1000; i++) { // Insert 1000 rows of test data
                pstmt.setInt(1, rand.nextInt(128));
                pstmt.setInt(2, rand.nextInt(32768));
                pstmt.setInt(3, rand.nextInt(8388608));
                pstmt.setInt(4, rand.nextInt(214748368));
                pstmt.setLong(5, rand.nextLong());
                pstmt.setFloat(6, rand.nextFloat());
                pstmt.setDouble(7, rand.nextDouble());
                pstmt.setBigDecimal(8, new java.math.BigDecimal(rand.nextDouble() * 100).setScale(2, java.math.RoundingMode.HALF_UP));
                pstmt.setDate(9, java.sql.Date.valueOf("2025-03-04"));
                pstmt.setTimestamp(10, java.sql.Timestamp.valueOf("2025-03-04 12:34:56"));
                pstmt.setTimestamp(11, java.sql.Timestamp.valueOf("2025-03-04 12:34:56"));
                pstmt.setTime(12, java.sql.Time.valueOf("12:34:56"));
                pstmt.setInt(13, 2025);
                pstmt.setString(14, "char_data");
                pstmt.setString(15, "varchar_data");
                pstmt.setBytes(16, "binary_data".getBytes());
                pstmt.setBytes(17, "varbinary_data".getBytes());
                pstmt.setBytes(18, "tinyblob_data".getBytes());
                pstmt.setBytes(19, "blob_data".getBytes());
                pstmt.setBytes(20, "mediumblob_data".getBytes());
                pstmt.setBytes(21, "longblob_data".getBytes());
                pstmt.setString(22, "tinytext_data");
                pstmt.setString(23, "text_data");
                pstmt.setString(24, "mediumtext_data");
                pstmt.setString(25, "longtext_data");
                pstmt.setString(26, "value1");
                pstmt.setString(27, "value1,value2");

                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
    }
}
