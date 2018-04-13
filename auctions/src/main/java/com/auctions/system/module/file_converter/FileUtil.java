package com.auctions.system.module.file_converter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import com.auctions.system.module.Properties;

public class FileUtil {

	static boolean createImage(String imageData,String imageName){
		boolean success = false;
		byte[] data = Base64.getDecoder().decode((imageData));
		FileOutputStream stream;
		try {
			stream = new FileOutputStream(Properties.getImagesPath() + imageName);
		    stream.write(data);
		    stream.close();
		    stream = null;
		    data = null;
		    
		    success = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	static boolean createVideo(final String videoData,final String videoName){
		boolean success = false;
		byte[] data = Base64.getDecoder().decode((videoData));
		FileOutputStream stream;
		try {
			stream = new FileOutputStream(Properties.getVideosPath() + videoName);
		    stream.write(data);
		    stream.close();
		    stream = null;
		    data = null;
		    //success = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Converter.convertVideoFile(videoData, videoName);

		return success;
	}
	 
}
