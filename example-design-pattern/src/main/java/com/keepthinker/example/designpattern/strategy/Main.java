package com.keepthinker.example.designpattern.strategy;

public class Main {

	public static void main(String[] args) {
		
		System.out.println(" ----------------- ");
		Battle battle = new Battle(2251, 1500);
		battle.operation(new ActiveStrategy());
		battle.operation(new ConservativeStrategy());

		System.out.println(" ----------------- ");
		battle.setArmyStrength(2250);
		battle.setEnermyStrength(1500);
		battle.operation(new ActiveStrategy());
		battle.operation(new ConservativeStrategy());
		
		System.out.println(" ----------------- ");
		battle.setArmyStrength(1200);
		battle.setEnermyStrength(1500);
		battle.operation(new ActiveStrategy());
		battle.operation(new ConservativeStrategy());

		System.out.println(" ----------------- ");
		battle.setArmyStrength(1001);
		battle.setEnermyStrength(1500);
		battle.operation(new ActiveStrategy());
		battle.operation(new ConservativeStrategy());
		
		System.out.println(" ----------------- ");
		battle.setArmyStrength(1000);
		battle.setEnermyStrength(1500);
		battle.operation(new ActiveStrategy());
		battle.operation(new ConservativeStrategy());


	}

}

class ActiveStrategy implements Strategy{

	@Override
	public boolean shouldFight(int enermyStrength, int armyStrength) {
		if(enermyStrength < armyStrength * 1.5) {
			return true;
		}
		return false;
	}
}

class ConservativeStrategy implements Strategy{

	@Override
	public boolean shouldFight(int enermyStrength, int armyStrength) {
		if(enermyStrength * 1.5 < armyStrength) {
			return true;
		}
		return false;
	}
}