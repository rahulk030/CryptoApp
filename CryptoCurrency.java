package org.example.cryptodata;

public class CryptoCurrency {
    private String key;
    private int holderCount;
    private int dailyActive;
    private int total;

    public CryptoCurrency() {}

    public CryptoCurrency(String key, int holderCount, int dailyActive, int total) {
        this.key = key;
        this.holderCount = holderCount;
        this.dailyActive = dailyActive;
        this.total = total;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getHolderCount() {
        return holderCount;
    }

    public void setHolderCount(int holderCount) {
        this.holderCount = holderCount;
    }

    public int getDailyActive() {
        return dailyActive;
    }

    public void setDailyActive(int dailyActive) {
        this.dailyActive = dailyActive;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
