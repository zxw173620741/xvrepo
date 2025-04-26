public class Student extends Person {
    String studentId;

    public Student() {
        studentId = "";
    }

    public Student(String studentId) {
        this.studentId = studentId;
    }

    public void study() {
        System.out.println(name + "正在学习，学号：" + studentId);
    }
}