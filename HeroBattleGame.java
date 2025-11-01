import java.util.*;

public class HeroBattleGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameManager gm = GameManager.getInstance();
        Set<Integer> usedBonuses = new HashSet<>();

        System.out.println("Hero Battle Game\n");

        System.out.println("Choose hero:\n1. Warrior\n2. Mage\n3. Archer");
        int choice = Integer.parseInt(sc.nextLine());
        System.out.print("Name: ");
        String name = sc.nextLine();

        Hero player = null;
        if (choice == 1) player = new Warrior(name);
        else if (choice == 2) player = new Mage(name);
        else if (choice == 3) player = new Archer(name);

        gm.registerObservers(player);

        List<Hero> enemies = new ArrayList<>();
        String playerType = player.getHeroType();
        if (!playerType.equals("WARRIOR")) {
            enemies.add(new Warrior("Enemy Warrior"));
        }
        if (!playerType.equals("MAGE")) {
            enemies.add(new Mage("Enemy Mage"));
        }
        if (!playerType.equals("ARCHER")) {
            enemies.add(new Archer("Enemy Archer"));
        }

        for (int i = 0; i < enemies.size(); i++) {
            gm.registerObservers(enemies.get(i));
        }

        System.out.println("\nYour Hero: " + player.getName());
        System.out.println("HP: " + player.getHealth() + "\n");

        Hero currentTarget = null;
        int roundNumber = 1;

        while (player.isAlive()) {
            boolean enemiesAlive = false;
            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i).isAlive()) {
                    enemiesAlive = true;
                    break;
                }
            }
            if (!enemiesAlive) {
                break;
            }

            System.out.println("ROUND " + roundNumber);
            System.out.println("\nYour HP: " + player.getHealth());

            if (player.getHeroType().equals("WARRIOR")) {
                System.out.println("1. Melee Attack (bonus damage)");
                System.out.println("2. Ranged Attack (normal)");
                int attackChoice = Integer.parseInt(sc.nextLine());
                if (attackChoice == 1) {
                    player.setAttackStrategy(new MeleeAttackStrategy());
                } else {
                    player.setAttackStrategy(new RangedAttackStrategy());
                }
            } else if (player.getHeroType().equals("MAGE")) {
                System.out.println("1. Magic Attack (bonus damage)");
                System.out.println("2. Ranged Attack (normal)");
                int attackChoice = Integer.parseInt(sc.nextLine());
                if (attackChoice == 1) {
                    player.setAttackStrategy(new MagicAttackStrategy());
                } else {
                    player.setAttackStrategy(new RangedAttackStrategy());
                }
            } else if (player.getHeroType().equals("ARCHER")) {
                System.out.println("1. Ranged Attack (bonus damage)");
                System.out.println("2. Melee Attack (normal)");
                int attackChoice = Integer.parseInt(sc.nextLine());
                if (attackChoice == 1) {
                    player.setAttackStrategy(new RangedAttackStrategy());
                } else {
                    player.setAttackStrategy(new MeleeAttackStrategy());
                }
            }

            if (currentTarget == null || !currentTarget.isAlive()) {
                System.out.println("Choose target:");
                int targetIndex = 1;
                for (int i = 0; i < enemies.size(); i++) {
                    if (enemies.get(i).isAlive()) {
                        System.out.println(targetIndex + ". " + enemies.get(i).getName() + " [" + enemies.get(i).getHealth() + " HP]");
                        targetIndex++;
                    }
                }

                int targetChoice = Integer.parseInt(sc.nextLine());
                targetIndex = 1;
                for (int i = 0; i < enemies.size(); i++) {
                    if (enemies.get(i).isAlive()) {
                        if (targetIndex == targetChoice) {
                            currentTarget = enemies.get(i);
                            break;
                        }
                        targetIndex++;
                    }
                }
            } else {
                System.out.println("Current target: " + currentTarget.getName() + " [" + currentTarget.getHealth() + " HP]");
            }

            player.attack(currentTarget);

            Random rand = new Random();
            for (int i = 0; i < enemies.size(); i++){
                if (enemies.get(i).isAlive() && player.isAlive()) {
                    int targetChance = rand.nextInt(10);
                    if (targetChance < 8) {
                        enemies.get(i).attack(player);
                    } else {
                        List<Hero> possibleTargets = new ArrayList<>();
                        for (int j = 0; j < enemies.size(); j++) {
                            if (enemies.get(j).isAlive() && i != j) {
                                possibleTargets.add(enemies.get(j));
                            }
                        }
                        if (possibleTargets.size() > 0) {
                            Hero randomTarget = possibleTargets.get(rand.nextInt(possibleTargets.size()));
                            enemies.get(i).attack(randomTarget);
                        } else {
                            enemies.get(i).attack(player);
                        }
                    }
                }
            }

            if (!player.isAlive()) {
                break;
            }

            enemiesAlive = false;
            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i).isAlive()) {
                    enemiesAlive = true;
                    break;
                }
            }
            if (!enemiesAlive) {
                break;
            }

            if (usedBonuses.size() < 3) {
                Random random = new Random();
                int healthBonus = 20 + random.nextInt(31);
                int damageBonus = 3 + random.nextInt(8);

                System.out.println("\nChoose bonus:");
                int optionNumber = 1;

                if (!usedBonuses.contains(1)) {
                    System.out.println(optionNumber + ". Restore " + healthBonus + " HP");
                    optionNumber++;
                }
                if (!usedBonuses.contains(2)) {
                    System.out.println(optionNumber + ". Increase damage by " + damageBonus);
                    optionNumber++;
                }
                if (!usedBonuses.contains(3)) {
                    System.out.println(optionNumber + ". Shield (block one attack)");
                }

                int bonusChoice = Integer.parseInt(sc.nextLine());
                int selectedBonus = 0;
                optionNumber = 1;

                if (!usedBonuses.contains(1)) {
                    if (optionNumber == bonusChoice) {
                        selectedBonus = 1;
                    }
                    optionNumber++;
                }
                if (!usedBonuses.contains(2)) {
                    if (optionNumber == bonusChoice) {
                        selectedBonus = 2;
                    }
                    optionNumber++;
                }
                if (!usedBonuses.contains(3)) {
                    if (optionNumber == bonusChoice) {
                        selectedBonus = 3;
                    }
                }

                if (selectedBonus == 1) {
                    player.heal(healthBonus);
                    System.out.println("You healed " + healthBonus + " HP");
                } else if (selectedBonus == 2) {
                    player.increaseDamage(damageBonus);
                    System.out.println("Damage increased by " + damageBonus);
                } else {
                    player.activateShield();
                    System.out.println("Activated shield");
                }

                usedBonuses.add(selectedBonus);
            }
            roundNumber++;
        }

        System.out.println("\nGame Over!");
        if (player.isAlive()) {
            System.out.println("You win!");
        } else {
            System.out.println("You lost!");
        }

        gm.printFinalStatistics();
        sc.close();
    }
}