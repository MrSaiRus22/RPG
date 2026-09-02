package Hero;

import Enemy.Enemy;
import Runner.Mortal;

public abstract class Hero implements Mortal {

    private String name;
    private int basedamage;
    private int health;

    public Hero(String name, int basedamage, int health) {
        this.name = name;
        this.basedamage = basedamage;
        this.health = health;
    }

    public void takeDamage(int damage){
        if (isAlive() && health <= damage) {
            health = 0;
        } else {
            this.health -= Math.min(health, damage);
            System.out.println(name + " получил урон " + damage + ". Осталось " + health);
        }
    }

    public abstract void attackEnemy(Enemy enemy);
    public abstract void normalAttack(Enemy enemy);
    public abstract void strongAttack(Enemy enemy);
    public abstract void specialAttack(Enemy enemy);
    public abstract boolean tryDodge();

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return basedamage;
    }

    public int getHealth() {
        return health;
    }

}
