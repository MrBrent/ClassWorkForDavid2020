import com.tobeagile.training.ebaby.services.PostOffice;

public class SaleNotification {
    public void sendEmailItemSold(Auction theAuction) {
        PostOffice.getInstance().sendEMail(theAuction.getSeller().getEmail(), String.format("Your %s auction sold to bidder %s for $%.2f.", theAuction.getItem(), theAuction.getHighestBidder().getEmail(), theAuction.getHighestBid()));
        PostOffice.getInstance().sendEMail(theAuction.getHighestBidder().getEmail(), String.format("Congratulations! You won an auction for a %s from %s for $%.2f.", theAuction.getItem(), theAuction.getSeller().getEmail(), theAuction.getHighestBid()));
    }
}
