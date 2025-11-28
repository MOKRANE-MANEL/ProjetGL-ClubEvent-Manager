public class Event {
    
    private String eventName;
    private String eventDate;
    private String eventLocation;
    private String clubName;
    private EventType Type;

    public Event(String eventName, String eventDate, String eventLocation, String clubName) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.clubName = clubName;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
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

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String toString() {
        return "Event Name: " + eventName + ", Date: " + eventDate + ", Location: " + eventLocation + ", Club: " + clubName;
    }

}
