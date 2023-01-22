package lt.code.accademy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lt.code.accademy.data.Student;
import lt.code.accademy.data.Teacher;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentsAndTeachers {
    String studentFile = "students.json";
    String teachersFile = "teachers.json";

    Examination examination = new Examination();
    void generateStudents(ObjectMapper mapper, Faker faker) {
        Map<String, Student> students = new HashMap<>();
        int counter = 1;
        for (int i = 0; i < 20; i++) {
            String id = String.valueOf(counter);
            String name = faker.name().fullName();
            String password = "alio";
            students.put(id, new Student(id, name, password));
            counter++;
        }
        examination.writeToFile(studentFile, students);
    }
    void generateTeachers(ObjectMapper mapper, Faker faker) {
        Map<String, Teacher> teachers = new HashMap<>();
        int counter = 1;
        for (int i = 0; i < 3; i++) {
            String subject = faker.superhero().name();
            String name = faker.name().fullName();
            String password = "alio";
            String id = String.valueOf(counter);
            teachers.put(id, new Teacher(subject, id, name, password));
            counter++;
        }
        examination.writeToFile(teachersFile, teachers);
    }
    Map<String, Student> readStudents(ObjectMapper mapper, String studentsFile) {
        try {
            Map<String, Student> students = mapper.readValue(studentsFile, new TypeReference<>() {
            });
            return students;
        } catch (IOException e) {
            System.out.printf("Can't read %s files: %s%n", studentsFile, e.getMessage());
        }
        return null;
    }
    Map<String, Teacher> readTeachers(ObjectMapper mapper, String teachersFile) {
        try {
            Map<String, Teacher> teachers = mapper.readValue(teachersFile, new TypeReference<>() {
            });
            return teachers;
        } catch (IOException e) {
            System.out.printf("Can't read %s files: %s%n", teachersFile, e.getMessage());
        }
        return null;
    }
}
