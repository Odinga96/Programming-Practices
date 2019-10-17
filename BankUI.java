// Java core packages
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Java extension packages
import javax.swing.*;

public class BankUI extends JPanel {
	private static final long serialVersionUID = 3417040053089159204L;
	// label text for GUI
	protected final static String names[] = { "Account number", "First name",
			"Last name", "Address", "SocSec", "Balance", "GPA", "Title", "Transaction Amount" };

	// GUI components; protected for future subclass access
	protected JLabel labels[];
	protected JTextField fields[];
	protected JButton doTask1, doTask2;
	protected JPanel innerPanelCenter, innerPanelSouth;
	// number of text fields in GUI
	protected int size;

	// constants representing text fields in GUI
	public static final int ACCOUNT = 0, FIRSTNAME = 1, LASTNAME = 2, ADDRESS = 3, SOCSEC = 4,
			BALANCE = 5, GPA = 6,  TITLE = 7, TRANSACTION = 8;

	// Set up GUI. Constructor argument of 4 creates four rows
	// of GUI components. Constructor argument of 5 (used in a
	// later program) creates five rows of GUI components.
	public BankUI(int mySize) {
		size = mySize;
		labels = new JLabel[size];
		fields = new JTextField[size];

		// create labels
		for (int count = 0; count < labels.length; count++)
			labels[count] = new JLabel(names[count]);


		// create text fields
		for (int count = 0; count < fields.length; count++)
			fields[count] = new JTextField();

		// set focus for text fields
                // x++ or ++x does not make difference on x but will change the other variale.
		for(int x=0; x < size; x++){
			final int fieldSize = x;
			fields[x].addKeyListener(new KeyAdapter(){
				public void keyPressed(KeyEvent e){
					if(e.getKeyCode() == KeyEvent.VK_ENTER)
						if(fieldSize == (size-1))
							doTask2.requestFocus();
						else fields[fieldSize+1].requestFocus();
				}
			});
		}

		// create panel to lay out labels and fields
		innerPanelCenter = new JPanel();
		innerPanelCenter.setLayout(new GridLayout(size, 2));

		// attach labels and fields to innerPanelCenter
		for (int count = 0; count < size; count++) {
			innerPanelCenter.add(labels[count]);
			innerPanelCenter.add(fields[count]);
		}

		// create generic buttons; no labels or event handlers
		doTask1 = new JButton();
		doTask2 = new JButton();

		// create panel to lay out buttons and attach buttons
		innerPanelSouth = new JPanel();
		innerPanelSouth.add(doTask1);
		innerPanelSouth.add(doTask2);

		// set layout of this container and attach panels to it
		setLayout(new BorderLayout());
		add(innerPanelCenter, BorderLayout.CENTER);
		add(innerPanelSouth, BorderLayout.SOUTH);

		// validate layout
		validate(); // make share panels fit.
		// pack(); // make all panels in constant sizes

	} // end constructor

	// return reference to generic task button doTask1
	public JButton getDoTask1Button() {
		return doTask1;
	}

	// return reference to generic task button doTask2
	public JButton getDoTask2Button() {
		return doTask2;
	}

	// return reference to fields array of JTextFields
	public JTextField[] getFields() {
		return fields;
	}

	// clear content of text fields
	public void clearFields() {
		for (int count = 0; count < size; count++)
			fields[count].setText("");
	}

	// set text field values; throw IllegalArgumentException if
	// incorrect number of Strings in argument
	public void setFieldValues(String strings[])
			throws IllegalArgumentException {
		if (strings.length != size)
			throw new IllegalArgumentException("There must be " + size
					+ " Strings in the array");

		for (int count = 0; count < size; count++)
			fields[count].setText(strings[count]);
	}

	// get array of Strings with current text field contents
	public String[] getFieldValues() {
		String values[] = new String[size];

		for (int count = 0; count < size; count++)
			values[count] = fields[count].getText();

		return values; //&values[0]
	}

} // end class BankUI
