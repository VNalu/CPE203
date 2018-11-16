public class View {
    private String SESSION_ID;
    private String PRODUCT_ID;
    private int PRICE;
    public View(String sessionId, String productId, int price) {
        this.SESSION_ID = sessionId;
        this.PRODUCT_ID = productId;
        this.PRICE = price;
    }
    public String getSessionId() {
        return SESSION_ID;
    }
    public void setSessionId(String newSessionId) {
        this.SESSION_ID = newSessionId;
    }
    public String getProduct() {
        return PRODUCT_ID;
    }
    public void setProduct(String newProductId) {
        this.PRODUCT_ID = newProductId;
    }
    public int getPrice() {
        return PRICE;
    }
    public void setPrice(int newPrice) {
        this.PRICE = newPrice;
    }

}