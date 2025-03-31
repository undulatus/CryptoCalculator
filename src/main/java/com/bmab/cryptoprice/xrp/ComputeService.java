package com.bmab.cryptoprice.xrp;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ComputeService {

    //can change or use param
    private final BigDecimal defaultAmount = BigDecimal.valueOf(200L);

    public String calcBidAverage200s(List<String> prices, List<String> bids, List<String> fees) {

        BigDecimal requestPriceTotal =
                CollectionUtils.emptyIfNull(prices).stream()
                    .map(this::parseWeightedPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal requestBidPriceTotal =
                CollectionUtils.emptyIfNull(bids).stream()
                        .map(this::parseWeightedBid)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal requestFeesTotal = CollectionUtils.emptyIfNull(fees)
                .stream().map(BigDecimal::new).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal actualPriceTotal = requestPriceTotal.add(requestBidPriceTotal).add(requestFeesTotal);

        //fixing the weights
        BigDecimal unitAmountTotal = parseAmountTotal(prices, bids);

        BigDecimal averageBid = actualPriceTotal.divide(unitAmountTotal, 4, RoundingMode.HALF_UP);

        return String.format("Bid = %s\n\tXRP = %s\n\tUSDC = %s",averageBid, unitAmountTotal,actualPriceTotal);
    }

    private BigDecimal parseWeightedPrice(String reqPrice) {
        String[] parts = reqPrice.split("@");
        if (parts.length == 2) {
            BigDecimal price = new BigDecimal(parts[0]);
            return price;
        } else {
            return new BigDecimal(reqPrice);
        }
    }

    private BigDecimal parseWeightedBid(String reqBid) {
        String[] parts = reqBid.split("@");
        if (parts.length == 2) {
            BigDecimal bid = new BigDecimal(parts[0]);
            BigDecimal amount = new BigDecimal(parts[1]);
            return bid.multiply(amount);
        } else {
            return new BigDecimal(reqBid).multiply(defaultAmount);
        }
    }

    private BigDecimal parseAmountTotal(List<String> prices, List<String> bids) {
        List<String> pricesWithWeight = CollectionUtils.emptyIfNull(prices).stream().filter(price -> price.contains("@")).toList();
        List<String> bidsWithWeight = CollectionUtils.emptyIfNull(bids).stream().filter(bid -> bid.contains("@")).toList();

        int countNonWeighted = CollectionUtils.emptyIfNull(prices).size() - pricesWithWeight.size()
                    +
                CollectionUtils.emptyIfNull(bids).size() - bidsWithWeight.size();

        BigDecimal nonWeightedAmountTotal = BigDecimal.valueOf(countNonWeighted).multiply(defaultAmount);

        BigDecimal weightedAmountTotal = calculateWeightedAmountTotal(pricesWithWeight).add(calculateWeightedAmountTotal(bidsWithWeight));

        return nonWeightedAmountTotal.add(weightedAmountTotal);
    }

    private BigDecimal calculateWeightedAmountTotal(List<String> weightedPriceOrBidList) {
        return CollectionUtils.emptyIfNull(weightedPriceOrBidList).stream()
                .map(price -> {
                    String[] p = price.split("@");
                    return new BigDecimal(p[1]);
                }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
