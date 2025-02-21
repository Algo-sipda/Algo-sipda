import java.util.*;

class pro_카드짝맞추기 {

    int total, answer;
    int[][] map;
    State[][] cards;
    boolean[] isExist;

    final int[] dr = {0, -1, 1, 0};
    final int[] dc = {-1, 0, 0, 1};

    class State {
        int r, c, d;
        public State(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public int solution(int[][] board, int r, int c) {
        answer = Integer.MAX_VALUE;
        map = board;
        cards = new State[7][2];
        isExist = new boolean[7];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) continue;
                if (isExist[map[i][j]]) {
                    cards[map[i][j]][1] = new State(i, j, 0);
                } else {
                    cards[map[i][j]][0] = new State(i, j, 0);
                    total++;
                    isExist[map[i][j]] = true;
                }
            }
        }

        perm(0, 0, r, c);

        return answer;
    }

    private void perm(int cnt, int dis, int r, int c) {
        if (cnt == total) {
            answer = Math.min(answer, dis);
            return;
        }

        for (int i = 1; i < 7; i++) {
            if (!isExist[i]) continue;
            int dis1 = calcDis(r, c, cards[i][0].r, cards[i][0].c) + calcDis(cards[i][0].r, cards[i][0].c, cards[i][1].r, cards[i][1].c) + 2;
            int dis2 = calcDis(r, c, cards[i][1].r, cards[i][1].c) + calcDis(cards[i][1].r, cards[i][1].c, cards[i][0].r, cards[i][0].c) + 2;

            map[cards[i][0].r][cards[i][0].c] = 0;
            map[cards[i][1].r][cards[i][1].c] = 0;
            isExist[i] = false;
            if (dis1 < dis2) {
                perm(cnt + 1, dis + dis1, cards[i][1].r, cards[i][1].c);
            } else {
                perm(cnt + 1, dis + dis2, cards[i][0].r, cards[i][0].c);
            }
            map[cards[i][0].r][cards[i][0].c] = i;
            map[cards[i][1].r][cards[i][1].c] = i;
            isExist[i] = true;
        }
    }

    private int calcDis(int startR, int startC, int endR, int endC) {
        Queue<State> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        queue.add(new State(startR, startC, 0));
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {
            State cur = queue.poll();
            if (cur.r == endR && cur.c == endC) {
                return cur.d;
            }
            // 상하좌우
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (isOut(nr, nc) || visited[nr][nc]) continue;

                visited[nr][nc] = true;
                queue.add(new State(nr, nc, cur.d + 1));
            }

            // ctrl 상하좌우
            for (int i = 0; i < 4; i++) {
                int r = cur.r;
                int c = cur.c;

                while (true) {
                    r += dr[i];
                    c += dc[i];

                    if (isOut(r, c)) {
                        r -= dr[i];
                        c -= dc[i];
                        break;
                    }

                    if (map[r][c] != 0) {
                        break;
                    }
                }
                if (visited[r][c]) continue;
                visited[r][c] = true;
                queue.add(new State(r, c, cur.d + 1));

            }
        }

        return -1;
    }

    private boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= map.length || nc >= map[0].length;
    }
}