import java.util.*;
import java.io.*;

public class Main{
	
	static class Point {
	    int r, c;

	    Point(int r, int c){
	        this.r = r;
	        this.c = c;
	    }
	}
	
    static int N, l, M;
    static int res = 0;
    static ArrayList<Point> fish = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            fish.add(new Point(r, c));
        }

        for(int i=0;i<M;i++){
            for(int j=0;j<M;j++){
                catchFish(i,j);
            }
        }

        System.out.println(res);
    }

    static void catchFish(int fish1, int fish2){
        int r = fish.get(fish1).r;
        int c = fish.get(fish2).c;

        int v = 1;
        int h = (l - 2*v)/2;

        while(h>0){
            int cnt = 0;

            for(int i=0;i<M;i++){
                if(c <= fish.get(i).c && fish.get(i).c <= c + h 
                		&& r <= fish.get(i).r && fish.get(i).r <= r + v)
                    cnt++;
            }

            if(res < cnt) res = cnt;

            v++;
            h = (l - 2*v)/2;
        }
    }
}