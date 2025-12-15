// File: ClubsController.java (Handles ClubsUI logic)
package controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Platform;
import model.Student;
import view.ClubsUI;

/**
 * Controller for ClubsUI - handles club browsing and navigation to events
 */
public class ClubsController {
    
    private MainController mainController;
    private Platform platform;
    private Student currentStudent;
    private ClubsUI clubsUI;
    
    public ClubsController(MainController mainController, Platform platform, Student currentStudent) {
        this.mainController = mainController;
        this.platform = platform;
        this.currentStudent = currentStudent;
    }
    
    public void setup(ClubsUI clubsUI) {
        this.clubsUI = clubsUI;
        
        // Bind events to club buttons using the public map
        for (Button clubButton : clubsUI.eventsButtonsMap.keySet()) {
            String clubName = clubsUI.eventsButtonsMap.get(clubButton);
            
            clubButton.setOnAction(e -> {
                mainController.showEventsUI(clubName);
            });
        }
    }
    
    // Additional club-related business logic can go here
    public void refreshClubs() {
        // Logic to refresh club data if needed
    }
}