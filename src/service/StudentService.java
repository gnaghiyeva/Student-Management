package service;

import helper.InputHelper;
import model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentService {
    List<Student> students = new ArrayList<>();
    private int nextID = 1;

    public void addStudent(String name, String surname, int age, List<String> subjects, double gpa){
        Student student = new Student(nextID++,name, surname, age, subjects, gpa);
        students.add(student);
        System.out.println("Students added successfully !");
    }

    public List<Student> getAllStudents(){
        return students;
    }

    public boolean deleteStudent(int id) {
        if (students.isEmpty()) {
            System.out.println("Heç bir tələbə yoxdur . ");
            return false;
        } else {
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getId() == id) {
                    students.remove(i);
                    System.out.println("Tələbə uğurla silindi.");
                    return true;
                }
            }
            System.out.println("Bu ID-li tələbə yoxdur.");
            return false;
        }
    }

    public Student getStudentByID(int id){
        for(Student student:students){
            if(student.getId()==id){
                return student;
            }
        }
        return null;
    }
    
    public boolean updateStudent(int id){
        Student student = getStudentByID(id);
        if (student==null){
            System.out.println("Bu ID-li tələbə tapılmadı.");
            return false;
        }
        String name = InputHelper.readText("Yeni ad:");
        String surname = InputHelper.readText("Yeni soyad");
        int age = InputHelper.readNumber("Yeni yaş");
        List<String> subjects = Collections.singletonList(InputHelper.readText("Yeni fənn"));
        double gpa = InputHelper.readNumber("Yeni gpa");

        student.setName(name);
        student.setSurname(surname);
        student.setAge(age);
        student.setSubjects(subjects);
        student.setGpa(gpa);
        System.out.println("Tələbə məlumatları aşağıdakı kimi güncəlləndi" +
                student.getName()+" "+student.getSurname() +"-" +student.getAge() +" // "+"\n"+
               "Fənnlər: "+ student.getSubjects()+" "+ "GPA: "+student.getGpa());
        return true;
    }
}