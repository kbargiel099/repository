package com.auctions.system.module.file_converter;

import it.sauronsoftware.jave.EncoderProgressListener;
import it.sauronsoftware.jave.MultimediaInfo;

public class ProgressListener implements EncoderProgressListener{

	@Override
	public void message(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void progress(int permil) {
		System.out.println("Progress " + permil);
		
	}

	@Override
	public void sourceInfo(MultimediaInfo arg0) {
		// TODO Auto-generated method stub
		
	}

}
