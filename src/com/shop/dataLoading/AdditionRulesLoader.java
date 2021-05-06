package com.shop.dataLoading;

import com.shop.rules.additionRules.ShopAdditionRule;
import com.shop.rules.additionRules.SimBOGOFRule;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdditionRulesLoader implements IDataLoader<ShopAdditionRule> {
    @Override
    public List<ShopAdditionRule> getDataAsList() {
        return Stream.of(
                    new SimBOGOFRule()
                ).collect(Collectors.toList());
    }
}
