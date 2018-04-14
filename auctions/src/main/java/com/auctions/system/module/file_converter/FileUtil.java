package com.auctions.system.module.file_converter;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Base64;

import com.auctions.system.module.Properties;

public class FileUtil {

	 public static boolean createImage(String imageData,String imageName){
		boolean success = false;
/*		byte[] data = Base64.getDecoder().decode((imageData));*/
		ByteArrayInputStream reader = new ByteArrayInputStream(
				Base64.getDecoder().decode((imageData)));
		FileOutputStream stream;
		byte[] buffer = new byte[256];
		try {
			
			stream = new FileOutputStream(Properties.getImagesPath() + imageName);
			while(reader.read(buffer) > 0){
				stream.write(buffer);
				if(reader.available() < 256){
					Arrays.fill(buffer, (byte)0);
				}
			}
		    stream.close();
		    stream = null;
		    reader = null;
		    
		    success = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public static boolean createVideo(final String videoData,final String videoName){
		boolean success = false;
		byte[] buffer = Base64.getDecoder().decode((videoData));
		FileOutputStream stream;
		try {
			
			stream = new FileOutputStream(Properties.getVideosPath() + videoName,true);
			stream.write(buffer);
		    stream.close();
		    stream = null;

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
