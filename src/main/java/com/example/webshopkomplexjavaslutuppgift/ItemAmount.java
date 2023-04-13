package com.example.webshopkomplexjavaslutuppgift;

public class ItemAmount {
    public long itemId;
    public int count;

    public ItemAmount() {
    }

    public ItemAmount(int itemId, int count) {
        this.itemId = itemId;
        this.count = count;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
