import java.io.*;
import java.util.*;

public class Main {
    static class Place {
        int r, c;
        boolean isRoad;
        List<Soldier> soldierList;

        public Place(int r, int c, boolean isRoad) {
            this.r = r;
            this.c = c;
            this.isRoad = isRoad;
            soldierList = new ArrayList<>();
        }
    }

    static class Soldier {
        int r, c, idx;
        boolean isStone;

        public Soldier(int idx, int r, int c) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            isStone = false;
        }
    }

    static int N, M;
    static int sr, sc, er, ec;
    static Place[][] map;
    static int[][] seeMap, dp;
    static Soldier[] soldiers;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());
        er = Integer.parseInt(st.nextToken());
        ec = Integer.parseInt(st.nextToken());

        map = new Place[N][N];
        dp = new int[N][N];
        soldiers = new Soldier[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            Soldier soldier = new Soldier(i, r, c);
            soldiers[i] = soldier;
        }

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                int k = Integer.parseInt(st.nextToken());
                boolean isRoad = (k == 0);
                
                Place place = new Place(i, j, isRoad);
                map[i][j] = place;

                if(!map[i][j].isRoad)
                    dp[i][j] = Integer.MAX_VALUE;
            }
        }

        init();

        if(dp[sr][sc] == Integer.MAX_VALUE || dp[sr][sc] == 0) {
            System.out.println(-1);
            return;
        }

        for(Soldier soldier : soldiers) {
            map[soldier.r][soldier.c].soldierList.add(soldier);
        }

        while(sr != er || sc != ec) {
            // 1. 메두사 이동
            boolean isArrive = move();
            if(isArrive) break;

            // 2. 메두사 시선
            int stopCnt = medusaSee();

            // 3. 전사이동(1)
            int moveSoldierCnt = moveSoldier(true);

            // 4. 전사이동(2)
            moveSoldierCnt += moveSoldier(false);

            // 5. 전사공격
            int killSoldierCnt = attackSoldier();

            // 6. 돌 초기화
            for(Soldier soldier: soldiers) {
                if(soldier != null)
                    soldier.isStone = false;
            }
            
            // 7. 출력물 추가
            sb.append(moveSoldierCnt).append(" ").append(stopCnt).append(" ").append(killSoldierCnt).append("\n");
        }

        sb.append("0");
        System.out.println(sb);
    }

    private static void init() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{er, ec});
        dp[er][ec] = 1;

        while(!q.isEmpty()) {
            int r = q.peek()[0];
            int c = q.poll()[1];

            for(int d = 0; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(nr<0 || nr>=N || nc < 0 || nc >= N || dp[nr][nc] != 0 || !map[nr][nc].isRoad)
                    continue;

                dp[nr][nc] = dp[r][c] + 1;
                q.add(new int[]{nr, nc});
            }
        }
    }

    private static boolean move() {
        int prev = dp[sr][sc];

        for(int d = 0; d<4;d++) {
            int nr = sr + dr[d];
            int nc = sc + dc[d];

            if(nr<0 || nr>=N || nc < 0 || nc >= N || dp[nr][nc] != prev-1)
                continue;

            List<Soldier> list = map[nr][nc].soldierList;

            for(Soldier soldier : list) {
                soldiers[soldier.idx] = null;
            }

            map[nr][nc].soldierList.clear();

            sr = nr;
            sc = nc;
            break;
        }

        return sr == er && sc == ec;
    }

    private static int medusaSee() {
        List<Set<Soldier>> list = new ArrayList<>();
        int[][][] tmp = new int[4][N][N];

        for(int i=0;i<4;i++) {
            list.add(new HashSet<>());
        }

        // 위
        seeUp(tmp[0], list.get(0));
        // 아래
        seeDown(tmp[1], list.get(1));
        // 왼
        seeLeft(tmp[2], list.get(2));
        // 오른쪽
        seeRight(tmp[3], list.get(3));

        int max = -1;
        for(int i=0;i<4;i++) {
            max = Math.max(max, list.get(i).size());
        }

        for(int i=0;i<4;i++) {
            if(list.get(i).size() == max) {
                for(Soldier soldier : list.get(i)) {
                    soldier.isStone = true;
                }
                seeMap = tmp[i];
                return list.get(i).size();
            }
        }

        return 0;
    }

    private static void seeUp(int[][] tmp, Set<Soldier> hs) {
        if(sr == 0) return;

        int dist = 2;

        for(int i = sr - 1; i>=0;i--) {
            for(int j=0;j<N;j++) {
                int diff = Math.abs(i-sr) + Math.abs(j-sc);

                if(diff > dist || tmp[i][j] != 0) continue;

                tmp[i][j] = -1;

                if(map[i][j].soldierList.isEmpty()) continue;

                hs.addAll(map[i][j].soldierList);

                if(i == 0) continue;

                int bias = 1;

                for(int m = i-1;m>=0;m--) {
                    int startC, endC;

                    if(j < sc) startC = j-bias;
                    else startC = j;

                    if(j > sc) endC = j+bias;
                    else endC = j;

                    for(int n = startC;n<=endC;n++) {
                        if(m>=N || n<0 || n>=N) continue;

                        tmp[m][n] = 1;
                    }

                    bias++;
                }
            }
            dist += 2;
        }
    }

    private static void seeDown(int[][] tmp, Set<Soldier> hs) {
        if(sr == N-1) return;

        int dist = 2;

        for(int i = sr + 1; i<N;i++) {
            for(int j=0;j<N;j++) {
                int diff = Math.abs(i-sr) + Math.abs(j-sc);

                if(diff > dist || tmp[i][j] != 0) continue;

                tmp[i][j] = -1;

                if(map[i][j].soldierList.isEmpty()) continue;

                hs.addAll(map[i][j].soldierList);

                if(i == N-1) continue;

                int bias = 1;

                for(int m = i+1;m<N;m++) {
                    int startC, endC;

                    if(j < sc) startC = j-bias;
                    else startC = j;

                    if(j > sc) endC = j+bias;
                    else endC = j;

                    for(int n = startC;n<=endC;n++) {
                        if(m < 0 || m >= N || n < 0 || n >= N) continue;

                        tmp[m][n] = 1;
                    }

                    bias++;
                }
            }
            dist += 2;
        }
    }

    private static void seeLeft(int[][] tmp, Set<Soldier> hs) {
        if(sc == 0) return;

        int dist = 2;

        for(int i = sc - 1; i>=0;i--) {
            for(int j=0;j<N;j++) {
                int diff = Math.abs(i-sc) + Math.abs(j-sr);

                if(diff > dist || tmp[j][i] != 0) continue;

                tmp[j][i] = -1;

                if(map[j][i].soldierList.isEmpty()) continue;

                hs.addAll(map[j][i].soldierList);

                if(i == 0) continue;

                int bias = 1;

                for(int m = i-1;m>=0;m--) {
                    int startR, endR;

                    if(j < sr) startR = j-bias;
                    else startR = j;

                    if(j > sr) endR = j+bias;
                    else endR = j;

                    for(int n = startR;n<=endR;n++) {
                        if(m>=N || n<0 || n>=N) continue;

                        tmp[n][m] = 1;
                    }

                    bias++;
                }
            }
            dist += 2;
        }
    }

    private static void seeRight(int[][] tmp, Set<Soldier> hs) {
        if(sc == N-1) return;

        int dist = 2;

        for(int i = sc + 1; i<N;i++) {
            for(int j=0;j<N;j++) {
                int diff = Math.abs(i-sc) + Math.abs(j-sr);

                if(diff > dist || tmp[j][i] != 0) continue;

                tmp[j][i] = -1;

                if(map[j][i].soldierList.isEmpty()) continue;

                hs.addAll(map[j][i].soldierList);

                if(i == N-1) continue;

                int bias = 1;

                for(int m = i+1;m<N;m++) {
                    int startR, endR;

                    if(j < sr) startR = j-bias;
                    else startR = j;

                    if(j > sr) endR = j+bias;
                    else endR = j;

                    for(int n = startR;n<=endR;n++) {
                        if(m < 0 || m >= N || n < 0 || n >= N) continue;

                        tmp[n][m] = 1;
                    }

                    bias++;
                }
            }
            dist += 2;
        }
    }

    private static int moveSoldier(boolean isFirst) {
        int[] dir = {3, 2, 1, 0};
        if(!isFirst) dir = new int[]{1, 0, 3, 2};

        int cnt = 0;

        for(Soldier soldier : soldiers) {
            if(soldier == null || soldier.isStone) continue;
            if(soldier.r == sr && soldier.c == sc) continue;

            int prevDiff = Math.abs(soldier.r - sr) + Math.abs(soldier.c - sc);

            int min = prevDiff;
            int nextR = -1, nextC = -1;

            for(int d = 0; d<4;d++) {
                int nr = soldier.r + dr[dir[d]];
                int nc = soldier.c + dc[dir[d]];

                if(nr < 0 || nr >=N || nc<0 || nc >=N) continue;
                if(seeMap[nr][nc] == -1) continue;

                int diff = Math.abs(nr - sr) + Math.abs(nc - sc);

                if(diff <= min) {
                    min = diff;
                    nextR = nr;
                    nextC = nc;
                }
            }

            if(min != prevDiff) {
                map[soldier.r][soldier.c].soldierList.remove(soldier);
                map[nextR][nextC].soldierList.add(soldier);
                soldier.r = nextR;
                soldier.c = nextC;
                cnt++;
            }
        }
        return cnt;
    }

    private static int attackSoldier() {
        int cnt = 0;

        for(Soldier soldier : soldiers) {
            if(soldier == null) continue;

            if(soldier.r != sr || soldier.c != sc) continue;

            map[soldier.r][soldier.c].soldierList.remove(soldier);
            soldiers[soldier.idx] = null;
            cnt++;
        }

        return cnt;
    }
}