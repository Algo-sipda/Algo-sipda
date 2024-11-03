import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new int[N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                if(Integer.parseInt(st.nextToken()) == 1) {
                    list[i]++;
                }
            }
        }

        for(int i=0;i<2;i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;

            for(int j=s;j<=e;j++) {
                if(list[j] == 0) continue;
                list[j]--;
            }
        }

        System.out.println(Arrays.stream(list).sum());
    }
}
