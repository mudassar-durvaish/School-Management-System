package sms.first;

public class TimeSchedule {
    private String classPeriod;
    private String classSubject;
    private int roomCapacity;
    private String roomID;
    private String teacherName;

    public TimeSchedule(String classPeriod, String classSubject, int roomCapacity, String roomID, String teacherName) {
        this.classPeriod = classPeriod;
        this.classSubject = classSubject;
        this.roomCapacity = roomCapacity;
        this.roomID = roomID;
        this.teacherName = teacherName;
    }

    public String getClassPeriod() {
        return classPeriod;
    }

    public void setClassPeriod(String classPeriod) {
        this.classPeriod = classPeriod;
    }

    public String getClassSubject() {
        return classSubject;
    }

    public void setClassSubject(String classSubject) {
        this.classSubject = classSubject;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "TimeSchedule{" +
                "classPeriod='" + classPeriod + '\'' +
                ", classSubject='" + classSubject + '\'' +
                ", roomCapacity=" + roomCapacity +
                ", roomID='" + roomID + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}

