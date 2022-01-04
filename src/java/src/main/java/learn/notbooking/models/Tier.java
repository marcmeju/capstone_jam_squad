package learn.notbooking.models;

public class Tier {
    private int tierId;
    private String tierName;
    private String tierDescription;

    public Package getPack() {
        return pack;
    }

    public void setPack(Package pack) {
        this.pack = pack;
    }

    private Package pack;

    public int getTierId() {
        return tierId;
    }

    public void setTierId(int tierId) {
        this.tierId = tierId;
    }

    public String getTierName() {
        return tierName;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public String getTierDescription() {
        return tierDescription;
    }

    public void setTierDescription(String tierDescription) {
        this.tierDescription = tierDescription;
    }
}
