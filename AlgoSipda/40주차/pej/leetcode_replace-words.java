// [LEETCODE] https://leetcode.com/problems/replace-words/description/
// Trie 자료구조 

class Solution {
    static TrieNode root ;

    public String replaceWords(List<String> dictionary, String sentence) {
        root = new TrieNode();

        for(String word : dictionary) {
            insert(word);
        }

        StringBuilder sb = new StringBuilder();
        String[] words = sentence.split(" ");
        for(String word : words) {
            sb.append(search(word));
            sb.append(" ");
        }

        return sb.toString().trim();
    }
    static void insert(String word) {
        char[] item = word.toCharArray();
        TrieNode cur = root;
        for(char c : item) {
            int id = c - 'a';
            if(cur.children[id] == null) {
                cur.children[id] = new TrieNode();
            }
            cur = cur.children[id];
        }
        cur.isEnd = true;
    }
    
    static String search(String word) {
        char[] item = word.toCharArray();
        TrieNode cur = root;
        for(int i = 0; i < item.length; i++) {
            char c = item[i];
            int id = c - 'a';
            if(cur.children[id] == null) {
                return word;
            }
            if(cur.children[id].isEnd) {
                return word.substring(0, i + 1);
            }
            cur = cur.children[id];
        }
        return word;
    }

    static class TrieNode {
        boolean isEnd;
        TrieNode[] children;
        TrieNode() {
            this.isEnd = false;
            this.children = new TrieNode[26];
        }
    }
}