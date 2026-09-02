package Hero;

import Enemy.Enemy;

public class Archer extends Hero {

    private int arrows;

    private int missChance = 10; // 10% промах
    private int criticalChance = 35; // 25% крит
    private int dodgeChance = 50;
    private int enemyDodgeChance = 20; // 20% уклонение врага

    public Archer(String name, int damage, int health, int arrows) {
        super(name, damage, health);
        this.arrows = arrows;
    }

    @Override
    public void normalAttack(Enemy enemy) {
        if (arrows > 0) {
            arrows--;
            System.out.println("⚔️ " + getName() + " Готовится выстрелить по " + enemy.getName() + "!");
            missEvasionCheck(enemy);
            System.out.println("🏹 " + getName() + " делает обычный выстрел!");
            int damage = getDamage();
            isCriticalDamage(enemy);
            System.out.println("Урон: " + damage + " (Стрелы: " + arrows + ")");
        } else {
            System.out.println("❌ Нет стрел! " + getName() + " бьет луком!");
            int damage = getDamage() / 2;
            enemy.takeDamage(damage);
            System.out.println("Урон: " + damage);
        }
    }

    @Override
    public void strongAttack(Enemy enemy) {
        if (arrows >= 3) {
            arrows -= 3;
            missEvasionCheck(enemy);
            System.out.println("🎯 " + getName() + " использует Тройной Выстрел!");
            int damage = getDamage() * 2;
            isCriticalDamage(enemy);
            System.out.println("Урон: " + damage + " (Стрелы: " + arrows + ")");
        } else {
            System.out.println("❌ Нет стрел! " + getName() + " бьет луком!");
            int damage = getDamage() / 2;
            enemy.takeDamage(damage);
            System.out.println("Урон: " + damage);
        }
    }

    @Override
    public void specialAttack(Enemy enemy) {
        if (arrows >= 5) {
            arrows -= 5;
            System.out.println("🌪️ " + getName() + " использует Град Стрел!");
            int damage = getDamage() * 3;
            isCriticalDamage(enemy);
            System.out.println("Урон: " + damage + " (Стрелы: " + arrows + ")");
        } else {
            System.out.println("❌ Нет стрел! " + getName() + " бьет луком!");
            int damage = getDamage() / 2;
            enemy.takeDamage(damage);
            System.out.println("Урон: " + damage);
        }
    }


    @Override
    public void attackEnemy(Enemy enemy) {
        System.out.println(getName() + " обрушил град стрел на " + enemy.getName() + "a");
        enemy.takeDamage(getDamage());
    }

    public int getArrows() {
        return arrows;
    }
}
