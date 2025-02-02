import java.util.*;

class Solution {
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int count = 0;
    }

    static class Trie {
        TrieNode root = new TrieNode();

        void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                node.children.putIfAbsent(c, new TrieNode());
                node = node.children.get(c);
                node.count++;
            }
        }

        int search(String word) {
            TrieNode node = root;
            int typed = 0;
            for (char c : word.toCharArray()) {
                typed++;
                node = node.children.get(c);
                if (node.count == 1) break;
            }
            return typed;
        }
    }

    public int solution(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int total = 0;
        for (String word : words) {
            total += trie.search(word);
        }
        return total;
    }
}