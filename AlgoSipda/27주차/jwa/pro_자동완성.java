// Trie 풀이 참고

import java.util.*;

class Solution {
	static class TrieNode {
		Map<Character, TrieNode> child = new HashMap<>();
		int cnt = 0;
	}

	static class Trie {
		TrieNode root;

		Trie() {
			this.root = new TrieNode();
		}

		void insert(String word) {
			TrieNode cur = this.root;

			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if (cur.child.get(c) == null) {
					cur.child.put(c, new TrieNode());
				}
				cur = cur.child.get(c);
				cur.cnt++;
			}
		}

		int getLength(String word) {
			TrieNode cur = this.root;
			int length = 0;

			for (int i = 0; i < word.length(); i++) {
				cur = cur.child.get(word.charAt(i));
				length++;

				if (cur.cnt == 1)
					break;
			}

			return length;
		}
	}

	public int solution(String[] words) {
		int answer = 0;
		Trie trie = new Trie();

		for (String s : words) {
			trie.insert(s);
		}

		for (String s : words) {
			answer += trie.getLength(s);
		}

		return answer;
	}
}
