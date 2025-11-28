import java.util.Scanner;

public class Screens {

    Scanner scanner = new Scanner(System.in);

    public void StartApp() {
        displayWelcomeScreen();
    }

    public void displayWelcomeScreen() {
        System.out.println("\n");
        System.out.println("Welcome to the Events Platform!");
        /*a short description of the platform */
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
        System.out.println("Password: ");
        String Password = scanner.nextLine();
        System.out.println("Email: ");
        String Email = scanner.nextLine();
        System.out.println("University: ");
        String University = scanner.nextLine();
        System.out.println("Speciality: ");
        String Speciality = scanner.nextLine();
        Platform platform = Platform.getInstance();

        if(platform.addStudent(new Student(Name, Password, Email, University, Speciality))) {
            System.out.println("You have been registered succesfully.");
            DisplayPlatformToAddEvent(platform);
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
        System.out.println("University: ");
        String University = scanner.nextLine();
        System.out.println("Describe your club: ");
        String description = scanner.nextLine();
        System.out.println("Describe your club: ");
        String Email = scanner.nextLine();
        System.out.println("Password: ");
        String Password = scanner.nextLine();
        Platform platform = Platform.getInstance();
        if(platform.addClub(new Club(ClubName, University, description, Email, Password))) {
            System.out.println("You have been registered succesfully.");
            DisplayPlatformToAddEvent(platform);
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
            System.out.println("Password: ");
            String password = scanner.nextLine();

            Platform platform = Platform.getInstance();

            for (Student s : platform.getStudents()) {
                if (s.getEmail().equals(Email) && s.getPassword().equals(password)) {
                    System.out.println("Valid information, Welcome to the platform.");
                    found = true;
                    String userName = s.getName();

                    DislpayEventsforStudent(platform, userName);
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
            System.out.println("Password: ");
            String password = scanner.nextLine();

            Platform platform = Platform.getInstance();

            for (Club c : platform.getClubs()) {
                if (c.getEmail().equals(Email) && c.getpassword().equals(password)) {
                    System.out.println("Valid information, Welcome to the platform.");
                    DisplayPlatformToAddEvent(platform);
                    found = true;
                    break;
                }
            }
            if(found == false) {
                System.out.println("Invalid informations.\n");
            }
        }
    }

    public void DislpayEventsforStudent(Platform p, String userName) {
        System.out.println("hello dear student, here are the events available for you:");
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
                if(e.getEventName().equals(eventName)) {
                    selectedEvent = e;
                    break;
                }
            }

            if(selectedEvent != null) {
                System.out.println("Please enter your club name:");
                String clubName = scanner.nextLine();

                Club selectedClub = null;
                for(Club c : p.getClubs()) {
                    if(c.getClubName().equals(clubName)) {
                        selectedClub = c;
                        break;
                    }
                }

                if(selectedClub != null) {
                    System.out.println("Please enter the registration date (YYYY-MM-DD):");
                    String registrationDate = scanner.nextLine();

                    Registration newRegistration = new Registration(userName, selectedClub.getClubName(), selectedEvent.getEventName(), registrationDate);

                    if(p.addRegistration(newRegistration)) {
                        System.out.println("You have been registered to the event: " + selectedEvent.getEventName());
                    } else {
                        System.out.println("You have already registered for this event.");
                    }
                } else {
                    System.out.println("Club not found.");
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

            for(Registration r : p.getRegistrations()) {
                if(r.getStudentName().equals(userName) && r.getEventName().equals(eventName)) {
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
    }

    public void DisplayPlatformToAddEvent(Platform p) {
        System.out.println("Hello dear club, you can add events to the platform. You can also remove events that you have added previously.");
        System.out.println("Would you like to add an event? or remove an event? (add/remove)");
        String response = "";
        response = scanner.nextLine();
        while(response.equals("add")) {
            System.out.println("Please enter the event name:");
            String eventName = scanner.nextLine();
            System.out.println("Please enter the event date:");
            String eventDate = scanner.nextLine();
            System.out.println("Please enter the event location:");
            String eventLocation = scanner.nextLine();
            System.out.println("Please enter your club name:");
            String clubName = scanner.nextLine();
            Event newEvent = new Event(eventName, eventDate, eventLocation, clubName);
            if(p.getEvents().add(newEvent)) {
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
                if(p.getEvents().remove(eventToRemove)) {
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
    }
}