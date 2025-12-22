import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static final long INF = Long.MAX_VALUE;
    static long[] dist;
    static ArrayList<int[]>[] adj;
    static PriorityQueue<long[]> pq = new PriorityQueue<>((o1,o2)->Long.compare(o1[0],o2[0]));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
        dist = new long[n+1]; Arrays.fill(dist, INF);
        adj = new ArrayList[n+1]; for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
            adj[u].add(new int[]{v,i});
            adj[v].add(new int[]{u,i});
        }

        dist[1] = 0; pq.add(new long[]{0,1});

        while(!pq.isEmpty()){
            long[] cur = pq.poll();
            long curTime = cur[0], curCycle = curTime / (long)m; int curPos = (int)cur[1];

            if(dist[curPos] != curTime) continue;

            for(int[] nxt : adj[curPos]){
                int nxtPos = nxt[0]; long nxtTime = ((curTime%m<=nxt[1])? (curCycle * m) : ((curCycle + 1) * m)) + nxt[1];

                if(nxtTime < dist[nxtPos]){
                    dist[nxtPos] = nxtTime;
                    pq.add(new long[]{nxtTime, nxtPos});
                }
            }
        }
        System.out.println(dist[n] + 1);
    }
}