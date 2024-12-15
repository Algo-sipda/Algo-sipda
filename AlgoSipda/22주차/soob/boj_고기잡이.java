import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, I, M;
    static List<int[]> fish;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        I = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        fish = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            fish.add(new int[]{y, x});
        }

        System.out.println(getMax());
    }

    private static int getMax() {
        int max = 0;

        for (int netX = 1; netX < I / 2; netX++) {
            int netY = I / 2 - netX;

            for (int[] f : fish) {
                int baseY = f[0];
                int baseX = f[1];

                for (int startY = Math.max(1, baseY - netY); startY <= Math.min(N - netY, baseY); startY++) {
                    for (int startX = Math.max(1, baseX - netX); startX <= Math.min(N - netX, baseX); startX++) {
                        int fishCount = 0;

                        for (int[] target : fish) {
                            if (target[0] >= startY && target[0] <= startY + netY && target[1] >= startX && target[1] <= startX + netX) {
                                fishCount++;
                            }
                        }

                        max = Math.max(max, fishCount);
                    }
                }
            }
        }
        return max;
    }
}