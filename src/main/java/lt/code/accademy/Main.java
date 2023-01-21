package lt.code.accademy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Examination examination = new Examination();
        LoggingMenu menu = new LoggingMenu();
        String action;
        do {
            menu.mainMenu();
            action = scanner.nextLine();
            menu.mainMenuAction(action);
        }while (!action.equals("0"));
    }





}
