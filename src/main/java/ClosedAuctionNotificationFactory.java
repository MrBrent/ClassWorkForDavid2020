public class ClosedAuctionNotificationFactory {
    public ClosedAuctionNotificationFactory(User highestBidder) {
    }

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
