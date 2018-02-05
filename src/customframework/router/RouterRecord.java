package customframework.router;

import customframework.controller.Controller;
import customframework.factory.ControllerFactory;
import customframework.factory.ModelFactory;
import customframework.model.Model;

public class RouterRecord {
	private String controllerName;
	
	private String controllerClass;
	
	private String function;
	
	private String successJsp;
	
	private String errorJsp;
	
	private String modelClass;

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}
	
	public String getControllerClass() {
		return controllerClass;
	}

	public void setControllerClass(String controllerClass) {
		this.controllerClass = controllerClass;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getSuccessJsp() {
		return successJsp;
	}

	public void setSuccessJsp(String successJsp) {
		this.successJsp = successJsp;
	}

	public String getErrorJsp() {
		return errorJsp;
	}

	public void setErrorJsp(String errorJsp) {
		this.errorJsp = errorJsp;
	}
	
	public void setController(){
		
	}
	
	public Controller getController(){
		return ControllerFactory.getController(this.controllerClass);
	}
	
	public String getModelClass() {
		return modelClass;
	}

	public void setModelClass(String modelClass) {
		this.modelClass = modelClass;
	}

	public Model getModel(){
		return ModelFactory.getNewModel(modelClass);
	}
}
