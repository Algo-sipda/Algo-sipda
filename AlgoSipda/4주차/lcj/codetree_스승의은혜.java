import java.io.*;
import java.util.*;

public class Main {
    static class Gift {
        int p, s;

        public Gift(int p, int s) {
            this.p = p;
            this.s = s;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        PriorityQueue<Gift> pq = new PriorityQueue<>((o1, o2) -> {
            int sum1 = o1.p + o1.s;
            int sum2 = o2.p + o2.s;

            if(sum1 != sum2) {
                return sum1 - sum2;
            }
            return o2.p - o1.p;
        });

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            pq.add(new Gift(p, s));
        }

        int sum = 0;
        int cnt = 0;

        while(!pq.isEmpty()) {
            Gift gift = pq.poll();
            int curSum = gift.p + gift.s;

            if(sum + curSum <= B) {
                sum += curSum;
                cnt++;
            }
            else {
                gift.p /= 2;
                curSum = gift.p + gift.s;

                if(sum + curSum <= B) {
                    sum += curSum;
                    cnt++;
                }
                break;
            }
        }
        System.out.println(cnt);
    }
}