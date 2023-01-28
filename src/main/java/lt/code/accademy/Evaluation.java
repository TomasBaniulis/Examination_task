package lt.code.accademy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.javafaker.Faker;
import lt.code.accademy.data.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Evaluation {

    ObjectMapper mapper;
    Scanner scanner;
    Faker faker;
    WriteReadFile writeReadFile;
    Examination examination;

    public Evaluation(ObjectMapper mapper, Scanner scanner, Faker faker, WriteReadFile writeReadFile, Examination examination) {
        this.mapper = mapper;
        this.scanner = scanner;
        this.faker = faker;
        this.writeReadFile = writeReadFile;
        this.examination = examination;
    }

    void evaluationMain (Scanner scanner){
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println("Enter exam id, to make evaluation");
        String examId = scanner.nextLine();
        String examFileName = examId + FileNames.JSON_EXTENSION;
        try {
            File file = new File(examFileName);
            if (!file.exists()){
                System.out.println("exam id doesn't exist!");
                return;
            }
            Exam exam = mapper.readValue(file, new TypeReference<>() {});
            LocalDate now = LocalDate.now();
            if (now.isBefore(LocalDate.parse(exam.getExamDate()))){
                System.out.println("It's to early to make evaluation! Exam date:" + exam.getExamDate());
                return;
            }
            String answerListFileName = exam.getExamId() + FileNames.ANSWERS_FILES_LIST_FILE_EXTENSION;
            evaluateStudents(answerListFileName, exam);

        }catch (IOException e){
            System.out.println("can't read exam file:" + e.getMessage());
        }
    }

    void evaluateStudents (String answerListFileName, Exam exam){
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            List<String> fileNames = readAnswerListFile(answerListFileName);
            Map < Student, Integer> marks = new HashMap<>();

            for (String fileName : fileNames) {
                File file = new File(fileName);
                StudentAnswers studentAnswer = mapper.readValue(file, new TypeReference<>() {});
                Map<Integer, Integer> studentAnswers = studentAnswer.getAnswers();
                Map<Integer, Integer> rightAnswers = exam.getRightAnswers();
                int numberOfRightAnswers = compareAnswers(studentAnswers, rightAnswers);
                int totalNumberOfQuestions = rightAnswers.size();
                int grade = numberOfRightAnswers *10/totalNumberOfQuestions;
                System.out.printf("Student %s grade:%s%n", studentAnswer.getName(), grade);
                Student student = new Student( studentAnswer.getId(),studentAnswer.getName(), studentAnswer.getPassword());
                marks.put(student, grade);
            }
            String examMarksFileName = exam.getExamId() + FileNames.GRADES_FILE_EXTENSION;
            writeReadFile.writeToFile(examMarksFileName, marks);

        }catch (IOException e){
            System.out.println("can't read file:" + e.getMessage() );
        }
    }

   int compareAnswers (Map <Integer, Integer> studentAnswers, Map <Integer, Integer> rightAnswers ){
        int rightAnswerCounter = 0;
        for (Map.Entry <Integer, Integer> answer : rightAnswers.entrySet()){
            int rightAnswer = answer.getValue();
            int studentAnswer = studentAnswers.get(answer.getKey());
            if (rightAnswer == studentAnswer){
                rightAnswerCounter ++;
            }
        }
        return rightAnswerCounter;
    }

    List <String> readAnswerListFile (String fileName) {
            try{
                List<String> fileNames = new ArrayList<>();
                File file = new File(fileName);
                if (!file.exists()){
                    System.out.println("file with answers is missing:" + fileName);
                    return null;
                }
                fileNames = mapper.readValue(file, new TypeReference<>() {});
                return fileNames;
            }catch (IOException e) {
                System.out.println("Cant read answer list file:" + e.getMessage());
            }
        return null;

    }

    void readEvaluation () {
        System.out.println("enter exam id");
        String examId = scanner.nextLine();
        String fileName = examId + FileNames.GRADES_FILE_EXTENSION;
        try{
            List<String> fileNames = new ArrayList<>();
            File file = new File(fileName);
            if (!file.exists()){
                System.out.println("file with grades is missing:" + fileName);
                return;
            }
            Map<Student, Integer> grades = mapper.readValue(file, new TypeReference<>() {});
            System.out.println("sucesfuly rad file !!!!!!!");

        }catch (IOException e) {
            System.out.println("Cant read answer list file:" + e.getMessage());
        }

    }


}
