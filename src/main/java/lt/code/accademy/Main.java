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
    public static void main(String[] args){
        Faker faker = new Faker();
        ObjectMapper mapper = new ObjectMapper();
        Scanner scanner = new Scanner(System.in);
        WriteReadFile writeReadFile = new WriteReadFile(mapper);
        Examination examination = new Examination(scanner, faker, mapper, writeReadFile);
        Evaluation evaluation = new Evaluation(mapper, scanner, faker, writeReadFile, examination);
        Grade grade = new Grade(mapper, scanner);
        LoggingMenu menu = new LoggingMenu(scanner,examination, evaluation, grade);

        StudentsAndTeachers studentsAndTeachers = new StudentsAndTeachers(mapper, faker, writeReadFile);

        //studentsAndTeachers.generateStudents();
        //studentsAndTeachers.generateTeachers();

        Map<String, Student> students = studentsAndTeachers.readStudents();
        Map<String, Teacher> teachers = studentsAndTeachers.readTeachers();

        menu.mainMenu(students, teachers);
    }
}
