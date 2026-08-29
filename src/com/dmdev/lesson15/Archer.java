package com.dmdev.lesson15;

import java.util.Random;

public class Archer extends Hero {
    private Random random = new Random();
    private int arrows;
    // Настройки боя
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
            // 1. Проверка на промах героя
            if (random.nextInt(100) < missChance) {
                System.out.println("😅 " + getName() + " Стрела прочертила изящную дугу, но враг остался невредим");
                return;
            }
            // 2. Проверка на уклонение врага
            if (random.nextInt(100) < enemyDodgeChance) {
                System.out.println("\uD83D\uDCA8 " + enemy.getName() + " увернулся от удара!");
                return;
            }

            System.out.println("🏹 " + getName() + " делает обычный выстрел!");
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
            enemy.takeDamage(damage);
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

        // 2. Проверка на уклонение врага
        if (random.nextInt(100) < enemyDodgeChance) {
            System.out.println("\uD83D\uDCA8 " + enemy.getName() + " увернулся от удара!");
            return;
        }

        if (arrows >= 3) {
            arrows -= 3;
            System.out.println("🎯 " + getName() + " использует Тройной Выстрел!");
            int damage = getDamage() * 2;
            boolean isCritical = random.nextInt(100) < criticalChance;

            if (isCritical) {
                damage = (int) (damage * 2.5);
                System.out.println("💥 КРИТИЧЕСКИЙ УДАР! Мощный удар!");
            } else {
                // Обычный урон иногда меньше (70-100% от базового)
                int damageVariation = 70 + random.nextInt(31); // 70-100%
                damage = damage * damageVariation / 100;
            }
            enemy.takeDamage(damage);
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
            boolean isCritical = random.nextInt(100) < criticalChance;

            if (isCritical) {
                damage = (int) (damage * 2.5);
                System.out.println("💥 КРИТИЧЕСКИЙ УДАР! Мощный удар!");
            } else {
                // Обычный урон иногда меньше (70-100% от базового)
                int damageVariation = 70 + random.nextInt(31); // 70-100%
                damage = damage * damageVariation / 100;
            }
            enemy.takeDamage(damage);
            System.out.println("Урон: " + damage + " (Стрелы: " + arrows + ")");
        } else {
            System.out.println("❌ Нет стрел! " + getName() + " бьет луком!");
            int damage = getDamage() / 2;
            enemy.takeDamage(damage);
            System.out.println("Урон: " + damage);
        }
    }


    // Дополнительный метод: Уклонение
    public boolean tryDodge() {
        int currentDodge = dodgeChance;
        if (Math.random() * 100 < currentDodge) {
            return true;
        }
        return false;
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
