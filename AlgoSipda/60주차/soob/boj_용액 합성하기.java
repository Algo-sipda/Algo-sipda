import java.io.*;
import java.util.*;

public class Main
{
    static int n; // 전체 용액의 수
    static int[] a; // 각 용액의 특성값

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 전체 용액의 수
        a = new int[n]; // 용액의 특성값 배열
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(st.nextToken()); // 각 용액의 특성값 입력
        Arrays.sort(a);
        System.out.println(solve());
    }

    static int solve()
    {
        int l = 0, r = n - 1;
        long bestAbs = Long.MAX_VALUE;
        int bestSum = 0;
        while (l < r)
        {
            int s = a[l] + a[r];
            long abs = Math.abs((long)s);
            if (abs < bestAbs)
            {
                bestAbs = abs;
                bestSum = s;
            }
            if (s > 0)
                r--;
            else if (s < 0)
                l++;
            else
                return 0;
        }
        return bestSum;
    }
}
