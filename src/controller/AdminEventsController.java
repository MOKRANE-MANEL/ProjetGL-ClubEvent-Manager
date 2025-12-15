// File: AdminEventsController.java (Handles AdminEventsUI logic)
package controller;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import view.AdminEventsUI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for AdminEventsUI - handles club admin dashboard functionality
 */
public class AdminEventsController {
    
    private MainController mainController;
    private Platform platform;
    private Club currentClub;
    private AdminEventsUI adminUI;
    private Stage adminStage;
    
    public AdminEventsController(MainController mainController, Platform platform, Club currentClub) {
        this.mainController = mainController;
        this.platform = platform;
        this.currentClub = currentClub;
    }
    
    public void showAdminDashboard(Stage parentStage) {
        adminUI = new AdminEventsUI();
        BorderPane root = adminUI.createContent(parentStage);
        
        adminStage = new Stage();
        adminStage.setScene(new javafx.scene.Scene(root, 1200, 800));
        adminStage.setTitle("Admin Dashboard - " + currentClub.getClubName());
        
        setupAdminController();
        
        adminStage.initOwner(parentStage);
        adminStage.show();
    }
    
    private void setupAdminController() {
        // =========== DATA INITIALIZATION ===========
        loadClubMembers();
        loadClubEvents();
        
        // =========== BUTTON HANDLERS ===========
        
        // User icon button - toggle side panel
        adminUI.userIconBtn.setOnAction(e -> {
            boolean isVisible = adminUI.sidePanel.isVisible();
            adminUI.sidePanel.setVisible(!isVisible);
        });
        
        // Add Member button
        adminUI.addMemberBtn.setOnAction(e -> {
            showAddMemberDialog();
        });
        
        // Delete Member button
        adminUI.deleteMemberBtn.setOnAction(e -> {
            showDeleteMemberDialog();
        });
        
        // Verify Member button
        adminUI.verifyMemberBtn.setOnAction(e -> {
            showVerifyMemberDialog();
        });
        
        // Verify Event button
        adminUI.verifyEventBtn.setOnAction(e -> {
            showVerifyEventDialog();
        });
        
        // Add Event button
        adminUI.addEventBtn.setOnAction(e -> {
            showAddEventDialog();
        });
    }
    
    private void loadClubMembers() {
        // Clear existing members
        adminUI.membersListBox.getChildren().clear();
        
        // Add all students as members (in real app, filter by club membership)
        int count = 0;
        for (Student student : platform.getStudents()) {
            Button clickTarget = new Button();
            javafx.scene.layout.HBox memberLabel = adminUI.createMemberLabel(student.getName(), clickTarget);
            adminUI.membersListBox.getChildren().add(memberLabel);
            count++;
        }
        
        // Update count label
        adminUI.membersCountLabel.setText("Club Members: " + count);
    }
    
    private void loadClubEvents() {
        // Clear existing events
        adminUI.eventsGrid.getChildren().clear();
        
        // Get events for current club
        List<Event> clubEvents = new ArrayList<>();
        for (Event event : platform.getEvents()) {
            if (event.getClubName().equals(currentClub.getClubName())) {
                clubEvents.add(event);
            }
        }
        
        // Add events to grid
        int col = 0, row = 0;
        for (Event event : clubEvents) {
            // Create event data map
            Map<String, String> eventData = new HashMap<>();
            eventData.put("name", event.getEventName());
            eventData.put("time", event.getEventDate().toString());
            eventData.put("location", event.getEventLocation());
            eventData.put("type", "Club Event");
            eventData.put("description", "Event by " + currentClub.getClubName());
            
            // Create action buttons
            List<Button> actionButtons = new ArrayList<>();
            
            // Edit button
            Button editBtn = new Button("✏️ Edit");
            editBtn.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-background-radius: 6;");
            editBtn.setOnAction(e -> {
                showEditEventDialog(event);
            });
            
            // Delete button
            Button deleteBtn = new Button("🗑️ Delete");
            deleteBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-background-radius: 6;");
            deleteBtn.setOnAction(e -> {
                handleDeleteEvent(event);
            });
            
            actionButtons.add(editBtn);
            actionButtons.add(deleteBtn);
            
            // Create event card using View factory method
            javafx.scene.layout.VBox eventCard = adminUI.createEventCard(eventData, actionButtons);
            
            // Add to grid
            adminUI.eventsGrid.add(eventCard, col, row);
            
            col++;
            if (col > 1) {
                col = 0;
                row++;
            }
        }
    }
    
    // ==================== DIALOG METHODS ====================
    
    private void showAddMemberDialog() {
        TextField idField = new TextField();
        Button confirmBtn = new Button("Add");
        Button cancelBtn = new Button("Cancel");
        
        Stage dialogStage = adminUI.createGenericDialogStage(
            "Add Member", 
            "Enter Member ID:", 
            confirmBtn, 
            cancelBtn, 
            idField
        );
        
        confirmBtn.setOnAction(e -> {
            String memberId = idField.getText();
            if (!memberId.isEmpty()) {
                showAlert("Success", "Member added: " + memberId);
                loadClubMembers(); // Refresh
                dialogStage.close();
            }
        });
        
        cancelBtn.setOnAction(e -> dialogStage.close());
        
        dialogStage.initOwner(adminStage);
        dialogStage.show();
    }
    
