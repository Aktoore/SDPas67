public class GameAnnouncer implements GameObserver {
    @Override
    public void onAttack(String attackerName, String defenderName, int damage, String attackType) {
        System.out.println(">>> " + attackerName + " " + getAttackVerb(attackType) +
                " " + defenderName + " dealing " + damage + " damage!");
    }

    @Override
    public void onHealthChange(String heroName, int currentHealth, int maxHealth) {
        double healthPercent = (currentHealth * 100.0) / maxHealth;
        String status = healthPercent > 70 ? "looking strong" :
                healthPercent > 30 ? "wounded" : "critically injured";
        System.out.println(">>> " + heroName + " is " + status + "!");
    }

    @Override
    public void onHeroDeath(String heroName) {
        System.out.println(">>>  " + heroName + " HAS FALLEN IN BATTLE! ");
    }

    @Override
    public void onStrategyChange(String heroName, String newStrategy) {
        System.out.println(">>> " + heroName + " changes tactics to " + newStrategy + " combat!");
    }

    private String getAttackVerb(String attackType) {
        switch(attackType) {
            case "MELEE": return "slashes at";
            case "RANGED": return "shoots";
            case "MAGIC": return "blasts";
            default: return "attacks";
        }
    }
}