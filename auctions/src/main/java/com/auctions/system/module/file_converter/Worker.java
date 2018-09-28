package com.auctions.system.module.file_converter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auctions.system.module.file_converter.converter.Converter;
import com.auctions.system.module.file_converter.listener.ProgressListener;
import com.auctions.system.module.file_converter.service.ConverterService;

@Component
public class Worker {

	private final int conversionComplete = 1000;
	private Map<Long,FileInfo> filesConvertingProgress = new HashMap<Long,FileInfo>();
	ExecutorService executor = Executors.newFixedThreadPool(10);
	
	@Autowired
	private ConverterService service;
	
	public void convertVideo(final long auctionId, final String videoName){
		executor.execute(new Runnable(){
			@Override
			public void run() {
				 filesConvertingProgress.put(auctionId, new FileInfo(0,videoName));
				 Converter.convertVideoFile(videoName,new ProgressListener(auctionId,getInstance()));
			}
		}); 
	}
	
	public FileInfo checkProgress(long auctionId){
		FileInfo progress = filesConvertingProgress.getOrDefault(auctionId, new FileInfo(-1));
		
		if(progress.getProgress() == conversionComplete) {
			service.createVideoReference(auctionId, progress.getName());
			filesConvertingProgress.remove(auctionId);
		}
			
		return progress;
	}

	public void updateValue(long auctionId, int value) {
		FileInfo info = filesConvertingProgress.get(auctionId);
		
		info.setProgress(value);
		filesConvertingProgress.put(auctionId, info);
	}
	
	public void remove(long auctionId){
		filesConvertingProgress.remove(auctionId);
	}
	
	private Worker getInstance(){
		return this;
	}

}
