package edu.sjsu.android.finalprojectv1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Use this class when API doesn't work
 * Just replace the constructor call from
 * = new CoinMarketCapApi(...);
 * to
 * = new MockApi();
 */
public class MockApi implements CurrencyDataApi {


    @Override
    public List<CryptoCurrency> getCryptoCurrencies() {
        List<CryptoCurrency> currencies = new ArrayList<>();
        currencies.add(new CryptoCurrency("1", "Bitcoin", "BTC", 29000.00f, "https://s2.coinmarketcap.com/static/img/coins/64x64/1.png", Calendar.getInstance(), "https://bitcoin.org/", "The original super cryptocurrency we all love jey..."));
        currencies.add(new CryptoCurrency("1", "Bitcoin2", "BTC2", 30000.00f, "https://s2.coinmarketcap.com/static/img/coins/64x64/1.png", Calendar.getInstance(), "https://bitcoin.org/", "The original super cryptocurrency we all love jey..."));
        currencies.add(new CryptoCurrency("1", "Bitcoin3", "BTC3", 35000.00f, "https://s2.coinmarketcap.com/static/img/coins/64x64/1.png", Calendar.getInstance(), "https://bitcoin.org/", "The original super cryptocurrency we all love jey..."));
        currencies.add(new CryptoCurrency("1", "Bitcoin4", "BTC4", 1000.00f, "https://s2.coinmarketcap.com/static/img/coins/64x64/1.png", Calendar.getInstance(), "https://bitcoin.org/", "The original super cryptocurrency we all love jey..."));
        return currencies;
    }

    @Override
    public CryptoCurrency updateCurrencyInformation(CryptoCurrency cryptoCurrency) {
        cryptoCurrency.setDescription(cryptoCurrency.getDescription() + " [updated text]");
        return cryptoCurrency;
    }
}
