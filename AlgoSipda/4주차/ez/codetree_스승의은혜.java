import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int cnt = 0;
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[][] gift = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            gift[i][0] = Integer.parseInt(st.nextToken());
            gift[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(gift, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if ((o1[0] + o1[1]) == (o2[0] + o2[1])) {
                    return o1[0] - o2[0];
                }
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return (o1[0] + o1[1]) - (o2[0] + o2[1]);
            }
        });

        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (sum + gift[i][0] + gift[i][1] <= B) {
                sum += gift[i][0] + gift[i][1];
                cnt++;
                continue;
            }
            if (sum + gift[i][0] / 2 + gift[i][1] <= B) {
                sum += gift[i][0] / 2 + gift[i][1];
                cnt++;
            } else {
                break;
            }
        }

        System.out.println(cnt);
    }
}