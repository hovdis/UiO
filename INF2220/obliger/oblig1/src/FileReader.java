import java.util.*;
import java.io.*;

public class FileReader {
    private Scanner in;
    FileReader(String fileName)throws Exception{
       in = new Scanner(new File(fileName));
    }

    Dictionary readFiles(){
        String line;
        boolean worked = true;
        Dictionary ret = new Dictionary();
        while (in.hasNextLine()){
            line = in.nextLine();
            worked = ret.insertWord(line);
            if(!worked){
                System.out.println("Something went wrong!");
            }
        }
        return ret;
    }

}
