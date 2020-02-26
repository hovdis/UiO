import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args)throws Exception{
        FileReader fl = new FileReader("words.txt");
        Dictionary dic = fl.readFiles();
        Scanner in = new Scanner(System.in);
        String linja;
        String svar;
        double totNodes = 0;
        double totAndDep = 0;
        ArrayList<Integer> nodesInDepth = dic.getNodesInDepth();


        while(true){
            System.out.println("\nWhat do you want to search for? ('q' to exit)");
            linja = in.nextLine().toLowerCase();
            if(linja.equalsIgnoreCase("q")){
                System.out.println("========== INFO ABOUT THE TREE ==========");
                System.out.println("Depth of the tree: " + (-1 + nodesInDepth.size()));
                System.out.println("++++++ NODES FOR EACH DEPTH: ++++++");
                for (int i = 0; i<nodesInDepth.size();i++){
                    totNodes += nodesInDepth.get(i);
                    totAndDep += i * nodesInDepth.get(i);
                    System.out.println("        ["+i+"]: " + nodesInDepth.get(i));
                }
                System.out.println("+++++++++++++++++++++++++++++++++++");
                double avgDepth = totAndDep/totNodes;
                System.out.println("Avg depth of nodes: "+ avgDepth);
                String firstWord = dic.getFirstWord();
                String lastWord = dic.getLastWord();
                System.out.println("The alphabetically first word is: "+ firstWord);
                System.out.println("The alphabetically last word is: "+ lastWord);
                System.out.println("==========================================");
                break;
            }
            System.out.println(linja);
            svar = dic.searchWord(linja);
            if(svar == null){
                System.out.println("This word does not exist. Similar words:");
                dic.spellCheck(linja);
            }else{
                System.out.println("We fount the word: " + svar);
            }
        }
    }
}
