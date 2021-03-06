/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.Contact;
import calendarapp.Event;
import calendarapp.Location;
import calendarapp.backend.EventController;
import calendarapp.backend.PickerController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Michael Kramer <mpk5206 @ psu.edu>
 * @version .1
 * @since .1
 */
public class EventUI extends JFrame {

    private EventController parentController;
    private Event sourceEvent;
    private JTextField eventNameTxtFld;
    private JTextField eventTagTxtFld;
    private DateTimePicker startTimeArea;
    private DateTimePicker endTimeArea;
    private ContactPicker contactArea;
    private LocationPicker locationArea;
    private ArrayList<Location> listOfLocations;
    private ArrayList<Contact> listOfContacts;
	private JComboBox priorityComboBox;

    public EventUI(EventController parentController, Event sourceEvent) {
        System.out.println("Creating EventUI.");
        this.parentController = parentController;
        this.listOfContacts = parentController.getContactList();
        this.listOfLocations = parentController.getLocationList();
        this.sourceEvent = sourceEvent;
        createWindow();
        addComponents();
        if (this.sourceEvent != null) {
            System.out.println("The sourceEvent was not null,"
                    + " will populateFields( )");
            populateFields();
        }
        System.out.println("Finished creating EventUI");
    }

    private void createWindow() {
        System.out.println("Creating the EventUI window.");
        //this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension windowSize = new Dimension(375, 667);
        //this.setPreferredSize(windowSize);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(screenSize);
        this.pack();
        this.setLocation((screenSize.width / 2) - this.getWidth() / 2,
                screenSize.height / 2 - this.getHeight() / 2);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent we) {
                //Do Nothing
            }

            @Override
            public void windowClosing(WindowEvent we) {
                System.out.println("EventUI Window Closed.");
                EventUI.this.parentController.dispose();
            }

            @Override
            public void windowClosed(WindowEvent we) {
//					System.out.println("EventUI Window Closed.");
//					EventUI.this.parentController.dispose();
            }

            @Override
            public void windowIconified(WindowEvent we) {
                //Do Nothing
            }

            @Override
            public void windowDeiconified(WindowEvent we) {
                //Do Nothing
            }

            @Override
            public void windowActivated(WindowEvent we) {
                //Do Nothing
            }

            @Override
            public void windowDeactivated(WindowEvent we) {
                //Do Nothing
            }
        });
       
        System.out.println("Finished creating the EventUI window.");
    }

    private void addComponents() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        eventNameTxtFld = new JTextField("Event Name");
        eventNameTxtFld.setHorizontalAlignment(JTextField.CENTER);
        //eventNameTxtFld.setForeground(Color.red);
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = .75;
        c.gridx = 0;
        c.gridwidth = 6;
        c.gridy = -1;
        c.weighty = .1;
        this.add(eventNameTxtFld, c);

        eventTagTxtFld = new JTextField("Event Tag");
        eventTagTxtFld.setHorizontalAlignment(JTextField.CENTER);
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridwidth = 6;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(eventTagTxtFld, c);
		Object[] priority = new Object[5];
		for(int i =0; i < priority.length; i++) {
			priority[i] = i;
		}
		priorityComboBox = new JComboBox(priority);
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridwidth = 6;
        c.weightx = 1;
        //c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        this.add(priorityComboBox, c);
        
        // gridx = 2 will be empty and be the space between columns
        JPanel leftBlankSpace = new JPanel();
        //leftBlankSpace.setBackground(Color.white);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.weightx = .25;
        c.gridy = 2;
        c.gridheight = 2;
        //c.gridwidth = 0;
        c.fill = GridBagConstraints.BOTH;
        this.add(leftBlankSpace, c);
        
        JLabel startLbl = new JLabel("Start Time: ");
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 3;
        //c.gridwidth = 0;
        this.add(startLbl, c);
        
        startTimeArea = new DateTimePicker(System.currentTimeMillis());
        configureStartTimeArea(startTimeArea);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.weightx = .5;
        c.gridy = 2;
        c.weighty = .5;
        c.fill = GridBagConstraints.CENTER;
        this.add(startTimeArea, c);

        JLabel endLbl = new JLabel("End Time: ");
        c = new GridBagConstraints();
        //c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = -1;
        this.add(endLbl, c);

        endTimeArea = new DateTimePicker(System.currentTimeMillis());
        configureEndTimeArea(endTimeArea);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.weightx = .5;
        c.gridy = 3;
        c.weighty = .5;
        c.fill = GridBagConstraints.CENTER;
        this.add(endTimeArea, c);

