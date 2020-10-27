import java.util.Date;

public class Auction {
    private final User user;
    private final String item;
    private final double startPrice;
    private final Date startTime;
    private final Date endTime;

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
}
