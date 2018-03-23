package module.auction_type;

import hello.RequestForm;

public class QuickPurchase implements AuctionTypeStrategy{

	@Override
	public boolean checkConditions(RequestForm form) {
		
		return false;
	}


}