    private void showDeleteMemberDialog() {
        TextField idField = new TextField();
        Button confirmBtn = new Button("Delete");
        Button cancelBtn = new Button("Cancel");
        
        Stage dialogStage = adminUI.createGenericDialogStage(
            "Delete Member", 
            "Enter Member ID to delete:", 
            confirmBtn, 
            cancelBtn, 
            idField
        );
        
        confirmBtn.setOnAction(e -> {
            String memberId = idField.getText();
            if (!memberId.isEmpty()) {
                showAlert("Success", "Member deleted: " + memberId);
                loadClubMembers(); // Refresh
                dialogStage.close();
            }
        });
        
        cancelBtn.setOnAction(e -> dialogStage.close());
        
        dialogStage.initOwner(adminStage);
        dialogStage.show();
    }
    
    private void showVerifyMemberDialog() {
        TextField idField = new TextField();
        Button confirmBtn = new Button("Verify");
        Button cancelBtn = new Button("Cancel");
        
        Stage dialogStage = adminUI.createGenericDialogStage(
            "Verify Member", 
            "Enter Member ID to verify:", 
            confirmBtn, 
            cancelBtn, 
            idField
        );
        
        confirmBtn.setOnAction(e -> {
            String memberId = idField.getText();
            if (!memberId.isEmpty()) {
                showAlert("Success", "Member verified: " + memberId);
                dialogStage.close();
            }
        });
        
        cancelBtn.setOnAction(e -> dialogStage.close());
        
        dialogStage.initOwner(adminStage);
        dialogStage.show();
    }
    
    private void showVerifyEventDialog() {
        TextField idField = new TextField();
        Button confirmBtn = new Button("Verify");
        Button cancelBtn = new Button("Cancel");
        
        Stage dialogStage = adminUI.createGenericDialogStage(
            "Verify Event", 
            "Enter Event ID to verify:", 
            confirmBtn, 
            cancelBtn, 
            idField
        );
        
        confirmBtn.setOnAction(e -> {
            String eventId = idField.getText();
            if (!eventId.isEmpty()) {
                showAlert("Success", "Event verified: " + eventId);
                dialogStage.close();
            }
        });
        
        cancelBtn.setOnAction(e -> dialogStage.close());
        
        dialogStage.initOwner(adminStage);
        dialogStage.show();
    }
    
    private void showAddEventDialog() {
        TextField nameField = new TextField();
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPrefRowCount(3);
        DatePicker datePicker = new DatePicker();
        TextField locationField = new TextField();
        
        Button saveBtn = new Button("Save");
        Button cancelBtn = new Button("Cancel");
        
        Stage dialogStage = adminUI.createNewEventDialogStage(
            "New Event",
            saveBtn,
            cancelBtn,
            nameField,
            descriptionArea,
            datePicker,
            locationField
        );
        
        saveBtn.setOnAction(e -> {
            if (nameField.getText().isEmpty() || 
                datePicker.getValue() == null || 
                locationField.getText().isEmpty()) {
                
                showAlert("Error", "Please fill in all required fields");
                return;
            }
            
            // Create new event
            Event newEvent = new Event(
                nameField.getText(),
                datePicker.getValue(),
                locationField.getText(),
                currentClub.getClubName()
            );
            
            if (platform.addEvent(newEvent)) {
                showAlert("Success", "Event created successfully!");
                loadClubEvents(); // Refresh
                dialogStage.close();
            } else {
                showAlert("Error", "Event with this name already exists");
            }
        });
        
        cancelBtn.setOnAction(e -> dialogStage.close());
        
        dialogStage.initOwner(adminStage);
        dialogStage.show();
    }
    
    private void showEditEventDialog(Event event) {
        TextField nameField = new TextField(event.getEventName());
        TextArea descriptionArea = new TextArea("Event by " + event.getClubName());
        descriptionArea.setPrefRowCount(3);
        DatePicker datePicker = new DatePicker(event.getEventDate());
        TextField locationField = new TextField(event.getEventLocation());
        
        Button saveBtn = new Button("Update");
        Button cancelBtn = new Button("Cancel");
        
        Stage dialogStage = adminUI.createNewEventDialogStage(
            "Edit Event",
            saveBtn,
            cancelBtn,
            nameField,
            descriptionArea,
            datePicker,
            locationField
        );
        
        saveBtn.setOnAction(e -> {
            if (nameField.getText().isEmpty() || 
                datePicker.getValue() == null || 
                locationField.getText().isEmpty()) {
                
                showAlert("Error", "Please fill in all required fields");
                return;
            }
            
            // Update event
            platform.removeEvent(event);
            
            Event updatedEvent = new Event(
                nameField.getText(),
                datePicker.getValue(),
                locationField.getText(),
                currentClub.getClubName()
            );
            
            if (platform.addEvent(updatedEvent)) {
                showAlert("Success", "Event updated successfully!");
                loadClubEvents(); // Refresh
                dialogStage.close();
            }
        });
        
        cancelBtn.setOnAction(e -> dialogStage.close());
        
        dialogStage.initOwner(adminStage);
        dialogStage.show();
    }
    
    private void handleDeleteEvent(Event event) {
        if (platform.removeEvent(event)) {
            showAlert("Success", "Event deleted successfully");
            loadClubEvents(); // Refresh
        } else {
            showAlert("Error", "Failed to delete event");
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