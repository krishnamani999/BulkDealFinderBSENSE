package customframework.factory;

import customframework.model.Model;

public class ModelFactory {
	public static Model getNewModel(String modelClass){
		Model model = null;
		try{
			Class<?> classObject = Class.forName(modelClass);
			model = (Model)classObject.newInstance();
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return model;
	}
}
