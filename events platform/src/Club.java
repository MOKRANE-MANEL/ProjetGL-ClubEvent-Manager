import java.util.Objects;

public class Club {
    private String ClubName;
    private String university;
    private String description;
    private String email;
    private String password;

    public Club(String clubName, String university, String description, String email, String password) {
        this.password = Objects.requireNonNull(password, "L'password du club ne peut pas être null");
        this.ClubName = Objects.requireNonNull(clubName, "Le nom du club ne peut pas être null");
        this.description = Objects.requireNonNull(description, "La description du club ne peut pas être null");
        this.university = Objects.requireNonNull(university, "L'universite ne peut pas être null");
        this.email = Objects.requireNonNull(email, "L'eamil du club ne peut pas être null");
    }

    public String getClubName() {
        return ClubName;
    }

    public String getUniversity() {
        return university;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getpassword() {                    
        return password;
    }   

    public void setClubName(String ClubName) {
        this.ClubName = Objects.requireNonNull(ClubName); 
    }
    public void setDescription(String description) {
        this.description = Objects.requireNonNull(description); 
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setapassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "Club Name: " + ClubName + ", University: " + university + ", Email: " + email;
    }

    public void addEvent(Event e, Platform p) {
        p.addEvent(e);
    }

    public void removeEvent(Event e, Platform p) {
        p.removeEvent(e);
    }
}