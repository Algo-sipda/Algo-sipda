import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M, answer;
    static int[][] map;
    static boolean[][] visited;
    static List<Point> list;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = 0;
        map = new int[N][M];
        visited = new boolean[N][M];
        list = new ArrayList<>();

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                visited[i][j] = true;
                list.add(new Point(i, j));

                // 탐색 시
                findFiveBlock();

                visited[i][j] = false;
                list.remove(list.size()-1);
            }
        }
        System.out.println(answer);
    }

    public static void findFiveBlock() {
        if(list.size() == 5) {
            // 5개 다 채웠다면? 4개 연속이 있는지 체크
            // 없다면 map에 있는값 체크해서 max 값 구하기
            int max = 0;
            int[] maxR = new int[21];
            int[] maxC = new int[21];
            for(int i=0;i < 5;i++) {
                Point cur = list.get(i);
                maxR[cur.r]++;
                maxC[cur.c]++;
                if(maxR[cur.r] >= 4 || maxC[cur.c] >= 4) return;
            }

            int sum = 0;
            for(int i=0;i<5;i++) {
                sum += map[list.get(i).r][list.get(i).c];
            }
            answer = Math.max(sum, answer);

            return;
        }

        for(int i=0;i<list.size();i++) {
            int curR = list.get(i).r;
            int curC = list.get(i).c;
            for(int d = 0; d<4;d++) {
                int nextR = curR + dr[d];
                int nextC = curC + dc[d];

                if(!isRange(nextR, nextC)) continue;
                if(visited[nextR][nextC]) continue;

                visited[nextR][nextC] = true;
                list.add(new Point(nextR, nextC));

                findFiveBlock();

                visited[nextR][nextC] = false;
                list.remove(list.size()-1);
            }
        }
    }

    public static boolean isRange(int r, int c) {
        return (0<=r && r<N && 0<=c && c<M);
    }
}