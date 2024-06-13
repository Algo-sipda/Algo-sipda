import java.util.*;

/*

1. 이차원 맵으로 석유 덩어리 번호 부여(2부터 ~)
2. hashmap으로 석유 번호별 석유량 계산
3. 가로 길이만큼 for문 돌려서 최대 석유량 계산

*/

class Solution {

    static int[][] map;
    static Map<Integer, Integer> oil;
    static int[] dr = { 1, 0, -1, 0};
    static int[] dc = { 0, 1, 0, -1};

    public int solution(int[][] land) {
        int answer = 0;
        map = copyMap(land);
        oil = new HashMap<>();
        int num = 2;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) {
                    int cnt = bfs(i, j, num);
                    oil.put(num++, cnt);
                }
            }
        }

        for (int i = 0; i < map[0].length; i++) {
            List<Integer> pick = new ArrayList<>();
            int sum = 0;
            for (int j = 0; j < map.length; j++) {
                if (map[j][i] == 0) continue;
                if (pick.contains(map[j][i])) continue;
                pick.add(map[j][i]);
                sum += oil.get(map[j][i]);
            }
            answer = Math.max(answer, sum);
        }
        return answer;
    }

    private int[][] copyMap(int[][] land) {
        int[][] copy = new int[land.length][land[0].length];
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                copy[i][j] = land[i][j];
            }
        }
        return copy;
    }

    private int bfs(int r, int c, int num) {
        Queue<int[]> queue = new ArrayDeque<>();
        int cnt = 1;
        map[r][c] = num;
        queue.add(new int[] { r, c });

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (nr < 0 || nc < 0 || nr >= map.length || nc >= map[0].length) continue;
                if (map[nr][nc] == 1) {
                    map[nr][nc] = num;
                    queue.add(new int[] { nr, nc });
                    cnt++;
                }
            }
        }
        return cnt;
    }
}