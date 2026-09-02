package Enemy;
import Hero.Hero;

public class Chicken extends Enemy {

    public Chicken(String name, int damage, int health, int criticalChance) {
        super(name, damage, health, criticalChance);
    }

    protected String getAttackMessage(Hero hero) {
        return getName() + " Клюнула " + hero.getName() + "a";
    }
}
