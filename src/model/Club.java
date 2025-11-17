package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Club {
    private final String id;
    private String name;
    private String description;
    private final List<Member> members;
    private final List<Event> events;

    public Club(String id, String name, String description) {
        this.id = Objects.requireNonNull(id, "L'ID du club ne peut pas être null");
        this.name = Objects.requireNonNull(name, "Le nom du club ne peut pas être null");
        this.description = Objects.requireNonNull(description, "La description du club ne peut pas être null");
        this.members = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<Member> getMembers() { return new ArrayList<>(members); }
    public List<Event> getEvents() { return new ArrayList<>(events); }

    public void setName(String name) { this.name = Objects.requireNonNull(name); }
    public void setDescription(String description) { this.description = Objects.requireNonNull(description); }

    public boolean addMember(Member member) {
        Objects.requireNonNull(member);
        if (!members.contains(member)) {
            members.add(member);
            return true;
        }
        return false;
    }

    public boolean removeMember(Member member) {
        return members.remove(member);
    }

    public boolean addEvent(Event event) {
        Objects.requireNonNull(event);
        if (!events.contains(event)) {
            events.add(event);
            return true;
        }
        return false;
    }

    public boolean removeEvent(Event event) {
        return events.remove(event);
    }

    public int getMemberCount() { return members.size(); }
    public int getEventCount() { return events.size(); }

    @Override
    public String toString() {
        return String.format("Club[%s: %s - %d membres, %d événements]", id, name, members.size(), events.size());
    }
}
