package com.auctions.system.module.file_converter;

import it.sauronsoftware.jave.EncoderProgressListener;
import it.sauronsoftware.jave.MultimediaInfo;

public class ProgressListener implements EncoderProgressListener{

	Worker worker;
	long userId;
	
	public ProgressListener(long userId,Worker worker){
		this.userId = userId;
		this.worker = worker;
	}
	
	@Override
	public void progress(int permil) {
		//System.out.println("Progress " + permil);
		worker.updateValue(userId, permil);
	}

	@Override
	public void message(String arg0) {}
	
	@Override
	public void sourceInfo(MultimediaInfo arg0) {}

}
