package model;
public class AdminObserver implements Observeur {

    private String adminName;

    public AdminObserver(String adminName) {
        this.adminName = adminName;
    }

    @Override
    public void update(Event event) {
        System.out.println("ADMIN " + adminName +
                " A ÉTÉ NOTIFIÉ : Nouvelle inscription dans l'événement → "
                + event.getEventName());
    }
}
