package Hero;

import Enemy.Enemy;

public class Hobbit extends Hero {

    private boolean isHidden = false;
    private int dodgeChance = 70; // 70% шанс уклонения
    private int specialCooldown = 3; // Кулдаун специальной атаки
    private int stones = 10; // Камни для метания
    private int missChance = 30; // 30% промах
    private int criticalChance = 65;
    private int enemyDodgeChance = 20; // 20% уклонение врага

    public Hobbit(String name, int damage, int health) {
        super(name, damage, health);
    }

    @Override
    public void normalAttack(Enemy enemy) {
        System.out.println("⚔️ " + getName() + " Готовится бросить камень " + enemy.getName() + "!");
        missEvasionCheck(enemy);
        System.out.println("🗿 " + getName() + " бросает камень в " + enemy.getName() + "!");
        int damage = getDamage();
        isCriticalDamage(enemy);
        stones--;
        System.out.println("Урон: " + damage + " (Камни: " + stones + ")");
    }

    @Override
    public void strongAttack(Enemy enemy) {
        System.out.println("⚔️ " + getName() + " Готовится к удару " + enemy.getName() + "!");
        missEvasionCheck(enemy);
        System.out.println("👊 " + getName() + " наносит точный удар по ногам " + enemy.getName() + "!");
        int damage = (int) (getDamage() * 1.5);
        isCriticalDamage(enemy);
        System.out.println("Урон: " + damage);
    }

    @Override
    public void specialAttack(Enemy enemy) {
        if (specialCooldown > 0) {
            System.out.println("⏳ Специальная атака перезаряжается! Осталось ходов: " + specialCooldown);
            System.out.println("Выполняется обычная атака...");
            normalAttack(enemy);
            return;
        }

        if (stones < 3) {
            System.out.println("❌ Недостаточно камней! (Нужно: 3, Есть: " + stones + ")");
            System.out.println("Выполняется сильная атака...");
            strongAttack(enemy);
            return;
        }
        // Скрытная атака
        System.out.println("\uD83D\uDC8D " + getName() + " надевает кольцо и становится невидимым!");
        System.out.println("🔪 " + getName() + " наносит скрытный удар " + enemy.getName() + "!");
        int damage = getDamage() * 3;
        enemy.takeDamage(damage);
        stones -= 3;
        specialCooldown = 3; // Кулдаун 3 хода
        isHidden = true;
        System.out.println("💥 КРИТИЧЕСКИЙ УРОН! Урон: " + damage + " (Камни: " + stones + ")");
        System.out.println("👻 " + getName() + " снова становится видимым!");
    }

    @Override
    public boolean tryDodge() {
        int currentDodge = isHidden ? dodgeChance + 30 : dodgeChance;
        if (Math.random() * 100 < currentDodge) {
            return true;
        }
        return false;
    }

    // Уменьшаем кулдаун каждый ход
    public void decreaseCooldown() {
        if (specialCooldown > 0) {
            specialCooldown--;
            if (specialCooldown == 0) {
                System.out.println("✨ Скрытная атака " + getName() + " снова готова!");
            }
        }
        if (isHidden) {
            isHidden = false; // Скрытность только на 1 ход
        }
    }
    @Override
    public void attackEnemy(Enemy enemy) {
        System.out.println(getName() + " Затыкал противника " + enemy.getName() + "a");
        enemy.takeDamage(getDamage());
    }
    // Геттеры и сеттеры
    public int getStones() {
        return stones;
    }

    public int getSpecialCooldown() {
        return specialCooldown;
    }
}
