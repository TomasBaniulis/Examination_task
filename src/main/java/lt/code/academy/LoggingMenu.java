package lt.code.academy;

import lt.code.academy.data.Student;
import lt.code.academy.data.Teacher;

import java.util.Map;
import java.util.Scanner;

public class LoggingMenu {
    Scanner scanner;
    Examination examination;
    Evaluation evaluation;
    Grade grade;

    public LoggingMenu(Scanner scanner, Examination examination, Evaluation evaluation, Grade grade) {
        this.scanner = scanner;
        this.examination = examination;
        this.evaluation = evaluation;
        this.grade = grade;
    }

    void mainMenu(Map<String, Student> students, Map<String, Teacher> teachers){
        String action;
        do {
            System.out.println("""
                [1] -> Teacher login
                [2] -> Student login
                [0] -> Exit
                """);
            action = scanner.nextLine();
            mainMenuAction(action,students, teachers);
        }while (!action.equals("0"));
    }
    void mainMenuAction(String action, Map<String, Student> students, Map <String, Teacher> teachers){
        switch (action){
            case "1" -> teacherLogin(teachers, action);
            case "2" -> studentLogin(students, action );
            case "0" -> System.out.println("Exit");
            default -> System.out.println("No such action");
        }
    }
    void teacherMenu(Teacher teacher ){
        String action;
        do {
            System.out.println("""
                [1] -> Create exam
                [2] -> Evaluate exam
                [3] -> Get student result
                [0] -> Exit
                """);
            action = scanner.nextLine();
            teacherMenuAction(action, teacher);
        }while (!action.equals("0"));
    }
    void studentMenu( Student student){
        String action;
        do {
            System.out.println("""
                [1] -> Take Exam
                [2] -> Get your results
                [0] -> Exit
                """);
            action = scanner.nextLine();
            studentMenuAction(action, student);
        }while (!action.equals("0"));
    }
    void teacherMenuAction(String action, Teacher teacher){
        switch (action){
            case "1"-> examination.createExam(teacher);
            case "2"-> evaluation.evaluationMain(scanner);
            case "3"-> grade.showGradesForTeacher();
            case "0" -> System.out.println("Exit");
            default -> System.out.println("No such action");
        }
    }
    void studentMenuAction(String action, Student student){
        switch (action){
            case "1"-> examination.takeExam(student);
            case "2"-> grade.showExamGradeForStudent(student);
            case "0" -> System.out.println("Exit");
            default -> System.out.println("No such action");
        }
    }
    void studentLogin (Map<String, Student> students, String action){
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
        studentMenu(student);
    }
    void teacherLogin (Map<String, Teacher> teachers, String action){
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
        teacherMenu(teacher);
    }




}
