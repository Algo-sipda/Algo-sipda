import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] words = new String[N];
		
		for(int i=0;i<N;i++) {
			words[i] = br.readLine();
		}

		int max = 0;
		String word1 = "";
		String word2 = "";
		for(int i=0;i<N-1;i++) {
			for(int j=i+1; j<N;j++) {
				int cnt = 0;
				int len = Math.min(words[i].length(), words[j].length());
				
				for(int k=0;k<len;k++) {
					if(words[i].charAt(k) != words[j].charAt(k)) break;
					cnt++;
				}
				
				if(max < cnt) {
					max = cnt;
					word1 = words[i];
					word2 = words[j];
				}
			}
			
		}
		System.out.print(word1+"\n"+word2);
	}

}