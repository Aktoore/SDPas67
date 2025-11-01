public class MagicAttackStrategy implements AttackStrategy {
    @Override
    public int calculateDamage(int baseDamage) {
        return (int)(baseDamage * 1.5);
    }

    @Override
    public String getAttackType() {
        return "MAGIC";
    }

    @Override
    public String getAttackDescription() {
        return "casts a devastating magical spell";
    }
}