package lt.code.accademy;

import java.util.Scanner;

public class LoggingMenu {

    Scanner scanner = new Scanner(System.in);
    void mainMenu(){
        String action;
        do {
            System.out.println("""
                [1] -> Teacher login
                [2] -> Student login
                [0] -> Exit
                """);
            action = scanner.nextLine();
            mainMenuAction(action);
        }while (!action.equals("0"));

    }
    void teacherMenu(){
        String action;
        do {
            System.out.println("""
                [1] -> Create exam
                [2] -> Get exam results
                [3] -> Get student result
                [0] -> Exit
                """);
            action = scanner.nextLine();
            teacherManuAction(action);
        }while (!action.equals("0"));
    }
    void studentMenu(){
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
    void mainMenuAction(String action){
        switch (action){
            case "1" -> teacherMenu();
            case "2" -> studentMenu();
            case "0" -> System.out.println("Exit");
            default -> System.out.println("No such action");
        }
    }
    void teacherManuAction(String action){
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

}
