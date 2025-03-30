import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	
	static HashMap<String, String> parents;
	static HashMap<String, Integer> numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case<=T;test_case++) {
			
			int N = Integer.parseInt(br.readLine());
			
			parents = new HashMap<>();
			numbers = new HashMap<>();
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine()," ");
				
				String f1 = st.nextToken();
				String f2 = st.nextToken();
				
				parents.putIfAbsent(f1, f1);
				numbers.putIfAbsent(f1, 1);
				
				parents.putIfAbsent(f2, f2);
				numbers.putIfAbsent(f2, 1);
				
				union(f1, f2);
				
				sb.append(numbers.get(find(f1))).append("\n");
			}
		}
		System.out.print(sb);
	}
	
	private static String find(String n) {
		if(n.equals(parents.get(n))){
			return n;
		}
		
		String p = find(parents.get(n));
		parents.replace(n, p);
		return p;
	}
	
	private static void union(String n1, String n2) {
		n1 = find(n1);
		n2 = find(n2);
		
		if(n1.equals(n2)) return;
		
		parents.put(n2, n1);//n1 밑에 n2가 들어감
		numbers.replace(n1, numbers.get(n2)+numbers.get(n1));
	}
}
