import org.junit.*;
import play.test.*;
import models.*;
import java.util.*;
 
public class FileModelTest extends UnitTest {
	
	private Account account;
	
	private File foo;
	
    @Before
    public void setup() {
        Fixtures.deleteDatabase();
        
    	account = new Account("a@b.com", "pass");
    	account.save();
    	
    	foo = new File(account, "foo", new byte[0]);
    	foo.save();
    }
 
    @Test
    public void listFile(){
    	File retrieved = account.files.iterator().next();
    	
    	assertEquals(foo, retrieved);
    }
    
    @Test
    public void testParseTags() throws Exception{
    	byte[] content = "a b c d b d e f gh gh gh gh gh gh GH I I J K L".getBytes("UTF-8");
    	
    	File contentFile = new File(account, "foo", content);
    	
    	List<Tag> tags = contentFile.extractSuggestions();
    	
    	assertEquals(4, tags.size());
    	
    	Tag tagB = Tag.find("byValue", "b").first();
    	Tag tagD = Tag.find("byValue", "d").first();
    	Tag tagGH = Tag.find("byValue", "gh").first();
    	Tag tagI = Tag.find("byValue", "i").first();
    	
    	assertTrue(tags.contains(tagB));
    	assertTrue(tags.contains(tagD));
    	assertTrue(tags.contains(tagGH));
    	assertTrue(tags.contains(tagI));
    }
}