package com.auctions.system.module.file_converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import com.auctions.system.module.Properties;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.VideoAttributes;
import it.sauronsoftware.jave.VideoSize;

public class Converter {

	
	/*	private boolean createWebMVideoFile(String videoData, String videoName){
	
	try {
		File source = new File(Properties.getVideosPath() + videoName);
		File target = new File(Properties.getVideosPath() + "target123.mp4");
		
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec("libmp3lame");
		audio.setBitRate(new Integer(64000));
		audio.setChannels(new Integer(1));
		audio.setSamplingRate(new Integer(22050));
		
		VideoAttributes video = new VideoAttributes();
		video.setCodec("h264");
		video.setBitRate(new Integer(160000));
		video.setFrameRate(new Integer(15));
		video.setSize(new VideoSize(400, 300));
		
		EncodingAttributes attrs = new EncodingAttributes();
		
		attrs.setFormat("h264");
		attrs.setAudioAttributes(audio);
		attrs.setVideoAttributes(video);
		
		Encoder encoder = new Encoder();
		encoder.encode(source, target, attrs);

		byte[] bFile = Files.readAllBytes(target.toPath());
		FileOutputStream stream = new FileOutputStream(target);
	    stream.write(bFile);
	    stream.close();
		
		return true;
	} catch (IllegalArgumentException e) {
		e.printStackTrace();
	}  catch (EncoderException e) {
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return false;
}*/
	static boolean convertVideoFile(String videoData, String videoName){
		String targetFileName = videoName.split("\\.")[0] + ".ogg";
		try {
			File source = new File(Properties.getVideosPath() + videoName);
			File target = new File(Properties.getVideosPath() + targetFileName);
			
			AudioAttributes audio = new AudioAttributes();
			audio.setCodec("libvorbis");
			audio.setBitRate(new Integer(96000));
			audio.setChannels(new Integer(2));
			audio.setSamplingRate(new Integer(441000));
			
			VideoAttributes video = new VideoAttributes();
			video.setCodec("libtheora");
/*			video.setTag("OGG");*/
			video.setBitRate(new Integer(819200));
			video.setFrameRate(new Integer(20));
			video.setSize(new VideoSize(640, 360));
			
			EncodingAttributes attrs = new EncodingAttributes();
			attrs.setFormat("ogg");
			attrs.setAudioAttributes(audio);
			attrs.setVideoAttributes(video);
			
			Encoder encoder = new Encoder();
			System.out.println("ENCODERS");
			encoder.encode(source, target, attrs);
			byte[] bFile = Files.readAllBytes(target.toPath());

			FileOutputStream stream = new FileOutputStream(target);
		    stream.write(bFile);
		    stream.close();
			stream = null;
			bFile = null;
			
			return true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}  catch (EncoderException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
