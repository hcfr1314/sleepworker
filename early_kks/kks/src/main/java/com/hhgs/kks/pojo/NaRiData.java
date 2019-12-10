package com.hhgs.kks.pojo;

/**
 * 南瑞YC/YX数据对象
 */
public class NaRiData {

    //测点id
    private Integer id;

    //原始测点名称
    private String descript;

    public NaRiData() {
    }

    public NaRiData(Integer id, String descript) {
        this.id = id;
        this.descript = descript;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    @Override
    public String toString() {
        return "NaRiDataMapper{" +
                "id=" + id +
                ", descript='" + descript + '\'' +
                '}';
    }
}
