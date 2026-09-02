package Hero;

import Enemy.Enemy;

public class Mage extends Hero {

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
        missEvasionCheck(enemy);
        System.out.println("🧙 " + getName() + " использует магическую стрелу!");
        int damage = getDamage();
        isCriticalDamage(enemy);
        System.out.println("Урон: " + damage + " (Мана: " + mana + ")");
    }

    @Override
    public void strongAttack(Enemy enemy) {
        if (mana >= 30) {
            mana -= 30;
            System.out.println("⚔️ " + getName() + " копит магическую энергию для удара по " + enemy.getName() + "!");
            missEvasionCheck(enemy);
            System.out.println("🔥 " + getName() + " использует Огненный Шар!");
            int damage = getDamage() * 3;
            isCriticalDamage(enemy);
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
            missEvasionCheck(enemy);
            System.out.println("⚡ " + getName() + " использует Первородный Всплеск — магия в её чистом, диком и концентрированном виде!");
            int damage = getDamage() * 5;
            isCriticalDamage(enemy);
            System.out.println("Урон: " + damage + " (Мана: " + mana + ")");
        } else {
            System.out.println("❌ Недостаточно маны! Выполняется сильная атака.");
            strongAttack(enemy);
        }
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
