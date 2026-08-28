package com.dmdev.lesson15;
import java.util.Random;

public class Warrior extends Hero {
    private Random random = new Random();
    private int endurance;
    // Настройки боя
    private int missChance = 25;
    private int criticalChance = 10;
    private int dodgeChance = 30;
    private int enemyDodgeChance = 20;

    public Warrior(String name, int damage, int health, int endurance) {
        super(name, damage, health);
        this.endurance = endurance;
    }

    @Override
    public void normalAttack(Enemy enemy) {
        System.out.println("⚔️ " + getName() + " замахивается для удара по " + enemy.getName() + "!");
        endurance += 30;
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
        System.out.println("💥 " + getName() + " использует Обычный удар !");
        // 3. Расчет урона с возможным критическим ударом
        int damage = getDamage();
        boolean isCritical = random.nextInt(100) < criticalChance;

        if (isCritical) {
            damage = (int) (damage * 4.5);
            System.out.println("💥 КРИТИЧЕСКИЙ УДАР! Мощный удар!");
        } else {
            // Обычный урон иногда меньше (70-100% от базового)
            int damageVariation = 70 + random.nextInt(31); // 70-100%
            damage = damage * damageVariation / 100;
        }
        System.out.println("Урон: " + damage);
        enemy.takeDamage(damage);

        System.out.println("Урон: " + damage + " (Выносливаость: " + endurance + ")");
    }
    @Override
    public void strongAttack (Enemy enemy){
        // 1. Проверка на промах героя
        if (random.nextInt(100) < missChance) {
            System.out.println("😅 " + getName() + " поскользнулся и промахнулся!");
            return;
        }
        if (endurance >= 30) {
            endurance -= 30;
            System.out.println("💥 " + getName() + " использует Рассекающий удар !");
            int damage = getDamage() * 3;
            boolean isCritical = random.nextInt(100) < criticalChance;

            if (isCritical) {
                damage = (int) (damage * 4.5);
                System.out.println("💥 КРИТИЧЕСКИЙ УДАР! Мощный удар!");
            } else {
                // Обычный урон иногда меньше (70-100% от базового)
                int damageVariation = 70 + random.nextInt(31); // 70-100%
                damage = damage * damageVariation / 100;
            }
                enemy.takeDamage(damage);
                System.out.println("Урон: " + damage + " (Выносливости: " + endurance + ")");
            } else {
                System.out.println("❌ Недостаточно Выносливости! Выполняется обычная атака.");
                normalAttack(enemy);
            }
        }
    @Override
    public void specialAttack (Enemy enemy){
        // 1. Проверка на промах героя
        if (random.nextInt(100) < missChance) {
            System.out.println("😅 " + getName() + " поскользнулся и промахнулся!");
            return;
        }
        if (endurance >= 50) {
            endurance -= 50;
            System.out.println("🌀 " + getName() + " использует Вихревой Удар!");
            int damage = getDamage() * 4;
            boolean isCritical = random.nextInt(100) < criticalChance;

            if (isCritical) {
                damage = (int) (damage * 4.5);
                System.out.println("💥 КРИТИЧЕСКИЙ УДАР! Мощный удар!");
            } else {
                // Обычный урон иногда меньше (70-100% от базового)
                int damageVariation = 70 + random.nextInt(31); // 70-100%
                damage = damage * damageVariation / 100;
            }
                enemy.takeDamage(damage);
                System.out.println("Урон: " + damage + " (Выносливость: " + endurance + ")");
            } else {
                System.out.println("❌ Недостаточно Выносливости! Выполняется сильная атака.");
                strongAttack(enemy);
            }
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
        public void attackEnemy (Enemy enemy){
            System.out.println(getName() + " cокрушил своим мечом " + enemy.getName() + "a");
            enemy.takeDamage(getDamage());
        }

        public int getEndurance () {
            return endurance;
        }
    }
