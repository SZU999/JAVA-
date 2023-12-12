import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        LinkedList<Integer> linkedList = new LinkedList<>();  // 队列
        for(int i = 0;i < n;i++)
            linkedList.add(i+1);

        int[] point = new int[n+1];  // 学生所在队列的位置
        for(int i = 1;i < n+1;i++)
            point[i] = i-1;

        for(int i  =0;i < m;i++){
            int num = sc.nextInt();
            int dis = sc.nextInt();
            int temp = linkedList.remove(point[num]);
            linkedList.add(point[num]+dis, temp);

            int p = point[num];
            if(dis > 0)
                for(int j = p;j < p+dis;j++)
                    point[linkedList.get(j)]--;
            else
                for(int j = p;j > p+dis;j--)
                    point[linkedList.get(j)]++;
            point[num] = p+dis;
        }

        for(int i = 0;i < linkedList.size();i++)
            System.out.print(linkedList.get(i) + " ");
        System.out.println();
        sc.close();
    }
}
