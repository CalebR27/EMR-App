public class Patient {

    private String name;
    private int age;
    private String DOB;
    private String sex;
    private int PID;
    private String notes;

    public Patient() {
        this.name = null;
        this.age = 0;
        this.DOB = null;
        this.sex = null;
        this.PID = 0;
        this.notes = null;
    }

    public Patient(String name, int age, String DOB, String sex, int PID, String notes) {
        this.name = name;
        this.age = age;
        this.DOB = DOB;
        this.sex = sex;
        this.PID = PID;
        this.notes = notes;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getDOB() {
        return this.DOB;
    }

    public String getSex() {
        return this.sex;
    }

    public int getPID() {
        return this.PID;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}