import java.io.*;
import java.util.*;

public class Main_19942_다이어트 {
	static class Ingredient {
		int p, f, s, v, cost;
		
		public Ingredient(int p, int f, int s, int v, int cost) {
			this.p = p;
			this.f = f;
			this.s = s;
			this.v = v;
			this.cost = cost;
		}
	}
	
	static int N, answer = Integer.MAX_VALUE;
	static int[] M = new int[4];
	static Map<Integer, Ingredient> ingredients;
	static List<Integer> choose;
	static List<Integer> answerChoose;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M.length;i++) {
			M[i] = Integer.parseInt(st.nextToken());
		}
		
		ingredients = new HashMap<>();
		choose = new ArrayList<>();
		answerChoose = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			ingredients.put(i, new Ingredient(p, f, s, v, cost));
		}
		
		dfs(0, 0, 0, 0, 0, 0);
		
		if(answer == Integer.MAX_VALUE) System.out.println(-1);
		else {
			System.out.println(answer);
			for(int a: answerChoose) {
				System.out.print((a+1)+" ");
			}
		}
	}
	
	public static void dfs(int stage, int sumP, int sumF, int sumS, int sumV, int sumCost) {
		if(sumCost > answer) return;
		
		if(sumP >= M[0] && sumF >= M[1] && sumS >= M[2] && sumV >= M[3]) {
			if(answer > sumCost) {
				answer = sumCost;
				answerChoose.clear();
				answerChoose.addAll(choose);
			}
			return;
		}
		
		if(stage >= N) return;
		
		choose.add(stage);
		Ingredient i = ingredients.get(stage);
		
		dfs(stage+1, sumP+i.p, sumF+i.f, sumS+i.s, sumV+i.v, sumCost + i.cost);
		choose.remove(Integer.valueOf(stage));
		
		dfs(stage+1, sumP, sumF, sumS, sumV, sumCost);
	}
}