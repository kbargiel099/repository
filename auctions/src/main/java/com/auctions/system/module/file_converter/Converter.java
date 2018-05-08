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

	
/*	public static boolean createWebMVideoFile(String videoName){
	
		String targetFileName = videoName.split("\\.")[0] + ".mp4";
		try {
			File source = new File(Properties.getVideosPath() + videoName);
			File target = new File(Properties.getVideosPath() + targetFileName);
			
			AudioAttributes audio = new AudioAttributes();
			audio.setCodec("libfaac");
			//audio.setCodec(AudioAttributes.DIRECT_STREAM_COPY);
			audio.setBitRate(new Integer(96000));
			audio.setChannels(new Integer(2));
			audio.setSamplingRate(new Integer(44100));
		
			VideoAttributes video = new VideoAttributes();
			video.setCodec("libx264");
			
			video.setBitRate(new Integer(640000));
			video.setFrameRate(new Integer(25));
			video.setSize(new VideoSize(640, 480));
			
			EncodingAttributes attrs = new EncodingAttributes();
			
			attrs.setFormat("mp4");
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
	
	public static boolean convertVideoFile(String videoName, ProgressListener listener){
		
		String targetFileName = videoName.split("\\.")[0] + ".ogg";
		try {
			File source = new File(Properties.getVideosPath() + videoName);
			File target = new File(Properties.getVideosPath() + targetFileName);
			
			AudioAttributes audio = new AudioAttributes();
			audio.setCodec("vorbis");
			//audio.setCodec("libvorbis");
			audio.setBitRate(new Integer(128000));
			audio.setChannels(new Integer(2));
			audio.setSamplingRate(new Integer(44100));
			
			VideoAttributes video = new VideoAttributes();
			video.setCodec("libtheora");
			video.setTag("OGG");
			video.setBitRate(new Integer(819200));
			video.setFrameRate(new Integer(25));
			video.setSize(new VideoSize(1280, 720));
			
			EncodingAttributes attrs = new EncodingAttributes();
			attrs.setFormat("ogg");
			attrs.setAudioAttributes(audio);
			attrs.setVideoAttributes(video);
			
			Encoder encoder = new Encoder();
			//for(String s : encoder.getAudioEncoders()){
			//	System.out.println(s);
			//}
			//System.out.println("ENCODERS");
			encoder.encode(source, target, attrs, listener);
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
