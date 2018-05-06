package com.auctions.system.module.file_converter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.auctions.system.portlet.user_profile.model.Auction;

public class Worker {

	private int conversionComplete = 1000;
	private Map<Long,FileInfo> filesConvertingProgress = new HashMap<Long,FileInfo>();
	ExecutorService executor = Executors.newFixedThreadPool(2);
	
	public void createImages(final Auction auction){
		executor.execute(new Runnable(){
			@Override
			public void run() {
				FileUtil.createImage(auction.getImageData(),auction.getImageName());
			}
		}); 
	}
	
	public void convertVideoToWebm(final long userId, final String videoName){
		final Worker w = this;
		executor.execute(new Runnable(){
			@Override
			public void run() {
				 filesConvertingProgress.put(userId, new FileInfo(0,videoName));
				 Converter.convertVideoFile(videoName,new ProgressListener(userId,w));
			}
		}); 
	}
	
	public FileInfo checkProgress(long userId){
		FileInfo progress = filesConvertingProgress.getOrDefault(userId, new FileInfo(-1));
		if(progress.getProgress() == conversionComplete) filesConvertingProgress.remove(userId);
			
		return progress;
	}

	public void updateValue(long userId, int value) {
		FileInfo info = filesConvertingProgress.get(userId);
		info.setProgress(value);
		filesConvertingProgress.put(userId, info);
	}
	
	public void remove(long userId){
		filesConvertingProgress.remove(userId);
	}

}
