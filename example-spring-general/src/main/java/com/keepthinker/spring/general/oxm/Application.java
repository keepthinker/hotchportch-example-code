package com.keepthinker.spring.general.oxm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import com.keepthinker.example.model.Actor;

public class Application {
	private	Actor actor = new Actor();
	private	Marshaller marshaller;
	private	Unmarshaller unmarshaller;
	private	static final String FILE_NAME = "actor.xml";
	
	public void setActor(Actor actor){
		this.actor = actor;
	}
	
	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}
	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}
	public void saveSettings() throws IOException {
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(FILE_NAME);
			this.marshaller.marshal(actor, new StreamResult(os));
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}
	public void loadSettings() throws IOException {
		FileInputStream is = null;
		try {
			is = new FileInputStream(FILE_NAME);
			this.actor = (Actor) this.unmarshaller.unmarshal(new StreamSource(is));
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}
}