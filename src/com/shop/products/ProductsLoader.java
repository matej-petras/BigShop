package com.shop.products;

import com.shop.utils.IDataLoader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductsLoader implements IDataLoader<Product> {
    @Override
    public List<Product> getDataAsList() {
        List<String> simParameters = Arrays.asList("SIM");
        List<String> caseParameters = Arrays.asList("Phone Case");
        List<String> phoneInsuranceParameters = Arrays.asList("Insurance", "2 years");
        List<String> wiredHeadphonesParameters = Arrays.asList("Headphones", "Wired");
        List<String> wirelessHeadphonesParameters = Arrays.asList("Headphones", "Wireless");

        return Stream.of(
                    new Product("MeowSim 123", simParameters, 20f),
                    new Product("XI APO Case", caseParameters, 10f),
                    new Product("Insurance Oi2", phoneInsuranceParameters, 120f),
                    new Product("Headphones Wired Move 2x", wiredHeadphonesParameters, 30f),
                    new Product("Wireless Headphones M1000", wirelessHeadphonesParameters, 50f)
                ).collect(Collectors.toList());
    }
}
