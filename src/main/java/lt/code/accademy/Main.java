package lt.code.accademy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.javafaker.Faker;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Faker faker = new Faker();
        StudentsAndTeachers studentsAndTeachers = new StudentsAndTeachers();
        LoggingMenu menu = new LoggingMenu();

        studentsAndTeachers.generateStudents(mapper, faker);
        studentsAndTeachers.generateTeachers(mapper, faker);
        menu.mainMenu();
    }





}
