import java.util.Arrays;

public enum EventType {
    CONFERENCE("Conférence"),
    WORKSHOP("Atelier"), 
    SOCIAL("Événement Social"),
    MEETING("Réunion"),
    COMPETITION("Compétition"),
    TRAINING("Formation");

    private final String label;

    EventType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static EventType fromLabel(String label) {
        return Arrays.stream(values())
                .filter(type -> type.getLabel().equalsIgnoreCase(label.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Type d'événement non trouvé: " + label));
    }

    @Override
    public String toString() {
        return label;
    }
}
