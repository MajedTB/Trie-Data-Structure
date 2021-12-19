
public class TrieNode {
	protected char el; // the letter stored in the node
	protected TrieNode[] childern; // reference to next letter(s)
	protected boolean isEndOfAWord;

	public TrieNode() {
		// 26 is the number of letters in the alphabet:
		// (we use index 0 to access letter 'A', index 25 to access 'Z')
		childern = new TrieNode[26];
	}

	public TrieNode(char el) {
		childern = new TrieNode[26];
		this.el = el;
	}

	@Override
	public String toString() {
		return el + "";
	}

}