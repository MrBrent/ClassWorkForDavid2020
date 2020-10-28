import java.util.Date;

public class Auction {
    private final User user;
    private final String item;
    private final double startPrice;
    private final Date startTime;
    private final Date endTime;
    private User highestBidder;
    private double currentBid;
    private AuctionState state;

    public Auction(User user, String item, double startPrice, Date startTime, Date endTime) {
        this.user = user;
        this.item = item;
        this.startPrice = startPrice;
        this.startTime = startTime;
        this.endTime = endTime;
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
        return user;
    }

    public void bid(User user, double price) {
        //if nobody is currently winning the auction
        if(state != AuctionState.STARTED){return;}
        if(getHighestBidder() == null){
            //  if the bid price is greater than or equal
            if(price >= startPrice){
                highestBidder = user;
                currentBid = price;
            } else {
                // Do Nothing
                return;
            }
        } else {
            if(price>currentBid){
                highestBidder = user;
                currentBid = price;
            }
            else {
                // Do Nothing
                return;
            }


        }
    }

    public User getHighestBidder() {
        return highestBidder;
    }

    public double getCurrentPrice() {
        if(highestBidder != null){
            return currentBid;
        } else {
            return startPrice;
        }
    }

    public void onStart() {
        //Auction state
        state = AuctionState.STARTED;
    }
}
