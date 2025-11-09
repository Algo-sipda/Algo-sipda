import java.io.*;
import java.util.*;

public class Main{
    static int N, M;
    static long sum, cnt;
    static ArrayList<Integer> req = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        long max = 0;
        long min = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            req.add(Integer.parseInt(st.nextToken()));
            max += req.get(i);
            if(min < req.get(i)) min = req.get(i);
        }
        while(min<=max){
            long mid = (min + max)/2;
            sum = 0;
            cnt = 0;

            for(int i=0;i<N;i++){
                if(sum + req.get(i) > mid){
                    cnt++;
                    sum = 0;
                }
                sum += req.get(i);
            }
            if(sum != 0) cnt++;
            if(cnt<=M) max = mid-1;
            else min = mid+1;
        }
        System.out.println(min);
    }
}