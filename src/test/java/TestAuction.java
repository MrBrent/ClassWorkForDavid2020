import com.tobeagile.training.ebaby.services.PostOffice;
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

    @Test
    void testBidOnUnstartedAuction(){
        User jacobNash = createSeller();
        User frankSalsa = createBuyer();
        Auction auction = createAuction(jacobNash);
        auction.bid(frankSalsa, 12.01);
        assertEquals(null, auction.getHighestBidder());
        assertEquals(12.00, auction.getHighestBid(), 0.001);
    }

    @Test
    void testSellerCannotBidOnOwnAuction() {
        User jacobNash = createSeller();
        Auction auction = createAuction(jacobNash);
        auction.onStart();
        auction.bid(jacobNash, 12.01);
        assertEquals(null, auction.getHighestBidder());
        assertEquals(12.00, auction.getHighestBid(), 0.001);
    }

    @Test
    void testBidOnStartedAuction(){
        User jacobNash = createSeller();
        User frankSalsa = createBuyer();
        Auction auction = createAuction(jacobNash);
        auction.onStart();
        auction.bid(frankSalsa, 12.01);
        assertEquals(frankSalsa, auction.getHighestBidder());
        assertEquals(12.01, auction.getHighestBid(), 0.001);
    }

    @Test
    void testBidStartingPrice() {
        User jacobNash = createSeller();
        User frankSalsa = createBuyer();
        Auction auction = createAuction(jacobNash);
        auction.onStart();
        auction.bid(frankSalsa, 12.00);
        assertEquals(frankSalsa, auction.getHighestBidder());
        assertEquals(12.00, auction.getHighestBid(), 0.001);
    }

    @Test
    void testItemFailedToSellNotification() {
//        Jacob Nash owns an auction
        User jacobNash = createSeller();
        Auction auction = createAuction(jacobNash);
//        It starts
        auction.onStart();
//        It ends
        auction.onClose();
//        Jacob Nash is notified “Sorry, your auction for magicItem did not have any bidders.”
        String email = PostOffice.getInstance().findEmail(jacobNash.getEmail(), "Sorry");
        assertEquals("",email);
    }

    private Auction createAuction(User jacobNash) {
        Instant now = Instant.now();
        Date startTime = Date.from(now.plusSeconds(1));
        Date endTime = Date.from(now.plusSeconds(2000));
        String item = "magicItem";
        double startPrice = 12.00;
        return jacobNash.makeAuction(item, startPrice, startTime, endTime);
    }

    private User createBuyer() {
        String firstName = "Frank";
        String lastName = "Salsa";
        String email = "frank.salsa@michigan.edu";
        String username = "fsalsa";
        String password = "12345";
        User testUser = new User(firstName, lastName, email, username, password);
        Users.getInstance().register(testUser);
        return Users.getInstance().logIn("fsalsa", "12345");
    }

    private User createSeller() {
        User jacobNash = createAndLogInUser();
        jacobNash.makeSeller();
        return jacobNash;
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
