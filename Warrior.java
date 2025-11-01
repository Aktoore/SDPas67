public class Warrior extends Hero {
    public Warrior(String name) {
        super(name, 150, 25);
        setAttackStrategy(new MeleeAttackStrategy());
    }

    @Override
    public String getHeroType() {
        return "WARRIOR";
    }
}