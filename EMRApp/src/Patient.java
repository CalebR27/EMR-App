import java.time.LocalDate;
import java.time.Period;

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

    public Patient(String name, String DOB, String sex, String notes) {
        this.name = name;
        this.DOB = DOB;
        changeDOBFormat();
        calculateAge();
        this.sex = sex;
        this.PID = 0;
        this.notes = notes;
    }

    public Patient(String name, String DOB, String sex, int PID, String notes) {
        this.name = name;
        this.DOB = DOB;
        changeDOBFormat();
        calculateAge();
        this.sex = sex;
        this.PID = PID;
        this.notes = notes;
    }

    public Patient(String name, int age, String DOB, String sex, int PID, String notes) {
        this.name = name;
        this.age = age;
        this.DOB = DOB;
        changeDOBFormat();
        this.sex = sex;
        this.PID = PID;
        this.notes = notes;
    }

    private void calculateAge() {

        //Getting the year, month, and day of the DOB with format MM/dd/yyyy
        int year = Integer.valueOf(this.DOB.substring(6)).intValue();
        int month = Integer.valueOf(this.DOB.substring(0, 2)).intValue();
        int day = Integer.valueOf(this.DOB.substring(3, 5)).intValue();
        
        LocalDate DOB = LocalDate.of(year, month, day);
        this.age = Period.between(DOB, LocalDate.now()).getYears();
    }

    private void changeDOBFormat() {
        if (this.DOB != null && this.DOB.contains("-")) {
            String year = this.DOB.substring(0, 4);
            String month = this.DOB.substring(5, 7);
            String day = this.DOB.substring(8, 10);

            this.DOB = month + "/" + day + "/" + year;
        }
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

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Patient or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof Patient)) { 
            return false; 
        } 
          
        // typecast o to Patient so that we can compare data members  
        Patient c = (Patient) o; 
          
        // Compare the data members and return accordingly  
        return Integer.compare(this.PID, c.getPID()) == 0; 
    }

}