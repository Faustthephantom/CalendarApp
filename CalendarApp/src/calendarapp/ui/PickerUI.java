/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.backend.PickerController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Faust
 */
public class PickerUI extends JFrame {
	private PickerController parentController;
	private Picker picker;
	private int editCode;
	
	public PickerUI (PickerController parentController, int editCode,
			Picker picker) {
		this.parentController = parentController;
		this.editCode = editCode;
		this.picker = picker;
		createWindow();
		this.setLayout(new BorderLayout());
		this.add(picker, BorderLayout.CENTER);
		addButtonArea();
	}
	
	private void createWindow() {
		System.out.println("Creating the PickerUI window.");
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension windowSize = new Dimension(375, 667);
		this.setPreferredSize(windowSize);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.pack();
		this.setLocation((screenSize.width/2) - this.getWidth()/2, 
				screenSize.height/2 - this.getHeight()/2);
		this.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent we) {
				//Do Nothing
			}

			@Override
			public void windowClosing(WindowEvent we) {
				System.out.println("EventUI Window Closed.");
				PickerUI.this.parentController.disposePickerUI();
			}

			@Override
			public void windowClosed(WindowEvent we) {
				System.out.println("EventUI Window Closed.");
				PickerUI.this.parentController.disposePickerUI();
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
		System.out.println("Finished creating the PickerUI window.");
	}
	
	private void addButtonArea() {
		JPanel buttonArea = new JPanel();
		this.add(buttonArea, BorderLayout.SOUTH);
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener((ActionEvent ae) -> {
			System.out.println("CancelBtn has been triggerd.");
			PickerUI.this.parentController.disposePickerUI();
		});
		buttonArea.add(cancelBtn);
		
		if(editCode == 0) {
			JButton editBtn = new JButton("Edit");
			editBtn.addActionListener((ActionEvent ae) -> {
				System.out.println("EditBtn triggered.");
				PickerUI.this.edit();
			});
			buttonArea.add(editBtn, BorderLayout.SOUTH);
		} else if (editCode == 1 ) {
			JButton deleteBtn = new JButton("Delete");
			deleteBtn.addActionListener((ActionEvent ae) -> {
				System.out.println("DeleteBtn triggered");
			});
			buttonArea.add(deleteBtn, BorderLayout.SOUTH);
		}
	}
	
	private void edit() {
		parentController.edit(picker.getSelected());
		
	}
}
