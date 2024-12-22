import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] stars = new int[k][2];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i][0] = Integer.parseInt(st.nextToken());
            stars[i][1] = Integer.parseInt(st.nextToken());
        }

        int maxStarsCovered = 0;

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                int x1 = stars[i][0];
                int y1 = stars[j][1];

                int count = 0;
                for (int[] star : stars) {
                    if (star[0] >= x1 && star[0] <= x1 + l &&
                            star[1] >= y1 && star[1] <= y1 + l) {
                        count++;
                    }
                }

                maxStarsCovered = Math.max(maxStarsCovered, count);
            }
        }

        System.out.println(k - maxStarsCovered);
    }
}