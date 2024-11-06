package uz.alex.apigateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalFilters {
    private final Logger log = LoggerFactory.getLogger(GlobalFilters.class);

    @Order(1)
    @Bean
    public GlobalFilter filter() {
        return ((exchange, chain) -> {
            // for prefFilter order works as asc
            log.info("Global pre Filter executed...1");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> log.info("Global post Filter executed...1")));
        });
    }

    @Order(2)
    @Bean
    public GlobalFilter anotherFilter() {
        return ((exchange, chain) -> {
            log.info("Another Global pre Filter executed...2");
            return chain.filter(exchange).then(Mono.fromRunnable(() ->
                    // for postFilter order works as desc
                    log.info("Another Global post Filter executed...2")));
        });
    }
}
