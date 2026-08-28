package com.dmdev.lesson15;

public class Orc extends Enemy {

    public Orc(String name, int damage, int health) {
        super(name, damage, health);
    }

    @Override
    public void attackHero(Hero hero) {
        System.out.println(getName() + " укусил " + hero.getName() + "a");
        hero.takeDamage(getDamage());
    }
}
