import java.util.List;
import java.util.Scanner;

public class StudentManagementSystem {
    public static void main(String[] args) {
        DatabaseManager.createTable(); // 테이블 생성

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n📌 학생 관리 시스템");
            System.out.println("1. 학생 추가");
            System.out.println("2. 학생 조회");
            System.out.println("3. 학생 삭제");
            System.out.println("4. 종료");
            System.out.print("선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1:
                    System.out.print("이름: ");
                    String name = scanner.nextLine();
                    System.out.print("나이: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("학번: ");
                    String studentId = scanner.nextLine();
                    StudentDAO.addStudent(name, age, studentId);
                    break;
                case 2:
                    List<Student> students = StudentDAO.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("⚠️ 등록된 학생이 없습니다.");
                    } else {
                        System.out.println("\n📋 학생 목록:");
                        students.forEach(System.out::println);
                    }
                    break;
                case 3:
                    System.out.print("삭제할 학생의 학번: ");
                    String deleteId = scanner.nextLine();
                    StudentDAO.deleteStudent(deleteId);
                    break;
                case 4:
                    System.out.println("📌 프로그램 종료");
                    scanner.close();
                    return;
                default:
                    System.out.println("⚠️ 잘못된 입력입니다.");
            }
        }
    }
}
