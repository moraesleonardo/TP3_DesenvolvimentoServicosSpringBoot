package info.moraes.tp3servicospringboot.actuator;


import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "customEndpoint")
public class CustomEndpoint {

    @ReadOperation
    public Map<String, Object> customMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("customMetric1", 123);
        metrics.put("customMetric2", "value");
        return metrics;
    }
}
