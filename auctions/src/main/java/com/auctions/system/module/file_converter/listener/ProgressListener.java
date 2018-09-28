package com.auctions.system.module.file_converter.listener;

import com.auctions.system.module.file_converter.Worker;

import it.sauronsoftware.jave.EncoderProgressListener;
import it.sauronsoftware.jave.MultimediaInfo;

public class ProgressListener implements EncoderProgressListener{

	private Worker worker;
	private long auctionId;
	
	public ProgressListener(long auctionId,Worker worker){
		this.auctionId = auctionId;
		this.worker = worker;
	}
	
	@Override
	public void progress(int permil) {
		worker.updateValue(auctionId, permil);
	}

	@Override
	public void message(String arg0) {}
	
	@Override
	public void sourceInfo(MultimediaInfo arg0) {}

}
