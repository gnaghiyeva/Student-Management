package service;

import helper.InputHelper;
import model.Student;

import java.util.*;
import java.util.stream.Collectors;

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
        System.out.println("Əgər hər hansı sahəni dəyişmək istəmirsənsə, sadəcə Enter bas.");

        String name = InputHelper.readText("Yeni ad ("+student.getName()+"): ");
        if (!name.isBlank()) {
            student.setName(name);
        }

        String surname = InputHelper.readText("Yeni soyad ("+student.getSurname()+"): ");
        if(!surname.isBlank()){
            student.setSurname(surname);
        }

        String age = InputHelper.readText("Yeni yaş (" + student.getAge() + "): ");
        if (!age.isBlank()) {
            try {
                int newAge = Integer.parseInt(age);
                student.setAge(newAge);
            } catch (NumberFormatException e) {
                System.out.println("Yaş rəqəm formatında deyil. Keçildi.");
            }
        }

        String subjectsInput = InputHelper.readText("Yeni fənlər (" + student.getSubjects() + "): ");

        if (subjectsInput != null && !subjectsInput.isBlank()) {
            List<String> subjects = Arrays.stream(subjectsInput.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());
            student.setSubjects(subjects);
        }

        String gpa = InputHelper.readText("Yeni gpa ("+ student.getGpa() + "): ");
        if (!gpa.isBlank()){
            try {
               double newGpa = Double.parseDouble(gpa);
               student.setGpa(newGpa);
            }catch (NumberFormatException e){
                System.out.println("GPA düzgün formatda deyil. Keçildi");
            }
        }
        System.out.println("Tələbə məlumatları aşağıdakı kimi güncəlləndi" +
                student.getName()+" "+student.getSurname() +"-" +student.getAge() +" // "+"\n"+
               "Fənnlər: "+ student.getSubjects()+" "+ "GPA: "+student.getGpa());
        return true;
    }

    public void findByName(String searchingName){
//        students.stream().filter(name->name.equals(searchingName)).collect(Collectors.toList());
      List<Student> filteredStudents =  students.stream().filter(student -> student.getName().equals(searchingName)).collect(Collectors.toList());
      if(filteredStudents.isEmpty()){
          System.out.println("Tələbə tapılmadı");
      }
      else {
//          filteredStudents.forEach(findedStudents-> System.out.println(findedStudents));
          filteredStudents.forEach(System.out::println);
      }
    }

    public void orderStudents(){
        List<Student> orderedStudents = students.stream().sorted(Comparator.comparing(student -> student.getGpa())).toList();
        orderedStudents.forEach(System.out::println);

    }

    public void oldStudent(){
        Optional<Student> oldStudent = students.stream().max(Comparator.comparing(Student::getAge));

        if(oldStudent.isPresent()){
            Student student = oldStudent.get();
            System.out.println(student.getName() + " "+ student.getSurname() + " "+ student.getAge() + "yaş");
        }else {
            System.out.println("Tələbə siyahısı boşdur");
        }

    }

    public void GroupStudentsBySubject(String title){
        List<Student> groupedStudents = students.stream().filter(student -> student.getSubjects().contains(title)).collect(Collectors.toList());
        if(groupedStudents.isEmpty()){
            System.out.println("Tələbə tapılmadı.");
        }else {
            groupedStudents.forEach(System.out::println);
        }
    }
}