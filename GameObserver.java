public interface GameObserver {
    void onAttack(String attackerName, String defenderName,int damage, String attackType);
    void onHealthChange(String heroName, int currentHealth, int maxHealth);
    void onHeroDeath(String heroName);
    void onStrategyChange(String heroName, String newStrategy);
}
