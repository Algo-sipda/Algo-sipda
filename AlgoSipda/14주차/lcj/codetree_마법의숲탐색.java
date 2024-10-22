import java.io.*;
import java.util.*;

public class Main {
    static class spiritPoint {
        int r, c, d;

        public spiritPoint(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    static class spiritIdxPoint {
        int idx, r, c;

        public spiritIdxPoint(int idx, int r, int c) {
            this.idx = idx;
            this.r = r;
            this.c = c;
        }
    }

    static int R, C, K;
    static spiritPoint[] spirits;
    static int[][] map, exit;
    static int answer = 0;

    // 위, 오, 아, 왼
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        spirits = new spiritPoint[K+1];
        map = new int[R+1][C+1];
        exit = new int[R+1][C+1];

        for(int k=1;k<=K;k++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            spirits[k] = new spiritPoint(-1, c, d);

            if(!moveGolem(k)) {
                // 초기화
                map = new int[R+1][C+1];
                exit = new int[R+1][C+1];
                continue;
            }

            // 정령 내려가기
            moveSpirit(k);
        }

        System.out.println(answer);
    }

    // 골렘 내리기
    private static boolean moveGolem(int idx) {
        int cr = spirits[idx].r;
        int cc = spirits[idx].c;
        int cd = spirits[idx].d;

        while(cr <= R-2) {
            // 남쪽으로 내려갈 수 있는지 확인
            if(isPossible(cr+1, cc-1) && isPossible(cr+2, cc) && isPossible(cr+1, cc+1)) {
                cr += 1;
                continue;
            }

            // 왼쪽으로 가서 내려갈 수 있는지 확인
            if(cc > 2 && isPossible(cr-1, cc-1) && isPossible(cr, cc-2) && isPossible(cr+1, cc-2) && isPossible(cr+1, cc-1) && isPossible(cr+2, cc-1)) {
                cr += 1;
                cc -= 1;
                cd = (cd+3) % 4;
                continue;
            }

            // 오른쪽으로 가서 내려갈 수 있는지 확인
            if(cc < C-1 && isPossible(cr-1, cc+1) && isPossible(cr, cc+2) && isPossible(cr+1, cc+2) && isPossible(cr+1, cc+1) && isPossible(cr+2, cc+1)) {
                cr += 1;
                cc += 1;
                cd = (cd+1) % 4;
                continue;
            }
            break;
        }

        // 골룸의 위치가 숲 안에 들어온 경우
        if(cr < 2) return false;

        // 숲 안에 들어오지 못한 경우 -> 골룸 설치
        // 1. 맵에 골룸 위치 정하기
        for(int d = 0; d< 4;d++) {
            map[cr + dr[d]][cc + dc[d]] = idx;
        }
        // 2. 정렬 위치 값 변경
        map[cr][cc] = idx;
        exit[cr + dr[cd]][cc + dc[cd]] = 1;
        spirits[idx].r = cr;
        spirits[idx].c = cc;
        spirits[idx].d = cd;

        return true;
    }

    // 정령 내리기
    private static void moveSpirit(int idx) {
        Queue<spiritIdxPoint> q = new LinkedList<>();
        boolean[][] visited = new boolean[R+1][C+1];

        q.add(new spiritIdxPoint(idx, spirits[idx].r, spirits[idx].c));
        visited[spirits[idx].r][spirits[idx].c] = true;

        int rowNum = 0;
        while(!q.isEmpty()) {
            spiritIdxPoint cur = q.poll();
            rowNum = Math.max(cur.r, rowNum);

            for(int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if(isNotRange(nr, nc) || map[nr][nc] == 0 || visited[nr][nc]) continue;

                if(exit[cur.r][cur.c] == 1) {         // 출구야!
                    visited[nr][nc] = true;
                    q.add(new spiritIdxPoint(map[nr][nc], nr, nc));
                }
                else {
                    // 골룸 이동 -> 근데 같은 골름 내부에서만 이동
                    if(map[cur.r][cur.c] == map[nr][nc]) {
                        visited[nr][nc] = true;
                        q.add(new spiritIdxPoint(map[nr][nc], nr, nc));
                    }
                }
            }
        }

        answer += rowNum;
    }

    private static boolean isNotRange(int r, int c) {
        return (r < 1 || c < 1 || r > R || c > C);
    }

    private static boolean isPossible(int r, int c) {
        if(r <= 0) return true;
        if(r < -1 || c < 0 || r > R || c > C) return false;
        return map[r][c] == 0;
    }
}