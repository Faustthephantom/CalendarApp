/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.backend;

import calendarapp.Contact;
import calendarapp.Event;
import calendarapp.Location;
import calendarapp.ui.ContactPicker;
import calendarapp.ui.EventPicker;
import calendarapp.ui.LocationPicker;
import calendarapp.ui.GsonPicker;
import calendarapp.ui.PickerUI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author Faust
 */
public class PickerController {
	public static final int EVENT = 0;
	public static final int CONTACT = 1;
	public static final int LOCATION = 2;
        public static final int GSON = 3;
	
	public static final int EDIT = 0;
	public static final int DELETE = 1;
	public static final int IMPORT = 0;
        public static final int EXPORT = 1;
	private PickerUI pickerUI;
	private NavigationController parentController;
	private int pickerCode = -1;
	private int editCode = -1;
	private User activeUser;
        private ArrayList<Location> locationList;
        private ArrayList<Contact> contactList;
        
	
	public PickerController (NavigationController parentController, 
			User activeUser, int pickerCode, int editCode) {
		System.out.println("Creating the PickerController.");
		this.parentController = parentController;
		this.pickerCode = pickerCode;
                this.activeUser = activeUser;
		this.locationList = activeUser.getLocationList();
                this.contactList = activeUser.getContactList();
		this.editCode = editCode;
		switch(pickerCode) {
			case EVENT:
				pickerUI = new PickerUI(this, editCode,
						new EventPicker(activeUser));
				break;
			case CONTACT:
				pickerUI = new PickerUI(this, editCode, 
                                        new ContactPicker(contactList));
				break;	
			case LOCATION:
				pickerUI = new PickerUI(this, editCode, 
                                        new LocationPicker(locationList));
				break;
                        case GSON:
                                pickerUI = new PickerUI(this, editCode,
                                        new GsonPicker(this));
				break;
			default:
				
				break;
		}
		pickerUI.setVisible(true);
		System.out.println("Finished creating the PickerController.");
	}
	
	public void dispose() {
		if(pickerUI != null) {
			pickerUI.dispose();
			pickerUI = null;
			if(pickerCode == PickerController.CONTACT) {
				parentController.updateContactTable();
			} else if (pickerCode == PickerController.EVENT) {
				parentController.updateEventTable();
			} else if(pickerCode == PickerController.LOCATION) {
				parentController.updateLocationTable();
			}
			parentController.disposePickerController();
		} else {
			System.out.println("The PickerController received a request to"
				+ " dispose of its UI but there was not an existing UI.");
		}
	}

	public boolean hasUI () {
		return pickerUI != null;
	}
	public void disposeUI () {
		pickerUI.dispose();
		pickerUI = null;
	}

	public void edit(int[] selectedRows) {
		boolean editSuccessful = false;
		switch(pickerCode) {
			case EVENT:
				System.out.println("Editing an Event.");
				if(selectedRows.length > 1 ) {
					System.out.println("More than one item is selected");
				}else if (selectedRows.length == 1) {
					System.out.println("One Item was selected.");
					parentController.requestEventController(activeUser.getEventList().get(selectedRows[0]));
					editSuccessful = true;
				}else {
					System.out.println("No item was selected");
				}
				break;
			case CONTACT:
				System.out.println("Editing a contact");
				if(selectedRows.length > 1 ) {
					System.out.println("More than one item is selected");
				}else if (selectedRows.length == 1) {
					System.out.println("One Item was selected.");
					parentController.requestContactController(activeUser.getContactList().get(selectedRows[0]));
					editSuccessful = true;
				}else {
					System.out.println("No item was selected");
				}
				break;
			case LOCATION: 
				System.out.println("Editing Location.");
				if(selectedRows.length > 1 ) {
					System.out.println("More than one item is selected");
				}else if (selectedRows.length == 1) {
					System.out.println("One Item was selected.");
					parentController.requestLocationController(activeUser.getLocationList().get(selectedRows[0]));
					editSuccessful = true;
				}else {
					System.out.println("No item was selected");
				}
			default:
				break;
		}
		if(editSuccessful)
			dispose();	
	}
        
        public void delete(int[] selectedRows) {
		boolean deleteSuccessful = false;
		switch(pickerCode) {
			case EVENT:
				System.out.println("Deleting an Event.");
				if(selectedRows.length > 1 ) {
					System.out.println("More than one item is selected");
				}else if (selectedRows.length == 1) {
					System.out.println("One Item was selected.");
					activeUser.getEventList().remove(selectedRows[0]);
					deleteSuccessful = true;
				}else {
					System.out.println("No item was selected");
				}
				break;
			case CONTACT:
				System.out.println("Deleting a contact");
				if(selectedRows.length > 1 ) {
					System.out.println("More than one item is selected");
				}else if (selectedRows.length == 1) {
					System.out.println("One Item was selected.");
					activeUser.getContactList().remove(selectedRows[0]);
					deleteSuccessful = true;
				}else {
					System.out.println("No item was selected");
				}
				break;
			case LOCATION: 
				System.out.println("Deleting a Location.");
				if(selectedRows.length > 1 ) {
					System.out.println("More than one item is selected");
				}else if (selectedRows.length == 1) {
					System.out.println("One Item was selected.");
					activeUser.getLocationList().remove(selectedRows[0]);
					deleteSuccessful = true;
				}else {
					System.out.println("No item was selected");
				}
			default:
				break;
		}
		if(deleteSuccessful)
			dispose();	
	}
	void bringUIToTop() {
		pickerUI.setState(JFrame.NORMAL);
		pickerUI.toFront();
		pickerUI.repaint();
	}

    public void importContacts(List<Contact> readContactArray) {
        System.out.println("importing " + readContactArray.size() + " contacts.");
        for(int i = 0; i < readContactArray.size(); i++) {
            this.activeUser.addContact(readContactArray.get(i));
        }
        parentController.updateContactTable();
        parentController.writeData();
    }

	public void importEvents(ArrayList<Event> eventsToImport) {
		System.out.println("importing " + eventsToImport.size() + " contacts.");
        for(int i = 0; i < eventsToImport.size(); i++) {
            this.activeUser.addEvent(eventsToImport.get(i));
        }
        parentController.updateEventTable();
        parentController.writeData();
	}
}
