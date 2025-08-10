import java.io.*;
import java.util.*;
public class Main {
    static int M, S;
    static ArrayList<Integer>[][] fish;
    static int[][] smell;
    static int sharkR, sharkC;
    static int[] drFish = {0, -1, -1, -1, 0, 1, 1, 1}; 
    static int[] dcFish = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] drShark = {-1, 0, 1, 0}; // 상좌하우
    static int[] dcShark = {0, -1, 0, 1};
    static int maxEat, bestDir[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        fish = new ArrayList[4][4];
        smell = new int[4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                fish[i][j] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            fish[x][y].add(d);
        }

        st = new StringTokenizer(br.readLine());
        sharkR = Integer.parseInt(st.nextToken()) - 1;
        sharkC = Integer.parseInt(st.nextToken()) - 1;

        while (S-- > 0) {
            // 1. 복제 마법 준비  -> 현재 물고기 상태를 복사
            ArrayList<Integer>[][] copy = new ArrayList[4][4];
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    copy[i][j] = new ArrayList<>(fish[i][j]);

            // 2. 물고기 이동
            moveFish();

            // 3. 상어 이동 (3칸)
            moveShark();

            // 4. 냄새 감소
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    if (smell[i][j] > 0) smell[i][j]--;

            // 5. 복제 마법 완료
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    fish[i][j].addAll(copy[i][j]);
        }

        // 결과 출력
        int ans = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                ans += fish[i][j].size();
        System.out.println(ans);
    }

    static void moveFish() {
        ArrayList<Integer>[][] next = new ArrayList[4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                next[i][j] = new ArrayList<>();

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                for (int d : fish[r][c]) {
                    boolean moved = false;
                    for (int k = 0; k < 8; k++) { // 8방향을 모두 시도
                        // 현재 방향에서 k만큼 회전
                        // (k=0: 현재 방향 유지, k=1: 왼쪽으로 1칸 회전, ..., k=7: 오른쪽으로 1칸 회전)     
                        int nd = (d - k + 8) % 8;
                        int nr = r + drFish[nd];
                        int nc = c + dcFish[nd];
                        if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue;
                        if (smell[nr][nc] > 0) continue;
                        if (nr == sharkR && nc == sharkC) continue;
                        next[nr][nc].add(nd);   // 이동 가능한 위치에 물고기 추가
                        moved = true;
                        break;
                    }
                    if (!moved) next[r][c].add(d);
                }
            }
        }
        fish = next;
    }

    static void moveShark() {
        maxEat = -1;
        bestDir = new int[3];
        dfs(sharkR, sharkC, 0, 0, new boolean[4][4], new int[3]);

        // 경로 적용
        for (int i = 0; i < 3; i++) {
            sharkR += drShark[bestDir[i]];
            sharkC += dcShark[bestDir[i]];
            if (!fish[sharkR][sharkC].isEmpty()) {
                fish[sharkR][sharkC].clear();
                smell[sharkR][sharkC] = 2;
            }
        }
    }

    static void dfs(int r, int c, int depth, int eat, boolean[][] visited, int[] path) {
        if (depth == 3) {
            if (eat > maxEat || (eat == maxEat && comparePath(path, bestDir) < 0)) {
                maxEat = eat;
                bestDir = path.clone();
            }
            return;
        }
        for (int dir = 0; dir < 4; dir++) {
            int nr = r + drShark[dir];
            int nc = c + dcShark[dir];
            if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue;
            int add = 0;
            if (!visited[nr][nc]) add = fish[nr][nc].size();
            visited[nr][nc] = true;
            path[depth] = dir;
            dfs(nr, nc, depth + 1, eat + add, visited, path);
            visited[nr][nc] = false;
        }
    }

    static int comparePath(int[] a, int[] b) {
        for (int i = 0; i < 3; i++) {
            if (a[i] != b[i]) return a[i] - b[i];
        }
        return 0;
    }
}
