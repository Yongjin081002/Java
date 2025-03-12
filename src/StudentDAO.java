import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String DB_URL = "jdbc:sqlite:students.db";

    // 학생 추가
    public static void addStudent(String name, int age, String studentId) {
        String sql = "INSERT INTO students (name, age, studentId) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, studentId);
            pstmt.executeUpdate();
            System.out.println("✅ 학생 추가 완료: " + name);
        } catch (SQLException e) {
            System.out.println("학생 추가 오류: " + e.getMessage());
        }
    }

    // 모든 학생 조회

    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:students.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("studentId")
                ));
            }
        } catch (SQLException e) {
            System.out.println("학생 조회 오류: " + e.getMessage());
        }
        return students;
    }

    // 학생 삭제
    public static void deleteStudent(String studentId) {
        String sql = "DELETE FROM students WHERE studentId = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, studentId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("🗑️ 학생 삭제 완료!");
            } else {
                System.out.println("⚠️ 해당 학번의 학생이 없습니다.");
            }
        } catch (SQLException e) {
            System.out.println("학생 삭제 오류: " + e.getMessage());
        }
    }
}
