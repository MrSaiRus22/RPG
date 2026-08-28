package com.dmdev.lesson15;

public class Mage extends Hero{

    public Mage(String name, int damage, int health) {
        super(name, damage, health);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        System.out.println(getName() + " сковал языками пламени " + enemy.getName() + "a");
        enemy.takeDamage(getDamage());
    }

}
