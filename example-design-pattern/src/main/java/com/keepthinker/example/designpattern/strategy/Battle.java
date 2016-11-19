package com.keepthinker.example.designpattern.strategy;

public class Battle {
	
	private int armyStrength;
	private int enermyStrength;
	
	public Battle(int armyStrength, int enermyStrength) {
		this.armyStrength = armyStrength;
		this.enermyStrength = enermyStrength;
	}

	public int getArmyStrength() {
		return armyStrength;
	}

	public void setArmyStrength(int armyStrength) {
		this.armyStrength = armyStrength;
	}
	
	public int getEnermyStrength() {
		return enermyStrength;
	}



	public void setEnermyStrength(int enermyStrength) {
		this.enermyStrength = enermyStrength;
	}



	public void operation(Strategy strategy){
		if(strategy.shouldFight(enermyStrength, armyStrength)){
			System.out.println("attack");
		}else{
			System.out.println("retreat");
		}
	}
}


