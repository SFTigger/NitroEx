import org.junit.*;
import play.test.*;
import models.*;
 
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
}