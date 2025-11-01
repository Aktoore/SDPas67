public class RangedAttackStrategy implements AttackStrategy {
    @Override
    public int calculateDamage(int baseDamage) {
        return baseDamage;
    }

    @Override
    public String getAttackType() {
        return "RANGED";
    }

    @Override
    public String getAttackDescription() {
        return "fires a precise ranged shot";
    }
}
