package com.dmdev.lesson15;

import java.util.Random;
import java.util.Random;

public class Mage extends Hero {
    private Random random = new Random();
    private int mana;
    private int missChance = 20; // 10% промах
    private int criticalChance = 25; // 25% крит
    private int dodgeChance = 20;
    private int enemyDodgeChance = 35; // 20% уклонение врага

    public Mage(String name, int damage, int health, int mana) {
        super(name, damage, health);
        this.mana = mana;
    }



    @Override
    public void normalAttack(Enemy enemy) {
        mana += 30;
        System.out.println("⚔️ " + getName() + " копит магическую энергию для удара по " + enemy.getName() + "!");
        // 1. Проверка на промах героя
        if (random.nextInt(100) < missChance) {
            System.out.println("😅 " + getName() + " поскользнулся и промахнулся!");
            return;
        }

        // 2. Проверка на уклонение врага
        if (random.nextInt(100) < enemyDodgeChance) {
            System.out.println("\uD83D\uDCA8 " + enemy.getName() + " увернулся от удара!");
            return;
        }
        System.out.println("🧙 " + getName() + " использует магическую стрелу!");
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
        System.out.println("Урон: " + damage + " (Мана: " + mana + ")");
    }

    @Override
    public void strongAttack(Enemy enemy) {
        if (mana >= 30) {
            mana -= 30;
            System.out.println("⚔️ " + getName() + " копит магическую энергию для удара по " + enemy.getName() + "!");
            System.out.println("🔥 " + getName() + " использует Огненный Шар!");
            // 1. Проверка на промах героя
            if (random.nextInt(100) < missChance) {
                System.out.println("😅 " + getName() + " промахнулся!");
                return;
            }

            // 2. Проверка на уклонение врага
            if (random.nextInt(100) < enemyDodgeChance) {
                System.out.println("\uD83D\uDCA8 " + enemy.getName() + " увернулся от удара!");
                return;
            }

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
            System.out.println("Урон: " + damage + " (Мана: " + mana + ")");
        } else {
            System.out.println("❌ Недостаточно маны! Выполняется обычная атака.");
            normalAttack(enemy);
        }
    }

    @Override
    public void specialAttack(Enemy enemy) {
        if (mana >= 50) {
            mana -= 50;
            System.out.println("⚔️ " + getName() + " копит магическую энергию для удара по " + enemy.getName() + "!");
            // 1. Проверка на промах героя
            if (random.nextInt(100) < missChance) {
                System.out.println("😅 " + getName() + " поскользнулся и промахнулся!");
                return;
            }

            // 2. Проверка на уклонение врага
            if (random.nextInt(100) < enemyDodgeChance) {
                System.out.println("\uD83D\uDCA8 " + enemy.getName() + " увернулся от удара!");
                return;
            }
            System.out.println("⚡ " + getName() + " использует Первородный Всплеск — магия в её чистом, диком и концентрированном виде!");
            int damage = getDamage() * 5;
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
            System.out.println("Урон: " + damage + " (Мана: " + mana + ")");
        } else {
            System.out.println("❌ Недостаточно маны! Выполняется сильная атака.");
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
    public void attackEnemy(Enemy enemy) {
        System.out.println(getName() + " испепелил заклинанием " + enemy.getName() + "a");
        enemy.takeDamage(getDamage());
    }

    public int getMana() {
        return mana;
    }

}
