package com.dmdev.lesson15;

public class Wolf extends Enemy {

    public Wolf(String name, int damage, int health) {
        super(name, damage, health);
    }

    @Override
    public void attackHero(Hero hero) {
        System.out.println(getName() + " укусил " + hero.getName() + "a");
        hero.takeDamage(getDamage());
    }
}
