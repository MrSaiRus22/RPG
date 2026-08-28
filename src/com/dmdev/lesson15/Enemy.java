package com.dmdev.lesson15;

public abstract class Enemy implements Mortal {

    private String name;
    private int damage;
    private int health;

    public Enemy(String name, int damage, int health) {
        this.name = name;
        this.damage = damage;
        this.health = health;
    }

    public void takeDamage(int damage){
        if (isAlive() && health <= damage) {
            health = 0;
        } else {
            this.health -= Math.min(health, damage);
            System.out.println(name + " получил урон " + damage + ". Осталось " + health);
        }
    }

    public abstract void attackHero(Hero hero);

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }
}
