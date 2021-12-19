import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TrieTest {

	public static void main(String[] args) {

		// creating the trie that we will be working with
		Trie trie = new Trie();

		// input
		Scanner sc = new Scanner(System.in);
		String choice;

		while (true) {
			choice = promptMenu();

			switch (choice) {
			case "1": // Insert a word
				System.out.print("Enter a word to insert: ");
				String word = sc.nextLine();
				trie.insert(word);
				System.out.printf("The word %s has been inserted\n", word);
				break;
			case "2": // Insert all permutations of a word
				System.out.print("Enter a word to insert its permutations: ");
				trie.insertPermutations(sc.nextLine(), "dictionary.txt");
				System.out.println();
				break;
			case "3": // Clear the Trie
				trie.clear();
				System.out.println("The trie has been cleared");
				break;
			case "4": // Delete a word
				if (trie.isEmpty()) {
					System.out.println("Cannot delete because trie is empty");
				} else {
					System.out.print("Enter a word to delete: ");
					trie.delete(sc.nextLine());
					System.out.println("The word has been deleted");
				}
				break;
			case "5": // List all words that begin with a prefix
				if (trie.isEmpty()) {
					System.out.println("The trie is empty");
				} else {
					System.out.print("Enter a prefix: ");
					String prefix = sc.nextLine().toUpperCase();

					String[] prefixWords = trie.allWordsPrefix(prefix);
					if (prefixWords == null) {
						System.out.println("There are no words that start with " + prefix);
					} else {
						System.out.printf("Words in the trie that starts with %s are: ", prefix);
						for (String w : prefixWords)
							System.out.print(w + " ");
						System.out.println();
					}
				}
				break;
			case "6": // Size of the trie
				System.out.println("The size of the trie is " + trie.size());
				break;
			case "7":
				System.out.print("Enter a word/prefix: ");
				String s = sc.nextLine().toUpperCase();

				System.out.println(
						(trie.contains(s) ? "The trie contain the word " : "the trie does not contain the word ") + s);
				System.out.println(
						(trie.isPrefix(s) ? "The trie contain the prefix " : "the trie does not contain the prefix ")
								+ s);
				break;
			case "8":
				System.exit(0);
			default:
				System.out.println("Invalid Choice");
				break;
			}

			// wait for 2 seconds
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("\n".repeat(10)); // clear the above output
		}

	}

	public static String promptMenu() {
		Scanner sc = new Scanner(System.in);

		System.out.print("\n<Trie Project>\n" + "   1) Insert a word\n" + "   2) Insert all permutations of a word\n"
				+ "   3) Clear the Trie\n" + "   4) Delete a word\n" + "   5) List all words that begin with a prefix\n"
				+ "   6) Size of the trie\n" + "   7) Check if a word/prefix is in the Trie\n" + "   8) End\n"
				+ "Your Choice: ");
		return sc.nextLine();
	}

}
