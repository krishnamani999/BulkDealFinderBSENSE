package modules.bulkdeal;

import customframework.model.Model;

public class BulkDealServiceImpl implements BulkDealService{

	private BulkDealDAO bulkDealDAO = new BulkDealDAO();
	
	@Override
	public Model openBulkDealPage(Model model) {
		BulkDealModel responseModel = new BulkDealModel();
		
		return responseModel;
	}
	
}
