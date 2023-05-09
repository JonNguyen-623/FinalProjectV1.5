package edu.sjsu.android.finalprojectv1;

import android.provider.CalendarContract;

import java.time.LocalDateTime;
import java.util.Calendar;

public class CryptoCurrency {

    private String currencyId;
    // example: Bitcoin
    private String fullName;
    // short name like for US Dollar it would be "USD"
    // Bitcoin would be BTC
    private String symbol;
    private float currentPriceInUsd;
    // example: https://s2.coinmarketcap.com/static/img/coins/64x64/1.png
    private String logoUrl;
    /**
     * int year = calendar.get(Calendar.YEAR);
     * int month = calendar.get(Calendar.MONTH);
     * int day = calendar.get(Calendar.DAY_OF_MONTH);
     * int hour = calendar.get(Calendar.HOUR_OF_DAY);
     * int minute = calendar.get(Calendar.MINUTE);
     * int second = calendar.get(Calendar.SECOND);
     */
    private Calendar lastPriceUpdate;
    // example: "https://bitcoin.org/"
    private String websiteUrl;
    private String description;

    private boolean isFavorite;

    public CryptoCurrency(String currencyId, String fullName, String symbol) {
        this.currencyId = currencyId;
        this.fullName = fullName;
        this.symbol = symbol;
    }

    public CryptoCurrency(String currencyId, String fullName, String symbol, float currentPriceInUsd, String logoUrl, Calendar lastPriceUpdate, String websiteUrl, String description) {
        this.currencyId = currencyId;
        this.fullName = fullName;
        this.symbol = symbol;
        this.currentPriceInUsd = currentPriceInUsd;
        this.logoUrl = logoUrl;
        this.lastPriceUpdate = lastPriceUpdate;
        this.websiteUrl = websiteUrl;
        this.description = description;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public float getCurrentPriceInUsd() {
        return currentPriceInUsd;
    }

    public void setCurrentPriceInUsd(float currentPriceInUsd) {
        this.currentPriceInUsd = currentPriceInUsd;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Calendar getLastPriceUpdate() {
        return lastPriceUpdate;
    }

    public void setLastPriceUpdate(Calendar lastPriceUpdate) {
        this.lastPriceUpdate = lastPriceUpdate;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    // optional extension
    // subreddit "https://reddit.com/r/bitcoin"

}
