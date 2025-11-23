import java.util.Scanner;
import java.util.Stack;

public class Main {

	static StringBuffer Word;
	static Stack<Character> stack = new Stack<>();
	static Stack<Character> newStk = new Stack<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Word = new StringBuffer(sc.next());
		StringBuffer explodeWord = new StringBuffer(sc.next());

		explode(explodeWord);

		StringBuffer answer = new StringBuffer();
		for (Character character : stack) {
			answer.append(character);
		}

		if (answer.length() == 0)
			System.out.println("FRULA");
		else
			System.out.println(answer);
	}

	public static void explode(StringBuffer explodeWord) {
		int idx = 0;
		int explode_len = explodeWord.length();
		while (idx < Word.length()) {
			stack.add(Word.charAt(idx));
			if (stack.peek() == explodeWord.charAt(explode_len - 1))
				check(explodeWord);
			idx++;
		}

	}

	public static void check(StringBuffer explodeWord) {
		if (stack.size() < explodeWord.length())
			return;
		newStk.clear();
		for (int i = 0; i < explodeWord.length(); i++) {
			char c = stack.pop();
			newStk.add(c);
			if (c != explodeWord.charAt(explodeWord.length() - 1 - i)) {
				while(!newStk.isEmpty())
					stack.add(newStk.pop());
				return;
			}
		}
	}
}