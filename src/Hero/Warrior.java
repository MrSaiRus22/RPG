package Hero;
import Enemy.Enemy;

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
        endurance += 30;
        System.out.println("⚔️ " + getName() + " замахивается для удара по " + enemy.getName() + "!");
        missEvasionCheck(enemy);
        System.out.println("💥 " + getName() + " использует Обычный удар !");
        int damage = getDamage();
        isCriticalDamage(enemy);
        System.out.println("Урон: " + damage + " (Выносливаость: " + endurance + ")");
    }
    @Override
    public void strongAttack (Enemy enemy){
        System.out.println("⚔️ " + getName() + " замахивается для удара по " + enemy.getName() + "!");
        missEvasionCheck(enemy);
        if (endurance >= 30) {
            endurance -= 30;
            System.out.println("💥 " + getName() + " использует Рассекающий удар !");

            int damage = getDamage() * 3;
            isCriticalDamage(enemy);

                System.out.println("Урон: " + damage + " (Выносливости: " + endurance + ")");
            } else {
                System.out.println("❌ Недостаточно Выносливости! Выполняется обычная атака.");
                normalAttack(enemy);
            }
        }
    @Override
    public void specialAttack (Enemy enemy){
        System.out.println("⚔️ " + getName() + " замахивается для удара по " + enemy.getName() + "!");
        missEvasionCheck(enemy);
        if (endurance >= 50) {
            endurance -= 50;
            System.out.println("🌀 " + getName() + " использует Вихревой Удар!");
            int damage = getDamage() * 5;
            isCriticalDamage(enemy);
                System.out.println("Урон: " + damage + " (Выносливость: " + endurance + ")");
            } else {
                System.out.println("❌ Недостаточно Выносливости! Выполняется сильная атака.");
                strongAttack(enemy);
            }
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
