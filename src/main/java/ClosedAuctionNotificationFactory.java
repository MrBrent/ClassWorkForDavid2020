public class ClosedAuctionNotificationFactory {
    static ClosedAuctionNotification getClosedAuctionNotification(User highestBidder) {
        ClosedAuctionNotification notifier;
        if(highestBidder != null){
             notifier = new SaleNotification();

        } else {
             notifier = new UnsoldNotification();
        }
        return notifier;
    }
}
