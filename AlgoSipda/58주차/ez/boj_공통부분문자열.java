import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_공통부분문자열 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        int max = 0;
        int[][] map = new int[a.length()+1][b.length()+1];

        for (int i=1; i<=a.length(); i++) {
            for (int j=1; j<=b.length(); j++) {
                if (a.charAt(i-1) == b.charAt(j-1)) {
                    map[i][j] = map[i-1][j-1] + 1;
                    max = Math.max(max, map[i][j]);
                }
            }
        }

        System.out.println(max);
    }
}
