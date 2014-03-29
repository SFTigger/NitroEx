import org.junit.*;
import play.test.*;
import models.*;
 
public class AccountModelTest extends UnitTest {
	
	private Account account;
	
    @Before
    public void setup() {
        Fixtures.deleteDatabase();
        
    	account = new Account("a@b.com", "pass");
    	account.save();
    }
 
    @Test
    public void testUserRetrieve() {
    	Account retrieved = Account.find("byEmail", "a@b.com").first();
    	
    	assertNotNull(retrieved);
    	assertEquals("a@b.com", retrieved.email);
    	assertEquals(account, retrieved);
    }
 
    @Test
    public void testNonExistantUser() {
    	Account retrieved = Account.find("byEmail", "foo").first();
    	
    	assertNull(retrieved);
    }
    
    @Test
    public void testDeleteAccount(){
    	account.delete();
    	
    	Account retrieved = Account.find("byEmail", "a@b.com").first();
    	
    	assertNull(retrieved);
    }
}