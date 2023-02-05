package lt.code.academy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.javafaker.Faker;
import lt.code.academy.data.*;

import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Examination {
    Scanner scanner;
    Faker faker;
    ObjectMapper mapper;
    WriteFileService writeReadFile;

    public Examination(Scanner scanner, Faker faker, ObjectMapper mapper, WriteFileService writeReadFile) {
        this.scanner = scanner;
        this.faker = faker;
        this.mapper = mapper;
        this.writeReadFile = writeReadFile;
    }

    Random random = new Random();

    void createExam (Teacher teacher){
        Exam exam = generateExamQuestions(teacher);
        String fileName = exam.getExamId() + FileNames.JSON_EXTENSION;
        writeReadFile.writeToFile(fileName, exam);
    }

     Exam generateExamQuestions (Teacher teacher){
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String examId =faker.code().ean8();
        String examName = faker.commerce().material();
        String date = getDate().toString();
        Map<Integer, String> questions = new HashMap<>();
        Map<Integer, Integer> answers = new HashMap<>();
        int counter = 1;
        for (int i = 0; i<10; i++){
            String question = faker.chuckNorris().fact();
            questions.put(counter, question);
            int answer = random.nextInt(1,3);
            answers.put(counter,answer);
            counter++;
        }
        System.out.println("Exam has been generated");
        System.out.println("Exam topic: " + examName );
        System.out.println("Exam id: " + examId);
        return new Exam(teacher.getSubjectName(), teacher.getTeacherName(),
                examId, examName, date, questions, answers);
    }

    void takeExam (Student student){
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println("Enter exam id");
        String id = scanner.nextLine();
        String examFileName = id + FileNames.JSON_EXTENSION; ;
        File examFile = new File(examFileName);
        if (!examFile.exists()){
            System.out.println("No such exam id!");
            return;
        }
        try {
            Exam exam = mapper.readValue(examFile, new TypeReference<>() {});
            LocalDate dateNow = LocalDate.now();
            if (!dateNow.equals(LocalDate.parse(exam.getExamDate()))){
                System.out.printf("You can't take exam today! Exam date: %s%n", exam.getExamDate() );
                return;
            }
            String fileName = id + student.getId() + FileNames.JSON_EXTENSION;
            String answersListFile = exam.getExamId() + FileNames.ANSWERS_FILES_LIST_FILE_EXTENSION;
            boolean firstAttempt = checkForSecondAttempt(fileName, answersListFile);
            if (firstAttempt != true){
                return;
            }
            Map <Integer, Integer> studentAnswers = runQuestions(exam);
            StudentAnswers answers = new StudentAnswers(student.getId(), student.getName(), exam.getExamId(), studentAnswers);
            writeReadFile.writeToFile(fileName, answers);
            createListOfStudentAnswerFiles(exam, fileName);
        }catch (IOException e){
            System.out.println("Can't read exam file:" + e.getMessage());
        }
    }

    Map<Integer, Integer> runQuestions (Exam exam){
        Map <Integer, String> questions = exam.getQuestions();
        Map<Integer, Integer> answers = new HashMap<>();
        int answer;
        for (Map.Entry<Integer, String> question: questions.entrySet()){
            System.out.printf("Question no. %s%n", question.getKey());
            System.out.println(question.getValue());
            answer = random.nextInt(1,3);
            System.out.println("Answer:" + answer);
            answers.put(question.getKey(), answer);
        }
        System.out.println("You have finished your exam!");
        return answers;
    }

    void createListOfStudentAnswerFiles (Exam exam, String name) {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<String > fileNames = new ArrayList<>();
        String fileName = exam.getExamId() + FileNames.ANSWERS_FILES_LIST_FILE_EXTENSION;
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
                fileNames.add(name);
                mapper.writeValue(file, fileNames);
                return;
            }
            fileNames = mapper.readValue(file, new TypeReference<>() {});
            fileNames.add(name);
            mapper.writeValue(file, fileNames);
        }catch (IOException e){
            System.out.println("Cant create file:" + e.getMessage());
        }
    }
    boolean checkForSecondAttempt (String fileName, String listFile){
        try {
            File file = new File(listFile);
            if (file.exists()) {
                List<String> answers = mapper.readValue(file, new TypeReference<>() {
                });
                for (String answer : answers) {
                    if (answer.equals(fileName)) {
                        System.out.println("You can't take exam second time!");
                        return false;
                    }
                }
            }
        }catch (IOException e){
            System.out.printf("Can't read %s file : %s%n", fileName, e.getMessage());
        }
        return true;
    }

    LocalDate getDate (){
        while (true){
            try{
                System.out.println("Enter exam date yyyy.MM.dd :");
                String date = scanner.nextLine();
                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                return localDate;
            }catch (DateTimeException e) {
                System.out.println("Wrong date format. Try again!");
            }
        }
    }
}

