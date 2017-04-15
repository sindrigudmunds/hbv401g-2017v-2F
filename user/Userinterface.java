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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenu;


public class Userinterface extends JFrame {

	private FlightManager fm;
	private FlightStorageImpl fs;
	private BookingManager bm;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private DefaultTableModel tableModel;
	private JCheckBox chckbxFlexibleDates;
	private String selectedDept, selectedDest, day, month, date;
	private int nrAdults, nrChildren;
	//private date selectedDate;

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
		bm = new BookingManager();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Userinterface.class.getResource("/user/takeoff.png")));
		setTitle("Flight booking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 710);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ret = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?");
			    if (ret == JOptionPane.YES_OPTION)
				   System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnInfo = new JMenu("Info");
		menuBar.add(mnInfo);
		
		JMenuItem mntmRulesAndRegulations = new JMenuItem("Rules and regulations");
		mntmRulesAndRegulations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog (null, "Booking with us does not book you a flight and we do not issue refunds. Have a nice day!", "Rules and regulations", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnInfo.add(mntmRulesAndRegulations);
		
		JMenuItem mntmAboutThisProgram = new JMenuItem("About this program");
		mntmAboutThisProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog (null, "This program was made for the class �r�un hugb�na�ar spring 2017 by Anna Karen, Gu�r�n ��ra and Sindri ��r", "About this program", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnInfo.add(mntmAboutThisProgram);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnBka = new JButton("Book flight");
		btnBka.setIcon(new ImageIcon(Userinterface.class.getResource("/user/rsz_luggage.png")));
		btnBka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetTable();
				int id = createBooking();
				// TODO: birta skjá með id-inu svo notandinn viti að búið sé að bóka
				System.out.println("Your booking number is: "+id);
			}
		});
		String data[][] = {};
		String col[] = {"Flight nr.","From", "To", "Time", "Date", "Avail. Seats", "Price Adult"};
	    tableModel = new DefaultTableModel(data,col);
 
        
        
        
		JLabel lblNewLabel = new JLabel("Departing from");
		
		JLabel lblNewLabel_1 = new JLabel("Going to");
		
		JComboBox<String> departurePlace = new JComboBox<String>();
		departurePlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dept = (String)departurePlace.getSelectedItem();
				
				switch(dept){
				case "Reykjav\u00EDk": setSelectedDeparture("REY");
					break;
				case "Akureyri": setSelectedDeparture("AEY");
					break;
				case "Egilssta\u00F0ir": setSelectedDeparture("EGS");
					break;
				case "Gr\u00EDmsey": setSelectedDeparture("GRM");
					break;
				case "\u00CDsafj\u00F6r\u00F0ur": setSelectedDeparture("ISF");
					break;
				default: setSelectedDeparture("");
					
				}
			}
		});
		departurePlace.setModel(new DefaultComboBoxModel(new String[] {"Reykjav\u00EDk", "Akureyri", "Egilssta\u00F0ir", "Gr\u00EDmsey", "\u00CDsafj\u00F6r\u00F0ur"}));
		
		JComboBox destPlace = new JComboBox();
		destPlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dept = (String)destPlace.getSelectedItem();
				
				switch(dept){
				case "Reykjav\u00EDk": setSelectedDest("REY");
					break;
				case "Akureyri": setSelectedDest("AEY");
					break;
				case "Egilssta\u00F0ir": setSelectedDest("EGS");
					break;
				case "Gr\u00EDmsey": setSelectedDest("GRM");
					break;
				case "\u00CDsafj\u00F6r\u00F0ur": setSelectedDest("ISF");
					break;
				default: setSelectedDest("");
					
				}
			}
		});
		destPlace.setModel(new DefaultComboBoxModel(new String[] {"Reykjav\u00EDk", "Akureyri", "Egilssta\u00F0ir", "Gr\u00EDmsey", "\u00CDsafj\u00F6r\u00F0ur"}));
		
		JLabel lblAdults = new JLabel("Adults");
		
		JComboBox nrAdult = new JComboBox();
		nrAdult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nrAdults = (String)nrAdult.getSelectedItem();
				setNrAdults(nrAdults);
				
			}
		});
		nrAdult.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
		
		JLabel lblChildre = new JLabel("Children");
		
		JComboBox nrChild = new JComboBox();
		nrChild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nrChildrens = (String)nrChild.getSelectedItem();
				setNrChildren(nrChildrens);
			}
		});
		nrChild.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}));
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setIcon(new ImageIcon(Userinterface.class.getResource("/user/rsz_1spyglass.png")));
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				String date = getDate(getDay(), getMonth());
				String departure = getSelectedDeparture();
				String dest = getSelectedDest();
				int nrPassengers = getNrChildren() + getNrAdults();
				boolean flexible = getFlexibleDates();
				ArrayList<Flight> flights = fm.searchFlights(departure, dest, date, nrPassengers, flexible);
				// Clear previous results from the table before showing new ones
				resetTable();
				showResults(flights);
				
				/*DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		        String date = fmt.format(this.txt_data_ini.getDate()); //jdatechooser
		        this.teste.setText(date);*/
			}
		});
		
		
		JLabel lblSelectFlightNr = new JLabel("Select flight nr. ");
		
		JComboBox comboBox_5 = new JComboBox();
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNamesOfPassangers = new JLabel("Names of passangers");
		
		JLabel lblNumbersOfBags = new JLabel("Numbers of bags");
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}));
		
		JLabel lblSpecialRec = new JLabel("Special requests");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JPanel panel = new JPanel();
		
		chckbxFlexibleDates = new JCheckBox("Flexible dates");
		
		JComboBox dayPicker = new JComboBox();
		dayPicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String day = (String)dayPicker.getSelectedItem();
				setDay(day);
			}
		});
		dayPicker.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		
		JLabel lblMonth = new JLabel("Month");
		
		JComboBox monthPicker = new JComboBox();
		monthPicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String month = (String)monthPicker.getSelectedItem();
				setMonth(month);
			}
		});
		monthPicker.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		
		JLabel lblDay = new JLabel("Day");
		
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
					.addContainerGap(189, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(263, Short.MAX_VALUE)
					.addComponent(btnBka)
					.addGap(169))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(258, Short.MAX_VALUE)
					.addComponent(btnSearch)
					.addGap(192))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(114)
					.addComponent(lblSelectFlightNr)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(284, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(destPlace, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(departurePlace, 0, 67, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDay)
								.addComponent(lblAdults))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(dayPicker, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(nrAdult, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(7)
									.addComponent(lblChildre)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(nrChild, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(4)
									.addComponent(lblMonth)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(monthPicker, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
									.addComponent(chckbxFlexibleDates))))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(11, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(departurePlace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxFlexibleDates)
						.addComponent(lblDay)
						.addComponent(dayPicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMonth)
						.addComponent(monthPicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(destPlace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAdults)
								.addComponent(lblChildre)
								.addComponent(nrChild, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nrAdult, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
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
		return this.selectedDept; //Taka burt
		// TODO: láta þetta virka með UI inputinu
	}
	
	private void setSelectedDeparture(String dept){
		this.selectedDept = dept;
	}
	
	private String getSelectedDest() {
		return this.selectedDest; //Taka burt
		// TODO: láta þetta virka með UI inputinu
	}
	private void setSelectedDest(String dest){
		this.selectedDest = dest;
	}
	private void setDay(String day){
		this.day = day;
	}
	private String getDay(){
		return this.day;
	}
	private void setMonth(String month){
		this.month = month;
	}
	private String getMonth(){
		return this.month;
	}
	private String getDate(String day, String month){
		String year = "/2017";
		this.date = day + "/" + month + year;
		return this.date;
	}

	
	private int getNrAdults() {
		return this.nrAdults; //Taka burt
		// TODO: láta þetta virka með UI inputinu
	}
	private void setNrAdults(String adults){
		this.nrAdults = Integer.parseInt(adults);
	}
	private int getNrChildren(){
		return this.nrChildren;
	}
	private void setNrChildren(String children){
		this.nrChildren = Integer.parseInt(children);
	}
	private boolean getFlexibleDates() {
		// Check if user wants to search by flexible dates or not
		if (chckbxFlexibleDates.isSelected()) {
			return true;
		} else {
			return false;
		}
	}
	
	// Creates a new booking in the database with information from the UI
	private int createBooking() {
		Flight flight = getSelectedFlight();
		int nrBag = getSelectedNrBag();
		ArrayList<Passenger> passengers = getPassengers();
		String specialNeeds = getSpecialNeeds();
		int bookingID  = bm.createBooking(flight, nrBag, passengers, specialNeeds);
		return bookingID;
	}
	
	private Flight getSelectedFlight() {
		// Taka burt
		return new Flight(201, "GRM", "AEY", "15:00", "17/04/2017", 35, 17000);
		// TODO: skila flug hlutnum sem notandi hefur valið
	}
	
	private int getSelectedNrBag() {
		return 5;//Taka burt
		// TODO: skila fjölda taskna sem notandi hefur valið
	}
	
	private ArrayList<Passenger> getPassengers() {
		// Taka burt
		PassengerManager pm = new PassengerManager();
		Passenger p_a = new Passenger("Jón", true);
		Passenger p_b = new Passenger("Gunna", true);
		Passenger p_c = new Passenger("Óli", false);
		pm.addPassenger(p_a);
		pm.addPassenger(p_b);
		pm.addPassenger(p_c);
		return pm.getPassengers();
		// TODO: láta passenger manager gera arraylist af passengers 
	}
	
	private String getSpecialNeeds() {
		return "Passenger with a dog"; // Taka burt
		// TODO: skila textanum úr special needs glugganum
	}
}
