import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Platform {
    private ArrayList<Student> students;
    private ArrayList<Club> clubs;
    private ArrayList<Event> events;
    private ArrayList<Registration> registrations;
    private static Platform instance = null;

    private final String clubsFile = "C:/Users/omen2/OneDrive/Desktop/GL project/events platform/src/clubs.txt";
    private final String studentsFile = "C:/Users/omen2/OneDrive/Desktop/GL project/events platform/src/students.txt";
    private final String eventsFile = "C:/Users/omen2/OneDrive/Desktop/GL project/events platform/src/events.txt";
    private final String registrationsFile = "C:/Users/omen2/OneDrive/Desktop/GL project/events platform/src/registrations.txt";

    private Platform() {
        students = new ArrayList<>();
        clubs = new ArrayList<>();
        events = new ArrayList<>();
        registrations = new ArrayList<>();

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

                StudentPassword = br.readLine().trim();
                StudentEmail = br.readLine().trim();
                StudentUniversity = br.readLine().trim();
                speciality = br.readLine().trim();

                students.add(new Student(StudentName, StudentPassword, StudentEmail, StudentUniversity, speciality));
            }
        } catch (IOException e) {
            System.err.println("Warning: could not read " + studentsFile + " : " + e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(new File(eventsFile)))) {
            String EventName;
            LocalDate EventDate;
            String EventLocation;
            String ClubName;
            String DateLine;

            while (true) {
                EventName = br.readLine();
                if (EventName == null) break;
                EventName = EventName.trim();

                DateLine = br.readLine().trim();
                EventDate = LocalDate.parse(DateLine);

                EventLocation = br.readLine().trim();
                ClubName = br.readLine().trim();

                events.add(new Event(EventName, EventDate, EventLocation, ClubName));
            }
        } catch (IOException e) {
            System.err.println("Warning: could not read " + eventsFile + " : " + e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(new File(registrationsFile)))) {
            String StudentName;
            String EventName;
            LocalDate RegistrationDate;
            String DateLine;

            while (true) {
                StudentName = br.readLine();
                if (StudentName == null) break;
                StudentName = StudentName.trim();

                EventName = br.readLine().trim();
                DateLine = br.readLine().trim();
                RegistrationDate = LocalDate.parse(DateLine);

                Student student = findStudentByName(StudentName);
                Event event = findEventByName(EventName);

                if (student == null) {
                    System.err.println("Warning: registration skipped because student not found: " + StudentName);
                    continue;
                }
                if (event == null) {
                    System.err.println("Warning: registration skipped because event not found: " + EventName);
                    continue;
                }

                registrations.add(new Registration(student, event, RegistrationDate));
            }
        } catch (IOException e) {
            System.err.println("Warning: could not read " + registrationsFile + " : " + e.getMessage());
        }
    }

    public static Platform getInstance() {
        if (instance == null) {
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

    public Student findStudentByName(String name) {
        if (name == null) return null;
        for (Student s : students) {
            if (name.equals(s.getName())) return s;
        }
        return null;
    }

    public Club findClubByName(String name) {
        if (name == null) return null;
        for (Club c : clubs) {
            if (name.equals(c.getClubName())) return c;
        }
        return null;
    }

    public Event findEventByName(String name) {
        if (name == null) return null;
        for (Event e : events) {
            if (name.equals(e.getEventName())) return e;
        }
        return null;
    }

    public boolean addStudent(Student s) {
        for (Student stu : students) {
            if (stu.getName().equals(s.getName())) {
                return false;
            }
        }

        students.add(s);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(studentsFile, true))) {
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

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(clubsFile, true))) {
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

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(eventsFile, true))) {
            bw.write(e.getEventName());
            bw.newLine();
            bw.write(e.getEventDate().toString());
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
            if (reg.getStudent().getName().equals(r.getStudent().getName()) &&
                reg.getEvent().getEventName().equals(r.getEvent().getEventName())) {
                return false;
            }
        }

        registrations.add(r);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(registrationsFile, true))) {
            bw.write(r.getStudent().getName());
            bw.newLine();
            bw.write(r.getEvent().getEventName());
            bw.newLine();
            bw.write(r.getRegistrationDate().toString());
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

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(eventsFile))) {
            for (Event ev : events) {
                bw.write(ev.getEventName());
                bw.newLine();
                bw.write(ev.getEventDate().toString());
                bw.newLine();
                bw.write(ev.getEventLocation());
                bw.newLine();
                bw.write(ev.getClubName());
                bw.newLine();
            }
        } catch (IOException ex) {
            System.err.println("Error rewriting events file: " + ex.getMessage());
        }

        registrations.removeIf(reg -> reg.getEvent().getEventName().equals(e.getEventName()));
        rewriteRegistrationsFile();

        return true;
    }

    public boolean removeRegistration(Registration r) {
        boolean removed = false;

        for (int i = 0; i < registrations.size(); i++) {
            if (registrations.get(i).getStudent().getName().equals(r.getStudent().getName()) &&
                registrations.get(i).getEvent().getEventName().equals(r.getEvent().getEventName())) {

                registrations.remove(i);
                removed = true;
                break;
            }
        }

        if (!removed) return false;

        rewriteRegistrationsFile();

        return true;
    }

    private void rewriteRegistrationsFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(registrationsFile))) {
            for (Registration reg : registrations) {
                bw.write(reg.getStudent().getName());
                bw.newLine();
                bw.write(reg.getEvent().getEventName());
                bw.newLine();
                bw.write(reg.getRegistrationDate().toString());
                bw.newLine();
            }
        } catch (IOException ex) {
            System.err.println("Error rewriting registrations file: " + ex.getMessage());
        }
    }
}
