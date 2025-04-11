package com.bmab.cryptoprice.xrp;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class ViewController {

    private ComputeService computeService;

    @GetMapping("/xrp")
    public String showPrice(
            @RequestParam(required = false) List<String> p,
            @RequestParam(required = false) List<String> b,
            @RequestParam(required = false) List<String> f) {
        return computeService.calcBidAverage200s(p, b, f);
    }
}
// sample
// http://localhost:8080/xrp?b=2.0250&b=2.0809@180&b=2.1150&b=2.1750&b=2.2170&b=2.27@500&b=2.32@215
// price
// http://localhost:8080/xrp?p=406.0125&p=375.4984@180&p=424.0575&p=436.0875&p=445.1736&p=1137.8375@500&p=502.4102@214.944032
// bid
// http://localhost:8080/xrp?b=1.9109@483.61608&b=2.0250&b=2.0809@180&b=2.1150&b=2.1750&b=2.2170&b=2.27@500&b=2.32@214.944032

// my stuff last week
// http://localhost:8080/xrp?p=927.8385@483.61608&p=406.0125&p=375.4984@180&p=424.0575&p=436.0875&p=445.1736&p=1137.8375@500&p=502.4102@214.944032

//updated after lock
// http://localhost:8080/xrp?p=316.2136@155&p=611.436@300&p=406.0125&p=375.4984@180&p=424.0575&p=436.0875&p=445.1736&p=1137.8375@500&p=502.4102@214.944032

//http://localhost:8080/xrp?p=&p=&p=&p=&p=&p=&p=&p=&p=&p=

//
// march 20 and up
// http://localhost:8080/xrp?p=406.0125&p=375.4984@180&p=424.0575&p=436.0875&p=445.1736&p=1137.8375@500&p=502.4102@214.944032&p=78.4837@33&p=167.1831@70&p=392.0036@155
// below march 20
// http://localhost:8080/xrp?p=406.0125&p=375.4984@180&p=424.0575&p=436.0875&p=445.1736&p=1137.8375@500&p=502.4102@214.944032&p=78.4837@33&p=167.1831@70&p=392.0036@155&p=375.2948@165&p=558.8826@237&p=7.2567@3
// http://localhost:8080/xrp?p=406.0125&p=375.4984@180&p=424.0575&p=436.0875&p=445.1736&p=1137.8375@500&p=502.4102@214.944032&p=78.4837@33&p=167.1831@70&p=392.0036@155&p=375.2948@165&p=558.8826@237&p=7.2567@3&p=41.1512@16.91&p=1100.4195@449.125&p=31.74@13&p=24.5951@10&p=2873.136@1190&p=432.58@200&p=216.4178@99&p=111.334@51

// #########
// April 12
// crypto exchange
// http://localhost:8080/xrp?p=350.18&p=351.45656@184&p=380.18&p=20.0754@10&p=195.66549@97&p=199.69983@99&p=195.66549@97&p=145.23624@72&f=2.5
// http://localhost:8080/xrp?p=350.18&p=351.45656@184&p=380.18&p=20.0754@10&p=195.66549@97&p=199.69983@99&p=195.66549@97&p=145.23624@72&f=4.9&f=36.813
// = 959 xrp @ 1.9194 (but conversion fee = 36.813) so
// ce = Bid = 1.9602 XRP = 959 USDC = 1879.87201
//
// http://localhost:8080/xrp?p=406.0125&p=611.436@300&p=316.2136@155&f=6.11
// = 655 xrp @ 2.03
// cb = Bid = 2.0455 XRP = 655 USDC = 1339.7721

// summary together
// Bid = 1.9948 XRP = 1614 USDC = 3219.6343 // http://localhost:8080/xrp?b=1.9602@959&b=2.0455@655
// $$$$$$ SOLD 400 xrp @ 818.3490
// Bid = 2.0459 XRP = 400 USDC = 818.3490
// ======= LEFT to play around low prices 1214 and sell at above 1.9948
// will breakeven my low prices

// ##########