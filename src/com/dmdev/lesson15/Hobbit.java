package com.dmdev.lesson15;

public class Hobbit extends Hero {

    public Hobbit(String name, int damage, int health) {
        super(name, damage, health);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        System.out.println(getName() + " Затыкал спичкой " + enemy.getName() + "a");
        enemy.takeDamage(getDamage());
    }
}
