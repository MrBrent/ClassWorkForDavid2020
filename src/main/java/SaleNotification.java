import com.tobeagile.training.ebaby.services.PostOffice;

public class SaleNotification {

    public void sendEmailItemNotSold(Auction theAuction) {
        PostOffice.getInstance().sendEMail(theAuction.getSeller().getEmail(), "Sorry, your auction for " + theAuction.getItem() + " did not have any bidders.");

    }
}
