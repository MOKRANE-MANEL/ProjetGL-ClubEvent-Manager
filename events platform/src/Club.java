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

    public void setClubName(String ClubName) throws IllegalArgumentException {
        if(ClubName == null) {
            throw new IllegalArgumentException("Le nom du club ne peut pas être null");
        }
        this.ClubName = Objects.requireNonNull(ClubName); 
    }

    public void setDescription(String description) throws IllegalArgumentException {
        if(description == null) {
            throw new IllegalArgumentException("La description du club ne peut pas être null");
        }
        this.description = Objects.requireNonNull(description); 
    }

    public void setUniversity(String university) throws IllegalArgumentException {
        if(university == null) {
            throw new IllegalArgumentException("L'universite ne peut pas être null");
        }
        this.university = university;
    }

    public void setEmail(String email) throws IllegalArgumentException {
        if(email == null) {
            throw new IllegalArgumentException("L'email du club ne peut pas être null");
        }
        this.email = email;
    }

    public void setpassword(String password) throws IllegalArgumentException {
        if(password == null) {
            throw new IllegalArgumentException("Le password du club ne peut pas être null");
        }
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