package lt.code.academy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lt.code.academy.data.FileNames;
import lt.code.academy.data.Student;
import lt.code.academy.data.Teacher;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StudentsAndTeachers {
    ObjectMapper mapper;
    Faker faker;
    WriteFileService writeReadFile;

    public StudentsAndTeachers(ObjectMapper mapper, Faker faker, WriteFileService writeReadFile) {
        this.mapper = mapper;
        this.faker = faker;
        this.writeReadFile = writeReadFile;
    }

    void generateStudents() {
        Map<String, Student> students = new HashMap<>();
        int counter = 1;
        for (int i = 0; i < 20; i++) {
            String id = String.valueOf(counter);
            String name = faker.name().fullName();
            String password = "alio";
            students.put(id, new Student(id, name, password));
            counter++;
        }
        writeReadFile.writeToFile(FileNames.STUDENTS_FILE.toString(), students);
    }
    void generateTeachers() {
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
        writeReadFile.writeToFile( FileNames.TEACHERS_FILE.toString(), teachers);
    }
    Map<String, Student> readStudents() {
        try {
            File file = new File(FileNames.STUDENTS_FILE.toString());
            Map<String, Student> students = mapper.readValue(file, new TypeReference<>() {
            });
            return students;
        } catch (IOException e) {
            System.out.printf("Can't read %s files: %s%n", FileNames.STUDENTS_FILE.toString(), e.getMessage());
        }
        return null;
    }
    Map<String, Teacher> readTeachers() {
        try {
            File file = new File(FileNames.TEACHERS_FILE.toString());
            Map<String, Teacher> teachers = mapper.readValue(file, new TypeReference<>() {
            });
            return teachers;
        } catch (IOException e) {
            System.out.printf("Can't read %s files: %s%n", FileNames.TEACHERS_FILE, e.getMessage());
        }
        return null;
    }
}
