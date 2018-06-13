package com.auctions.system.portlet.user_profile.model;

import com.auctions.system.portlet.home_page.model.AuctionPresenter;

public class BoughtPresenter extends AuctionPresenter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long purchaseId;
	private String createDate;
	private String paymentStatusName; 
	
	public BoughtPresenter(long purchaseId,long id, String name, String imageName, long subjectPrice,
			String createDate, String paymentStatusName) {
		super(id, name, imageName, subjectPrice);
		this.purchaseId = purchaseId;
		this.createDate = createDate;
		this.paymentStatusName = paymentStatusName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getPaymentStatusName() {
		return paymentStatusName;
	}

	public void setPaymentStatusName(String paymentStatusName) {
		this.paymentStatusName = paymentStatusName;
	}

	public long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}

}
