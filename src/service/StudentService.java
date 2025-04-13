package service;

import model.Student;

import java.util.ArrayList;
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
}