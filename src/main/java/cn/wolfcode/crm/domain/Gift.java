package cn.wolfcode.crm.domain;

public class Gift {
    private Long id;

    private String name;

    private String sn;

    private Integer points;

    private Integer totalquantity;

    private Integer inventory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getTotalquantity() {
        return totalquantity;
    }

    public void setTotalquantity(Integer totalquantity) {
        this.totalquantity = totalquantity;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
}