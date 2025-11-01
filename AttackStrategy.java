public interface AttackStrategy {
    int calculateDamage(int baseDamage);
    String getAttackType();
    String getAttackDescription();
}
