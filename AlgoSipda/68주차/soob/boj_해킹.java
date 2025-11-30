import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());
        for (int t = 0; t < tc; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            List[] dependList = new ArrayList[n+1];
            for (int i = 0; i <= n; i++) {
                dependList[i] = new ArrayList<Point>();
            }
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                dependList[b].add(new Point(a, s));
            }

            Queue<Point> queue = new LinkedList<Point>();
            queue.add(new Point(c, 0));
            int[] hacked = new int[n+1];
            Arrays.fill(hacked, -1);
            hacked[c] = 0;
            while (!queue.isEmpty()){
                int s = queue.size();
                for (int i = 0; i < s; i++) {
                    Point p = queue.poll();
                    for(Object object : dependList[p.x]){
                        Point point = (Point)object;
                        if(hacked[point.x] == -1 || hacked[point.x] > point.y + p.y){
                            hacked[point.x] = point.y + p.y;
                            queue.add(new Point(point.x, point.y + p.y));
                        }
                    }
                }
            }
            int totalHacked = 0, lastTime = 0;
            for(int hackedComputer : hacked){
                if(hackedComputer != -1){
                    totalHacked++;
                    lastTime = Math.max(lastTime, hackedComputer);
                }
            }
            System.out.println(totalHacked + " " + lastTime);
        }

    }

}