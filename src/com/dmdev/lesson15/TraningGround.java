package com.dmdev.lesson15;

import java.util.Scanner;

public class TraningGround {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Mage mage = new Mage("Гендальф", 100, 750, 100);
        Archer archer = new Archer("Леголас", 75, 900, 20);
        Warrior warrior = new Warrior("Арагорн", 50, 1250,100);
        Hobbit hobbit = new Hobbit("Бильбо", 25, 500);

        Balrog balrog = new Balrog("Балрог", 300, 2100);
        Orc orc = new Orc("Орк", 170, 1350);
        Wolf wolf = new Wolf("Варг", 325, 700);
        Chicken chicken = new Chicken("Курочка", 75, 300);

        // Выбор героя
        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║        ВЫБЕРИТЕ ГЕРОЯ                                                      ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println(" 1. \uD83E\uDDD9  " + mage.getName() + " | Урон: " + mage.getDamage() + " | Здоровье: " + mage.getHealth() + "  | Мана: " + mage.getMana());
        System.out.println(" 2. ⚔\uFE0F  " + warrior.getName() + "  | Урон: " + warrior.getDamage() + " | Здоровье: " + warrior.getHealth() + " | Выносливость: " + warrior.getEndurance());
        System.out.println(" 3. \uD83C\uDFF9  " + archer.getName() + "  | Урон: " + archer.getDamage() + " | Здоровье: " + archer.getHealth() + "  | Стрелы: " + archer.getArrows());
        System.out.println(" 4. \uD83E\uDDB6  " + hobbit.getName() + "   | Урон: " + hobbit.getDamage() + "  | Здоровье: " + hobbit.getHealth() + "  | Камни: " + hobbit.getStones() + "  ");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");

        System.out.print("Введите номер героя (1-4): ");

        int choice = scan.nextInt();
        while (choice < 1 || choice > 4) {
            System.out.print("❌ Неверный номер! Введите число от 1 до 4: ");
            choice = scan.nextInt();
        }

        // Выбор врага (случайный)
        int random = (int) (Math.random() * 4) + 1;
        Enemy randomEnemy = randEnemy(random, orc, balrog, wolf, chicken);

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║     ПРОТИВНИК ПОЯВИЛСЯ!                                                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println(randomEnemy.getName() + " | Здоровье: " + randomEnemy.getHealth() + " | Урон: " + randomEnemy.getDamage());
        System.out.println("══════════════════════════════════════════════════════════════════════════════");

        // Получаем выбранного героя
        Hero selectedHero = getHero(choice, mage, warrior, archer, hobbit);

