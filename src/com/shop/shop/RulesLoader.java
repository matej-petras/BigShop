package com.shop.shop;

import com.shop.products.productRules.IAdditionRule;
import com.shop.products.productRules.SimBOGOFRule;
import com.shop.utils.IDataLoader;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RulesLoader implements IDataLoader<IAdditionRule> {
    @Override
    public List<IAdditionRule> getDataAsList() {
        return Stream.of(
                    new SimBOGOFRule()
                ).collect(Collectors.toList());
    }
}
