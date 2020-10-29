import com.tobeagile.training.ebaby.services.PostOffice;

import java.util.Date;

public class Auction {
    private final User seller;
    private final String item;
    private final double startPrice;
    private final Date startTime;
    private final Date endTime;
    private User highestBidder;
    private double highestBid;
    private AuctionState state;

    public Auction(User user, String item, double startPrice, Date startTime, Date endTime) {
        this.seller = user;
        this.item = item;
        this.startPrice = startPrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.highestBid = this.startPrice;
    }

    public String getItem() {
        return item;
    }

    public double getPrice() {
        return startPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public User getSeller() {
        return seller;
    }

    public void bid(User user, double price) {
        if (user == getSeller()) {
            return;
        }
        if (state != AuctionState.STARTED) {
            return;
        }
        if (getHighestBidder() == null) {
            if (price >= startPrice) {
                highestBidder = user;
                highestBid = price;
            }

        } else {
            if (price > highestBid) {
                highestBidder = user;
                highestBid = price;
            }

        }
    }

    public User getHighestBidder() {
        return highestBidder;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public void onStart() {
        state = AuctionState.STARTED;
    }

    public void onClose() {
        state = AuctionState.CLOSED;
        // If auction was sold then send email to seller and buyer
        if(highestBidder != null){
            SaleNotification sold = new SaleNotification();
            sold.sendEmailItemSold(this);

        } else {
            // Otherwise do below
            UnsoldNotification notSold = new UnsoldNotification();
            notSold.sendEmailItemNotSold(this);
        }
    }

    private void sendEmailItemSold() {

    }
}
