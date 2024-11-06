package uz.alex.apigateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
public class PreFilter implements GlobalFilter, Ordered {
    @Override
    public int getOrder() {
        return 0;
    }

    private final Logger log = LoggerFactory.getLogger(PreFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("PreFilter first executed filter order works as asc________________");
        log.info("Pre filtering request");
        String path = exchange.getRequest().getPath().toString();
        log.info("Request path: {}", path);
        HttpHeaders headers = exchange.getRequest().getHeaders();
        Set<String> headerNames = headers.keySet();
        headerNames.forEach(header -> {
            log.info("HeaderName and HeaderValue: {} -> {}", header, headers.getFirst(header));
        });

        return chain.filter(exchange);
    }
}
