package test;

import javax.servlet.http.HttpServletRequest;

import customframework.controller.Controller;
import customframework.model.Model;

public class TestController implements Controller{
	public Model handleRequest(Model model){
		Model responseModel = new Model(){
			String message = "Hello World!";

			@Override
			public void populate(HttpServletRequest request) {
				
				
			}
			
			
		};
		return responseModel;
	}
}
