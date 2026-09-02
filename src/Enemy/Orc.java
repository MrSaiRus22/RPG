package Enemy;
import Hero.Hero;

public class Orc extends Enemy {

    public Orc(String name, int damage, int health, int criticalChance) {
        super(name, damage, health, criticalChance);
    }

    protected String getAttackMessage(Hero hero) {
        return getName() + " Ударил буловой " + hero.getName() + "a";
    }
}
