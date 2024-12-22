import java.io.*;
import java.util.*;

public class Main {
    static class Village implements Comparable<Village> {
        long x, a;

        Village(long x, long a) {
            this.x = x;
            this.a = a;
        }

        @Override
        public int compareTo(Village o) {
            return Long.compare(this.x, o.x);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Village> villages = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            villages.add(new Village(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }

        Collections.sort(villages);

        long total = 0;

        for (Village village : villages) {
            total += village.a;
        }

        long mid = (total + 1) / 2;
        long sum = 0;

        for (Village village : villages) {
            sum += village.a;
            if (sum >= mid) {
                System.out.println(village.x);
                return;
            }
        }
    }
}

// total이 홀수일 때 주의
