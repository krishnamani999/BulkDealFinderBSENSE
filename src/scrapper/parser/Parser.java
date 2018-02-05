package scrapper.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import scrapper.beans.DealBean;
import scrapper.util.Constants;
import scrapper.util.StringUtils;

public class Parser {
	public static List<DealBean> getDealsList(String html, String exchangeName){
		List<DealBean> dealsList = new ArrayList<>();
		Document doc = Jsoup.parse(html);
		Elements dealSelectorElements = doc.getElementsByClass("client");
		for(Element e: dealSelectorElements){
			Element dealElement = e.parent();
			DealBean dealBean = getBeanFromElement(dealElement, exchangeName);
			dealsList.add(dealBean);
			//System.out.println(e.text()+" "+e.firstElementSibling().text());
		}
		return dealsList;
	}
	
	private static DealBean getBeanFromElement(Element element, String exchangeName){
				
		String securityName = element.child(0).text();
		String investorName = element.child(1).text();
		double price = Double.parseDouble(StringUtils.trimCommas(element.child(2).text()));
		long quantity = Long.parseLong(StringUtils.trimCommas(element.child(3).text()));
		String transactionType = element.child(4).text();
		
		DealBean dealBean = new DealBean(securityName, investorName, price, quantity, transactionType, exchangeName);
		
		return dealBean;
	}
	
	public static List<String> getInvListFromResponse(String input){
		List<String> invList = new ArrayList<>();
		try {
			JSONObject jsonObject = new JSONObject("{\"response\":"+input+"}");
			JSONArray jsonArray = jsonObject.getJSONArray("response");
			int length = jsonArray.length();
			for(int i=0; i<length; i++){
				invList.add(jsonArray.getString(i));
			}
		} catch (JSONException e) {
			System.out.println("Error Occured: " + e.getMessage());
		}
		return invList;
	}
}
