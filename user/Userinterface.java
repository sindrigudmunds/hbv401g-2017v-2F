package user;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import managers.*;
import dataAccess.*;
import models.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
import javax.swing.JTextArea;


public class Userinterface extends JFrame {

	private FlightManager fm;
	private FlightStorageImpl fs;
	private BookingManager bm;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JCheckBox chckbxFlexibleDates;
	private String selectedDept, selectedDest, day, month, date, specialNeeds;
	private int nrAdults, nrChildren, bookingFlightID, nrSeats, flightPrice, selectedNrBags;
	private Flight flight;
	private JComboBox<String> departurePlace;
	private JComboBox destPlace;
	private JLabel lblTotalPrice;
	private JTextField txtSpecialRequests;
	private JComboBox dayPicker;
	private JComboBox monthPicker;
	private JComboBox nrAdult, nrChild, nrBagsCombobox;

	
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
		setBounds(100, 100, 562, 618);
		
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
				// Only create a booking if a flight has been chosen from the list
				String msg = checkChosenBookingParam();
				if (msg == "") {
					int bookingNumber = createBooking();
					JOptionPane.showMessageDialog (null, "Thank you for booking a flight with Fake Airlines. Your booking number is: " + bookingNumber, "Booking information", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				} else {
					JOptionPane.showMessageDialog (null, msg, "Searching Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		String data[][] = {};
		String col[] = {"Flight nr.","From", "To", "Time", "Date", "Avail. Seats", "Price Adult"};
	    tableModel = new DefaultTableModel(data,col);
		DefaultComboBoxModel fraRey = new DefaultComboBoxModel(new String[]{"Going To", "Akureyri", "Egilssta\u00F0ir", "\u00CDsafj\u00F6r\u00F0ur"});
		DefaultComboBoxModel fraAey = new DefaultComboBoxModel(new String[]{"Going To", "Reykjav\u00EDk", "Gr\u00EDmsey"});
		DefaultComboBoxModel fraGrm = new DefaultComboBoxModel(new String[]{"Going To", "Akureyri"});
		DefaultComboBoxModel fraIsf = new DefaultComboBoxModel(new String[]{"Going To", "Reykjav\u00EDk"});
		DefaultComboBoxModel fraEgs = new DefaultComboBoxModel(new String[]{"Going To", "Reykjav\u00EDk"});
		
		departurePlace = new JComboBox<String>();
		departurePlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dept = (String)departurePlace.getSelectedItem();
				
				switch(dept){
				case "Reykjav\u00EDk": 
					setSelectedDeparture("REY");
					destPlace.setModel(fraRey);
					break;
				case "Akureyri": setSelectedDeparture("AEY");
					destPlace.setModel(fraAey);
					break;
				case "Egilssta\u00F0ir": setSelectedDeparture("EGS");
					destPlace.setModel(fraEgs);
					break;
				case "Gr\u00EDmsey": setSelectedDeparture("GRM");
					destPlace.setModel(fraGrm);
					break;
				case "\u00CDsafj\u00F6r\u00F0ur": setSelectedDeparture("ISF");
					destPlace.setModel(fraIsf);
					break;
				default: setSelectedDeparture("");
					
				}
			}
		});
		departurePlace.setModel(new DefaultComboBoxModel(new String[] {"Departing from", "Reykjav\u00EDk", "Akureyri", "Egilssta\u00F0ir", "Gr\u00EDmsey", "\u00CDsafj\u00F6r\u00F0ur"}));
		
		//
		destPlace = new JComboBox();
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
		destPlace.setModel(new DefaultComboBoxModel(new String[] {"Going to"}));
		
		JLabel lblAdults = new JLabel("Passengers");
		
