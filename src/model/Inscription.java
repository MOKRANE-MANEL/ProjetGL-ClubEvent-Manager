import java.time.LocalDateTime;

public class Inscription {

    private Student student;
    private Event event;
    private LocalDateTime dateInscription;

    public Inscription(Student student, Event event) {
        this.student = student;
        this.event = event;
        this.dateInscription = LocalDateTime.now();

        event.notifyObservers();
    }

    public Student getStudent() { return student; }
    public Event getEvent() { return event; }
    public LocalDateTime getDateInscription() { return dateInscription; }
}
