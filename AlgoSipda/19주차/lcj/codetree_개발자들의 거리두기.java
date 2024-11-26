import java.io.*;
import java.util.*;

public class Main {

    static class Info implements Comparable<Info> {
        int loc;
        boolean infection;

        public Info(int loc, boolean infection) {
            this.loc = loc;
            this.infection = infection;
        }

        @Override
        public int compareTo(Info o) {
            return this.loc - o.loc;
        }
    }
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        List<Info> info = new ArrayList<>();

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int loc = Integer.parseInt(st.nextToken());
            int infec = Integer.parseInt(st.nextToken());
            info.add(new Info(loc, infec == 1?true:false));
        }

        Collections.sort(info);

        int minDist= Integer.MAX_VALUE;
        for(int i=0;i<N;i++) {
            if(!info.get(i).infection) {
                if(i != 0 && info.get(i-1).infection) {
                    minDist = Math.min(minDist, info.get(i).loc - info.get(i-1).loc-1);
                }
                if(i != (N-1) && info.get(i+1).infection) {
                    minDist = Math.min(minDist, info.get(i+1).loc - info.get(i).loc-1);
                }
            }
        }

        int dist = Integer.MAX_VALUE;
        int cnt = 0;
        for(int i=0;i<N;i++) {
            if(!info.get(i).infection) continue;
            if(dist + minDist < info.get(i).loc) cnt++;
            dist = info.get(i).loc;
        }

        System.out.println(cnt);
    }
}