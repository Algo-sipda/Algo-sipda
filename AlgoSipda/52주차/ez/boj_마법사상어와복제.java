import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_마법사상어와복제 {
    public static int M, S;
    public static int sharkR, sharkC;
    public static int[][] smell;
    public static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    public static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static ArrayList<ArrayList<ArrayList<Integer>>> map;
    public static ArrayList<Integer> list;
    public static int max;
    public static int[] s_dr = {-1, 0, 1, 0};
    public static int[] s_dc = {0, -1, 0, 1};
    
    public static class Fish{
        int r, c, d;
        public Fish(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        smell = new int[4][4];
        Queue<Fish> firstQueue = new LinkedList<>();
        map = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            map.add(new ArrayList<>());
            for (int j = 0; j < 4; j++) {
                map.get(i).add(new ArrayList<>());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map.get(r - 1).get(c - 1).add(d - 1);
        }

        st = new StringTokenizer(br.readLine());
        sharkR = Integer.parseInt(st.nextToken()) - 1;
        sharkC = Integer.parseInt(st.nextToken()) - 1;

        int time = 1;
        while (time <= S) {
            // 큐 복제
            firstQueue.clear();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < map.get(i).get(j).size(); k++) {
                        firstQueue.offer(new Fish(i, j, map.get(i).get(j).get(k)));
                    }
                }
            }

            // 물고기 이동
            ArrayList<ArrayList<ArrayList<Integer>>> copy = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                copy.add(new ArrayList<>());
                for (int j = 0; j < 4; j++) {
                    copy.get(i).add(new ArrayList<>());
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < map.get(i).get(j).size(); k++) {
                        int firstDir =  map.get(i).get(j).get(k);
                        boolean check = true;
                        int nr = i, nc = j;

                        for (int d = 0; d < 8; d++) {
                            int nd = ((firstDir - d) + 8) % 8;
                            nr = i + dr[nd];
                            nc = j + dc[nd];
                            if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue;
                            if (smell[nr][nc] != 0 && time - smell[nr][nc] <= 2) continue;
                            if (nr == sharkR && nc == sharkC) continue;
                            copy.get(nr).get(nc).add(nd);
                            check = false;
                            break;
                        }

                        if (check) {
                            copy.get(i).get(j).add(firstDir);
                        }

                    }

                }
            }

            map = copy;

            // 상어 이동
            max = 0;
            list = new ArrayList<>();
            dfs(0, new int[3]);
            Collections.sort(list);
            String str = String.valueOf(list.get(0));
            for (int i = 0; i < 3; i++) {
                int dir = str.charAt(i) - '1';
                sharkR += s_dr[dir];
                sharkC += s_dc[dir];
                if (map.get(sharkR).get(sharkC).size() > 0) {
                    map.get(sharkR).get(sharkC).clear();
                    smell[sharkR][sharkC] = time;
                }

            }

            // 복제 
            while (!firstQueue.isEmpty()){
                Fish f = firstQueue.poll();
                map.get(f.r).get(f.c).add(f.d);
            }

            time++;
        }
        
        // 물고기 개수
        int ans = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ans += map.get(i).get(j).size();
            }
        }
        System.out.println(ans);
    }

    public static void dfs(int depth, int[] arr) {
        if (depth == 3) {
            int[] copy = new int[3];
            String str = "";
            for (int i = 0; i < 3; i++) {
                copy[i] = arr[i];
                str += String.valueOf(copy[i]);
            }

            int cnt = eatCount(str);

            if (max < cnt) {
                list.clear();
                list.add(Integer.parseInt(str));
                max = cnt;
            } else if (max == cnt) {
                list.add(Integer.parseInt(str));
            }
            return;
        }
        for (int i = 1; i <= 4; i++) {
            arr[depth] = i;
            dfs(depth + 1, arr);
        }
    }

    public static int eatCount(String dir){
        int r = sharkR;
        int c = sharkC;
        int cnt = 0;
        boolean[][] visited = new boolean[4][4];

        for (int i = 0; i < 3; i++) {
            int d = dir.charAt(i) - '1';
            r += s_dr[d];
            c += s_dc[d];
            if (r < 0 || c < 0 || r >= 4 || c >= 4) {
                return -1;
            }
            if (!visited[r][c]) cnt += map.get(r).get(c).size();
            visited[r][c] = true;
        }
        return cnt;
    }
}