package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.JSONObject;

import model.Data;
import view.Window;

public class Controller {
	
	private Window window = new Window();
	private Data data = new Data();
	
	public void init() {
		window.init();
		window.getAllCities(new GetAllCities());
		window.getCertainCity(new GetCertainCity());
		window.deleteACity(new DeleteCity());
		window.addACity(new AddCity());
		window.updateACity(new UpdateCity());
		window.fetchCitiesBetween(new GetCitiesBetweenMinMaxPopulation());
	}
	
	class UpdateCity implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cityDetails = window.updateCity();
			String[] details = cityDetails.split(" ");
			
			data.updateCity(details[0], details[1], details[2]);
			window.clearTable();
			window.showAllCities(data.getCities());
		}
		
	}
	
	class AddCity implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String addCity = window.addCity();
			String[] cityDetails = addCity.split(" ");
			
			String name = cityDetails[0];
			String population = cityDetails[1];
			
			JSONObject reqObj = prepareReqJsonObj(name, population);
			String reqString = reqObj.toString();
			
			String response = data.addCity(reqString);
			
			window.clearTable();
			window.showAllCities(data.getCities());
		}

		private JSONObject prepareReqJsonObj(String name, String population) {
			JSONObject obj = new JSONObject();
			obj.put("name", name);
			obj.put("population", population);
			return obj;
		}
		
	}
	
	class DeleteCity implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.clearTable();
			String string = window.findCity();
			data.deleteCity(string);
			window.showAllCities(data.getCities());
		}	
	}
	
	class GetAllCities implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.clearTable();
			window.showAllCities(data.getCities());
		}
	}
	
	class GetCertainCity implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e ) {
			window.clearTable();
			String string = window.findCity();
			window.showAllCities(data.getSpecificCity(string));
		}
	}
	
	class GetCitiesBetweenMinMaxPopulation implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.clearTable();
			String string = window.findCitiesMinMaxPopulation();
			String[] population = string.split(" ");
			window.showAllCities(data.getSpecificCity(population[0], population[1]));
		}
		
	}
}
