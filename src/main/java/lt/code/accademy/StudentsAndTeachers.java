package lt.code.accademy;

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

    Map<String, Teacher> teachers = new HashMap<>();
    Map<String, Student> students = new HashMap<>();
    Examination examination = new Examination();

    void generateStudents (ObjectMapper mapper, Faker faker){
        int counter = 1;
        for (int i=0; i<20; i++){
            String id = String.valueOf(counter);
            String name = faker.name().fullName();
            String password = "alio";
            students.put(id, new Student(id, name, password));
            counter++;
        }
        examination.writeToFile(mapper, studentFile, students);
    }
    void generateTeachers (ObjectMapper mapper, Faker faker){
        int counter = 1;
        for (int i=0; i<3; i++){
            String subject = faker.superhero().name();
            String name = faker.name().fullName();
            String password = "alio";
            String id = String.valueOf(counter);
            teachers.put(id, new Teacher(subject, id, name, password) );
            counter++;
        }
        examination.writeToFile(mapper, teachersFile, teachers);
    }



}
