package com.dmdev.lesson15;

import java.util.Scanner;

public class TraningGround {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Mage mage = new Mage("Гендальф", 70, 45);
        Archer archer = new Archer("Леголас", 23, 60);
        Warrior warrior = new Warrior("Арагорн", 27, 100);
        Hobbit hobbit = new Hobbit("Бильбо",5,3);

        Balrog balrog = new Balrog("Балрог",100,30);
        Orc orc = new Orc("Орк", 40, 21);
        Wolf wolf = new Wolf("Варг", 20,40);
        Chicken chicken = new Chicken("Курочка",1,1);

        System.out.println("=== Выбор Героя ===");
        System.out.println("1. " + mage.getName() + " | Урон: " + mage.getDamage() + " | Здоровье: " + mage.getHealth());
        System.out.println("2. " + warrior.getName() + " | Урон: " + warrior.getDamage() + " | Здоровье: " + warrior.getHealth());
        System.out.println("3. " + archer.getName() + " | Урон: " + archer.getDamage() + " | Здоровье: " + archer.getHealth());
        System.out.println("4. " + hobbit.getName() + " | Урон: " + hobbit.getDamage() + " | Здоровье: " + hobbit.getHealth());
        System.out.print("Введите номер героя (1-3): ");
        int choice = scan.nextInt();
        while (choice < 1 || choice > 4) {
            System.out.print("Неверный номер! Введите число от 1 до 4: ");
            choice = scan.nextInt();
        }
        int random = (int) (Math.random() * 4) + 1;
        Enemy randomEnemy = randEnemy(random, balrog, orc, chicken, wolf);
        Hero selectedHero = getHero(choice, mage, warrior, archer, hobbit);
        attackEnemy(randomEnemy, selectedHero);
    }

    public static void attackEnemy(Enemy enemy, Hero hero) {
        System.out.println("=== НАЧАЛО БОЯ ===");
        while (enemy.isAlive() && hero.isAlive()) {
            hero.attackEnemy(enemy);
            System.out.println(hero.getName() + " (здоровье: " + hero.getHealth() + ") атакует " + enemy.getName() + " (здоровье: " + enemy.getHealth() + ")");
            if (enemy.isAlive()) {
                enemy.attackHero(hero);
                System.out.println(enemy.getName() + " (здоровье: " + enemy.getHealth() + ") атакует " + hero.getName() + " (здоровье: " + hero.getHealth() + ")");
            }
        }
        if (!hero.isAlive() && !enemy.isAlive()) {
            System.out.println(hero.getName() + " и " + enemy.getName() + " убили друг друга!");
        } else if (!hero.isAlive()) {
            System.out.println(enemy.getName() + " Сожрал " + hero.getName() + "а!");
        } else if (!enemy.isAlive()) {
            System.out.println(hero.getName() + " Сокрушил " + enemy.getName() + "а!");
        }
        System.out.println("=== БОЙ ЗАКОНЧЕН ===");
        System.out.println("\n=== СОСТОЯНИЕ ПОСЛЕ БОЯ ===");
        System.out.println("Враг: " + enemy.getName() + " | Здоровье: " + enemy.getHealth());
        System.out.println("Герой: " + hero.getName() + " | Здоровье: " + hero.getHealth());
    }
    private static Hero getHero(int number, Mage mage, Warrior warrior, Archer archer, Hobbit hobbit) {
        switch (number) {
            case 1: return mage;
            case 2: return warrior;
            case 3: return archer;
            case 4: return hobbit;
            default: return null;
        }
    }
    private static Enemy randEnemy(int number, Balrog balrog,Orc orc,Chicken chicken, Wolf wolf) {
        switch (number) {
            case 1: return orc;
            case 2: return balrog;
            case 3: return wolf;
            case 4: return chicken;
            default: return null;
        }
    }
}

