import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_스티커붙이기 {

    static int N, M, K, res;
    static int[][] map, sticker;
    static PriorityQueue<State> pq;

    static class State implements Comparable<State> {
        int r, c;
        public State(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(State o) {
            if (this.r == o.r) {
                return this.c - o.c;
            }
            return this.r - o.r;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int t = 0; t < K; t++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            sticker = new int[R][C];
            int num = 0;
            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                    if (sticker[i][j] == 1) {
                        num++;
                    }
                }
            }

            int cnt = 0;
            while (true) {
                pq = new PriorityQueue<>();
                isAttach(sticker);
                if (pq.size() > 0) {
                    State cur = pq.poll();
                    attach(sticker, cur);
                    res += num;
                    break;
                }
                sticker = rotate(sticker);
                cnt++;
                if (cnt == 4) {
                    break;
                }
            }
        }
        System.out.println(res);
    }

    private static void attach(int[][] sticker, State cur) {
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                if (sticker[i][j] == 1) {
                    map[i + cur.r][j + cur.c] = sticker[i][j];
                }
            }
        }
    }

    private static void isAttach(int[][] sticker) {

        for (int i = 0; i <= N - sticker.length; i++) {
            for (int j = 0; j <= M - sticker[0].length; j++) {
                boolean flag = true;
                for (int n = 0; n < sticker.length; n++) {
                    for (int m = 0; m < sticker[0].length; m++) {
                        if (sticker[n][m] == 1 && map[i + n][j + m] == 1)  {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {
                    pq.add(new State(i, j));
                }
            }
        }
    }

    private static int[][] rotate(int[][] sticker) {
        int n = sticker.length;
        int m = sticker[0].length;

        int[][] newSticker = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newSticker[j][n - i - 1] = sticker[i][j];
            }
        }
        return newSticker;
    }
}

/*
1. 회전시키지 않고 붙일 수 있는 위치 찾기
- 겹치거나 벗어나면 안됨
- 붙일 수 있는 위치가 여러 곳이면 가장 위쪽, 왼쪽 선택 -> PQ(r, c)
2. 스티커 붙일 수 없으면
- 시계방향 90도 회전 후 다시 반복
3. 1-2번 4번 반복해서 했는데도 붙이지 못하면 버리기

result: 노트북에서 몇 개의 칸이 채워졌는지 구하기
붙인 스티커 칸 수 계속 더하는 방식으로

세로N, 가로M
스티커 개수 K
스티커 행, 열 개수 R, C
0 -> 붙지 않은 칸
1 -> 붙은 칸

스티커 어떻게 저장하지 -> ArrayList<int[][]> stickers
 */