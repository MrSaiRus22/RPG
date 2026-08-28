package com.dmdev.lesson15;

public class Warrior extends Hero{

    public Warrior(String name, int damage, int health) {
        super(name, damage, health);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        System.out.println(getName() + " cокрушил своим мечом " + enemy.getName() + "a");
        enemy.takeDamage(getDamage());
    }
}
