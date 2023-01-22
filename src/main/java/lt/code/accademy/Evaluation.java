package lt.code.accademy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.code.accademy.data.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Evaluation {

    ObjectMapper mapper = new ObjectMapper();
    Examination examination = new Examination();
    WriteReadFile writeReadFile = new WriteReadFile();

    void evaluationMain (Scanner scanner){
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
                System.out.println("total number of questions:" + totalNumberOfQuestions);
                int grade = numberOfRightAnswers *10/totalNumberOfQuestions;
                System.out.println("grade:" + grade);
                Student student = new Student( studentAnswer.getId(),studentAnswer.getName());
                marks.put(student, grade);
            }
            String examMarksFileName = exam.getExamId() + FileNames.GRADES_FILE_EXTENSION;
            writeReadFile.writeToFile(mapper,examMarksFileName, marks);

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
       System.out.println(rightAnswerCounter);
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

}
