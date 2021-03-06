package com.techleads.app;

public interface DBQueries {
	String INSERT_INTO_CUSTOMER = "INSERT INTO CUSTOMER ( CUST_NAME, ORDER_DTE, WEEKEND) VALUES(?,?, ?)";
	String INSERT_INTO_ADDRESS = "INSERT INTO ADDRESS (STREETNAME,  CITY,  STATE,  MOBILENUM,  PINCODE,  CUST_ID) VALUES (?,?,?,?,?,?)";
	String INSERT_INTO_ORDERS="INSERT INTO ORDERS  (ITEM_TYP, ITEM_NME, QUANTITY, PRICE, DISCOUNT, TOTALAMOUNT, CUST_ID)  VALUES (?,?,?,?,?,?,?)";
	String INSERT_INTO_TOT_AMT="INSERT INTO TOTAL_AMT(ITEM_TOTAL, SHIPPING_CHARGE, ORDER_TOTAL, CUST_ID)VALUES(?,?,?,?)";
	
	String SELECT_CUST_BY_ID="SELECT CUST_ID, CUST_NAME, ORDER_DTE, WEEKEND FROM CUSTOMER WHERE CUST_ID=?";
	String SELECT_ADDRS_BY_ID="SELECT ID_ADDRS, STREETNAME, CITY, STATE, MOBILENUM, PINCODE, CUST_ID FROM ADDRESS WHERE CUST_ID=?";
	String SELECT_ORDERS_BY_CUST_ID="SELECT ITEM_ID, ITEM_TYP, ITEM_NME, QUANTITY, PRICE, DISCOUNT, TOTALAMOUNT, CUST_ID FROM ORDERS WHERE CUST_ID=?";
	String SELECT_TOTAL_AMT_BY_CUST_ID="SELECT TMT_ID, ITEM_TOTAL, SHIPPING_CHARGE, ORDER_TOTAL, CUST_ID FROM TOTAL_AMT WHERE CUST_ID=?";
	
	String SELECT_CUSTMER_DETLS = "SELECT   \r\n"
			+ "A.CUST_ID, A.CUST_NAME, A.ORDER_DTE, A.WEEKEND,\r\n"
			+ "B.ID_ADDRS, B.STREETNAME, B.CITY, B.STATE, B.MOBILENUM, B.PINCODE, B.CUST_ID AS ADDR_CUST_ID,\r\n"
			+ "C.ITEM_ID, C.ITEM_TYP, C.ITEM_NME, C.QUANTITY, C.PRICE, C.DISCOUNT, C.TOTALAMOUNT, C.CUST_ID AS ORD_CUST_ID,\r\n"
			+ "D.TMT_ID, D.ITEM_TOTAL, D.SHIPPING_CHARGE, D.ORDER_TOTAL, D.CUST_ID AS TOT_CUST_ID\r\n"
			+ "FROM CUSTOMER A, ADDRESS B, ORDERS C, TOTAL_AMT D\r\n"
			+ "WHERE   A.CUST_ID =?\r\n"
			+ "and A.CUST_ID=B.CUST_ID\r\n"
			+ "and  A.CUST_ID=C.CUST_ID\r\n"
			+ "and A.CUST_ID=D.CUST_ID";
}
