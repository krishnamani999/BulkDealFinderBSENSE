package customframework.controller;

import customframework.exception.FrameworkException;
import customframework.model.Model;

public interface Controller {
	public Model handleRequest(Model model) throws FrameworkException;
}
