import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Platform {
    private ArrayList<Student> students;
    private ArrayList<Club> clubs;
    private ArrayList<Event> events;
    private ArrayList<Registration> registrations;
    private static Platform instance = null;

    private Platform() {
        students = new ArrayList<Student>();
        clubs = new ArrayList<Club>();
        events = new ArrayList<Event>();
        registrations = new ArrayList<Registration>();

        String clubsFile = "C:/Users/omen2/OneDrive/Desktop/GL project/events platform/src/clubs.txt";
        String studentsFile = "C:/Users/omen2/OneDrive/Desktop/GL project/events platform/src/students.txt";
        String eventsFile = "C:/Users/omen2/OneDrive/Desktop/GL project/events platform/src/events.txt";
        String registrationsFile = "C:/Users/omen2/OneDrive/Desktop/GL project/events platform/src/registrations.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(new File(clubsFile)))) {
            String ClubName;
            String ClubUniversity;
            String description;
            String ClubEmail;
            String ClubId;   

            while (true) {
                ClubName = br.readLine();
                if (ClubName == null) break;
                ClubName = ClubName.trim();
                ClubUniversity = br.readLine().trim();
                description = br.readLine().trim();
                ClubEmail = br.readLine().trim();
                ClubId = br.readLine().trim();
                clubs.add(new Club(ClubName, ClubUniversity, description, ClubEmail, ClubId));
            }
        } catch (IOException e) {
            System.err.println("Warning: could not read " + clubsFile + " : " + e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(new File(studentsFile)))) {
            String StudentName;
            String StudentPassword;
            String StudentEmail;
            String StudentUniversity;
            String speciality;

            while (true) {
                StudentName = br.readLine();
                if (StudentName == null) break;
                StudentName = StudentName.trim();

                StudentPassword = br.readLine();
                StudentPassword = StudentPassword.trim();

                StudentEmail = br.readLine();
                StudentEmail = StudentEmail.trim();

                StudentUniversity = br.readLine();
                StudentUniversity = StudentUniversity.trim();

                speciality = br.readLine();
                speciality = speciality.trim();
                students.add(new Student(StudentName, StudentPassword, StudentEmail, StudentUniversity, speciality));
            }
        } catch (IOException e) {
            System.err.println("Warning: could not read " + studentsFile + " : " + e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(new File(eventsFile)))) {
            String EventName;
            String EventDate;
            String EventLocation;
            String ClubName;
            EventType eventType;   

            while (true) {
                EventName = br.readLine();
                if (EventName == null) break;
                EventName = EventName.trim();
                EventDate = br.readLine().trim();
                EventLocation = br.readLine().trim();
                ClubName = br.readLine().trim();
                events.add(new Event(EventName, EventDate, EventLocation, ClubName));
            }
        } catch (IOException e) {
            System.err.println("Warning: could not read " + clubsFile + " : " + e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(new File(registrationsFile)))) {
            String StudentName;
            String ClubName;
            String EventName;
            String RegistrationDate;   

            while (true) {
                StudentName = br.readLine();
                if (StudentName == null) break;
                StudentName = StudentName.trim();
                ClubName = br.readLine().trim();
                EventName = br.readLine().trim();
                RegistrationDate = br.readLine().trim();
                registrations.add(new Registration(StudentName, ClubName, EventName, RegistrationDate));
            }
        } catch (IOException e) {
            System.err.println("Warning: could not read " + registrationsFile + " : " + e.getMessage());
        }
    }
    
    public static Platform getInstance() {
        if(instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Club> getClubs() {
        return clubs;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public ArrayList<Registration> getRegistrations() {
        return registrations;
    }

    public boolean addStudent(Student s) {
        for (Student stu : students) {
            if (stu.getName().equals(s.getName())) {
                return false;
            }
        }

        students.add(s);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/omen2/OneDrive/Desktop/GL project/events platform/src/students.txt", true))) {
            bw.write(s.getName());
            bw.newLine();
            bw.write(s.getPassword());
            bw.newLine();
            bw.write(s.getEmail());
            bw.newLine();
            bw.write(s.getUniversity());
            bw.newLine();
            bw.write(s.getSpeciality());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to students file: " + e.getMessage());
        }

        return true;
    }

    public boolean addClub(Club c) {
        for (Club clu : clubs) {
            if (clu.getClubName().equals(c.getClubName())) {
                return false;
            }
        }

        clubs.add(c);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/omen2/OneDrive/Desktop/GL project/events platform/src/clubs.txt", true))) {
            bw.write(c.getClubName());
            bw.newLine();
            bw.write(c.getUniversity());
            bw.newLine();
            bw.write(c.getDescription());
            bw.newLine();
            bw.write(c.getEmail());
            bw.newLine();
            bw.write(c.getpassword());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to clubs file: " + e.getMessage());
        }

        return true;
    }

    public boolean addEvent(Event e) {
        for (Event ev : events) {
            if (ev.getEventName().equals(e.getEventName())) {
                return false; 
            }
        }

        events.add(e);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/omen2/OneDrive/Desktop/GL project/events platform/src/events.txt", true))) {
            bw.write(e.getEventName());
            bw.newLine();
            bw.write(e.getEventDate());
            bw.newLine();
            bw.write(e.getEventLocation());
            bw.newLine();
            bw.write(e.getClubName());
            bw.newLine();
        } catch (IOException ex) {
            System.err.println("Error writing event to file: " + ex.getMessage());
        }

        return true;
    }


    public boolean addRegistration(Registration r) {
        for (Registration reg : registrations) {
            if (reg.getStudentName().equals(r.getStudentName()) &&
                reg.getEventName().equals(r.getEventName())) {
                return false;
            }
        }

        registrations.add(r);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/omen2/OneDrive/Desktop/GL project/events platform/src/registrations.txt", true))) {
            bw.write(r.getStudentName());
            bw.newLine();
            bw.write(r.getClubName());
            bw.newLine();
            bw.write(r.getEventName());
            bw.newLine();
            bw.write(r.getRegistrationDate());
            bw.newLine();
        } catch (IOException ex) {
            System.err.println("Error writing registration to file: " + ex.getMessage());
        }

        return true;
    }


    public boolean removeEvent(Event e) {
        boolean removed = false;

        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getEventName().equals(e.getEventName())) {
                events.remove(i);
                removed = true;
                break;
            }
        }

        if (!removed) return false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/omen2/OneDrive/Desktop/GL project/events platform/src/events.txt"))) {

            for (Event ev : events) {
                bw.write(ev.getEventName());
                bw.newLine();
                bw.write(ev.getEventDate());
                bw.newLine();
                bw.write(ev.getEventLocation());
                bw.newLine();
                bw.write(ev.getClubName());
                bw.newLine();
            }

        } catch (IOException ex) {
            System.err.println("Error rewriting events file: " + ex.getMessage());
        }

        return true;
    }


    public boolean removeRegistration(Registration r) {
        boolean removed = false;

        for (int i = 0; i < registrations.size(); i++) {
            if (registrations.get(i).getStudentName().equals(r.getStudentName()) &&
                registrations.get(i).getEventName().equals(r.getEventName())) {

                registrations.remove(i);
                removed = true;
                break;
            }
        }

        if (!removed) return false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/omen2/OneDrive/Desktop/GL project/events platform/src/registrations.txt"))) {

            for (Registration reg : registrations) {
                bw.write(reg.getStudentName());
                bw.newLine();
                bw.write(reg.getClubName());
                bw.newLine();
                bw.write(reg.getEventName());
                bw.newLine();
                bw.write(reg.getRegistrationDate());
                bw.newLine();
            }

        } catch (IOException ex) {
            System.err.println("Error rewriting registrations file: " + ex.getMessage());
        }

        return true;
    }

}