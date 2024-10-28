import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int wordCnt = words.length;
        List[] graph = new List[wordCnt];
        for (int i = 0; i < wordCnt; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < wordCnt - 1; i++) {
            for (int j = i + 1; j < wordCnt; j++) {
                if (canChange(words[i], words[j])) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }

        Queue<String> queue = new LinkedList<>();
        boolean[] visited = new boolean[wordCnt];
        queue.add(begin);
        int answer = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String now = queue.poll();
                if (now.equals(target)) return answer;
                for (int j = 0; j < wordCnt; j++) {
                    if (!visited[j] && canChange(now, words[j])) {
                        visited[j] = true;
                        queue.add(words[j]);
                    }
                }
            }
            answer++;
        }

        return 0;
    }
    
    public boolean canChange(String now, String target) {
        int diff = 0;
        for (int i = 0; i < now.length(); i++) {
            if (now.charAt(i) != target.charAt(i)) {
                diff++;
                if (diff > 1) return false;
            }
        }
        return true;
    }
}