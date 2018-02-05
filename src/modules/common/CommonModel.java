package modules.common;

import javax.servlet.http.HttpServletRequest;

import customframework.model.Model;

public class CommonModel implements Model{
	String function;

	public String getFunction() {
		return function;
	}


	public void setFunction(String function) {
		this.function = function;
	}

	@Override
	public void populate(HttpServletRequest request) {
		this.setFunction(request.getParameter("function"));
	}	
	
}
