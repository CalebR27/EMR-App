public class Vitals {
    private int height;
    private int weight;
    private int BPM;
    private int temperature;
    private int PID;
    private String BP;
    private String position;

    public Vitals() {
        this.height = 0;
        this.weight = 0;
        this.BPM = 0;
        this.temperature = 0;
        this.PID = 0;
        this.BP = null;
        this.position = null;
    }

    public Vitals (int height, int weight, int BPM, int temperature, int PID, String BP, String position) {
        this.height = height;
        this.weight = weight;
        this.BPM = BPM;
        this.temperature = temperature;
        this.PID = PID;
        this.BP = BP;
        this.position = position;
    }

    public Vitals (int height, int weight, int BPM, int temperature, String BP, String position) {
        this.height = height;
        this.weight = weight;
        this.BPM = BPM;
        this.temperature = temperature;
        this.PID = 0;
        this.BP = BP;
        this.position = position;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getBPM() {
        return this.BPM;
    }

    public int getTemperature() {
        return this.temperature;
    }

    public int getPID() {
        return this.PID;
    }

    public String getBP() {
        return this.BP;
    }

    public String getPosition() {
        return this.position;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setBPM(int BPM) {
        this.BPM = BPM;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public void setBP (String BP) {
        this.BP = BP;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}