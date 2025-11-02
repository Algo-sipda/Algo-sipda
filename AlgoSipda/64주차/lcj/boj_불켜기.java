import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static int n, m;
    static boolean[][] light;
    static boolean[][] visited;
    static ArrayList<Node>[][] list;
    
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        list = new ArrayList[n][n];
        light = new boolean[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                list[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int nx = Integer.parseInt(st.nextToken())-1;
            int ny = Integer.parseInt(st.nextToken())-1;
            int tx = Integer.parseInt(st.nextToken())-1;
            int ty = Integer.parseInt(st.nextToken())-1;

            Node node = new Node(tx, ty);
            list[nx][ny].add(node);
        }

        System.out.println(bfs() + 1);
    }

    static int bfs(){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0));
        int tmp = 0;
        visited = new boolean[n][n];
        visited[0][0] = true;
        light[0][0] = true;

        boolean isTurnOn = false;
        while(!q.isEmpty()){
            Node poll = q.poll();

            for(Node node : list[poll.x][poll.y]){
                if(!light[node.x][node.y]){
                    tmp++;
                    light[node.x][node.y] = true;
                    isTurnOn = true;
                }
            }

            for(int i = 0; i < 4; i++){
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(visited[nx][ny] || !light[nx][ny]) continue;

                q.add(new Node(nx, ny));
                visited[nx][ny] = true;
            }
        }

        if(isTurnOn){
            tmp += bfs();
        }

        return tmp;
    }

}