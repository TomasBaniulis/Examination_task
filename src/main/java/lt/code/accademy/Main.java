package lt.code.accademy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.javafaker.Faker;
import lt.code.accademy.data.FileNames;
import lt.code.accademy.data.Student;
import lt.code.accademy.data.Teacher;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Faker faker = new Faker();
        StudentsAndTeachers studentsAndTeachers = new StudentsAndTeachers();
        LoggingMenu menu = new LoggingMenu();

       File studentFile = new File("students.json");
       File teacherFile = new File("teachers.json");
        Map<String, Student> students = studentsAndTeachers.readStudents(mapper, FileNames.STUDENTS_FILE.toString());
        Map<String, Teacher> teachers = studentsAndTeachers.readTeachers(mapper, FileNames.TEACHERS_FILE.toString());

        //studentsAndTeachers.generateStudents(mapper, faker);
        //studentsAndTeachers.generateTeachers(mapper, faker);
        menu.mainMenu(scanner, students, teachers);
    }

}
