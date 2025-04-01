import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int node;
        long cost;

        public Node(int node,long cost){
            this.node = node;
            this.cost = cost;
        }

    }
    
    static int N, M;
    static ArrayList<ArrayList<Node>> list = new ArrayList<>();
    static boolean[] sight, visited;
    static long[] dist;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        sight = new boolean[N];
        dist = new long[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N ; i++) {
            int temp = Integer.parseInt(st.nextToken());
            if(temp == 1 ){
                sight[i] = false;
            }else{
                sight[i] = true;
            }
        }
        
        for (int i = 0; i < N ; i++) {
            list.add(new ArrayList<>());
        }
        
        for (int i = 0; i < M ; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b,c));
            list.get(b).add(new Node(a,c));

        }
        
        Arrays.fill(dist,Long.MAX_VALUE);
        visited = new boolean[N];

        dijstra();
        System.out.println(dist[N-1] != Long.MAX_VALUE ? dist[N-1] : -1);
    }
    
    static void dijstra(){
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> Math.toIntExact(o1.cost - o2.cost));
        dist[0] = 0;
        queue.add(new Node(0,0));

        while (!queue.isEmpty()){
            Node now = queue.poll();

            if(visited[now.node]) continue;

            visited[now.node] = true;

            for(Node next : list.get(now.node)){
                if(next.node != N-1 && !sight[next.node]) continue;

                if(dist[next.node] > dist[now.node] + next.cost){
                    dist[next.node] = dist[now.node] + next.cost;
                    queue.add(new Node(next.node,dist[next.node]));
                }
            }
        }
    }
}