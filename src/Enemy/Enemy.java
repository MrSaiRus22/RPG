package Enemy;

import Hero.Hero;
import Runner.Mortal;
import java.util.Random;

public abstract class Enemy implements Mortal {

    private String name;
    private int damage;
    private int health;
    private int criticalChance;
    private Random random = new Random();

    public Enemy(String name, int damage, int health, int criticalChance) {
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.criticalChance = criticalChance;
    }

    public void takeDamage(int damage){
        if (isAlive() && health <= damage) {
            health = 0;
        } else {
            this.health -= Math.min(health, damage);
            System.out.println(name + " получил урон " + damage + ". Осталось " + health);
        }
    }

    public void attackHero(Hero hero) {

        // 1. Вывести сообщение об атаке (можно переопределить)

        String attackMessage = getAttackMessage(hero);
        System.out.println(attackMessage);
        int damage = getDamage();
        boolean isCritical = random.nextInt(100) < criticalChance;

        if (isCritical) {
            damage = (int) (damage * 2.5);
            System.out.println("💥 КРИТИЧЕСКИЙ УДАР! Мощный удар!");
        } else {
            // Обычный урон иногда меньше (70-100% от базового)
            int damageVariation = 70 + random.nextInt(31); // 70-100%
            damage = damage * damageVariation / 100;
        }
        hero.takeDamage(damage);
    }

    // Методы, которые можно переопределить в наследниках

    // 1. Сообщение об атаке
    protected String getAttackMessage(Hero hero) {
        return getName() + " атакует " + hero.getName() + "a";
    }


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

    public void setHealth(int health) {
        this.health = health;
    }
}
