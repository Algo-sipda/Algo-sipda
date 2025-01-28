import java.util.*;

public class pro_자동완성 {

    class Node {
        HashMap<Character, Node> child;
        boolean endOfWord;
        int cnt;

        public Node() {
            this.child = new HashMap<>();
            this.endOfWord = false;
            this.cnt = 0;
        }
    }

    class Trie {
        Node root;

        public Trie() {
            this.root = new Node();
        }

        void insert(String str) {
            Node node = this.root;

            for (int i = 0; i < str.length(); i++) {
                char lang = str.charAt(i);
                node.child.computeIfAbsent(lang, key -> new Node());
                node.child.get(lang).cnt += 1;
                node = node.child.get(lang);
            }
            node.endOfWord = true;
        }

        int search(String str) {
            Node node = this.root;
            int idx = 0;
            boolean isFrist = true;

            for (int i = 0; i < str.length(); i++) {
                char lang = str.charAt(i);
                if (isFrist && node.child.get(lang).cnt == 1) {
                    idx = i + 1;
                    isFrist = false;
                }
                node = node.child.get(lang);
            }

            // 마지막 노드가 leaf 노드가 아니면
            if (node.child.size() != 0) {
                return str.length();
            }

            return idx;
        }

    }

    public int solution(String[] words) {
        int answer = 0;
        Trie trie = new Trie();

        for (String word : words) {
            trie.insert(word);
        }

        for (String word : words) {
            answer += trie.search(word);
        }

        return answer;
    }
}
