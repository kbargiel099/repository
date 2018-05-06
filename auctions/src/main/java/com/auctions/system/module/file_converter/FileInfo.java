package com.auctions.system.module.file_converter;

public class FileInfo {

	private int progress;
	private String name;
	
	public FileInfo(int progress) {
		super();
		this.progress = progress;
	}
	
	public FileInfo(int progress, String name) {
		super();
		this.progress = progress;
		this.name = name;
	}
	
	public int getProgress() {
		return progress;
	}
	
	public void setProgress(int progress) {
		this.progress = progress;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
}
