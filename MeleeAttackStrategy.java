public class MeleeAttackStrategy implements AttackStrategy {
    @Override
    public int calculateDamage(int baseDamage) {
        return (int)(baseDamage * 1.2);
    }

    @Override
    public String getAttackType() {
        return "MELEE";
    }

    @Override
    public String getAttackDescription() {
        return "charges forward with a powerful melee strike";
    }
}
