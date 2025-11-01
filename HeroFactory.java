public class HeroFactory {
    public static Hero createHero(String type, String name) {
        switch(type.toUpperCase()) {
            case "WARRIOR":
                return new Warrior(name);
            case "MAGE":
                return new Mage(name);
            case "ARCHER":
                return new Archer(name);
            default:
                throw new IllegalArgumentException("Unknown hero type: " + type);
        }
    }
}
