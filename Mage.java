public class Mage extends Hero {
    public Mage(String name) {
        super(name, 100, 20);
        setAttackStrategy(new MagicAttackStrategy());
    }

    @Override
    public String getHeroType() {
        return "MAGE";
    }
}
