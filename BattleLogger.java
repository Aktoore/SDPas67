public class BattleLogger implements GameObserver {
    @Override
    public void onAttack(String attackerName, String defenderName, int damage, String attackType) {
        System.out.println("! " + attackerName + " attacked " + defenderName + " with " + attackType + " for " + damage + " damage");
    }

    @Override
    public void onHealthChange(String heroName, int currentHealth, int maxHealth) {
        System.out.println("! " + heroName + " health: " + currentHealth + "/" + maxHealth);
    }

    @Override
    public void onHeroDeath(String heroName) {
        System.out.println(" !*** " + heroName + " has been defeated! ***");
    }

    @Override
    public void onStrategyChange(String heroName, String newStrategy) {
        System.out.println("! " + heroName + " switched to " + newStrategy + " strategy");
    }
}