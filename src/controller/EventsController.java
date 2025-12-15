// File: EventsController.java (Handles EventsUI logic)
package controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.*;
import view.EventsUI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for EventsUI - handles event browsing, registration, and display
 */
public class EventsController {
    
    private MainController mainController;
    private Platform platform;
    private Student currentStudent;
    private EventsUI eventsUI;
    
    public EventsController(MainController mainController, Platform platform, Student currentStudent) {
        this.mainController = mainController;
        this.platform = platform;
        this.currentStudent = currentStudent;
    }
    
    public void showEventsUI(String clubName, Scene scene, Stage stage) {
        eventsUI = new EventsUI();
        
        // Get events for this club from Model
        List<Map<String, String>> eventsData = getEventsForClub(clubName);
        
        Pane root = eventsUI.createContent(stage, scene, clubName, eventsData);
        scene.setRoot(root);
        
        // Bind controller logic to view elements
        setupEventsController(clubName);
    }
    
    private void setupEventsController(String clubName) {
        // Handle Back button
        eventsUI.backButtonContainer.setOnMouseClicked(e -> {
            mainController.showClubsUI();
        });
        
        // Handle Join Event buttons using the public map
        for (Map.Entry<Button, String> entry : eventsUI.joinButtonsMap.entrySet()) {
            Button joinButton = entry.getKey();
            String eventName = entry.getValue();
            
            joinButton.setOnAction(e -> {
                handleEventRegistration(eventName, clubName);
            });
        }
    }
    
    private List<Map<String, String>> getEventsForClub(String clubName) {
        List<Map<String, String>> eventsData = new ArrayList<>();
        
        for (Event event : platform.getEvents()) {
            if (event.getClubName().equals(clubName)) {
                Map<String, String> eventMap = new HashMap<>();
                eventMap.put("id", event.getEventName());
                eventMap.put("name", event.getEventName());
                eventMap.put("date", event.getEventDate().toString());
                eventMap.put("location", event.getEventLocation());
                eventMap.put("club", event.getClubName());
                eventMap.put("type", "Club Event");
                eventMap.put("description", "Event organized by " + clubName);
                
                eventsData.add(eventMap);
            }
        }
        
        return eventsData;
    }
    
    private void handleEventRegistration(String eventName, String clubName) {
        // Find the event in Model
        Event event = platform.findEventByName(eventName);
        if (event != null && currentStudent != null) {
            
            // Check if already registered
            for (Registration registration : platform.getRegistrations()) {
                if (registration.getStudent().getName().equals(currentStudent.getName()) &&
                    registration.getEvent().getEventName().equals(eventName)) {
                    
                    showAlert("Already Registered", "You are already registered for this event!");
                    return;
                }
            }
            
            // Create new registration
            LocalDate registrationDate = LocalDate.now();
            Registration registration = new Registration(currentStudent, event, registrationDate);
            
            if (platform.addRegistration(registration)) {
                showAlert("Success", "Successfully registered for: " + event.getEventName());
                
                // Notify observers (AdminObserver)
                event.notifyObservers();
            } else {
                showAlert("Error", "Registration failed. Please try again.");
            }
        } else {
            showAlert("Error", "Event not found or user not logged in");
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}