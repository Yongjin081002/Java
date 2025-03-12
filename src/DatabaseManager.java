import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:students.db"; // SQLite 파일 경로

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("📌 SQLite 연결 성공!");
        } catch (SQLException e) {
            System.out.println("SQLite 연결 실패: " + e.getMessage());
        }
        return conn;
    }

    public static void createTable() { // 존재하지 않을 때만 생성함
        String sql = "CREATE TABLE IF NOT EXISTS students ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, "
                + "age INTEGER NOT NULL, "
                + "studentId TEXT UNIQUE NOT NULL"
                + ");";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("📌 학생 테이블 생성 완료!");
        } catch (SQLException e) {
            System.out.println("테이블 생성 오류: " + e.getMessage());
        }
    }
}
