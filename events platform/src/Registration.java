public class Registration {
    private String studentName;
    private String clubName;
    private String eventName;
    private String registrationDate;

    public Registration(String studentName, String clubName, String eventName, String registrationDate) {
        this.studentName = studentName;
        this.clubName = clubName;
        this.eventName = eventName;
        this.registrationDate = registrationDate;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getClubName() {
        return clubName;
    }

    public String getEventName() {
        return eventName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String toString() {
        return "Student Name: " + studentName + ", Club Name: " + clubName + ", Event Name: " + eventName + ", Registration Date: " + registrationDate;
    }
}