//        // gridx = 2 will be empty and be the space between columns
//        JPanel blankSpace = new JPanel();
//        blankSpace.setBackground(Color.white);
//        c = new GridBagConstraints();
//        c.gridx = 2;
//        c.weightx = .25;
//        c.gridy = 2;
//        c.gridheight = 2;
//        c.fill = GridBagConstraints.BOTH;
//        this.add(blankSpace, c);

        JLabel locationLbl = new JLabel("Location: ");
        
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 3;
        c.gridy = 2;
        this.add(locationLbl, c);

        locationArea = new LocationPicker(listOfLocations);
        //configureLocationArea(locationArea);
        
        c = new GridBagConstraints();
        c.gridx = 4;
        c.weightx = .25;
        c.gridy = 2;
        
        c.fill = GridBagConstraints.BOTH;
        this.add(locationArea, c);

        JLabel contactsLbl = new JLabel("Contacts:");
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 3;
        c.gridy = 3;
        this.add(contactsLbl, c);

        contactArea = new ContactPicker(listOfContacts);
        //configureContactArea(contactArea);
        c = new GridBagConstraints();
        c.gridx = 4;
        c.weightx = .75;
        c.gridy = 3;
        
        c.fill = GridBagConstraints.BOTH;
        this.add(contactArea, c);
        
