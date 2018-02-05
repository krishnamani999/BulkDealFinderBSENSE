package modules.bulkdeal;

import java.lang.reflect.Method;

import customframework.controller.Controller;
import customframework.exception.FrameworkException;
import customframework.factory.ImplementationFactory;
import customframework.model.Model;

public class BulkDealController implements Controller{

	@Override
	public Model handleRequest(Model model) throws FrameworkException {
		
		BulkDealService serviceBean = ImplementationFactory.getBulkDealServiceBean();
		BulkDealModel requestModel = (BulkDealModel) model;
		try {
			Method method = serviceBean.getClass().getMethod(requestModel.getFunction(), Model.class);
			model = (Model) method.invoke(serviceBean, model);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
