package lt.code.accademy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.javafaker.Faker;
import lt.code.accademy.data.Student;
import lt.code.accademy.data.Teacher;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
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
        Map<String, Student> students = mapper.readValue(studentFile, new TypeReference<>() {});
        Map<String, Teacher> teachers = mapper.readValue(teacherFile, new TypeReference<>() {});

        //studentsAndTeachers.generateStudents(mapper, faker);
        //studentsAndTeachers.generateTeachers(mapper, faker);
        menu.mainMenu(scanner, students, teachers);
    }

}
