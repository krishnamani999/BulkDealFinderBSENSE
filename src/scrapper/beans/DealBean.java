package scrapper.beans;

public class DealBean {
	String securityName;
	String investorName;
	double price;
	long quantity;
	String transactionType;
	String exchangeName;
	
	public DealBean(String securityName, String investorName, double price, long quantity, String transactionType, String exchangeName) {
		this.securityName = securityName;
		this.investorName = investorName;
		this.price = price;
		this.quantity = quantity;
		this.transactionType = transactionType;
		this.exchangeName = exchangeName;
	}
	public String getSecurityName() {
		return securityName;
	}
	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}
	public String getInvestorName() {
		return investorName;
	}
	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getExchangeName() {
		return exchangeName;
	}
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	
}
