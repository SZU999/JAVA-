import java.util.LinkedList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        LinkedList<Summon> list1 = new LinkedList<>();//先手玩家的英雄和随从，位置0为英雄，位置1--7为随从
        LinkedList<Summon> list2 = new LinkedList<>();//后手玩家的英雄和随从，位置0为英雄，位置1--7为随从
        LinkedList<Summon> currentPlayer = list1;//当前玩家
        LinkedList<Summon> opponent = list2;//对手玩家
        list1.add(new Summon(0, 30));//先初始化英雄，攻击力为0，生命值为30
        list2.add(new Summon(0, 30));
        for (int i = 0; i < n; i++) {
            String tmp = sc.nextLine();
            String arr[] = tmp.split("\\ ");
            if (arr[0].equals("summon")) {
                //在arr[1]的位置插入一个随从
                currentPlayer.add(Integer.parseInt(arr[1]),
                        new Summon(Integer.parseInt(arr[2]), Integer.parseInt(arr[3])));
            } else if (arr[0].equals("attack")) {
                int attackerNum = Integer.parseInt(arr[1]);//攻击方当的随从编号
                int defenderNum = Integer.parseInt(arr[2]);//被攻击放的随从编号
                Summon attSummon = currentPlayer.get(attackerNum);//攻击方的随从
                Summon deSummon = opponent.get(defenderNum);//被攻击方的随从
                attSummon.health -= deSummon.attack;//生命值更新
                deSummon.health -= attSummon.attack;//生命值更新
                if (attSummon.health <= 0 ) {
                    currentPlayer.remove(attackerNum);
                }
                if (deSummon.health <= 0 && defenderNum!=0) {//如果被攻击方的生命值小于0，且被攻击放不是英雄，则删掉
                    opponent.remove(defenderNum);
                }
            } else if (arr[0].equals("end")) {
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
            System.out.println("1");
        } else if (list1.getFirst().health <= 0) {
            System.out.println("-1");
        } else {
            System.out.println("0");
        }
        System.out.println(list1.getFirst().health);
        System.out.print(list1.size() - 1 + " ");
        for (int i = 1; i < list1.size(); i++) {
            System.out.print(list1.get(i).health + " ");
        }
        System.out.println();
        System.out.println(list2.getFirst().health);
        System.out.print(list2.size() - 1 + " ");
        for (int i = 1; i < list2.size(); i++) {
            System.out.print(list2.get(i).health + " ");
        }
    }
}
class Summon {
    int attack;
    int health;
    public Summon(int attack, int health) {
        super();
        this.attack = attack;
        this.health = health;
    }
    public String toString() {
        return "attach:" + String.valueOf(attack) + " health:" + String.valueOf(health);
    }
}
