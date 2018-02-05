package scrapper.main;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import scrapper.beans.DealBean;
import scrapper.parser.Parser;
import scrapper.util.Constants;
import scrapper.util.DealUtils;
import scrapper.util.NetworkUtils;

public class Main {
	public static void main(String args[]){
		
		boolean isProxyRequired = false;
		
		DateFormat dateFormat = new SimpleDateFormat("/yyyy/MM/dd");
		Date date = new Date();
		date = new GregorianCalendar(2017, Calendar.DECEMBER, 19).getTime();
		String currentDate = dateFormat.format(date);		
		
		try {
			//Get investors list from api
			StringBuilder invUrlBuilder = new StringBuilder(Constants.baseURL);
			invUrlBuilder.append("api/invList");
			URL invListApiUrl = new URL(invUrlBuilder.toString());
			String invListResponse = NetworkUtils.getResponseFromURL(invListApiUrl, isProxyRequired);
			//System.out.println(invListResponse);
			List<String> invList = Parser.getInvListFromResponse(invListResponse);
			
			for(int i=0; i<Constants.exchangesList.length; i++){
				StringBuilder urlBuilder = new StringBuilder(Constants.url);
				String exchangeName = Constants.exchangesList[i];
				urlBuilder.append(exchangeName).append(currentDate);
				URL url = new URL(urlBuilder.toString());
				System.out.println("Bulk Deals in " + exchangeName);
				
				//Get the response from the url
				String response = NetworkUtils.getResponseFromURL(url, isProxyRequired);
				if(response.indexOf("Invalid path URL.") > -1){
					System.out.println("Deals not yet declared for the given date: " + currentDate);
					System.out.println("---------------------------------------------------------------------------------");
					continue;
				}
				
				//Get deals by parsing the response
				List<DealBean> dealsList = Parser.getDealsList(response, exchangeName);
				
				//Print the deals
				//System.out.println("Total number of deals: " + dealsList.size());
				/*for(DealBean deal: dealsList){
					System.out.print(deal.getSecurityName()+" ");
					System.out.print(deal.getInvestorName()+" ");
					System.out.print(deal.getPrice()+" ");
					System.out.print(deal.getQuantity()+" ");
					System.out.print(deal.getTransactionType() + "\n");
				}*/
				
				//Filter out important deals
				List<DealBean> importantDealsList = DealUtils.getImportantDeals(invList, dealsList);
				System.out.println("Total number of deals: " + dealsList.size() + "    Number of Important Deals: " + importantDealsList.size());
				for(DealBean deal: importantDealsList){
					System.out.print(deal.getSecurityName()+" ");
					System.out.print(deal.getInvestorName()+" ");
					System.out.print(deal.getPrice()+" ");
					System.out.print(deal.getQuantity()+" ");
					System.out.print(deal.getTransactionType() + "\n");
				}
				System.out.println("---------------------------------------------------------------------------------");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
