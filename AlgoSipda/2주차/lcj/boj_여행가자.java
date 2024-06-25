import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static int n, parent[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        parent = new int[n+1];
        for(int i = 1; i <= n; i++)
            parent[i] = i;
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                int link = Integer.parseInt(st.nextToken());
                if(j > i && link == 1)
                    union(i, j);
            }
        }
        st = new StringTokenizer(br.readLine());
        int root = -1;
        for(int i = 1; i <= m; i++) {
            int now = Integer.parseInt(st.nextToken());
            if(root == -1)
                root = find(now);
            if(root != find(now)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
    
    public static void union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);
        
        if(p1 > p2)
            parent[p1] = p2;
        else
            parent[p2] = p1;
    }
    
    public static int find(int n) {
        if(parent[n] == n)
            return n;
            
        return parent[n] = find(parent[n]);
    }
}