import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        dice = new int[n][6];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxTotal = 0;

        for (int i = 0; i < 6; i++) {
            int top = dice[0][i];
            int bottom = dice[0][getOpposite(i)];
            int total = getMaxSide(top, bottom, dice[0]);

            for (int j = 1; j < n; j++) {
                for (int k = 0; k < 6; k++) {
                    if (dice[j][k] == top) {
                        bottom = top;
                        top = dice[j][getOpposite(k)];
                        total += getMaxSide(top, bottom, dice[j]);
                        break;
                    }
                }
            }

            maxTotal = Math.max(maxTotal, total);
        }

        System.out.println(maxTotal);
    }

    static int getOpposite(int idx) {
        if (idx == 0)
            return 5;
        if (idx == 1)
            return 3;
        if (idx == 2)
            return 4;
        if (idx == 3)
            return 1;
        if (idx == 4)
            return 2;
        return 0;
    }

    static int getMaxSide(int top, int bottom, int[] sides) {
        int max = 0;
        for (int i = 0; i < 6; i++) {
            if (sides[i] != top && sides[i] != bottom) {
                max = Math.max(max, sides[i]);
            }
        }
        return max;
    }
}
