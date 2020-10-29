import com.tobeagile.training.ebaby.services.PostOffice;

public class UnsoldNotification {

    public void sendClosedAuctionNotification(Auction theAuction) {
        PostOffice.getInstance().sendEMail(theAuction.getSeller().getEmail(), "Sorry, your auction for " + theAuction.getItem() + " did not have any bidders.");

    }
}
