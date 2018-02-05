package customframework.model;

import javax.servlet.http.HttpServletRequest;

public interface Model {
	public void populate(HttpServletRequest request);
}
