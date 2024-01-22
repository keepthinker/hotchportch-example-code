package com.keepthinker.example.general.xml;

import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;

public class Main {
	public static void main(String[] args){
		new BreadthFirstPrint().print("mapper/object-table.xml");
	}
}
