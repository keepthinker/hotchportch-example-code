package com.keepthinker.example.designpattern.adapter;

import com.keepthinker.example.designpattern.adapter.data.DataGetter;
import com.keepthinker.example.designpattern.adapter.net.Networking;

public class DataAdaptor implements DataGetter{
	
	private Networking networking;
	
	public DataAdaptor(Networking networking){
		this.networking = networking;
	}
	
	@Override
	public String readString() {
		return networking.readStr();
	}

	@Override
	public byte readByte() {
		return networking.read();
	}

}
