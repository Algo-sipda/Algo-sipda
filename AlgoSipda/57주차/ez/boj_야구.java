import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_야구 {
    static int N;
    static int[][] table;
    static int[] hitter;

    static int out;
    static int lastHitter;
    static Queue<Integer> score;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        table = new int[N][9];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 9; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        hitter = new int[9];
        permutation(0, 0);
        System.out.println(res);
    }

    private static void permutation(int cnt, int flag) {
        if (cnt == 9) {
            lastHitter = 0;
            int sum = 0;
            for (int i = 0; i < N; i++) {
                out = 0;
                score = new ArrayDeque<Integer>();
                sum += playGame(i);
            }
            res = Math.max(res, sum);
            return;
        }

        for (int i = 1; i < 9; i++) {
            if ((flag & 1 << i) != 0)
                continue;
            if (cnt == 3) {
                hitter[cnt] = 0;
                permutation(cnt + 1, (flag | 1 << 0));
                return;
            } else {
                hitter[cnt] = i;
                permutation(cnt + 1, (flag | 1 << i));
            }
        }
    }

    private static int playGame(int round) {
        int totalScore = 0;
        int idx = lastHitter;
        while (true) {
            boolean homerun = false;

            if (table[round][hitter[idx]] == 0) {
                out++;
            } else if (table[round][hitter[idx]] == 1) {
                score.add(1);
            } else if (table[round][hitter[idx]] == 2) {
                score.add(1);
                score.add(0);
            } else if (table[round][hitter[idx]] == 3) {
                score.add(1);
                score.add(0);
                score.add(0);
            } else if (table[round][hitter[idx]] == 4) {
                score.add(1);
                homerun = true;
            }
            idx++;
            if (idx == 9) {
                idx = 0;
            }
            if (out == 3) {
                lastHitter = idx;
                break;
            }

            if (homerun) {
                while (!score.isEmpty()) {
                    totalScore += score.poll();
                }
            } else {
                while (score.size() > 3) {
                    totalScore += score.poll();
                }
            }
        }
        return totalScore;
    }
}