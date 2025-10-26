import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class boj_숫자고르기 {
    static int[] arr;
    static boolean[] visited;
    static int n, count;
    static ArrayList<Integer> list;
    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        arr = new int[n+1];
        visited = new boolean[n+1];
        list = new ArrayList<>();

        for(int i=0;i<n;i++) {
            arr[i+1] = sc.nextInt();
        }
        for(int i=1;i<=n;i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }
        Collections.sort(list);
        System.out.println(list.size());
        for(int i=0;i<list.size();i++)
            System.out.println(list.get(i));
    }
    public static void dfs(int start, int end) {
        if(!visited[arr[start]]) {
            visited[arr[start]] = true;
            dfs(arr[start], end);
            visited[arr[start]] = false;
        }
        if(arr[start] == end) list.add(arr[end]);
    }
}