package Hero;

import Enemy.Enemy;
import Runner.Mortal;
import java.util.Random;

public abstract class Hero implements Mortal {

    private String name;
    private int basedamage;
    private int health;
    private int missChance;
    private int enemyDodgeChance;
    private int criticalChance;
    private int dodgeChance;
    private Random random = new Random();

    public Hero(String name, int basedamage, int health) {
        this.name = name;
        this.basedamage = basedamage;
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

    public abstract void attackEnemy(Enemy enemy);
    public abstract void normalAttack(Enemy enemy);
    public abstract void strongAttack(Enemy enemy);
    public abstract void specialAttack(Enemy enemy);
    public void missEvasionCheck(Enemy enemy){
        // 1. Проверка на промах героя
        if (random.nextInt(100) < missChance) {
            System.out.println("😅 " + getName() + " поскользнулся и промахнулся!");
            return;
        }
        // 2. Проверка на уклонение врага
        if (random.nextInt(100) < enemyDodgeChance) {
            System.out.println("🌀 " + enemy.getName() + " увернулся от удара!");
            return;
        }
    }
    public void isCriticalDamage(Enemy enemy){
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
        System.out.println("Урон: " + damage);
        enemy.takeDamage(damage);
    }
    // Дополнительный метод: Уклонение
    public boolean tryDodge() {
        int currentDodge =  dodgeChance;
        if (Math.random() * 100 < currentDodge) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return basedamage;
    }

    public int getHealth() {
        return health;
    }

}
