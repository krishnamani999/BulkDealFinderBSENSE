package modules.bulkdeal;

import javax.servlet.http.HttpServletRequest;

import modules.common.CommonModel;

public class BulkDealModel extends CommonModel{

	private int a=10;
	
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	@Override
	public void populate(HttpServletRequest request) {
		super.populate(request);		
	}
	
}
