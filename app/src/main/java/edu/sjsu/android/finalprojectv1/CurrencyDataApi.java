package edu.sjsu.android.finalprojectv1;

import java.util.List;

public interface CurrencyDataApi {

    public List<CryptoCurrency> getCryptoCurrencies();

    /**
     * Updates the given crypto currency and returns the same object but updated
     * @param cryptoCurrency
     * @return
     */
    public CryptoCurrency updateCurrencyInformation(CryptoCurrency cryptoCurrency);
}
