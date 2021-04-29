package com.shop.products;

import com.shop.utils.IDataLoader;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductsLoader implements IDataLoader {
    @Override
    public List<Product> getDataAsList() {
        return Stream.of(
                    new Product("SIM", 20f),
                    new Product("Phone Case", 10f),
                    new Product("Phone Insurance (2 years)", 120f),
                    new Product("Wired Headphones", 30f),
                    new Product("Wireless Headphones", 50f)
                ).collect(Collectors.toList());
    }
}
