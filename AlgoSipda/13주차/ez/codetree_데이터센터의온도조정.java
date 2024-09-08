import java.util.*;
import java.io.*;

public class Main {

    static int N, C, G, H;
    static List<Integer> temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == 0) {
                if (map.containsKey(a)) {
                    map.put(a, map.get(a) + G);
                } else {
                    map.put(a, G);
                }
            } else {
                if (map.containsKey(a)) {
                    map.put(a, map.get(a) + G - C);
                } else {
                    map.put(a, G - C);
                }
            }
            if (a != 0) {
                if (map.containsKey(0)) {
                    map.put(0, map.get(0) + C);
                } else {
                    map.put(0, C);
                }
            }

            if (map.containsKey(b + 1)) {
                map.put(b + 1, map.get(b + 1) + H - G);
            } else {
                map.put(b + 1, H - G);
            }
        }

        temp = new ArrayList<>(map.keySet());
        Collections.sort(temp);
        int sum = 0;
        int res = 0;
        for (int key : temp) {
            sum += map.get(key);
            res = Math.max(res, sum);
        }
        System.out.println(res);
    }
}