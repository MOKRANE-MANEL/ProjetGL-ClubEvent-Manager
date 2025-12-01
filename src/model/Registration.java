package model;

import java.time.LocalDate;

public class Registration {
    private Student student;
    private Event event;
    private LocalDate registrationDate;

    public Registration(Student student, Event event, LocalDate registrationDate) {
        this.student = student;
        this.event = event;
        this.registrationDate = registrationDate;
    }

    public Student getStudent() {
        return student;
    }

    public Event getEvent() {
        return event;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String toString() {
        return "Registration{student=" + student.getName() + ", event=" + event.getEventName() + ", registrationDate=" + registrationDate + "}";
    }
}