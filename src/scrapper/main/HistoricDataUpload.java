package scrapper.main;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import modules.common.DBManager;
import scrapper.beans.DealBean;
import scrapper.parser.Parser;
import scrapper.util.Constants;
import scrapper.util.DealUtils;
import scrapper.util.NetworkUtils;

public class HistoricDataUpload {
	public static void main(String args[]){
		boolean isProxyRequired = false;
			
		DateFormat dateFormat = new SimpleDateFormat("/yyyy/MM/dd");
		Date date = new Date();
		Calendar c = new GregorianCalendar(2017, Calendar.NOVEMBER, 11);
		
		MongoClient mongoClient = DBManager.getConnection();
		MongoDatabase mongoDatabase =  DBManager.getDatabase(mongoClient);
		MongoCollection<Document> collection = mongoDatabase.getCollection("bulkDealsCollection");
		
		for(int j=0; j<90; j++){
			c.add(Calendar.DATE, 1);
			date = c.getTime();
			String currentDate = dateFormat.format(date);		
			
			try {
				for(int i=0; i<Constants.exchangesList.length; i++){
					StringBuilder urlBuilder = new StringBuilder(Constants.url);
					String exchangeName = Constants.exchangesList[i];
					urlBuilder.append(exchangeName).append(currentDate);
					URL url = new URL(urlBuilder.toString());
					System.out.println("Bulk Deals in " + exchangeName + " on: " + currentDate);
					
					//Get the response from the url
					String response = NetworkUtils.getResponseFromURL(url, isProxyRequired);
					if(response.indexOf("Invalid path URL.") > -1){
						//System.out.println("Deals not yet declared for the given date: " + currentDate);
						//System.out.println("---------------------------------------------------------------------------------");
						continue;
					}
				
					//Get deals by parsing the response
					List<DealBean> dealsList = Parser.getDealsList(response, exchangeName);
					
					//Print the deals
					System.out.println("Deals on: " + currentDate + " : " + dealsList.size());
					persistData(dealsList, collection);
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
		DBManager.closeConnection(mongoClient);
	}
	
	public static void persistData(List<DealBean> dealsList, MongoCollection<Document> collection){
		
		List<Document> documentList = convertBeanListToDocumentList(dealsList);
		
		collection.insertMany(documentList);
		System.out.println("inserted successfully");
	}
	
	public static List<Document> convertBeanListToDocumentList(List<DealBean> dealsList){
		List<Document> documentList = new ArrayList<>();
		
		for(DealBean deal: dealsList){
			Document document = new Document("exchangeName", deal.getExchangeName())
									.append("securityName", deal.getSecurityName())
									.append("investorName", deal.getInvestorName())
									.append("transactionType", deal.getTransactionType())
									.append("quantity", deal.getQuantity())
									.append("price", deal.getPrice());
			documentList.add(document);
		}
		
		return documentList;
	}
}