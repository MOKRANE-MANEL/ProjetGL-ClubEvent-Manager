import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Event implements Sujet {

    private String name;
    private LocalDate date;
    private String description;
    private int maxInscription;

    private List<Observeur> observers = new ArrayList<>();

    public Event(String name, LocalDate date, String description, int maxInscription) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.maxInscription = maxInscription;
    }

    public String getName() { return name; }
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }
    public int getMaxInscription() { return maxInscription; }

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
}
