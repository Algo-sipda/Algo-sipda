import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    
    public static class Node {
        int clipboard;
        int total;
        int time;
        
        public Node(int clipboard, int total, int time) {
            this.clipboard = clipboard;
            this.total = total;
            this.time = time;
        }
    }

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int s = Integer.parseInt(br.readLine());
		bfs(s);
	}

    static boolean[][] visited = new boolean[1001][1001];
    
	private static void bfs(int s) {

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 1, 0));
        visited[0][1] = true; 
        
        while(!q.isEmpty()) {
            Node current = q.poll();
            
            if(current.total == s) {
                System.out.println(current.time);
                return;
            }
            
            //클립보드에 저장
            q.offer(new Node(current.total, current.total, current.time + 1)); 
            
            
            // 클립보드에 있는 이모티콘 넣기
            if(current.clipboard != 0 && current.total + current.clipboard <= s && !visited[current.clipboard][current.total + current.clipboard]) {
                q.offer(new Node(current.clipboard, current.total + current.clipboard, current.time + 1));
                visited[current.clipboard][current.total + current.clipboard] = true;
            }
            
            //화면에 있는 이모티콘 중 하나 삭제
            if(current.total >= 1 && !visited[current.clipboard][current.total - 1]) {
                q.offer(new Node(current.clipboard, current.total - 1, current.time + 1));
                visited[current.clipboard][current.total - 1] = true;
            }
        }
	}
}