// [BOJ] 키 순서
// https://www.acmicpc.net/problem/2458


// a -> b 인 bigger
// b -> a 인 smaller 
// 특정 인원에서 시작 하여 N-1 개가 나오면 가능
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Integer>[] bigger;
    static List<Integer>[] smaller;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bigger = new ArrayList[N+1];
        smaller = new ArrayList[N+1];
        for(int i = 0; i < N+1; i++){
            bigger[i] = new ArrayList<>();
            smaller[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // a(키) -> b
            bigger[a].add(b);
            smaller[b].add(a);
        }

        int answer = 0;
        for(int me = 1; me <= N; me ++){
            int up = dfs(me, bigger); //자신보다 큰 사람들 찾기 
            int down = dfs(me, smaller); // 자신보다 작은 사람들 찾기 
            if(up + down == N - 1) answer++; // 정확하게 자신을 제외한 사람의 수가 나오면 내 위치 정해짐
        }

        System.out.println(answer);

    }

    static int dfs(int start, List<Integer>[] adj){
        visited = new boolean[N+1];
        return traverse(start, adj);
    }

    static int traverse(int current, List<Integer>[] graph){
        visited[current] = true;
        int count = 0;
        for(int next : graph[current]) {
            if(!visited[next]) {
                count += 1 + traverse(next, graph); // 그림으로 그려서 확인해보자
            }
        }
        return count;
    }
}