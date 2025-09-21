import java.io.*;

public class Main_1802_종이접기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc<T; tc++) {
			boolean isEqual = true;
			
			String str = br.readLine();
			int len = str.length();
			
			int[] input = new int[len];
			
			for(int i=0;i<len;i++) {
				input[i] = Integer.parseInt(str.charAt(i)+"");
			}
			
			while(isEqual && len > 1) {
				for(int i=0;i<len/2;i++) {
					if(input[i] + input[len-1-i] != 1) {
						isEqual = false;
						break;
					}
				}
				
				len /= 2;
			}
			
			sb.append(isEqual?"YES":"NO").append("\n");
		}
		System.out.print(sb);
	}

}