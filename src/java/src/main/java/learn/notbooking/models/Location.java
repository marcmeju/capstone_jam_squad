package learn.notbooking.models;

public class Location {
    private int locationId;
    private String locationAddress;
    private String locationCity;
    private String locationState;
    private int locationZipCode;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationState() {
        return locationState;
    }

    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }

    public int getLocationZipCode() {
        return locationZipCode;
    }

    public void setLocationZipCode(int locationZipCode) {
        this.locationZipCode = locationZipCode;
    }
}
