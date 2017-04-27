package com.keepthinker.example.spring.expression.context;

import com.keepthinker.example.spring.expression.model.Inventor;
import com.keepthinker.example.spring.expression.model.MovieFinder;
import com.keepthinker.example.spring.expression.model.PlaceOfBirth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotatedContext {
	
	@Value("1")
	private int id;
	
	@Value("Jenny")
	private String name;
	
	@Bean
	public MovieFinder defaultMovieFinder(){
		return new MovieFinder(id, name);
	}
	
	@Bean
	public PlaceOfBirth defaultPlaceOfBirth(){
		return new PlaceOfBirth("Shen Zhen", "China");
	}
	
	@Bean
	public Inventor defaultInventor(){
		return new Inventor();
	}
}
