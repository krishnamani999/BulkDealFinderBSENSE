package scrapper.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
	public static String getResponseFromURL(URL url, boolean isProxyRequired) throws IOException{
		
		HttpURLConnection conn;
		if(isProxyRequired){
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Constants.proxyURL, Constants.proxyPort));
			conn = (HttpURLConnection) url.openConnection(proxy);
		} else{
			conn = (HttpURLConnection) url.openConnection();
		}		
		InputStream in = null;
		Scanner scanner = null;
		try{
			in = conn.getInputStream();
			
			scanner = new Scanner(in);
			scanner.useDelimiter("\\A");
			
			boolean hasInput = scanner.hasNext();
			if(hasInput){
				return scanner.next();
			} else{
				return null;
			}
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		} finally{
			conn.disconnect();
			in.close();
			scanner.close();
		}
	}
}
