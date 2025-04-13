import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int y, x;
        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static final int MAX = 40001;
    static int[] surface = new int[MAX];
    static int[] depth = new int[MAX];
    static Node[] hole;
    static int N, K, ans, lastX;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        br.readLine();
        for (int i = 0; i < N / 2 - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for (int x = x1; x <= x2; x++) {
                depth[x] = y1;
            }
            lastX = x2 - 1;
        }
        br.readLine();

        K = Integer.parseInt(br.readLine());
        hole = new Node[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            hole[i] = new Node(y, x);
        }

        for (Node cur : hole) {
            int h = cur.y;
            int x = cur.x;
            for (int i = x; i >= 0; i--) {
                h = Math.min(h, depth[i]);
                surface[i] = Math.max(surface[i], h);
            }
            h = cur.y;
            for (int i = x; i <= lastX; i++) {
                h = Math.min(h, depth[i]);
                surface[i] = Math.max(surface[i], h);
            }
        }

        for (int i = 0; i <= lastX; i++) {
            ans += depth[i] - surface[i];
        }

        System.out.println(ans);
    }
} 
