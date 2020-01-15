package dev.zmq.m3h.FM;

public class FMListData {

    private String title;
    private String stationName;
    private int imgId;

    public FMListData(String title, String stationName, int imgId) {
        this.title = title;
        this.stationName = stationName;
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
