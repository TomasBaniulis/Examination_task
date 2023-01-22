package lt.code.accademy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.code.accademy.data.Exam;
import lt.code.accademy.data.ExamMarks;
import lt.code.accademy.data.Student;
import lt.code.accademy.data.StudentAnswers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Evaluation {

    ObjectMapper mapper = new ObjectMapper();
    Examination examination = new Examination();

    void evaluationMain (Scanner scanner){
        System.out.println("Enter exam id, to make evaluation");
        String examId = scanner.nextLine();



    }

    void evaluateStudents (String namesListFile, Exam exam, String examFileName){
        try {
            List<String> fileNames = readStudentsAnswersFile(namesListFile);
            Map < Student, Integer> marks = new HashMap<>();

            for (String fileName : fileNames) {
                File file = new File(fileName);
                StudentAnswers studentAnswer = mapper.readValue(file, new TypeReference<StudentAnswers>() {});
                Map<Integer, Integer> studentAnswers = getStudentAnswers(fileName);
                Map<Integer, Integer> rightAnswers = getRightAnswers(exam, examFileName);
                int numberOfRightAnswers = compareAnswers(studentAnswers, rightAnswers);
                int totalNumberOfQuestions = rightAnswers.size();
                int grade = numberOfRightAnswers/totalNumberOfQuestions;
                Student student = new Student(studentAnswer.getId(), studentAnswer.getName());
                marks.put(student, grade);
            }
            String examMarksFileName = exam.getExamId()+"marks.json";
            examination.writeToFile(examMarksFileName, marks);

        }catch (IOException e){
            System.out.println("can't read file:" + e.getMessage() );
        }
    }

    int compareAnswers (Map <Integer, Integer> studentAnswers, Map <Integer, Integer> rightAnswers ){
        if(studentAnswers.size() != rightAnswers.size()){
            System.out.println("answers can't be compared");
            return 0;
        }
        int rightAnswerCounter =0;
        for (Map.Entry <Integer, Integer> answer : rightAnswers.entrySet()){
            if (answer.getValue() == studentAnswers.get(answer.getKey())){
                rightAnswerCounter ++;
            }

            return rightAnswerCounter;
        }
        return 0;
    }

    Map <Integer, Integer> getRightAnswers (Exam exam, String examFileName){
        try {
            File file = new File(examFileName);
            if (!file.exists()) {
                System.out.println("can't find exam file");
                return null;
            }
            Map<Integer, Integer> rightAnswers = mapper.readValue(file, new TypeReference<>() {});
            return rightAnswers;
        }catch (IOException e){
            System.out.println("can't read the file:" + e.getMessage());
        }
        return null;
    }

    Map <Integer, Integer> getStudentAnswers (String fileName){
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("can't find student answers file");
                return null;
            }
            Map<Integer, Integer> studentAnswers = mapper.readValue(file, new TypeReference<>() {});
            return studentAnswers;
        }catch (IOException e){
            System.out.println("can't read the file:" + e.getMessage());
        }
        return null;
    }
    List <String> readStudentsAnswersFile (String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("The answer list file doesn't exist!!!");
            return null;
        }
        try{
            List<String>fileNames = mapper.readValue(file, new TypeReference<>() {});
            return fileNames;
        }catch (IOException e){
            System.out.println("Can't read the file:" + e.getMessage());
        }
        return null;
    }

}
