package lt.code.academy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.javafaker.Faker;
import lt.code.academy.data.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Evaluation {

    ObjectMapper mapper;
    Scanner scanner;
    Faker faker;
    WriteFileService writeReadFile;
    Examination examination;

    public Evaluation(ObjectMapper mapper, Scanner scanner, Faker faker, WriteFileService writeReadFile, Examination examination) {
        this.mapper = mapper;
        this.scanner = scanner;
        this.faker = faker;
        this.writeReadFile = writeReadFile;
        this.examination = examination;
    }

    void evaluationMain(Scanner scanner) {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println("Enter exam id, to make evaluation");
        String examId = scanner.nextLine();
        String examFileName = examId + FileNames.JSON_EXTENSION;
        try {
            File file = new File(examFileName);
            if (!file.exists()) {
                System.out.println("exam doesn't exist!");
                return;
            }
            Exam exam = mapper.readValue(file, new TypeReference<>() {
            });
            LocalDate now = LocalDate.now();
            if (now.isBefore(LocalDate.parse(exam.getExamDate()))) {
                System.out.println("It's to early to make evaluation! Exam date:" + exam.getExamDate());
                return;
            }
            String answerListFileName = exam.getExamId() + FileNames.ANSWERS_FILES_LIST_FILE_EXTENSION;
            evaluateStudents(answerListFileName, exam);

        } catch (IOException e) {
            System.out.println("can't read exam file:" + e.getMessage());
        }
    }

    private void evaluateStudents(String answerListFileName, Exam exam) {
        try {
            List<String> fileNames = readAnswerListFile(answerListFileName);
            Map<Student, Integer> marks = new HashMap<>();
            for (String fileName : fileNames) {
                File file = new File(fileName);
                if (!file.exists()){
                    System.out.println("0 students took exam");
                    return;
                }
                StudentAnswers studentAnswer = mapper.readValue(file, new TypeReference<>() {
                });
                Map<Integer, Integer> studentAnswers = studentAnswer.getAnswers();
                Map<Integer, Integer> rightAnswers = exam.getRightAnswers();
                int numberOfRightAnswers = compareAnswers(studentAnswers, rightAnswers);
                int totalNumberOfQuestions = rightAnswers.size();
                int grade = numberOfRightAnswers * 10 / totalNumberOfQuestions;
                System.out.printf("Student %s grade:%s%n", studentAnswer.getName(), grade);
                Student student = new Student(studentAnswer.getId(), studentAnswer.getName(), studentAnswer.getPassword());
                marks.put(student, grade);
            }
            String examMarksFileName = exam.getExamId() + FileNames.GRADES_FILE_EXTENSION;
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            writeReadFile.writeToFile(examMarksFileName, marks);

        } catch (IOException e) {
            System.out.println("can't read file:" + e.getMessage());
        }
    }

    public int compareAnswers(Map<Integer, Integer> studentAnswers, Map<Integer, Integer> rightAnswers) {
        int rightAnswerCounter = 0;
        for (Map.Entry<Integer, Integer> answer : rightAnswers.entrySet()) {
            int rightAnswer = answer.getValue();
            int studentAnswer = studentAnswers.get(answer.getKey());
            if (rightAnswer == studentAnswer) {
                rightAnswerCounter++;
            }
        }
        return rightAnswerCounter;
    }

     List<String> readAnswerListFile(String fileName) {
        try {
            List<String> fileNames = new ArrayList<>();
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("file with answers is missing:" + fileName);
                return null;
            }
            fileNames = mapper.readValue(file, new TypeReference<>() {
            });
            return fileNames;
        } catch (IOException e) {
            System.out.println("Cant read answer list file:" + e.getMessage());
        }
        return null;
    }
}
