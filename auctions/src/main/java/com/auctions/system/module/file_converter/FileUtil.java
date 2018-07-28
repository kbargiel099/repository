package com.auctions.system.module.file_converter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class FileUtil {
	
	public static boolean create(final String data,final String name){
		boolean success = false;
		byte[] buffer = Base64.getDecoder().decode(data);
		FileOutputStream stream;
		try {
			
			stream = new FileOutputStream(name,true);
			stream.write(buffer);
		    stream.close();
		    stream = null;

		    success = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return success;
	}
	
	public static boolean create(final byte[] data,final String name){
		boolean success = false;
		FileOutputStream stream;
		try {
			
			stream = new FileOutputStream(name,true);
			stream.write(data);
		    stream.close();
		    stream = null;

		    success = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return success;
	}
	 
}
