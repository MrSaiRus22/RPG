package Enemy;
import Hero.Hero;

public class Wolf extends Enemy {

    public Wolf(String name, int damage, int health, int criticalChance) {
        super(name, damage, health, criticalChance);
    }

    @Override
    protected String getAttackMessage(Hero hero) {
        return getName() + " Впился зубами в " + hero.getName() + "a";
    }
}
