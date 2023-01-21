package lt.code.accademy;

import java.util.Scanner;

public class LoggingMenu {

    Scanner scanner = new Scanner(System.in);
    String action = scanner.nextLine();

    void mainMenu(){
        System.out.println("""
                [1] -> Teacher login
                [2] -> Student login
                [0] -> Exit
                """);
    }
    void teacherMenu(){
        System.out.println("""
                [1] -> Create exam
                [2] -> Get exam results
                [3] -> Get student result
                [0] -> Exit
                """);
    }
    void StudentMenu(){
        System.out.println("""
                [1] -> Take Exam
                [2] -> Get your results
                [0] -> Exit
                """);
    }
    void mainMenuAction(String a){
        switch (a){
            case "1" -> teacherManuAction();
            case "2" ->studentMenuAction();
            case "0" -> System.out.println("Exit");
            default -> System.out.println("No such action");
        }
    }
    void teacherManuAction(){
        switch (action){
            case "1"-> System.out.println("creating exam");
            case "2"-> System.out.println("getting exam results");
            case "3"-> System.out.println("student results");
            case "0" -> System.out.println("Exit");
            default -> System.out.println("No such action");
        }
    }
    void studentMenuAction(){
        switch (action){
            case "1"-> System.out.println("taking exam");
            case "2"-> System.out.println("getting exam results");
            case "0" -> System.out.println("Exit");
            default -> System.out.println("No such action");

        }

    }

}
