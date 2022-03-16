package hu.ace.geaapp.ui.view.main_test.bottomnav.model;

public class BottomItemTest {

    private int itemId;
    private int itemIconId;
    private String itemName;
    private boolean hasNotification;

    public BottomItemTest(int itemId, int itemIconId, String itemName, boolean hasNotification){
        this.itemIconId = itemIconId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.hasNotification = hasNotification;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemIconId() {
        return itemIconId;
    }

    public void setItemIconId(int itemIconId) {
        this.itemIconId = itemIconId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isHasNotification() {
        return hasNotification;
    }

    public void setHasNotification(boolean hasNotification) {
        this.hasNotification = hasNotification;
    }
}
