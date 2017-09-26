package vo;

public class ProductVo {

	private String productCode;
	private String productName;
	private String productCompany;
	private int priceRetail;
	private int priceSale;
	private int productQnty;
	private String productImage1;
	private String productImage2;
	private String productMessage;
	private String productDate;
	private String category1CodeFk;
	

	public ProductVo(){}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCompany() {
		return productCompany;
	}
	public void setProductCompany(String productCompany) {
		this.productCompany = productCompany;
	}
	public int getPriceRetail() {
		return priceRetail;
	}
	public void setPriceRetail(int priceRetail) {
		this.priceRetail = priceRetail;
	}
	public int getPriceSale() {
		return priceSale;
	}
	public void setPriceSale(int priceSale) {
		this.priceSale = priceSale;
	}
	public int getProductQnty() {
		return productQnty;
	}
	public void setProductQnty(int productQnty) {
		this.productQnty = productQnty;
	}
	public String getProductImage1() {
		return productImage1;
	}
	public void setProductImage1(String productImage1) {
		this.productImage1 = productImage1;
	}
	public String getProductImage2() {
		return productImage2;
	}
	public void setProductImage2(String productImage2) {
		this.productImage2 = productImage2;
	}
	public String getProductMessage() {
		return productMessage;
	}
	public void setProductMessage(String productMessage) {
		this.productMessage = productMessage;
	}
	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	public String getCategory1CodeFk() {
		return category1CodeFk;
	}

	public void setCategory1CodeFk(String category1CodeFk) {
		this.category1CodeFk = category1CodeFk;
	}

}

/*CREATE TABLE PRODUCTS(
		 PRODUCT_CODE VARCHAR2(20),
		 CATEGORY1_CODE_FK VARCHAR2(20),
		 PRODUCT_NAME VARCHAR2(60),
		 PRODUCT_COMPANY VARCHAR2(30),
		 PRICE_RETAIL NUMBER(10),
		 PRICE_SALE NUMBER(10),
		 PRODUCT_QNTY NUMBER(10),
		 PRODUCT_IMAGE1 VARCHAR2(50),
		 PRODUCT_IMAGE2 VARCHAR2(50),
		 PRODUCT_MESSAGE VARCHAR2(1000),
		 PRODUCT_DATE DATE,
		 CONSTRAINT PRODUCTS_PRODUCT_CODE_PK PRIMARY KEY (PRODUCT_CODE)
		)*/