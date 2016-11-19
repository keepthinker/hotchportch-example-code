package com.keepthinker.example.general.xml.dynamictable;

import java.util.Collection;

public interface FileParser {
	Collection<Database> parse(String relativePath);
}
