package com.keepthinker.example.general.rmi;

import java.io.Serializable;

public class TransferObject implements Serializable{
	private static final long serialVersionUID = -5839980247964756727L;
	private int id;
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
