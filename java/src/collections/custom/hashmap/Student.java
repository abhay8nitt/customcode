package collections.custom.hashmap;

import java.util.Objects;

class Student {
    private String name;
    private int rollNumber;
    private String grade;
    private char hosteler;

    public Student(String name, int rollNumber, String grade, char hosteler) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.hosteler = hosteler;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return rollNumber == student.rollNumber &&
                Objects.equals(name, student.name) &&
                Objects.equals(grade, student.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rollNumber, grade);
    }

    @Override
    public String toString() {
        return "Student(" +
                "name='" + name + '\'' +
                ", rollNumber=" + rollNumber +
                ", grade='" + grade + '\'' +
                ", hosteler=" + hosteler +
                ')';
    }
}
