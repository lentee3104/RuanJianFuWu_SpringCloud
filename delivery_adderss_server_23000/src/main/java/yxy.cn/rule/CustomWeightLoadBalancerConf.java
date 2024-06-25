package yxy.cn.rule;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ThreadLocalRandom;

public class CustomWeightLoadBalancerConf {

//    ReactorLoadBalancer<ServiceInstance> weightLoadBalancer(Environment envir, LoadBalancerClientFactory lbf) {
//        String name = envir.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
//        return new Custom(lbf.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
//
//    }

    @Bean
    public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
            ConfigurableApplicationContext context) {
        return ServiceInstanceListSupplier.builder()
                .withDiscoveryClient()
                .withWeighted(instance -> ThreadLocalRandom.current().nextInt(1, 101))
                .withCaching()
                .build(context);
    }

        //这里的environment是指
}
