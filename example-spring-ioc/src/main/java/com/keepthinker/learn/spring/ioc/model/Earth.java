package com.keepthinker.example.spring.ioc.model;

import java.util.List;

public class Earth {
	private static int counter = 0;
	private int id;
	private double quality;
	private List<Animal> animals;
	private Animal animal;
	
	public Earth(){
		counter++;
		id = counter;
	}

	public double getQuality() {
		return quality;
	}

	public void setQuality(double quality) {
		this.quality = quality;
	}

	public int getId() {
		return id;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}
	
	
}
