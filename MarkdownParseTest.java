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
        String Test = Files.readString(Path.of("TestFile.md"));
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(MarkdownParse.getLinks(Test), expect);
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
}