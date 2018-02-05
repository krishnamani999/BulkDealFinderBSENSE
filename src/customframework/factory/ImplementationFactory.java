package customframework.factory;

import modules.bulkdeal.BulkDealService;
import modules.bulkdeal.BulkDealServiceImpl;
import test.TestService;
import test.TestServiceImpl;

public class ImplementationFactory {
	private static TestService testService = new TestServiceImpl();
	
	private static BulkDealService bulkDealService = new BulkDealServiceImpl();
	
	public static TestService getTestServiceBean(){
		return testService;
	}
	
	public static BulkDealService getBulkDealServiceBean(){
		return bulkDealService;
	}
}
