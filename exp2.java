import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        LinkedList<Summon> list1 = new LinkedList<>();
        LinkedList<Summon> list2 = new LinkedList<>();
        LinkedList<Summon> currentPlayer = list1;
        LinkedList<Summon> opponent = list2;
        list1.add(new Summon(0, 30));
        list2.add(new Summon(0, 30));
        for (int i = 0; i < n; i++) {
            String tmp = sc.nextLine();
            String[] arr = tmp.split("\\ ");
            if ("summon".equals(arr[0])) {
                currentPlayer.add(Integer.parseInt(arr[1]),
                        new Summon(Integer.parseInt(arr[2]), Integer.parseInt(arr[3])));
            } else if ("attack".equals(arr[0])) {
                int attackerNum = Integer.parseInt(arr[1]);
                int defenderNum = Integer.parseInt(arr[2]);
                Summon attSummon = currentPlayer.get(attackerNum);
                Summon deSummon = opponent.get(defenderNum);
                attSummon.health -= deSummon.attack;
                deSummon.health -= attSummon.attack;
                if (attSummon.health <= 0 ) {
                    currentPlayer.remove(attackerNum);
                }
                if (deSummon.health <= 0 && defenderNum != 0) {
                    opponent.remove(defenderNum);
                }
            } else if ("end".equals(arr[0])) {
                if (currentPlayer.equals(list1)) {
                    currentPlayer = list2;
                    opponent = list1;
                } else {
                    currentPlayer = list1;
                    opponent = list2;
                }
            }
        }
        if (list2.getFirst().health <= 0) {
            LOGGER.info("1");
        } else if (list1.getFirst().health <= 0) {
            LOGGER.info("-1");
        } else {
            LOGGER.info("0");
        }
        LOGGER.info(String.valueOf(list1.getFirst().health));
        StringBuilder sb1 = new StringBuilder();
        sb1.append(list1.size() - 1).append(" ");
        for (int i = 1; i < list1.size(); i++) {
            sb1.append(list1.get(i).health).append(" ");
        }
        LOGGER.info(sb1.toString());
        LOGGER.info(String.valueOf(list2.getFirst().health));
        StringBuilder sb2 = new StringBuilder();
        sb2.append(list2.size() - 1).append(" ");
        for (int i = 1; i < list2.size(); i++) {
            sb2.append(list2.get(i).health).append(" ");
        }
        LOGGER.info(sb2.toString());
    }
}

class Summon {
    int attack;
    int health;

    public Summon(int attack, int health) {
        this.attack = attack;
        this.health = health;
    }

    @Override
    public String toString() {
        return "attack:" + attack + " health:" + health;
    }
}
