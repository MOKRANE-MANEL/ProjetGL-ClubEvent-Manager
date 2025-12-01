import java.time.LocalDate;
import java.util.Scanner;

public class Screens {

    Scanner scanner = new Scanner(System.in);

    public void StartApp() {
        displayWelcomeScreen();
    }

    public void displayWelcomeScreen() {
        System.out.println("\n");
        System.out.println("Welcome to the Events Platform!");
        System.out.println("This platform allows students to discover and register for events organized by various clubs at their universities.");
        System.out.println("Whether you're looking to enhance your skills, network with peers, or simply have fun, our platform has something for everyone.");
        System.out.println("Join us today and start exploring the exciting events happening around you!\n");
        System.out.println("Please select your login type (1-2):");
        System.out.println("1. Student Login");
        System.out.println("2. Club Login");
        int choice = scanner.nextInt();
        scanner.nextLine();
        while(choice < 1 || choice > 2) {
            System.out.println("Invalid choice. Please select 1 or 2:");
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        
        System.out.println("\n1. Sign In");
        System.out.println("2. Sign Up");
        System.out.println("select 1 (if you have already registered to the platform) or 2 (to register to the platform): ");
        int temp = scanner.nextInt();
        scanner.nextLine();
        while(temp > 2 || temp < 1) {
            System.out.println("Invalid choice. Please select 1 or 2:");
            temp = scanner.nextInt();
            scanner.nextLine();
        }
        if(choice == 1) {
            if(temp == 1) {
            StudentSignInScreen();
            }
            else {
                StudentSignUpScreen();
            }
        }
        else {
            if(temp == 1) {
            ClubSignInScreen();
        }
        else {
            ClubSignUpScreen();
        }
        }
    }

    public void StudentSignUpScreen() {

        System.out.println("\n");
        System.out.println("Sign Up to our Events Platform");
        System.out.println("Please enter your details to create a new account.");
        System.out.println("Name: ");
        String Name = scanner.nextLine();
        while(Name.trim().isEmpty()) {
            System.out.println("Name cannot be empty. Please enter your name:");
            Name = scanner.nextLine();
        }
        System.out.println("Password: ");
        String Password = scanner.nextLine();
        while(Password.trim().isEmpty()) {
            System.out.println("Password cannot be empty. Please enter your password:");
            Password = scanner.nextLine();
        }
        System.out.println("Email: ");
        String Email = scanner.nextLine();
        while(Email.trim().isEmpty()) {
            System.out.println("Email cannot be empty. Please enter your email:");
            Email = scanner.nextLine();
        }
        System.out.println("University: ");
        String University = scanner.nextLine();
        while(University.trim().isEmpty()) {
            System.out.println("University cannot be empty. Please enter your university:");
            University = scanner.nextLine();
        }
        System.out.println("Speciality: ");
        String Speciality = scanner.nextLine();
        while(Speciality.trim().isEmpty()) {
            System.out.println("Speciality cannot be empty. Please enter your speciality:");
            Speciality = scanner.nextLine();
        }
        Platform platform = Platform.getInstance();

        if(platform.addStudent(new Student(Name, Password, Email, University, Speciality))) {
            System.out.println("You have been registered succesfully.");
            Student user = platform.findStudentByName(Name);
            DislpayEventsforStudent(platform, user);

        }
        else {
            System.out.println("Student name already exists. Registration failed.");
            StudentSignUpScreen();
            return;
        }
    }

    public void ClubSignUpScreen() {
        
        System.out.println("Club Sign Up to our Events Platform");
        System.out.println("Please enter your club details to create a new account.");
        System.out.println("\n");
        System.out.println("Club name: ");
        String ClubName = scanner.nextLine();
        while(ClubName.trim().isEmpty()) {
            System.out.println("Club name cannot be empty. Please enter your club name:");
            ClubName = scanner.nextLine();
        }
        System.out.println("University: ");
        String University = scanner.nextLine();
        while(University.trim().isEmpty()) {
            System.out.println("University cannot be empty. Please enter your university:");
            University = scanner.nextLine();
        }
        System.out.println("Describe your club: ");
        String description = scanner.nextLine();
        while(description.trim().isEmpty()) {
            System.out.println("Description cannot be empty. Please describe your club:");
            description = scanner.nextLine();
        }
        System.out.println("Email: ");
        String Email = scanner.nextLine();
        while(Email.trim().isEmpty()) {
            System.out.println("Email cannot be empty. Please enter your email:");
            Email = scanner.nextLine();
        }
        System.out.println("Password: ");
        String Password = scanner.nextLine();
        while(Password.trim().isEmpty()) {
            System.out.println("Password cannot be empty. Please enter your password:");
            Password = scanner.nextLine();
        }
        Platform platform = Platform.getInstance();
        if(platform.addClub(new Club(ClubName, University, description, Email, Password))) {
            System.out.println("You have been registered succesfully.");
            Club club = platform.findClubByName(ClubName);
            DisplayPlatformToAddEvent(platform, club);
        }
        else {
            System.out.println("Club name already exists. Registration failed.");
            ClubSignUpScreen();
            return;
        }
    }

    public void StudentSignInScreen() {
        boolean found = false;
        while (!found) {
            System.out.println("\n");
            System.out.println("Sign In to our Events Platform");
            System.out.println("Please enter your details.");
            System.out.println("Email: ");
            String Email = scanner.nextLine();
            while(Email.trim().isEmpty()) {
                System.out.println("Email cannot be empty. Please enter your email:");
                Email = scanner.nextLine();
            }
            System.out.println("Password: ");
            String password = scanner.nextLine();
            while(password.trim().isEmpty()) {
                System.out.println("Password cannot be empty. Please enter your password:");
                password = scanner.nextLine();
            }

            Platform platform = Platform.getInstance();

            for (Student s : platform.getStudents()) {
                if (s.getEmail().trim().equalsIgnoreCase(Email.trim()) && s.getPassword().trim().equalsIgnoreCase(password.trim())) {
                    System.out.println("Valid information, Welcome to the platform.");
                    found = true;

                    DislpayEventsforStudent(platform, s);
                    break;
                }
            }
            if(found == false) {
                System.out.println("Invalid informations.\n");
            }
        }
    }

    public void ClubSignInScreen() {
        boolean found = false;
        while (!found) {
            System.out.println("Sign In to our Events Platform");
            System.out.println("Please enter your details.");
            System.out.println("Email: ");
            String Email = scanner.nextLine();
            while(Email.trim().isEmpty()) {
                System.out.println("Email cannot be empty. Please enter your email:");
                Email = scanner.nextLine();
            }
            System.out.println("Password: ");
            String password = scanner.nextLine();
            while(password.trim().isEmpty()) {
                System.out.println("Password cannot be empty. Please enter your password:");
                password = scanner.nextLine();
            }

            Platform platform = Platform.getInstance();

            for (Club c : platform.getClubs()) {
                if (c.getEmail().trim().equalsIgnoreCase(Email.trim()) && c.getpassword().trim().equalsIgnoreCase(password.trim())) {
                    System.out.println("Valid information, Welcome to the platform.");
                    DisplayPlatformToAddEvent(platform, c);
                    found = true;
                    break;
                }
            }
            if(found == false) {
                System.out.println("Invalid informations.\n");
            }
        }
    }

    public void DislpayEventsforStudent(Platform p, Student user) {
        System.out.println("hello dear "+ user.getName() + ", here are the events available for you:");
        System.out.println("---------------");
        for(Event e : p.getEvents()) {
            System.out.println(e.toString());
            System.out.println("---------------");
        }
        System.out.println("You can register to any event you like!");
        System.out.println("You can also remove your registration from an event you have already registered to.");
        System.out.println("Would you like to register to an event? or remove your registration from an event? (register/remove)");
        
        String response = scanner.nextLine();

        while(response.equals("register")) {

            System.out.println("Please enter the name of the event you would like to register to:");
            String eventName = scanner.nextLine();

            Event selectedEvent = null;
            for(Event e : p.getEvents()) {
                if(e.getEventName().trim().equalsIgnoreCase(eventName)) {
                    selectedEvent = e;
                    break;
                }
            }

            if(selectedEvent != null) {
                LocalDate registrationDate = LocalDate.now();

                Registration newRegistration = new Registration(user, selectedEvent, registrationDate);

                if(p.addRegistration(newRegistration)) {
                    System.out.println("You have been registered to the event: " + selectedEvent.getEventName());
                } else {
                    System.out.println("You have already registered for this event.");
                }
            } else {
                System.out.println("Event not found.");
            }

            System.out.println("Would you like to register to another event? (yes/no)");
            String r2 = scanner.nextLine();

            if(r2.equals("yes")) {
                response = "register";
            } else {
                System.out.println("Would you like to remove your registration from an event? (yes/no)");
                r2 = scanner.nextLine();
                if(r2.equals("yes")) {
                    response = "remove";
                } else {
                    System.out.println("Thank you for using the Events Platform. Goodbye!");
                    response = "exit";
                }
            }
        }

        while(response.equals("remove")) {

            System.out.println("Please enter the name of the event you would like to remove your registration from:");
            String eventName = scanner.nextLine();

            Registration registrationToRemove = null;

            for (Registration r : p.getRegistrations()) {
                String regStudent = r.getStudent().getName();
                String regEvent   = r.getEvent().getEventName();

                if (regStudent != null && regEvent != null &&
                    regStudent.trim().equalsIgnoreCase(user.getName().trim()) && regEvent.trim().equalsIgnoreCase(eventName.trim())) {
                    registrationToRemove = r;
                    break;
                }
            }

            if(registrationToRemove != null) {
                if(p.removeRegistration(registrationToRemove)) {
                    System.out.println("Your registration for the event " + eventName + " has been removed.");
                } else {
                    System.out.println("Failed to remove registration.");
                }
            } else {
                System.out.println("Registration not found.");
            }

            System.out.println("Would you like to remove your registration from another event? (yes/no)");
            String r3 = scanner.nextLine();

            if(r3.equals("yes")) {
                response = "remove";
            } else {
                System.out.println("Thank you for using the Events Platform. Goodbye!");
                response = "exit";
            }
        }

        if(response != "register" && response != "remove") {
            System.out.println("Invalid option. Exiting the platform.. Goodbye!");
        }
    }

    public void DisplayPlatformToAddEvent(Platform p, Club club) {
        System.out.println("Hello dear " + club.getClubName() + ", you can add events to the platform. You can also remove events that you have added previously.");
        System.out.println("Would you like to add an event? or remove an event? (add/remove)");
        String response = "";
        response = scanner.nextLine();
        while(response.equals("add")) {
            System.out.println("Please enter the event name:");
            String eventName = scanner.nextLine();
            System.out.println("Please enter the event date (the format must be YYYY-MM-DD):");
            String dateInput = scanner.nextLine();
            LocalDate eventDate = LocalDate.parse(dateInput);
            System.out.println("Please enter the event location:");
            String eventLocation = scanner.nextLine();
            String clubName = club.getClubName();
            Event newEvent = new Event(eventName, eventDate, eventLocation, clubName);
            if(p.addEvent(newEvent)) {
                System.out.println("Event added successfully: " + newEvent.toString());
            } else {
                System.out.println("Failed to add event. It may already exist.");
            }
            System.out.println("Would you like to add another event? (yes/no)");
            String response2 = scanner.nextLine();
            if(response2.equals("yes")) {
                response = "add";
            } else {
                System.out.println("Would you like to remove an event? (yes/no)");
                response2 = scanner.nextLine();
                if(response2.equals("yes")) {
                    response = "remove";
                } else {
                    System.out.println("Thank you for using the Events Platform. Goodbye!");
                    response = "exit";
                }
            }
        }

        while(response.equals("remove")) {
            System.out.println("Please enter the name of the event you would like to remove:");
            String eventName = scanner.nextLine();
            Event eventToRemove = null;
            for(Event e : p.getEvents()) {
                if(e.getEventName().equals(eventName)) {
                    eventToRemove = e;
                    break;
                }
            }
            if(eventToRemove != null) {
                if(p.removeEvent(eventToRemove)) {
                    System.out.println("Event removed successfully: " + eventToRemove.toString());
                } else {
                    System.out.println("Failed to remove event.");
                }
            } else {
                System.out.println("Event not found.");
            }
            System.out.println("Would you like to remove another event? (yes/no)");
            String response3 = scanner.nextLine();
            if(response3.equals("yes")) {
                response = "remove";
            } else {
                System.out.println("Thank you for using the Events Platform. Goodbye!");
                response = "exit";
            }
        }

        if(response != "add" && response != "remove") {
            System.out.println("Invalid option. Exiting the platform.. Goodbye!"); 
        }
          
    }
}