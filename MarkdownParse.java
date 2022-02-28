// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) throws IOException {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        int previousIndex = -1;
        while(currentIndex < markdown.length() &&
        markdown.substring(currentIndex).contains("["))
        {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen); 
            previousIndex = currentIndex;

            int openParen2 = markdown.indexOf("(", openParen + 1);
            int closeParen2 = markdown.indexOf(")", openParen2 + 1);

//          int nextOpenBracket = markdown.indexOf("[", currentIndex);
//          int openParen = markdown.indexOf("(", nextOpenBracket);
//          int closeParen = markdown.indexOf(")", openParen);

            // This line of code checks to see if the link is an image link 
            // TestFile2.md
            if (nextOpenBracket != 0 && markdown.charAt(nextOpenBracket -1) == '!'){   
                currentIndex = nextOpenBracket+1;  
                continue;   
            }

            // These lines of code checks to see if there is no close parenthesis
            // TestFile3.md
            if (nextOpenBracket == -1 || nextCloseBracket == -1 
                    || closeParen == -1 || openParen == -1) {
                return toReturn;
            }

            // These lines of code checks to see if the program can still print out a link 
            // if there are words in between the closed brackets and the open parenthesis
            // TestFile4.md
            if (openParen > 0 && markdown.charAt(openParen-1) != ']') {
                currentIndex = openParen + 1;
                continue;
            }

            // These lines of code checks to see if there is no open parenthesis
            // TestFile5.md
            if (nextOpenBracket == -1 || nextCloseBracket == -1 
                    || closeParen == -1 || openParen == -1) {
                return toReturn;
            }


            if (nextOpenBracket == -1 
            || openParen == -1 || closeParen == -1) break;
    
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;

            if (currentIndex <= previousIndex) {
                 throw new IOException();
        }
}

return toReturn;

}

            // 
            // TestFile4.md
//            if (openParen2 == -1 && closeParen2 == -1 && closeParen == -1) {
//                closeParen = markdown.indexOf("]", openParen);
//                toReturn.add(markdown.substring(openParen + 1, closeParen));
//                return toReturn; 
 //           }

            // check for ")["
//            if (openParen > 0 && markdown.charAt(openParen-1) != ']') {
//                currentIndex = openParen + 1;
//                continue;
//            }


            // Test Code 

    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}