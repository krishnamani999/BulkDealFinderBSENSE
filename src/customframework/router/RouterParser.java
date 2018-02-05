package customframework.router;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RouterParser {
	Map<String, RouterRecord> routerMap = new HashMap<>();
	public void parse(){
		
		InputStream is = getClass().getResourceAsStream("RouterConfig.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder builder = new StringBuilder();
		try{
			String line = br.readLine();
			while(line != null){
				builder.append(line);
				line = br.readLine();
			}
		} catch(Exception e){
			
		} finally{
			try{br.close(); is.close();} catch(Exception e){}
		}
		String documentString = builder.toString();
		Document doc = Jsoup.parse(documentString);
		Elements elements = doc.getElementsByTag("record");
		for(Element e: elements){
			String controllerName = e.attr("controller");
			String controllerClass = e.attr("class");
			String functionName = e.attr("function");
			String successJsp = e.attr("successJsp");
			String errorJsp = e.attr("errorJsp");
			String modelClass = e.attr("modelClass");
			
			RouterRecord record = new RouterRecord();
			record.setControllerName(controllerName);
			record.setFunction(functionName);
			record.setSuccessJsp(successJsp);
			record.setErrorJsp(errorJsp);
			record.setControllerClass(controllerClass);
			record.setModelClass(modelClass);
			
			routerMap.put(controllerName+"_"+functionName, record);
		}
	}
	
	public Map<String, RouterRecord> getRouterMap(){
		return routerMap;
	}
}