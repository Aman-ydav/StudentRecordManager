public class Student {
    public String name;
    public int rollNo;
    public int marks;

    public Student(String name, int rollNo, int marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Roll: " + rollNo + ", Name: " + name + ", Marks: " + marks;
    }
}
