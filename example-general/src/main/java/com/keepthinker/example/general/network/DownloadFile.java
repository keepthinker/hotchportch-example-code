package com.keepthinker.example.general.network;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.apache.commons.io.FileUtils;

public class DownloadFile {

	public static void main(String[] args)  throws Exception{
		URL website = new URL("http://oimagea5.ydstatic.com/image?id=-6365943639112727605&product=adpublish");
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream("pic.jpeg");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		FileUtils.copyURLToFile(website, new File("pic1.jpeg"));
	}

}
