import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAuction {
    @BeforeEach
    void setUp(){
        Users.clear();
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

    private User createUser() {
        String firstName = "Jacob";
        String lastName = "Nash";
        String email = "Jacob.Nash@intel.com";
        String username = "JNashty";
        String password = "12345";
        return new User(firstName, lastName, email, username, password);
    }


}
