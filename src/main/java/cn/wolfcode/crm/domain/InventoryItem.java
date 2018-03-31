package cn.wolfcode.crm.domain;

import java.util.Date;

public class InventoryItem {
    private Long id;

    private Long storeNumber;

    private Long newNumber;

    private Long employee_id;

    private Date inventoryTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(Long storeNumber) {
        this.storeNumber = storeNumber;
    }

    public Long getNewNumber() {
        return newNumber;
    }

    public void setNewNumber(Long newNumber) {
        this.newNumber = newNumber;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public Date getInventoryTime() {
        return inventoryTime;
    }

    public void setInventoryTime(Date inventoryTime) {
        this.inventoryTime = inventoryTime;
    }
}