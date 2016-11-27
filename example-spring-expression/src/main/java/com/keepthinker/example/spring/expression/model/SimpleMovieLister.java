package com.keepthinker.example.spring.expression.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SimpleMovieLister {
	private MovieFinder movieFinder;
	private String defaultLocale;
	@Autowired
	public void configure(@Qualifier("defaultMovieFinder") MovieFinder movieFinder,
			@Value("#{ systemProperties['user.country'] }") String defaultLocale) {
		this.movieFinder = movieFinder;
		this.defaultLocale = defaultLocale;
	}
	public String getDefaultLocale() {
		return defaultLocale;
	}
	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
	}
	public MovieFinder getMovieFinder() {
		return movieFinder;
	}
	public void setMovieFinder(MovieFinder movieFinder) {
		this.movieFinder = movieFinder;
	}
	
}
