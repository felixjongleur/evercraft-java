package org.dnd;

import org.dnd.util.Range;

public class Character {

	private String name;
	private Range hitPoints;
	private Armor armor;
	private Alignment alignment;
	private Abilities abilities;

	public Character(){
		this("");
	};
	
	public Character(String name) {
		this.setName(name);
		this.setArmor(new Armor());
		this.alignment = new Alignment(0);
		this.hitPoints = new Range(0, 5, Integer.MAX_VALUE);
		this.abilities = new Abilities(10);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public long getAlignment() {
		return alignment.getAlignment();
	}

	public void setAlignment(int alignment) {
		this.alignment.setAlignment(alignment);
	}

	public boolean isNuetral() {
		return alignment.isNeutral();
	}

	public boolean isEvil() {
		return alignment.isEvil();
	}

	public boolean isGood() {
		return alignment.isGood();
	}

	public int attack(int roll, Character questCharacter) {
		int damageDone = 0;
		if(doesAttackSucceed(getModifiedRoll(roll), questCharacter)){
			damageDone += getModifiedDamage(1);
			// Enforces that the damage is only doubled if it is a natural 20
			if(roll == 20) {
				damageDone++;
			}
		}
		questCharacter.decrementHP(damageDone);
		return damageDone;
	}

	private boolean doesAttackSucceed(int roll, Character questCharacter) {
		return getModifiedRoll(roll) >= questCharacter.getArmor().getDefense();
	}

	public int getModifiedDamage(int damage) {
		return Math.max(0, damage + abilities.getModifier(abilities.getStrength()));
	}
	
	public int getModifiedRoll(int roll) {
		int modifiedRoll = roll + abilities.getModifier(abilities.getStrength());
		
		if(modifiedRoll > 0) {
			modifiedRoll = Math.min(modifiedRoll, 20);
		} else {
			modifiedRoll = 0;
		}
		
		return modifiedRoll;
	}

	private void decrementHP(int hp) {
		hitPoints.setValue(hitPoints.getValue() - hp);
	}

	public Armor getArmor() {
		return armor;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	}

	public void setHP(int hitPoints) {
		this.hitPoints.setValue(hitPoints);
	}

	public int getHP() {
		return hitPoints.getValue();
	}
	
	public boolean isDead() {
		return getHP() <= 0;
	}

	public int getStrength() {
		return abilities.getStrength();
	}

	public int getDexterity() {
		return abilities.getDexterity();
	}

	public int getConstitution() {
		return abilities.getConstitution();
	}

	public int getWisdom() {
		return abilities.getWisdom();
	}

	public int getIntelligence() {
		return abilities.getIntelligence();
	}

	public int getCharisma() {
		return abilities.getCharisma();
	}

	public void setStrength(int strength) {
		abilities.setStrength(strength);		
	}
	
}