        // Начинаем бой
        attackEnemy(randomEnemy, selectedHero, scan);
    }

    public static void attackEnemy(Enemy enemy, Hero hero, Scanner scan) {
        System.out.println("\n⚔️ " + hero.getName() + " VS " + enemy.getName() + " ⚔️");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");

        // Убеждаемся, что герой - Хоббит для доступа к специальным методам
        Hobbit hobbit = null;
        if (hero instanceof Hobbit) {
            hobbit = (Hobbit) hero;
        }

        while (enemy.isAlive() && hero.isAlive()) {
            // Уменьшаем кулдаун для Хоббита
            if (hobbit != null) {
                hobbit.decreaseCooldown();
            }

            System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║        ВЫБЕРИТЕ ДЕЙСТВИЕ                                                   ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
            System.out.println(" 1. Обычная атака");
            System.out.println(" 2. Сильная атака");

            // Информация о специальной атаке для Хоббита
            if (hobbit != null) {
                String cooldownInfo = hobbit.getSpecialCooldown() > 0 ?
                        " (кулдаун: " + hobbit.getSpecialCooldown() + " ходов)" : " (готова)";
                System.out.println(" 3. Скрытная атака" + cooldownInfo);
            } else {
                System.out.println(" 3. Специальная атака");
            }

            System.out.println(" 4. Информация о герое");
            System.out.println(" 5. Пропустить ход");
            System.out.println("══════════════════════════════════════════════════════════════════════════════");
            System.out.print("Ваш выбор: ");

            int attackChoice = scan.nextInt();
            System.out.println();

            // Обработка выбора
            switch (attackChoice) {
                case 1:
                    hero.normalAttack(enemy);
                    break;
                case 2:
                    hero.strongAttack(enemy);
                    break;
                case 3:
                    hero.specialAttack(enemy);
                    break;
                case 4:
                    showHeroInfo(hero);
                    continue; // Не даем врагу атаковать
                case 5:
                    System.out.println("⏭️ " + hero.getName() + " пропускает ход!");
                    break;
                default:
                    System.out.println("❌ Неверный выбор! Выполняется обычная атака.");
                    hero.normalAttack(enemy);
                    break;
            }

            // Проверка жив ли враг
            if (!enemy.isAlive()) {
                System.out.println("\n💀 " + enemy.getName() + " повержен!");
                break;
            }

            // Проверка уклонения для Хоббита
            boolean dodged = false;
            if (hero != null) {
                if (hero.tryDodge()) {
                    dodged = true;
                    System.out.println("🌀 " + hero.getName() + " увернулся от атаки!");
                }
            }

            // Враг атакует в ответ (если герой не уклонился)
            if (!dodged) {
                enemy.attackHero(hero);
                System.out.println("👹 " + enemy.getName() + " атакует в ответ!");
                System.out.println(hero.getName() + " (здоровье: " + hero.getHealth() + ")");
            }

            // Проверка жив ли герой
            if (!hero.isAlive()) {
                System.out.println("\n💀 " + hero.getName() + " пал в бою!");
                break;
            }

            System.out.println("─".repeat(50));
        }

        // Результат боя
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║           РЕЗУЛЬТАТ БОЯ               ║");
        System.out.println("╚═══════════════════════════════════════╝");

        if (!hero.isAlive() && !enemy.isAlive()) {
            System.out.println("💥 " + hero.getName() + " и " + enemy.getName() + " убили друг друга!");
        } else if (!hero.isAlive()) {
            System.out.println("👹 " + enemy.getName() + " победил " + hero.getName() + "а!");
        } else if (!enemy.isAlive()) {
            System.out.println("⚔️ " + hero.getName() + " победил " + enemy.getName() + "а!");
        }

        System.out.println("\n📊 СОСТОЯНИЕ ПОСЛЕ БОЯ:");
        System.out.println("Герой: " + hero.getName() + " | Здоровье: " + hero.getHealth());
        if (hobbit != null) {
            System.out.println("Камни: " + hobbit.getStones());
            System.out.println("Скрытность: " + (hobbit.tryDodge() ? "Активна" : "Неактивна"));
        }
        System.out.println("Враг: " + enemy.getName() + " | Здоровье: " + enemy.getHealth());
        System.out.println("═".repeat(50));
    }

    private static void showHeroInfo(Hero hero) {
        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("║        ИНФОРМАЦИЯ О ГЕРОЕ           ║");
        System.out.println("╚═════════════════════════════════════╝");
        System.out.println("📋 Имя: " + hero.getName());
        System.out.println("❤️ Здоровье: " + hero.getHealth());
        System.out.println("⚔️ Урон: " + hero.getDamage());

        if (hero instanceof Hobbit) {
            Hobbit hobbit = (Hobbit) hero;
            System.out.println("\uD83E\uDEA8 Камни: " + hobbit.getStones());
            System.out.println("🎯 Шанс крита: 65%");
            System.out.println("😅 Шанс промаха: 30%");
            System.out.println("🌀 Шанс уклонения: 70%");
            System.out.println("⌛ Скрытная атака: " +
                    (hobbit.getSpecialCooldown() == 0 ? "✅ Готова" : "⏳ Кулдаун " + hobbit.getSpecialCooldown() + " ходов"));
        }
        if (hero instanceof Warrior){
            Warrior warrior = (Warrior) hero;
            System.out.println("⚡ Выносливость: " + warrior.getEndurance());
            System.out.println("🎯 Шанс крита: 10%");
            System.out.println("😅 Шанс промаха: 25%");
            System.out.println("🌀 Шанс уклонения: 30%");
        }
        if (hero instanceof Mage){
            Mage mage = (Mage) hero;
            System.out.println("\uD83D\uDD2E Мана: " + mage.getMana());
            System.out.println("🎯 Шанс крита: 25%");
            System.out.println("😅 Шанс промаха: 20%");
            System.out.println("🌀 Шанс уклонения: 20%");
        }
        if (hero instanceof Archer){
            Archer archer = (Archer) hero;
            System.out.println("\uD83C\uDFF9 Стрелы: " + archer.getArrows());
            System.out.println("🎯 Шанс крита: 35%");
            System.out.println("😅 Шанс промаха: 10%");
            System.out.println("🌀 Шанс уклонения: 50%");
        }
        System.out.println("═".repeat(50));
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

    private static Enemy randEnemy(int number, Orc orc, Balrog balrog, Wolf wolf, Chicken chicken) {
        switch (number) {
            case 1: return orc;
            case 2: return balrog;
            case 3: return wolf;
            case 4: return chicken;
            default: return null;
        }
    }
}

