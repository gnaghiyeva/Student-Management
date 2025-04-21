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
                    break;
                case 2:
                    showAllStudents();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    findStudent();
                    break;
                case 6:
                    orderStudents();
                case 7:
                    findOldStudent();
                case 8:
                    groupedStudents();


            }
        }while (choice!=0);

    }
    private void printMenu(){
        System.out.println("S T U D E N T    M A N A G E M E N T    S Y S T E M ");
        System.out.println("""
                1. Yeni tələbə əlavə et
                2. Bütün tələbələri göstər
                3. Tələbəni sil
                4. Tələbəni güncəllə
                5. Tələbəni tap
                6. GPA uyğun tələbələri sırala
                7. Ən yaşlı tələbəni tap
                8. Tələbələri fənnə görə qruplaşdır
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

    public void updateStudent(){
        if (studentService.getAllStudents().isEmpty()) {
            System.out.println("Heç bir tələbə yoxdur.");
            return;
        }
        while (true) {
            int id = InputHelper.readNumber("Hansı ID-li tələbəni güncəlləmək istəyirsən?");
            boolean updated = studentService.updateStudent(id);
            if (updated) {
                break;
            }
        }
    }

    public void findStudent(){
        String name = InputHelper.readText("Hansi adli sagirid axtarmaq steyirsen");
        studentService.findByName(name);
    }

    public void orderStudents(){
        studentService.orderStudents();
    }

    public void findOldStudent(){
        if(studentService.getAllStudents().isEmpty()){
            System.out.println("Heç bir tələbə yoxdur.");
        }else {
            studentService.oldStudent();
        }
    }

    public void groupedStudents(){
        String title = InputHelper.readText("Hansı fənnə görə qruplaşdırmaq istəyirsiniz ?");
        studentService.GroupStudentsBySubject(title);
    }
}
