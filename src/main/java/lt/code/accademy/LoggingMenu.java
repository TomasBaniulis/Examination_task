package lt.code.accademy;

import lt.code.accademy.data.Student;
import lt.code.accademy.data.Teacher;

import java.util.Map;
import java.util.Scanner;

public class LoggingMenu {

    void mainMenu(Scanner scanner, Map<String, Student> students, Map<String, Teacher> teachers){
        String action;
        do {
            System.out.println("""
                [1] -> Teacher login
                [2] -> Student login
                [0] -> Exit
                """);
            action = scanner.nextLine();
            mainMenuAction(action, scanner,students, teachers);
        }while (!action.equals("0"));
    }
    void mainMenuAction(String action, Scanner scanner, Map<String, Student> students, Map <String, Teacher> teachers){
        switch (action){
            case "1" -> teacherLogin(teachers, scanner, action);
            case "2" -> studentLogin(students, scanner, action );
            case "0" -> System.out.println("Exit");
            default -> System.out.println("No such action");
        }
    }
    void teacherMenu(Scanner scanner){
        String action;
        do {
            System.out.println("""
                [1] -> Create exam
                [2] -> Get exam results
                [3] -> Get student result
                [0] -> Exit
                """);
            action = scanner.nextLine();
            teacherMenuAction(action);
        }while (!action.equals("0"));
    }
    void studentMenu(Scanner scanner){
        String action;
        do {
            System.out.println("""
                [1] -> Take Exam
                [2] -> Get your results
                [0] -> Exit
                """);
            action = scanner.nextLine();
            studentMenuAction(action);
        }while (!action.equals("0"));
    }
    void teacherMenuAction(String action){
        switch (action){
            case "1"-> System.out.println("creating exam");
            case "2"-> System.out.println("getting exam results");
            case "3"-> System.out.println("student results");
            case "0" -> System.out.println("Exit");
            default -> System.out.println("No such action");
        }
    }
    void studentMenuAction(String action){
        switch (action){
            case "1"-> System.out.println("taking exam");
            case "2"-> System.out.println("getting exam results");
            case "0" -> System.out.println("Exit");
            default -> System.out.println("No such action");
        }
    }
    void studentLogin (Map<String, Student> students, Scanner scanner, String action){
        System.out.println("Enter your id");
        String id = scanner.nextLine();
        Student student = students.get(id);
        if (student == null){
            System.out.println("No such student id!");
            return;
        }
        System.out.println("Enter password");
        String password = scanner.nextLine();
        if (!student.getPassword().equals(password)){
            System.out.println("Wrong password");
            return;
        }
        studentMenu(scanner);
    }
    void teacherLogin (Map<String, Teacher> teachers, Scanner scanner, String action){
        System.out.println("Enter your id");
        String id = scanner.nextLine();
        Teacher teacher = teachers.get(id);
        if (teacher == null){
            System.out.println("No such student id!");
            return;
        }
        System.out.println("Enter password");
        String password = scanner.nextLine();
        if (!teacher.getPassword().equals(password)){
            System.out.println("Wrong password");
            return;
        }
        teacherMenu(scanner);
    }




}
