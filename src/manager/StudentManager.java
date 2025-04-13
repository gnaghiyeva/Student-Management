package manager;

import helper.InputHelper;
import model.Student;
import service.StudentService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentManager {
    private StudentService studentService = new StudentService();
    public void start(){
        int choice;

        do {
            printMenu();
            choice = InputHelper.readNumber("Seçim: ");

            switch (choice){
                case 1:
                    addStudent();
                case 2:
                    showAllStudents();
                case 3:
                    deleteStudent();

            }
        }while (choice!=0);

    }
    private void printMenu(){
        System.out.println("S T U D E N T    M A N A G E M E N T    S Y S T E M ");
        System.out.println("""
                1. Yeni tələbə əlavə et
                2. Bütün tələbələri göstər
                3. Tələbəni sil
                """);
    }


    public void addStudent(){
        String name = InputHelper.readText("Ad: ");
        String surname = InputHelper.readText("Soyad: ");
        int age = InputHelper.readNumber("Yaş: ");
        String[] subjectArr  = InputHelper.readText("Subjects: ").split(", ");

        List<String> subjects = new ArrayList<>();
        for(String subject:subjectArr){
            subjects.add(subject.trim());
        }

        double gpa = InputHelper.readNumber("GPA: ");

        studentService.addStudent(name,surname,age,subjects,gpa)
        ;
    }

    public void showAllStudents(){
        List<Student> students = studentService.getAllStudents();
        if(students.isEmpty()){
            System.out.println("Heç bir tələbə yoxdur . ");
        }
        else {
            students.forEach(student ->
                    System.out.println(student.getId()+". "+
                            student.getName() + " " +student.getSurname() + " - "+
                            student.getAge() + " yaş"));
        }

    }

    public void deleteStudent(){
        while (true){
            int id = InputHelper.readNumber("Hansı id li tələbəni silmək istəyirsən ?");
            boolean deleted =  studentService.deleteStudent(id);
            if(deleted){
                break;
            }
        }
    }
}
