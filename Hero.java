import java.util.*;

public abstract class Hero {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int baseDamage;
    protected AttackStrategy strategy;
    protected List<GameObserver> observers;
    protected boolean isAlive;
    protected boolean hasShield;
    protected Random random;

    public Hero(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.baseDamage = damage;
        this.maxHealth = health;
        this.observers = new ArrayList<>();
        this.isAlive = true;
        this.hasShield = false;
        this.random = new Random();
    }
    public void registerObserver(GameObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }
    public void setAttackStrategy(AttackStrategy newStrategy) {
        this.strategy = newStrategy;
        for (int i = 0; i < this.observers.size(); i++) {
            observers.get(i).onStrategyChange(name, newStrategy.getAttackType());
        }
    }
    public void attack(Hero target) {
        if (!isAlive) {
            return;
        }
        int baseDamageValue = strategy.calculateDamage(baseDamage);
        int variation = (int)(baseDamageValue * 0.2);
        int minDamage = baseDamageValue - variation;
        int maxDamage = baseDamageValue + variation;
        int finalDamage = minDamage + random.nextInt(maxDamage - minDamage + 1);

        System.out.println(name + " attacks!");
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).onAttack(name, target.getName(), finalDamage, strategy.getAttackType());
        }
        target.takeDamage(finalDamage);
    }
    public void takeDamage(int damage) {
        if (!isAlive) {
            return;
        }
        if (hasShield) {
            System.out.println(name + " blocked attack with shield!");
            hasShield = false;
            for (int i = 0; i < observers.size(); i++) {
                observers.get(i).onHealthChange(name, health, maxHealth);
            }
            return;
        }
        health -= damage;
        if (health <= 0) {
            health = 0;
            isAlive = false;
            for (int i = 0; i < observers.size(); i++) {
                observers.get(i).onHeroDeath(name);
            }
        }
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).onHealthChange(name, health, maxHealth);
        }
    }
    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public void heal(int amount) {
        health += amount;
        if (health > maxHealth) {
            health = maxHealth;
        }
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).onHealthChange(name, health, maxHealth);
        }
    }
    public void increaseDamage(int amount) {
        baseDamage += amount;
    }
    public void activateShield() {
        hasShield = true;
    }
    public abstract String getHeroType();
}