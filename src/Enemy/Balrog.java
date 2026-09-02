package Enemy;
import Hero.Hero;

public class Balrog extends Enemy {

    public Balrog(String name, int damage, int health, int criticalChance) {
        super(name, damage, health, criticalChance);
    }

    @Override
    protected String getAttackMessage(Hero hero) {
        return getName() + " Изверг пламя на " + hero.getName() + "a";
    }
}
