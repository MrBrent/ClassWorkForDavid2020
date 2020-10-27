import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestUser {
    @BeforeEach
    void setUp(){
        Users.clear();
    }
    @Test
    void testUser() {

        User uut = createUser();

        assertEquals("Jacob", uut.getFirstName());
        assertEquals("Nash", uut.getLastName());
        assertEquals("Jacob.Nash@intel.com", uut.getEmail());
        assertEquals("JNashty", uut.getUsername());
        assertEquals("12345", uut.getPassword());
    }

    @Test
    void testRegisterUser() {
        User testUser = createUser();

        Users.getInstance().register(testUser);
        assertEquals(false, testUser.isLoggedIn());
        User somebodyWhoLoggedIn = Users.getInstance().logIn("JNashty", "12345");
        assertEquals(true, somebodyWhoLoggedIn.isLoggedIn());
        Users.getInstance().logOut(somebodyWhoLoggedIn);
        assertEquals(false, somebodyWhoLoggedIn.isLoggedIn());
    }

    @Test
    void testRegisteredSeller() {
        User jacobNash = createAndLogInUser();
        assertEquals(false, jacobNash.isSeller());
        jacobNash.makeSeller();
        assertEquals(true, jacobNash.isSeller());
    }

    @Test
    void testCreateAuction() {
        User jacobNash = createAndLogInUser();
        jacobNash.makeSeller();
        Instant now = Instant.now();
        Date startTime = Date.from(now.plusSeconds(1));
        Date endTime = Date.from(now.plusSeconds(2));
        String item = "magicItem";
        double startPrice = 12.00;

        Auction auction = jacobNash.makeAuction(item, startPrice, startTime, endTime);

        assertEquals(jacobNash, auction.getSeller());
        assertEquals(item, auction.getItem());
        assertEquals(startPrice, auction.getPrice(), 0.001);
        assertEquals(startTime, auction.getStartTime());
        assertEquals(endTime, auction.getEndTime());
    }

    @Test
    void testUserCannotCreateAuction() {
        User jacobNash = createAndLogInUser();
        Instant now = Instant.now();
        Date startTime = Date.from(now.plusSeconds(1));
        Date endTime = Date.from(now.plusSeconds(2));
        String item = "magicItem";
        double startPrice = 12.00;
        assertThrows(RuntimeException.class, ()->
          jacobNash.makeAuction(item, startPrice, startTime, endTime));
    }

    private User createAndLogInUser() {
        User testUser = createUser();
        Users.getInstance().register(testUser);
        return Users.getInstance().logIn("JNashty", "12345");
    }

    @Test
    void testUnregisteredUser() {
        User somebodyWhoLoggedIn = Users.getInstance().logIn("UnregisteredUser", "badpassword");
        assertEquals(null, somebodyWhoLoggedIn);
    }

    @Test
    void testWrongPassword() {
        Users users = Users.getInstance();
        users.register(createUser());
        User somebodyWhoLoggedIn = users.logIn("JNashty", "badpassword");
        assertEquals(null, somebodyWhoLoggedIn);
    }

    private User createUser() {
        String firstName = "Jacob";
        String lastName = "Nash";
        String email = "Jacob.Nash@intel.com";
        String username = "JNashty";
        String password = "12345";
        return new User(firstName, lastName, email, username, password);
    }


}
