package com.auctions.system.portlet.user_profile.model;

import java.sql.Timestamp;
import java.util.List;

public class Auction {

	private int id;
	private String name;
	private long serialNumber;
	private List<PaymentMethod> acceptedPaymentMethods;
	private Timestamp endDate;
	private AuctionType auctionType;
	private String subjectName;
	private String imageName;
	private String imageExt;
	private String description;
	private int subjectQuantity;
	
	public Auction(){
		
	}
	
	public Auction(int id, String name, long serialNumber, List<PaymentMethod> acceptedPaymentMethods,
			Timestamp endDate, AuctionType auctionType, String subjectName, String imageName, String imageExt,
			String description,int subjectQuantity) {
		super();
		this.id = id;
		this.name = name;
		this.serialNumber = serialNumber;
		this.acceptedPaymentMethods = acceptedPaymentMethods;
		this.endDate = endDate;
		this.auctionType = auctionType;
		this.subjectName = subjectName;
		this.imageName = imageName;
		this.imageExt = imageExt;
		this.description = description;
		this.subjectQuantity = subjectQuantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public List<PaymentMethod> getAcceptedPaymentMethods() {
		return acceptedPaymentMethods;
	}

	public void setAcceptedPaymentMethods(List<PaymentMethod> acceptedPaymentMethods) {
		this.acceptedPaymentMethods = acceptedPaymentMethods;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public AuctionType getAuctionType() {
		return auctionType;
	}

	public void setAuctionType(AuctionType auctionType) {
		this.auctionType = auctionType;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageExt() {
		return imageExt;
	}

	public void setImageExt(String imageExt) {
		this.imageExt = imageExt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSubjectQuantity() {
		return subjectQuantity;
	}

	public void setSubjectQuantity(int subjectQuantity) {
		this.subjectQuantity = subjectQuantity;
	}
	
	
	
	
	
	
	
	//private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	
}
