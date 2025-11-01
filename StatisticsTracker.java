public class StatisticsTracker implements GameObserver {
    private int totalAttacks = 0;
    private int totalDamage = 0;
    private int defeatedHeroes = 0;

    @Override
    public void onAttack(String attackerName, String defenderName, int damage, String attackType) {
        totalAttacks++;
        totalDamage += damage;
    }

    @Override
    public void onHealthChange(String heroName, int currentHealth, int maxHealth) {

    }

    @Override
    public void onHeroDeath(String heroName) {
        defeatedHeroes++;
    }

    @Override
    public void onStrategyChange(String heroName, String newStrategy) {

    }

    public void printStatistics() {
        System.out.println("\n-------- BATTLE STATISTICS --------");
        System.out.println("Total Attacks: " + totalAttacks);
        System.out.println("Total Damage Dealt: " + totalDamage);
        System.out.println("Heroes Defeated: " + defeatedHeroes);
        System.out.println("Average Damage per Attack: " + (totalAttacks > 0 ? totalDamage/totalAttacks : 0));
        System.out.println("------------------------\n");
    }
}