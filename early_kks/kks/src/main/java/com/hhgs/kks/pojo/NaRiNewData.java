package com.hhgs.kks.pojo;

public class NaRiNewData {

    //测点id
    private String id;

    //原始测点名称
    private String descript;

    //lcuid
    private String lcuid;

    public NaRiNewData() {
    }

    public NaRiNewData(String id, String descript, String lcuid) {
        this.id = id;
        this.descript = descript;
        this.lcuid = lcuid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getLcuid() {
        return lcuid;
    }

    public void setLcuid(String lcuid) {
        this.lcuid = lcuid;
    }

    @Override
    public String toString() {
        return "NaRiNewData{" +
                "id=" + id +
                ", descript='" + descript + '\'' +
                ", lcuid='" + lcuid + '\'' +
                '}';
    }
}
