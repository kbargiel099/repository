package com.auctions.system.module.file_converter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUtil {
	
	public static boolean create(final String data,final String name){
		byte[] buffer = Base64.getDecoder().decode(data);
		FileOutputStream stream;
		try {
			
			stream = new FileOutputStream(name,true);
			stream.write(buffer);
		    stream.close();
		    stream = null;

		    return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public static boolean create(CommonsMultipartFile file,final String path){
		FileOutputStream stream;
		try {
			
			stream = new FileOutputStream(path + file.getOriginalFilename(), true);
			stream.write(file.getBytes());
		    stream.close();
		    stream = null;

		    return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}
	 
}
