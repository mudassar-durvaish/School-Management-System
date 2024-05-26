package sms.first;

public class Employee {

    private String employeeId;
    private String name;
    private String gender;
    private String level;
    private int salary;

    public Employee(String employeeId, String name, String gender, String level, int salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.gender = gender;
        this.level = level;
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", level='" + level + '\'' +
                ", salary=" + salary +
                '}';
    }
}
