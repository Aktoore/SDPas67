public class Archer extends Hero {
    public Archer(String name) {
        super(name, 120, 22);
        setAttackStrategy(new RangedAttackStrategy());
    }

    @Override
    public String getHeroType() {
        return "ARCHER";
    }
}