public class Student {
    private String name;
    private String password;
    private String email;
    private String university;
    private String speciality;

    public Student(String name, String password, String email, String university, String speciality) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.university = university;
        this.speciality = speciality;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUniversity() {
        return university;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String toString() {
        return "Student{name='" + name + "', password=" + password + ", email='" + email + "', university='" + university + "', speciality='" + speciality + "}";
    }

    public void register_to_event(Event e, Club c, Platform p, String registrationDate) {
        Registration r = new Registration(this.name, c.getClubName(), e.getEventName(), registrationDate);
        p.getRegistrations().add(r);
    }

    public boolean is_registered_to_event(Event e, Platform p) {
        for (Registration r : p.getRegistrations()) {
            if (r.getStudentName().equals(this.name) && r.getEventName().equals(e.getEventName())) {
                return true;
            }
        }
        return false;
    }

    public void unregister_from_event(Event e, Platform p) {
        Registration toRemove = null;
        for (Registration r : p.getRegistrations()) {
            if (r.getStudentName().equals(this.name) && r.getEventName().equals(e.getEventName())) {
                toRemove = r;
                break;
            }
        }
        if (toRemove != null) {
            p.getRegistrations().remove(toRemove);
        }
    }

}
