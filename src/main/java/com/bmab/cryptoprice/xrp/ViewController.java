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


//
// march 20 and up
// http://localhost:8080/xrp?p=406.0125&p=375.4984@180&p=424.0575&p=436.0875&p=445.1736&p=1137.8375@500&p=502.4102@214.944032&p=78.4837@33&p=167.1831@70&p=392.0036@155
// below march 20
// http://localhost:8080/xrp?p=406.0125&p=375.4984@180&p=424.0575&p=436.0875&p=445.1736&p=1137.8375@500&p=502.4102@214.944032&p=78.4837@33&p=167.1831@70&p=392.0036@155&p=375.2948@165&p=558.8826@237&p=7.2567@3
// http://localhost:8080/xrp?p=406.0125&p=375.4984@180&p=424.0575&p=436.0875&p=445.1736&p=1137.8375@500&p=502.4102@214.944032&p=78.4837@33&p=167.1831@70&p=392.0036@155&p=375.2948@165&p=558.8826@237&p=7.2567@3&p=41.1512@16.91&p=1100.4195@449.125&p=31.74@13&p=24.5951@10&p=2873.136@1190&p=432.58@200&p=216.4178@99&p=111.334@51