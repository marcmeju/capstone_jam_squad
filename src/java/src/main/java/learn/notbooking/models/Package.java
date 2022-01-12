package learn.notbooking.models;

import java.util.ArrayList;
import java.util.List;

public class Package {
private int packageId;
private String packageName;
private int tierId;
private int packagePrice;
private List<Event> events = new ArrayList<>();

    public Package() {
    }

    public Package(int packageId, String packageName, int tierId, int packagePrice) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.tierId = tierId;
        this.packagePrice = packagePrice;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getTierId() {
        return tierId;
    }

    public void setTierId(int tierId) {
        this.tierId = tierId;
    }

    public int getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(int packagePrice) {
        this.packagePrice = packagePrice;
    }
}
