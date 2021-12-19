import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Trie {
	protected TrieNode root = null;

	public Trie() {
		root = new TrieNode();
	}

	public void insert(String s) {
		TrieNode tmp = root; // start from the root

		for (char c : s.toUpperCase().toCharArray()) { // iterating over the letters of s
			if (tmp.childern[c - 'A'] == null) // if there is no node at that index
				tmp.childern[c - 'A'] = new TrieNode(c); // add it to that position

			tmp = tmp.childern[c - 'A']; // go to next letter
		}

		tmp.isEndOfAWord = true; // mark it as a word at the last node
	}
	
	// this method will insert every valid word that can be spelled from the given word into the trie
		public void insertPermutations(String s, String dictFile) {
			s = s.toUpperCase();
			try (Scanner dictionary = new Scanner(new File(dictFile))) {

				System.out.print("The words to be inserted are: ");
				
				// the word will be inserted if it passed 2 tests
				// the algorithm will run the tests for each word in the dictionary
				while (dictionary.hasNext()) { // iterate over the dictionary file
					String dictWord = dictionary.next(); 
					if (dictWord.length() <= s.length()) { // Test 1: the dictionary word is shorter than the given word
						boolean flag = true;
						// Test 2: the recurrence of each letter in the dictionary word <= the recurrence of each letter in the given word
						for (int i = 0; i < dictWord.length() && flag; i++)  
							flag = flag && (countLetters(dictWord, dictWord.charAt(i)) <= countLetters(s,dictWord.charAt(i)));

						if (flag) { // word passed the test
							System.out.print(dictWord + " ");
							insert(dictWord);
						}
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("Dictionary file was not found");
			}
		}

	public void delete(String s) {
		// we need to use recursion to access the previous parents
		recDelete(root, s.toUpperCase(), 0);
	}

	private TrieNode recDelete(TrieNode tmp, String s, int index) {
		if (tmp == null) { // if reached an empty node, or the trie is empty
			return null;
		} else if (index == s.length()) { // if it reached the node of the last letter of s
			tmp.isEndOfAWord = false; // unmark it

			if (isLeaf(tmp)) // if the node has no words beyond it
				return null; // return null to delete its reference

			else // it has other words beyond it
				return tmp; // return the same node to keep its reference
		} else { // did not reach the last letter

			// delete or keep the pointer to the node, based on what the method returns
			tmp.childern[s.charAt(index) - 'A'] = recDelete(tmp.childern[s.charAt(index) - 'A'], s, index + 1);

			if (isLeaf(tmp) && !tmp.isEndOfAWord)
				return null; // return null to delete it
			else
				return tmp; // return tmp to keep it
		}
	}

	public boolean contains(String s) {
		TrieNode tmp = root;

		for (char c : s.toUpperCase().toCharArray()) { // iterating over the letters of s
			if (tmp.childern[c - 'A'] == null) // there is no reference to that letter
				return false;

			tmp = tmp.childern[c - 'A']; // go to next letter
		}
		return tmp.isEndOfAWord; 
	}

	public boolean isPrefix(String p) {
		TrieNode tmp = root;

		for (char c : p.toUpperCase().toCharArray()) {// iterating over the letters of s
			if (tmp.childern[c - 'A'] == null)// there is no reference to that letter
				return false;

			tmp = tmp.childern[c - 'A']; // go to next letter
		}
		return true;
	}

	public boolean isEmpty() {
		for (TrieNode node : root.childern) 
			if (node != null)
				return false;
		return true;
	}

	// helper method for deletion
	public static boolean isLeaf(TrieNode node) {
		for (TrieNode n : node.childern) { //
			if (n != null)
				return false;
		}
		return true;
	}

	public void clear() {
		// removing the reference to each letter
		for (int i = 0; i < root.childern.length; i++)
			root.childern[i] = null;
	}

	public String[] allWordsPrefix(String p) {
		TrieNode tmp = root;

		// find the location of p
		for (char c : p.toUpperCase().toCharArray()) {
			if (tmp.childern[c - 'A'] == null) // p does not exist
				return null;

			tmp = tmp.childern[c - 'A']; // go to next child
		}
		// now we have the reference to the last letter of p
		
		ArrayList<String> words = new ArrayList<String>(); // used to store words
		allWordsPrefix(p.toUpperCase(), tmp, words); // find the words and insert them into the list
		
		return words.toArray(new String[words.size()]);

	}

	// recursive helper method for allWordsPrefix
	private void allWordsPrefix(String p, TrieNode tmp, ArrayList<String> words) {
		if (tmp != null) {
			if (tmp.isEndOfAWord)
				words.add(p);

			for (char c = 'A'; c <= 'Z'; c++) {
				if (tmp.childern[c - 'A'] != null) // recursive case: the node has letter in it
					allWordsPrefix(p + c, tmp.childern[c - 'A'], words); // find the words beyond it
			}
		}
	}

	public int size() {
		return recSize(root, 0);
	}

	private int recSize(TrieNode node, int count) {
		for (char c = 'A'; c <= 'Z'; c++) {
			if (node.childern[c - 'A'] != null) // recursive case: the node has letter in it
				count = recSize(node.childern[c - 'A'], count + 1); // count the letter beyond it
		}
		return count;
	}

	

	// helper method (for insertWordPermutations) to count the recurrence of a letter c in string s 
	private int countLetters(String s, char c) {
		int count = 0;
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) == c)
				count++;
		return count;
	}

}
