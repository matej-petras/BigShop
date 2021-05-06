package com.shop.dataLoading;

import java.util.List;

// Ideal in case of setting up dependency doubles
public interface IDataLoader<T> {
    List<T> getDataAsList();
}
