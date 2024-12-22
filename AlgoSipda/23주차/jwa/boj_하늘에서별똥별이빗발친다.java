import java.io.*;
import java.util.*;

public class Main {
    static class Star {
        int x, y;

        Star(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Star[] stars = new Star[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i] = new Star(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int answer = 0;

        for (int i = 0; i < K; i++) {
            for (int j = i; j < K; j++) {
                int count = 0;
                int x = Math.min(stars[i].x, stars[j].x);
                int y = Math.min(stars[i].y, stars[j].y);
                for (Star star : stars) {
                    if (x <= star.x && star.x <= x + L && y <= star.y && star.y <= y + L) {
                        count++;
                    }
                }
                answer = Math.max(answer, count);
            }
        }

        System.out.println(K - answer);
    }
}
