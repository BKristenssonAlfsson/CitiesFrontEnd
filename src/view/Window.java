package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;


public class Window {
	private JButton getAllCities = new JButton("Get all cities");
	private JButton fetchCertainCity = new JButton("Search city");
	private JButton fetchCitiesBetween = new JButton("Search cities on population");
	private JButton addCity = new JButton("Add a new city");
	private JButton updateCity = new JButton("Update a city");
	private JButton deleteCity = new JButton("Delete a city");
	private JLabel[] details = new JLabel[24];
	
	public void init() {
		JFrame frame = new JFrame("Cities Database");
		Container container = new Container();
		container.setLayout(new MigLayout());
		frame.setContentPane(container);
				
		container.add(controls());
		container.add(showTable());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private JPanel controls() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		
		Dimension button = new Dimension(500,100);
		Font buttonText = new Font("Ariel", Font.BOLD, 30);
		
		getAllCities.setPreferredSize(button);
		getAllCities.setFont(buttonText);
		fetchCertainCity.setPreferredSize(button);
		fetchCertainCity.setFont(buttonText);
		fetchCitiesBetween.setPreferredSize(button);
		fetchCitiesBetween.setFont(buttonText);
		addCity.setPreferredSize(button);
		addCity.setFont(buttonText);
		deleteCity.setPreferredSize(button);
		deleteCity.setFont(buttonText);
		updateCity.setPreferredSize(button);
		updateCity.setFont(buttonText);
		
		panel.add(addCity, "wrap");
		panel.add(getAllCities, "wrap");
		panel.add(fetchCertainCity, "wrap");
		panel.add(fetchCitiesBetween, "wrap");
		panel.add(deleteCity, "wrap");
		panel.add(updateCity, "wrap");
		return panel;
	}
	
	public void getAllCities(ActionListener act) {
		getAllCities.addActionListener(act);
	}
	
	public void getCertainCity(ActionListener act) {
		fetchCertainCity.addActionListener(act);
	}
	
	public void deleteACity(ActionListener act) {
		deleteCity.addActionListener(act);
	}
	
	public void updateACity(ActionListener act) {
		updateCity.addActionListener(act);
	}
	
	public void fetchCitiesBetween(ActionListener act) {
		fetchCitiesBetween.addActionListener(act);
	}
	
	private Component showTable() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(500, 600));
		panel.setLayout(new GridLayout(12,2));
		
		for ( int i = 0; i < details.length; i++ ) {
			details[i] = new JLabel();
			details[i].setSize(new Dimension(150, 130));
			details[i].setFont(new Font("Ariel", Font.BOLD, 30));
			switch (i) {
			case 0:
				details[i].setText("City");
				break;
			case 1:
				details[i].setText("Population");
				break;
			}
			panel.add(details[i]);
		}
		
		return panel;
	}

	public void clearTable() {
		for (int i = 2; i < details.length; i++ ) {
			details[i].setText("");
		}
	}
	
	public void showAllCities(Map<String, Integer> cities) {
		int pos = 2;
		
		for ( Map.Entry<String, Integer> entry: cities.entrySet()) {
			details[pos].setText(entry.getKey());
			pos++;
			details[pos].setText(entry.getValue().toString());
			pos++;
		}
		
	}

	public String findCity() {
		JLabel cityName = new JLabel("Enter city");
		cityName.setFont(new Font("Ariel", Font.BOLD, 30 ));
		String city = JOptionPane.showInputDialog(null, cityName);
		return city;
	}

	public void addACity(ActionListener act) {
		addCity.addActionListener(act);
	}
	
	public String addCity() {
		JLabel cityName = new JLabel("Enter cityname");
		JLabel addPopulation = new JLabel("Enter population");
		cityName.setFont(new Font("Ariel", Font.BOLD, 30 ));
		addPopulation.setFont(new Font("Ariel", Font.BOLD, 30 ));
		String city = JOptionPane.showInputDialog(null, cityName);
		String population = JOptionPane.showInputDialog(null, addPopulation);
		
		return city + " " + population;
	}

	public String updateCity() {
		JLabel cityName = new JLabel("Enter cityname");
		cityName.setFont(new Font("Ariel", Font.BOLD, 30 ));
		String city = JOptionPane.showInputDialog(null, cityName);
		
		JLabel newName = new JLabel("Enter new cityname for " + city);
		newName.setFont(new Font("Ariel", Font.BOLD, 30 ));
		String newCityName = JOptionPane.showInputDialog(null, newName);
		
		JLabel addPopulation = new JLabel("Enter population for " + newCityName);
		addPopulation.setFont(new Font("Ariel", Font.BOLD, 30 ));
		String population = JOptionPane.showInputDialog(null, addPopulation);
		
		return city + " " + newCityName + " " + population;
	}

	public String findCitiesMinMaxPopulation() {
		JLabel minPop = new JLabel("Minimum population");
		JLabel maxPop = new JLabel("Maximum population");
		minPop.setFont(new Font("Ariel", Font.BOLD, 30 ));
		maxPop.setFont(new Font("Ariel", Font.BOLD, 30 ));
		String min = JOptionPane.showInputDialog(null, minPop);
		String max = JOptionPane.showInputDialog(null, maxPop);
		return min + " " + max;
	}
}
