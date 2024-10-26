class Solution {

    static String[] wordList;
    static int answer = 51;

    public int solution(String begin, String target, String[] words) {
        wordList = words;
        dfs(begin, target, 0, new boolean[words.length]);
        return answer == 51 ? 0 : answer;
    }

    public void dfs(String word, String target, int count, boolean[] visited) {
        if(target.equals(word)) {
            answer = Math.min(answer, count);
            return;
        }

        for(int i = 0; i < wordList.length; i++) {
            if(visited[i]) continue;

            if(isSimilar(word, wordList[i])) {
                visited[i] = true;
                dfs(wordList[i], target, count + 1, visited);
                visited[i] = false;
            }
        }
    }

    public boolean isSimilar(String word, String target) {
        int count = 0;
        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) != target.charAt(i)) count++;
        }
        return count == 1;
    }
}