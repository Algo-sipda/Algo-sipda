import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
	private static class Product {
		int w, v;

		public Product(int w, int v) {
			super();
			this.w = w;
			this.v = v;
		}
	}
	
	static int N, K;
	static Product[] products;
	static int[][] dp;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		products = new Product[N+1];
		dp = new int[N+1][K+1];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			products[i] = new Product(w, v);
		}
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=K;j++) {
				if(products[i].w>j)
					dp[i][j] = dp[i-1][j];
				else
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-products[i].w]+products[i].v);
			}
		}
		System.out.println(dp[N][K]);
	}
}