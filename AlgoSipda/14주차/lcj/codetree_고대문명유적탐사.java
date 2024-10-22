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

    static int K, M, curIdx = 0, answer;
    static int[][] map;
    static Queue<Integer> relicsNumber = new LinkedList<>();
    static Queue<Point> removePoint = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    static final int MAP_SIZE = 5;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[MAP_SIZE][MAP_SIZE];
        for(int i=0;i<MAP_SIZE;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<MAP_SIZE;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            relicsNumber.add(Integer.parseInt(st.nextToken()));
        }

        while(K-- > 0) {
            // System.out.println("K : "+K);
            if(!findRotationMax()) break; 
            // mapPrint(map);
            if(!getRelics()) continue;
        }

        System.out.println(sb);
    }

    // 회전 시 최대인 맵에서 유물 획득하기
    public static boolean getRelics() {
        answer = 0;
        int row = -1;
        while(true) {
            // 1. 유물 없애기
            row = removeRelics();
            // mapPrint(map);
            // System.out.println("answer: " + answer);
            // 2. 없어진 마지막 행에서 부터 0열부터 쭈우욱 유물 넣어주기
            if(row == -1) break;
            addRelics(row);
            // mapPrint(map);
        }
        if(answer > 0) {
            // System.out.println(answer);
            sb.append(answer+" ");
        }
        
        if(row == -1) return false;
        return true;
    }

    // 유물 넣기
    public static void addRelics(int row) {
        // System.out.println("시작");
        // mapPrint(map);
        if(row == -1) return;
        for(int j=0;j<MAP_SIZE;j++) {
            for(int i=row; i>=0;i--) {
                if(map[i][j] == 0) {
                    map[i][j] = relicsNumber.poll();
                }
            }
        }
        // mapPrint(map);
    }

    // 유물 제거하기
    public static int removeRelics() {
        boolean[][] visited = new boolean[MAP_SIZE][MAP_SIZE];
        int maxRow = -1;
        for(int i=0;i<MAP_SIZE;i++) {
            for(int j=0;j<MAP_SIZE;j++) {
                if(!visited[i][j]) {
                    removePoint.clear();
                    bfs(i, j, map, visited);
                    if(removePoint.size() >= 3) {
                        while(!removePoint.isEmpty()) {
                            Point cur = removePoint.poll();
                            map[cur.r][cur.c] = 0;
                            maxRow = Math.max(maxRow, cur.r);
                            answer++;
                        }
                    }
                }
            }
        }
        return maxRow;
    }

    // 회전 시 최대 개수인 부분 찾기
    public static boolean findRotationMax() {

        int maxR=0, maxC=0;
        int rot = 3;
        int max = 0;

        for(int i=0;i<MAP_SIZE-2;i++) {
            for(int j=0;j<MAP_SIZE-2;j++) {
                int[][] copyMap = new int[MAP_SIZE][MAP_SIZE];

                copyMap = mapCopy(map);

                for(int r = 0; r<3; r++) {
                    copyMap = rotation3x3(j, i, copyMap);
                    // 1. 3이상인거 개수 세기
                    int cnt = countRelics(copyMap);

                    if(max < cnt) {
                        max = cnt;
                        rot = r;
                        maxR = j;
                        maxC = i;
                    }
                    else if(max == cnt) {
                        // rotation 비교
                        if(rot > r) {
                            rot = r;
                            maxR = j;
                            maxC = i;
                        }
                    }
                }
            }
        }

        if(max == 0) return false;
        for(int r=0;r<=rot;r++) {
            map = rotation3x3(maxR, maxC, map);
        }
        return true;
    }

    // 보물 개수 세기
    public static int countRelics(int[][] curMap) {
        boolean[][] visited = new boolean[MAP_SIZE][MAP_SIZE];
        int totalCnt = 0;

        for(int i=0;i<MAP_SIZE;i++) {
            for(int j=0;j<MAP_SIZE;j++) {
                if(!visited[i][j]) {
                    totalCnt += bfs(i, j, curMap, visited);
                }
            }
        }
        return totalCnt;
    }

    // map 탐방하면서 3개 이상 있는 것 개수 세기
    public static int bfs(int r, int c, int[][] curMap, boolean[][] visited) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c));
        removePoint.add(new Point(r, c));
        visited[r][c] = true;
        int curNum = curMap[r][c];

        int cnt = 1;

        while(!q.isEmpty()) {
            Point cur = q.poll();

            for(int d = 0; d<4;d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if(!isRange(nr, nc)) continue;
                if(visited[nr][nc]) continue;
                if(curMap[nr][nc] == curNum) {
                    q.add(new Point(nr, nc));
                    removePoint.add(new Point(nr, nc));
                    visited[nr][nc] = true;
                    cnt++;
                }
            }
        }
        return cnt>=3?cnt: 0;
    }

    // 범위 안에 들어있는지
    public static boolean isRange(int r, int c) {
        return 0<=r && r<MAP_SIZE && 0<=c && c<MAP_SIZE;
    }

    // 3x3 회전하기(시작점에서 부터)
    public static int[][] rotation3x3(int r, int c, int[][] originMap) {
        int[][] rotationMap = new int[MAP_SIZE][MAP_SIZE];
        rotationMap = mapCopy(originMap);

        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                rotationMap[r+i][c+j] = originMap[r+2-j][c+i];
            }
        }

        return rotationMap;
    }

    // map 복사하기
    public static int[][] mapCopy(int[][] mapOrigin) {
        int[][] copyMap = new int[MAP_SIZE][MAP_SIZE];
        
        for(int i=0;i<MAP_SIZE;i++) {
            for(int j=0;j<MAP_SIZE;j++) {
                copyMap[i][j] = mapOrigin[i][j];
            }
        }
        return copyMap;
    }

    // map 출력하기
    public static void mapPrint(int[][] curMap) {
        System.out.println("====================================");
        for(int i=0;i<MAP_SIZE;i++) {
            for(int j=0;j<MAP_SIZE;j++) {
                System.out.print(curMap[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("====================================");
    }
}