//        JPanel rightBlankSpace = new JPanel();
//        rightBlankSpace.setBackground(Color.BLACK);
//        c = new GridBagConstraints();
//        c.gridx = -3;
//        c.weightx = .75;
//        c.gridy = 2;
//        c.gridheight = 2;
//        c.gridwidth = 1;
//        c.anchor = GridBagConstraints.CENTER;
//        c.fill = GridBagConstraints.BOTH;
//        this.add(rightBlankSpace, c);

        JPanel descriptionArea = new JPanel();
        configureDescriptionArea(descriptionArea);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridwidth = 6;
        c.weighty = .5;
        c.gridy = 4;
        c.anchor = GridBagConstraints.CENTER;
        this.add(descriptionArea, c);

        JPanel buttonArea = new JPanel();
        configureButtonArea(buttonArea);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridwidth = 6;
        c.weightx = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 5;
        c.weighty = .5;
        c.fill = GridBagConstraints.BOTH;

        this.add(buttonArea, c);
        
        

    }

    private void configureStartTimeArea(JPanel startTimeArea) {
        //This Area is odd consider using other means to get the user's time
        System.out.println("Configuring the startTimeArea");
        //startTimeArea.setBackground(Color.WHITE);
        //This area should add what ever components we decide to handle
        //The start time as
    }

    private void configureEndTimeArea(JPanel endTimeArea) {
        System.out.println("Configuring the endTimeArea");
        //endTimeArea.setBackground(Color.WHITE);
        //This area should add whatever components we end up using to handle the
        //end time
    }

    private void configureButtonArea(JPanel buttonArea) {
        //This should contain the buttons Add, Save, etc;
        buttonArea.setBackground(Color.LIGHT_GRAY);
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener((ActionEvent ae) -> {
            System.out.println("Cancel Button triggered");
            EventUI.this.parentController.dispose();
        });
        buttonArea.add(cancelBtn);
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener((ActionEvent ae) -> {
            System.out.println("Save Button triggered");
            EventUI.this.submitEvent();
        });
        buttonArea.add(saveBtn);
        if (sourceEvent != null) {
            JButton deleteBtn = new JButton("Delete");
            deleteBtn.addActionListener((ActionEvent ae) -> {
                System.out.println("Delete Button Triggered");
                parentController.deleteEvent(sourceEvent);
                parentController.dispose();
            });
            buttonArea.add(deleteBtn);
        }
    }

    private void submitEvent() {
        if (validateInput().isEmpty()) {
            System.out.println("The Event Entered was valid.");
            parentController.submitEvent();
        } else {
            System.out.println("The Event Entered was not valid.");
        }
    }

    private void configureLocationArea(JPanel locationArea) {
        locationArea.setBackground(Color.CYAN);
        //This area should be filled with whatever we decide to configure
        //location with
    }

    private void configureContactArea(JPanel contactArea) {
        contactArea.setBackground(Color.MAGENTA);
        //This area should be filled with whatever we decide to configure the
        //Contact with

    }

    private void configureDescriptionArea(JPanel descriptionArea) {
        //This should be used to configure the descriptionArea
        descriptionArea.setLayout(new GridBagLayout());
        descriptionArea.setBackground(Color.YELLOW);
        JPanel topBuffer = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridwidth = 3;
        c.weightx = .99;
        c.gridy = 0;
        c.weighty = .15;
        c.fill = GridBagConstraints.BOTH;
        descriptionArea.add(topBuffer, c);

        JPanel leftBuffer = new JPanel();
        c = new GridBagConstraints();
        c.gridx = 0;
        c.weightx = .1;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        descriptionArea.add(leftBuffer, c);

        JPanel container = new JPanel();
        JTextArea descriptionTxtFld = new JTextArea();
        descriptionTxtFld.setLineWrap(true);
        descriptionTxtFld.setWrapStyleWord(true);
        JScrollPane descriptionPane = new JScrollPane(descriptionTxtFld);
        descriptionPane.setPreferredSize(new Dimension(0, 0));
        container.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        container.add(descriptionPane, c);
        container.setBackground(Color.RED);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.weightx = .99;
        c.gridy = 1;
        c.weighty = .99;
        c.fill = GridBagConstraints.BOTH;
        descriptionArea.add(container, c);

        JPanel rightBuffer = new JPanel();
        c = new GridBagConstraints();
        c.gridx = 2;
        c.weightx = .1;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        descriptionArea.add(rightBuffer, c);

        JPanel bottomBuffer = new JPanel();
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridwidth = 3;
        c.weightx = .99;
        c.gridy = 2;
        c.weighty = .15;
        c.fill = GridBagConstraints.BOTH;
        descriptionArea.add(bottomBuffer, c);
    }

    private void populateFields() {
        //FIXME: GitHub Issue #18
        this.eventNameTxtFld.setText(sourceEvent.getEventName());
        this.eventTagTxtFld.setText(sourceEvent.getEventTag());
        startTimeArea.setDate(sourceEvent.getEventStartDate());
        endTimeArea.setDate(sourceEvent.getEventEndDate());
		priorityComboBox.setSelectedIndex(sourceEvent.getEventPriority());
        if(sourceEvent.hasLocation())
            locationArea.setSelected(sourceEvent.getEventLocation().getName());
        if(sourceEvent.hasContacts())
            contactArea.setSelected(sourceEvent.getEventContactList());
    }

    private ArrayList<String> validateInput() {
        ArrayList<String> errorMessages = new ArrayList<String>();
        if (this.eventNameTxtFld.getText() == null) {
            errorMessages.add("The event name is not valid.");
        }
        if (this.eventTagTxtFld.getText() == null) {
            errorMessages.add("The event tag is not valid.");
        }
        if (this.startTimeArea.getDate() == null) {
            errorMessages.add("The start time is not valid.");
        }
        if (this.endTimeArea.getDate() == null) {
            errorMessages.add("The end time is not valid.");
        }
        if (this.contactArea.getSelected() == null) {
            errorMessages.add("The associated contact list was not valid.");
        }
        if (this.locationArea.getLocation() == null) {
            errorMessages.add("The associated location was not valid.");
        }
        return errorMessages;
    }

    public String getEventName() {
        return this.eventNameTxtFld.getText();
    }

    public String getEventTag() {
        return this.eventTagTxtFld.getText();
    }

    public Calendar getEventStartDate() {
        return this.startTimeArea.getDate();
    }

    public Calendar getEventEndDate() {
        return this.endTimeArea.getDate();
    }

    public Location getEventLocation() {
        return locationArea.getSelectedItem();
    }

    public ArrayList<Contact> getEventContacts() {
        return contactArea.getSelectedContacts();
    }
	public int getEventPriority() {
		return priorityComboBox.getSelectedIndex();
	}
}
