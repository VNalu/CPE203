public class Buy {
    private String SESSION_ID;
    private String PRODUCT_ID;
    private int PRICE;
    private int QUANTITY;
    public Buy(String sessionId, String productId, int price, int quantity) {
        this.SESSION_ID = sessionId;
        this.PRODUCT_ID = productId;
        this.PRICE = price;
        this.QUANTITY = quantity;
    }
    public String getSessionId() {
        return SESSION_ID;
    }
    public void setSessionId(String newSessionId) {
        this.SESSION_ID = newSessionId;
    }
    public String getProductId() {
        return PRODUCT_ID;
    }
    public void setProductId(String newProductId) {
        this.PRODUCT_ID = newProductId;
    }
    public int getPrice() {
        return PRICE;
    }
    public void setPrice(int newPrice) {
        this.PRICE = newPrice;
    }
    public int getQuantity() {
        return QUANTITY;
    }
    public void setQuantity(int newQuantity) {
        this.QUANTITY = newQuantity;
    }
}