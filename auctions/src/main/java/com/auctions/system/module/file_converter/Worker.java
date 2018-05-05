package com.auctions.system.module.file_converter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.auctions.system.portlet.user_profile.model.Auction;

public class Worker {

	ExecutorService executor = Executors.newFixedThreadPool(2);
	
	public void createFiles(final Auction auction, final boolean hasVideo){
		executor.execute(new Runnable(){
			@Override
			public void run() {
				
				if(hasVideo) FileUtil.createVideo(auction.getVideoData(),auction.getVideoName());
				FileUtil.createImage(auction.getImageData(),auction.getImageName());
			}
		}); 
	}
	
	public void convertVideoToWebm(final String videoName){
		executor.execute(new Runnable(){
			@Override
			public void run() {
				
				 Converter.convertVideoFile(videoName);
			}
		}); 
	}
}
