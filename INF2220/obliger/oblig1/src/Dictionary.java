import java.util.*;

class Dictionary {

    private ArrayList<Integer> nodesInDepth = new ArrayList<>();
    private Node rootNode;

    boolean insertWord(String iWord){
        int depth = 0;
        if(rootNode == null){
            rootNode = new Node();
            rootNode.word = iWord;
            nodesInDepth.add(0, 1);
            return true;
        }
        Node tmp = rootNode;

        while(tmp.word != null){
            if(tmp.word.compareTo(iWord) < 0){
                if(tmp.right == null){
                    tmp.right = new Node();
                } tmp = tmp.right;
            }else if(tmp.word.compareTo(iWord) > 0){
                if(tmp.left == null){
                    tmp.left = new Node();
                } tmp = tmp.left;
            }
            else return false;
            depth++;
        }
        tmp.word = iWord;
        try {
            nodesInDepth.set( depth, nodesInDepth.get(depth)+1 );
        } catch ( IndexOutOfBoundsException e ) {
            nodesInDepth.add( depth, 1 );
        }
        tmp.height = depth;
        return true;
    }
    String searchWord(String sWord){
        Node tmp = rootNode;
        while(tmp != null && tmp.word.compareTo(sWord) != 0){
            if(tmp.word.compareTo(sWord) < 0){
                if(tmp.right == null){
                    return null;
                } tmp = tmp.right;
            }else if(tmp.word.compareTo(sWord) > 0){
                if(tmp.left == null){
                    return null;
                } tmp = tmp.left;
            }else{
                return tmp.word;
            }
        }
        return (tmp == null ? null : tmp.word);
    }


    void spellCheck(String findWord){
        ArrayList<String> ret = new ArrayList<>();
        System.out.println("Here are all the words that are similar:");
        ret = swap(ret, findWord);
        ret = replace(ret, findWord);
        ret = addRand(ret, findWord);
        ret = removeRand(ret, findWord);

        for(String s : ret){
            System.out.println(s);
        }
    }

    private ArrayList<String> swap(ArrayList<String> ret, String word) {
        char[] wordArray = word.toCharArray();

        for(int i = 0; i < wordArray.length - 1; i++) {
            wordArray = word.toCharArray();

            char first = wordArray[i];
            char second = wordArray[i + 1];

            wordArray[i] = second;
            wordArray[i + 1] = first;

            String returnedString = searchWord(new String(wordArray));

            if(returnedString != null && !ret.contains(returnedString)) {
                ret.add(returnedString);
            }
        }

        return ret;
    }


    private ArrayList<String> replace(ArrayList<String> ret, String findWord){
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] tmpArr;
        String testString;

        for(int i = 0; i<findWord.length();i++){
            tmpArr = findWord.toCharArray();
            for(char j : alphabet){
                tmpArr[i] = j;
                testString = new String(tmpArr);
                if(searchWord(testString) != null && !ret.contains(testString)){
                    ret.add(testString);
                }
            }
        }
        return ret;
    }

    private ArrayList<String> addRand(ArrayList<String> ret, String findWord){
        char[] tmp;
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] orgArr = findWord.toCharArray();
        int offset = 0;
        String testString;


        for(int i = 0;i<findWord.length()+1;i++){
            tmp = new char[findWord.length()+1];
            for(int j = 0;j<findWord.length();j++){
                if(j == i){
                    offset++;
                }
                tmp[j+offset] = orgArr[j];
            }

            for(char k : alphabet){
                tmp[i] = k;
                testString = new String(tmp);
                if(searchWord(testString) != null && !ret.contains(testString)){
                    ret.add(testString);
                }
            }
            offset = 0;
        }
        return ret;
    }

    private ArrayList<String> removeRand(ArrayList<String> ret, String findWord){
        char[] word = findWord.toCharArray();
        char[] tmp = new char[findWord.length()-1];
        boolean overlook = false;
        String testString;
        for(int i = 0 ; i<word.length;i++){
            for(int j = 0 ; j < tmp.length;j++){
                if(i == j){
                    overlook = true;
                }
                if(overlook){
                    tmp[j] = word[j+1];
                }else{
                    tmp[j] = word[j];
                }
            }
            testString = new String(tmp);
            if(searchWord(testString) != null && !ret.contains(testString)){
                ret.add(testString);
            }
            overlook = false;
        }
        return ret;
    }

    String getFirstWord(){
        Node tmp = rootNode;
        while(tmp.left != null){
            tmp = tmp.left;
        }return tmp.word;
    }

    String getLastWord(){
        Node tmp = rootNode;
        while(tmp.right != null){
            tmp = tmp.right;
        }return tmp.word;
    }

    ArrayList<Integer> getNodesInDepth() {
        return nodesInDepth;
    }

    private class Node{
        Node left;
        Node right;
        String word;
        int height;
    }


}
