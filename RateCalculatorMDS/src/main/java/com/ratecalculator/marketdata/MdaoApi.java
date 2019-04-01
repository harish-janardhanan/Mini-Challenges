package com.ratecalculator.marketdata;

import java.util.List;

public interface MdaoApi<T> {

    List<T> getAll();

    T get(String lenderName);
}
