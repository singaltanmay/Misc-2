class StreamChecker {

    private class TrieNode {
        boolean isTerminal = false;
        Map<Character, TrieNode> children = new HashMap<>();

        public String toString(){
            return "isTerminal: " + isTerminal + "\n" + children;
        }
    }

    private TrieNode dict = new TrieNode();

    private void addToDict(String input){
        StringBuilder s = new StringBuilder(input).reverse();
        char[] arr = s.toString().toCharArray();
        TrieNode node = dict;
        for(char c : arr){
            if(!node.children.containsKey(c)){
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
        }
        node.isTerminal = true;
    }

    public StreamChecker(String[] words) {
        for(String s : words) addToDict(s);
        //System.out.println(dict);
    }
    
    LinkedList<Character> qList = new LinkedList<>();

    public boolean query(char letter) {
        qList.addFirst(letter);
        //System.out.println(qList);
        // Now check the qList on the trie
        TrieNode node = dict;
        Iterator<Character> it = qList.iterator();

        while(node != null){
            if(!it.hasNext()) return false;
            char c = it.next();
            if(node.children.containsKey(c)){
                node = node.children.get(c);
                if(node.isTerminal) return true;
            } else return false;
        }

        return false;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
