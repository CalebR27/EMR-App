public class Medication {
    private String name;
    private String frequency;
    private int PID;
    private String route;
    private String time;
    private String MID;

    public Medication() {
        this.name = null;
        this.frequency = null;
        this.PID = 0;
        this.route = null;
        this.time = null;
        this.MID = null;
    }

    public Medication(String name, String frequency, int PID, String route, String time) {
        this.name = name;
        this.frequency = frequency;
        this.PID = PID;
        this.route = route;
        this.time = time;
        changeTimeFormat();
        createMID();
    }

    public Medication(String name, String frequency, int PID, String route, String time, String MID) {
        this.name = name;
        this.frequency = frequency;
        this.PID = PID;
        this.route = route;
        this.time = time;
        //changeTimeFormat();
        this.MID = MID;
    }

    private void createMID() {
        String fullMID = Database.getLatest("Medication");

        if(fullMID == null) {
            fullMID = "M10000";
        } else {
            int MID = Integer.valueOf(fullMID.substring(1)).intValue();
            MID++;
            fullMID = "M" + String.valueOf(MID);
        }
        this.MID = fullMID;
    }

    private void changeTimeFormat() {
        if (this.time != null && this.time.contains("-")) {
            String year = this.time.substring(0, 4);
            String month = this.time.substring(5, 7);
            String day = this.time.substring(8, 10);
            String hour = this.time.substring(11, 13);
            String minute = this.time.substring(14, 16);

            this.time = month + "/" + day + "/" + year + " " + hour + ":" + minute;
        }
    }

    public String getName() {
        return this.name;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public int getPID() {
        return this.PID;
    }

    public String getRoute() {
        return this.route;
    }

    public String getTime() {
        return this.time;
    }

    public String getMID() {
        return this.MID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }


}