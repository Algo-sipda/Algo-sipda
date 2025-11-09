import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_불켜기 {
    static int dr[] = {0, 0, -1, 1};
    static int dc[] = {-1, 1, 0, 0};

    static int N;
    static ArrayList<Room> shed[][];
    static boolean light[][];
    static boolean visit[][];
    static int result = 1;

    static class Room {
        int r;
        int c;

        Room (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        shed = new ArrayList[N + 1][N + 1];
        light = new boolean[N + 1][N + 1];
        visit = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                shed[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            shed[r][c].add(new Room(a, b));
        }

        bfs();
        System.out.println(result);
    }

    static void bfs() {
        Queue<Room> q = new LinkedList<>();
        q.offer(new Room(1, 1));
        light[1][1] = true;
        visit[1][1] = true;

        while(!q.isEmpty()) {
            Room temp = q.poll();
            if (!shed[temp.r][temp.c].isEmpty()) {
                visit = new boolean[N + 1][N + 1];
                visit[temp.r][temp.c] = true;
                for (Room room : shed[temp.r][temp.c]) {
                    if (!light[room.r][room.c]) {
                        light[room.r][room.c] = true;
                        result++;
                    }
                }
                shed[temp.r][temp.c].clear();
            }

            for (int i = 0; i < 4; i++) {
                int newR = temp.r + dr[i];
                int newC = temp.c + dc[i];

                if (newR <= 0 || newC <= 0 || newR > N || newC > N) continue;

                if (light[newR][newC] == true && !visit[newR][newC]) {
                    q.offer(new Room(newR, newC));
                    visit[newR][newC] = true;
                }
            }
        }
    }
}