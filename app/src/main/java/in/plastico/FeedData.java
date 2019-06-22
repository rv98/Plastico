package in.plastico;

public class FeedData {
    private String feedId;
    private String userName;
    private String productName;
    private String productQty;
    private String productPrice;
    private String brandName;
    private String feedType;
    private String offerDiscount;

    public FeedData(String feedId,String userName,String productName, String productQty, String productPrice, String brandName, String feedType, String offerDiscount) {
        this.feedId=feedId;
        this.userName=userName;
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
        this.brandName = brandName;
        this.feedType = feedType;
        this.offerDiscount = offerDiscount;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getOfferDiscount() {
        return offerDiscount;
    }

    public void setOfferDiscount(String offerDiscount) {
        this.offerDiscount = offerDiscount;
    }
}
