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
            previousIndex = currentIndex;

            int nextOpenBracket = markdown.indexOf("[", currentIndex);

            // This line of code checks to see if the link is an image link 
            if(nextOpenBracket != 0 && markdown.charAt(nextOpenBracket -1) == '!'){   
                currentIndex = nextOpenBracket+1;  
                continue;   
            }

            int openParen = markdown.indexOf("(", nextOpenBracket);
            
            // check for ")["
            if (openParen > 0 && markdown.charAt(openParen-1) != ']') {
                currentIndex = openParen + 1;
                continue;
            }

            int closeParen = markdown.indexOf(")", openParen);

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
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}