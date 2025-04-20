package model;

import java.util.List;

public class Student {
    private int id;
    private String name;
    private String surname;
    private int age;
    private List<String> subjects;
    private double gpa;

    public Student (int id, String name, String surname, int age, List<String> subjects, double gpa) {
        this.id = id;
        this.name = name;
        this.surname=surname;
        this.age=age;
        this.subjects=subjects;
        this.gpa=gpa;
    }



    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

}
