import java.io.*;
import java.util.*;

/*
    n개의 숫자 -> 각 숫자: 각 번호에서 이동 가능한 번호
    어떻게 이동해도 loop에 빠지지 않는 서로 다른 번호의 수를 구해라
    (0: 이동할 곳 X)
*/

public class Main {

    static int N;
    static int[] arr;
    static boolean[] visited, isDone;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N+1];
        visited = new boolean[N+1];
        isDone = new boolean[N+1];
        for(int i=1;i<=N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i=1; i<=N;i++) {
            if(!isDone[i]) {
                visited[i] = true;
                isDone[i] = dfs(i);
                visited[i] = false;
            }
        }

        int cnt = 0;
        for(int i=1; i<=N;i++) {
            if(!isDone[i]) cnt++;
        }

        System.out.println(cnt);
    }

    public static boolean dfs(int num) {
        int next = arr[num];
        if(next == 0) {
            return false;
        }

        if(visited[next]) {
            return true;
        }
        visited[next] = true;
        isDone[next] = dfs(next);
        visited[next] = false;

        return isDone[next];
    }
}