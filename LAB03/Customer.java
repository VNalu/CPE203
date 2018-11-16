import java.util.ArrayList;

public class Customer {
    private ArrayList<String> sessionIds;
    private ArrayList<Buy> buyList;
    private ArrayList<View> viewList;
    public Customer(ArrayList<String> sessionIds, ArrayList<Buy> buyList,
    ArrayList<View> viewList) {
        this.sessionIds = sessionIds;
        this.buyList = buyList;
        this.viewList = viewList;
    }
    public ArrayList getSessionId() {
        return sessionIds;
    }
    public void addSessionId(String newSessionId) {
        sessionIds.add(newSessionId);
    }
    public ArrayList getBuyList() {
        return buyList;
    }
    public void addBuy(Buy newBuy) {
        buyList.add(newBuy);
    }
    public ArrayList getViewList() {
        return viewList;
    }
    public void addView(View newView) {
        viewList.add(newView);
    }
}
