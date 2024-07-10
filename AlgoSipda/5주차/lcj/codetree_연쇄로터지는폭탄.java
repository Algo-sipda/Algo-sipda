import java.io.*;
import java.util.*;

public class Main {

    public static class Bomb {
        int cur, bombSize;

        public Bomb (int cur, int bombSize) {
            this.cur = cur;
            this.bombSize = bombSize;
        }
    }

    public static int N;
    public static int[] bomb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        bomb = new int[N];
        for(int i=0;i<N;i++) {
            bomb[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bomb);
        
        int answer = 0;
        for(int i=0;i<N;i++) {
            int cnt = solution(i);
            answer = Math.max(answer, cnt);
        }
        System.out.println(answer);
    }

    public static int solution(int idx) {
        int answer = 1;

        Queue<Bomb> q = new LinkedList<>();
        boolean[] visited = new boolean[N];
        q.add(new Bomb(bomb[idx], 1));
        visited[idx]= true;

        while(!q.isEmpty()) {
            Bomb cur = q.poll();
            int bombBefore = cur.cur - cur.bombSize;
            int bombAfter = cur.cur + cur.bombSize;
            // 1. 범위 idx 안에 속해야 함
            // 2. 방문한 적이 없어야 한다.
            // 3. 범위 안에 속한 폭탄이 있는 경우 q에 넣기
            boolean isChanged = false;
            for(int i=0;i<N;i++) {
                if(!visited[i] && bombBefore <= bomb[i] && bomb[i] <= bombAfter){
                    // System.out.println("cur: " + cur.cur +" / bomb: "+ bomb[i]+" / bombSize : "+cur.bombSize);
                    q.add(new Bomb(bomb[i], cur.bombSize+1));
                    answer++;
                    visited[i] = true;
                }
            }
        }

        return answer;
    }
}