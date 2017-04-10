package user;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import managers.*;
import dataAccess.*;
import models.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Userinterface extends JFrame {

	private FlightManager fm;
	private FlightStorageImpl fs;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private DefaultTableModel tableModel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Userinterface frame = new Userinterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Userinterface() {
		fs = new FlightStorageImpl();
		fm = new FlightManager(fs);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Userinterface.class.getResource("/user/takeoff.png")));
		setTitle("Flight booking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 710);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnBka = new JButton("Book flight");
		btnBka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetTable();
			}
		});
		String data[][] = {};
		String col[] = {"Flight nr.","From", "To", "Time", "Date", "Avail. Seats", "Price Adult"};
	    tableModel = new DefaultTableModel(data,col);
 
        
        
        
		JLabel lblNewLabel = new JLabel("Departing from");
		
		JLabel lblNewLabel_1 = new JLabel("Going to");
		
		JComboBox comboBox = new JComboBox();
		
		JComboBox comboBox_1 = new JComboBox();
		
		JLabel lblDate = new JLabel("Date");
		
		JComboBox comboBox_2 = new JComboBox();
		
		JRadioButton rdbtnFlexableDates = new JRadioButton("Flexible dates");
		
		JLabel lblAdults = new JLabel("Adults");
		
		JComboBox comboBox_3 = new JComboBox();
		
		JLabel lblChildre = new JLabel("Children");
		
		JComboBox comboBox_4 = new JComboBox();
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String departure = getSelectedDeparture();
				String dest = getSelectedDest();
				String date = getSelectedDate();
				int nrPassengers = getSelectedNrPassengers();
				ArrayList<Flight> flights = fm.searchFlights(departure, dest, date, nrPassengers);
				// Clear previous results from the table before showing new ones
				resetTable();
				showResults(flights);
			}
		});
		
		
		JLabel lblSelectFlightNr = new JLabel("Select flight nr. ");
		
		JComboBox comboBox_5 = new JComboBox();
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNamesOfPassangers = new JLabel("Names of passangers");
		
		JLabel lblNumbersOfBags = new JLabel("Numbers of bags");
		
		JComboBox comboBox_6 = new JComboBox();
		
		JLabel lblSpecialRec = new JLabel("Special requests");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addComponent(lblNamesOfPassangers))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNumbersOfBags))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblSpecialRec)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(comboBox_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(textField_1))
					.addContainerGap(171, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(209, Short.MAX_VALUE)
					.addComponent(btnBka)
					.addGap(169))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(214, Short.MAX_VALUE)
					.addComponent(btnSearch)
					.addGap(192))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(comboBox_1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(comboBox, 0, 67, Short.MAX_VALUE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblDate)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnFlexableDates))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAdults)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblChildre)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(35, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(114)
					.addComponent(lblSelectFlightNr)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(198, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDate)
						.addComponent(rdbtnFlexableDates))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAdults)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblChildre)
						.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addComponent(btnSearch)
					.addGap(9)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectFlightNr)
						.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNamesOfPassangers))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSpecialRec)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumbersOfBags))
					.addGap(29)
					.addComponent(btnBka)
					.addContainerGap())
		);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane);
		
		
		table = new JTable();
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		scrollPane.setViewportView(table);
		table.setModel(tableModel);
		contentPane.setLayout(gl_contentPane);
		table.getTableHeader().setFont(new Font("Lucida Grande", Font.PLAIN, 10));
	}
	
	// Show search results in the table
	private void showResults(ArrayList<Flight> flights) {
		for (int i=0; i<flights.size(); i++) {
			Flight fl = flights.get(i);
			int flightID = fl.getFlightID();
			String flightDepart = fl.getFlightDepart();
			String flightDest = fl.getFlightDest();
			String flightTime = fl.getFlightTime();
			String flightDate = fl.getFlightDate();
			int availableSeats = fl.getAvailableSeats();
			int price = fl.getFlightPrice();
			Object[] row = new Object[] { flightID, flightDepart, flightDest, flightTime, flightDate, availableSeats, price  };
			tableModel.addRow(row);
		}	
	}
	
	// Reset the table before showing new results
	private void resetTable() {
		tableModel.setRowCount(0);
	}
	
	private String getSelectedDeparture() {
		return "REY";
		// TODO: láta þetta virka með UI inputinu
	}
	
	private String getSelectedDest() {
		return "AEY";
		// TODO: láta þetta virka með UI inputinu
	}
	
	private String getSelectedDate() {
		return "28/04/2017";
		// TODO: láta þetta virka með UI inputinu
	}
	
	private int getSelectedNrPassengers() {
		return 3;
		// TODO: láta þetta virka með UI inputinu
	}
	
}
