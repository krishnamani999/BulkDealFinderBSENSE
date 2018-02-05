package scrapper.util;

import java.util.ArrayList;
import java.util.List;

import scrapper.beans.DealBean;

public class DealUtils {
	public static List<DealBean> getImportantDeals(List<String> invList, List<DealBean> dealsList){
		List<DealBean> importantDealsList = new ArrayList<>();
		for(DealBean deal: dealsList){
			String dealInvName = deal.getInvestorName().toLowerCase();
			for(String investor: invList){
				String[] splitName = investor.split(" ");
				String s1 = splitName[0].toLowerCase();
				String s2 = splitName[1].toLowerCase();
				if(dealInvName.indexOf(s1) > -1 && dealInvName.indexOf(s2) > -1){
					importantDealsList.add(deal);
					break;
				}
			}
		}
		return importantDealsList;
	}
}