		nrAdult = new JComboBox();
		nrAdult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nrAdults = (String)nrAdult.getSelectedItem();
				setNrAdults(nrAdults);
				
			}
		});
		nrAdult.setModel(new DefaultComboBoxModel(new String[] {"Adults", "1", "2", "3", "4", "5", "6", "7", "8", "9"}));
		
		nrChild = new JComboBox();
		nrChild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nrChildrens = (String)nrChild.getSelectedItem();
				setNrChildren(nrChildrens);
			}
		});
		nrChild.setModel(new DefaultComboBoxModel(new String[] {"Children", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}));
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setIcon(new ImageIcon(Userinterface.class.getResource("/user/rsz_1spyglass.png")));
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String msg = checkChosenSearchParam();
				// Only search for flights if the user has chosen from all the dropdown lists
				if (msg == "") {
					// Get information from UI and search for a matching flight
					String date = getDate(getDay(), getMonth());
					String departure = getSelectedDeparture();
					String dest = getSelectedDest();
					int nrPassengers = getNrChildren() + getNrAdults();
					boolean flexible = getFlexibleDates();
					ArrayList<Flight> flights = fm.searchFlights(departure, dest, date, nrPassengers, flexible);
					
					// Clear previous results from the table before showing new ones
					resetTable();
					
					// If the search came back with no results
					if (flights.isEmpty()){
						JOptionPane.showMessageDialog (null, "Your search returned no results. Please try again with different search parameters.", "Searching Error", JOptionPane.INFORMATION_MESSAGE);
					} else {
						showResults(flights);
					}
				} else {
					JOptionPane.showMessageDialog (null, msg, "Searching Error", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		
		JPanel panel = new JPanel();
		
		chckbxFlexibleDates = new JCheckBox("Flexible \r\ndates");
		
		dayPicker = new JComboBox();
		dayPicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String day = (String)dayPicker.getSelectedItem();
				setDay(day);
			}
		});
		dayPicker.setModel(new DefaultComboBoxModel(new String[] {"Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		
		monthPicker = new JComboBox();
		monthPicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String month = (String)monthPicker.getSelectedItem();
				setMonth(month);
			}
		});
		monthPicker.setModel(new DefaultComboBoxModel(new String[] {"Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		
		JLabel label = new JLabel("");
		
		JLabel lblToBookA = new JLabel("To book a flight select a flight from the table and click Book flight");
		
		JLabel label_1 = new JLabel("");
		
		lblTotalPrice = new JLabel("");
		
		JLabel lblDate = new JLabel("Date");
		
		nrBagsCombobox = new JComboBox();
		nrBagsCombobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nrOfBags = (String)nrBagsCombobox.getSelectedItem();
				switch(nrOfBags){
					case "1": setSelectedNrBag(1);
						break;
					case "2": setSelectedNrBag(2);
						break;
					case "3": setSelectedNrBag(3);
						break;
					case "4": setSelectedNrBag(4);
					default: setSelectedNrBag(0);
				}
				int totalPrice = getFlightPrice()*getNrAdults() + (getFlightPrice()/ 2)*getNrChildren() + getSelectedNrBag()*1000;
	            String flightprice = Integer.toString(totalPrice);
				String totalFlightPrice = "Total Price: " + flightprice + " kr.";
				lblTotalPrice.setText(totalFlightPrice);
				
			}
		});
		nrBagsCombobox.setModel(new DefaultComboBoxModel(new String[] {"Number of Bags", "0", "1", "2", "3", "4"}));
		
		txtSpecialRequests = new JTextField();
		txtSpecialRequests.setText("Special Requests");
		txtSpecialRequests.setColumns(10);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(56)
							.addComponent(label))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(destPlace, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(departurePlace, 0, 166, Short.MAX_VALUE))
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblDate, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblAdults, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(dayPicker, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(nrAdult, 0, 75, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(nrChild, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(monthPicker, 0, 74, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(chckbxFlexibleDates))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtSpecialRequests)
								.addComponent(nrBagsCombobox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(92)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTotalPrice, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(129)
									.addComponent(label_1))
								.addComponent(btnBka))
							.addGap(189)))
					.addGap(0))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(97)
					.addComponent(lblToBookA)
					.addContainerGap(131, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(chckbxFlexibleDates)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(3)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(monthPicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblDate, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
									.addComponent(dayPicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(departurePlace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(destPlace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nrChild, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAdults, GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
						.addComponent(nrAdult, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addComponent(btnSearch)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblToBookA)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(nrBagsCombobox)
								.addComponent(lblTotalPrice, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnBka)
							.addGap(17)
							.addComponent(label_1))
						.addComponent(txtSpecialRequests, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13))
		);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane);
		
		
		table = new JTable();
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		scrollPane.setViewportView(table);
		table.setModel(tableModel);
		contentPane.setLayout(gl_contentPane);
		table.getTableHeader().setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            int flightID = (int) table.getValueAt(table.getSelectedRow(), 0);
	            int nrSeats = (int) table.getValueAt(table.getSelectedRow(), 5);
	            int flightPrice = (int) table.getValueAt(table.getSelectedRow(), 6);
	            setBookingFlightID(flightID);
	            setFlightPrice(flightPrice);
	            setNrSeats(nrSeats);
	            int totalPrice = getFlightPrice()*getNrAdults() + (getFlightPrice()/ 2)*getNrChildren();
	            String flightprice = Integer.toString(totalPrice);
				String totalFlightPrice = "Total Price: " + flightprice + " kr.";
				lblTotalPrice.setText(totalFlightPrice);
	        }
	    });
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
		return this.selectedDept;
	}
	
	private void setSelectedDeparture(String dept){
		this.selectedDept = dept;
	}
	
	private String getSelectedDest() {
		return this.selectedDest; 
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
	private int getFlightPrice(){
		return this.flightPrice;
	}
	private void setFlightPrice(int flightPrice){
		this.flightPrice = flightPrice;
	}
	
	private int getNrAdults() {
		return this.nrAdults;
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
		int flightID = getBookingFlightID();
		int nrBag = getSelectedNrBag();
		int nrAdult = getNrAdults();
		int nrChildren = getNrChildren(); 
		String specialNeeds = txtSpecialRequests.getText(); 	
		int bookingID = bm.createBooking(flightID, nrBag, nrAdult, nrChildren, specialNeeds);
		return bookingID;
	}
	
	private int getSelectedNrBag() {
		return this.selectedNrBags;
	}
	private void setSelectedNrBag(int nrBag){
		this.selectedNrBags = nrBag;
	}
	private void setBookingFlightID(int flightID){
		this.bookingFlightID = flightID;
	}
	private int getBookingFlightID(){
		return this.bookingFlightID;
	}
	private int getNrSeats(){
		return this.nrSeats;
	}
	private void setNrSeats(int nrSeats){
		this.nrSeats = nrSeats;
	}
	private void setSpecialNeeds(String needs){
		this.specialNeeds = needs;
	}
	private String getSpecialNeeds() {
		return this.specialNeeds; 
	}
	
	private String checkChosenSearchParam() {
		String msg = "";
		if (departurePlace.getSelectedIndex() <= 0 || destPlace.getSelectedIndex() <= 0) {
			msg = "Please choose from the dropdown lists where you are \"Departing From\" and \"Going To\".";
		} else if (dayPicker.getSelectedIndex() <= 0 || monthPicker.getSelectedIndex() <= 0){
			msg = "Please choose a departure date.";
		} else if (nrAdult.getSelectedIndex() <= 0 || nrChild.getSelectedIndex() <= 0){
			msg = "Please add information about number of passengers.";
		}
		return msg;
	}
	
	private String checkChosenBookingParam() {
		String msg = "";
		if (tableModel.getRowCount() <= 0 || getBookingFlightID() <= 0) {
			msg = "Please choose a flight from the list.";
		} else if (nrBagsCombobox.getSelectedIndex() <= 0) {
			msg = "Please choose how many bags you want.";
		}
		return msg;
	}
}