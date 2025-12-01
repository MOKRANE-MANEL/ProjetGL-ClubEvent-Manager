import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Event implements Sujet {
    
    private String eventName;
    private LocalDate eventDate;
    private String eventLocation;
    private String clubName;

    private List<Observeur> observers = new ArrayList<>();

    public Event(String eventName, LocalDate eventDate, String eventLocation, String clubName) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.clubName = clubName;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getClubName() {
        return clubName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    @Override
    public void addObserver(Observeur o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observeur o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observeur o : observers) {
            o.update(this);
        }
    }

    public String toString() {
        return "Event Name: " + eventName + ", Date: " + eventDate + ", Location: " + eventLocation + ", Club: " + clubName;
    }

}
