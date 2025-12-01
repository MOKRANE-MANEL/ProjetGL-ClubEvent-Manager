public interface Sujet {
    void addObserver(Observeur o);
    void removeObserver(Observeur o);
    void notifyObservers();
}