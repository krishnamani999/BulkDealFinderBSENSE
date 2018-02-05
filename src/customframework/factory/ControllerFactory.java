package customframework.factory;

import java.util.HashMap;
import java.util.Map;

import customframework.controller.Controller;

public class ControllerFactory {
	
	private static Map<String, Controller> controllerMap = new HashMap();
	
	public static Controller getController(String controllerClass){
		Controller controller = controllerMap.get(controllerClass);
		if(controller == null){
			try{
				Class<?> classObject = Class.forName(controllerClass);
				controller = (Controller) classObject.newInstance();
				controllerMap.put(controllerClass, controller);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return controller;
	}
}
