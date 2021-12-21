# Trie-Data-Structure
This project was developed for *Data Structures and Algorithms* course (*ICS202*), which impelements "Trie" data structure.
a Trie is a special type of tree data structure, which allows efficient search of words. It consist of multiple TrieNodes, each TrieNode has a key, children and a boolean value that indicate if it is an end of a word. 
*Note: all the words should be in uppercase.*

The class TrieTest contain a menu driven program to test the Trie.

The methods written in the Trie class support the following operations:
- insert(String s) [void] : insert the String 's' into the Trie.
- insertPermutations(String s, String dictFile) [void]: Inserts all valid words from dictionary 'dictFile' that can be spelled from string 's'.
- delete(String s) [void]: delete String 's' from the Trie, nodes will be deleted depending on the word position in the Trie.
- contains(String s) [boolean] : returns true if the Trie contains the String 's' in it.
- isPrefix(String p) [boolean] : returns ture if the Trie contains the prefix 'p' in it.
- isEmpty() [boolean]: returns if the Trie is empty.
- clear() [void]
- allWordsPrefix(String p) [String[]] : return all words in the Trie that starts the String 'p'.
- size() [int]: return the number of nodes in the Trie
