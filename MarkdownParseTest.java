import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test // Test method for TestFile.md
    public void getLinks() throws IOException {

        String contents= Files.readString(Path.of("TestFile.md"));
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test // Test method for TestFile2.md
    public void getLinks2() throws IOException {
        Path fileName = Path.of("TestFile2.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of(), links);
    }

    @Test // Test method for TestFile3.md
    public void getLinks3() throws IOException {
        String contents = "[testFileWithNoLink](something.com";
        List<String> expect = List.of();
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test // Test method for TestFile4.md
    public void getLinks4() throws IOException {
        String contents = "[LinkWithWordsInTheMiddle] There are words in between the two (Middle.com)";
        List<String> expect = List.of();
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test // Test method for TestFile5.md
    public void getLinks5() throws IOException {
        String contents = "[testFileWithNoOpenP]something.com)";
        List<String> expect = List.of();
        assertEquals(MarkdownParse.getLinks(contents), expect);    
    }

    @Test // Test method for Snippet1.md
    public void getSnippetLinks1() throws IOException {
        Path fileName = Path.of("Snippet1.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of("url.com", "`google.com", "google.com", "ucsd.edu"), links);
    }

    @Test // Test method for Snippet2.md
    public void getSnippetLinks2() throws IOException {
        Path fileName = Path.of("Snippet2.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of("a.com", "a.com(()))", "example.com"), links);
    }

    @Test // Test method for Snippet3.md
    public void getSnippetLinks3() throws IOException {
        Path fileName = Path.of("Snippet3.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of("https://www.twitter.com", 
        "https://ucsd-cse15l-w22.github.io/","github.com", 
        "https://cse.ucsd.edu/"), links);
    }

}