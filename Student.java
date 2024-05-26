package sms.first;

public class Student {

    private String studentId;
    private String name;
    private int session;
    private int studentClass;
    private int studentAttendance;

    public Student(String studentId, String name, int session, int studentClass, int studentAttendance) {
        this.studentId = studentId;
        this.name = name;
        this.session = session;
        this.studentClass = studentClass;
        this.studentAttendance = studentAttendance;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public int getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(int studentClass) {
        this.studentClass = studentClass;
    }

    public int getStudentAttendance() {
        return studentAttendance;
    }

    public void setStudentAttendance(int studentAttendance) {
        this.studentAttendance = studentAttendance;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", session=" + session +
                ", studentClass=" + studentClass +
                ", studentAttendance=" + studentAttendance +
                '}';
    }
}
