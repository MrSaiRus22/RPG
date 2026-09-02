package Enemy;
import Hero.Hero;

import java.util.Random;
public class Wolf extends Enemy {

    private Random random = new Random();
    private int criticalChance = 40;

    public Wolf(String name, int damage, int health) {
        super(name, damage, health);
    }

    @Override
    public void attackHero(Hero hero) {
        System.out.println(getName() + " укусил " + hero.getName() + "a");
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
        hero.takeDamage(damage);
    }
}
