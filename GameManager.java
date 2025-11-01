public class GameManager {
    private static GameManager instance;
    private BattleLogger logger;
    private GameAnnouncer announcer;
    private StatisticsTracker statsTracker;

    private GameManager() {
        logger = new BattleLogger();
        announcer = new GameAnnouncer();
        statsTracker = new StatisticsTracker();
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void registerObservers(Hero hero) {
        hero.registerObserver(logger);
        hero.registerObserver(announcer);
        hero.registerObserver(statsTracker);
    }

    public void printFinalStatistics() {
        statsTracker.printStatistics();
    }
}