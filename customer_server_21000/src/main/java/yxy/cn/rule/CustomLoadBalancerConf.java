package yxy.cn.rule;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class CustomLoadBalancerConf {
    @Bean
    ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment envir, LoadBalancerClientFactory lbf){
        //获取 需要进行 负载均衡 的服务 的名字
        String name = envir.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new RandomLoadBalancer(lbf.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    }
}
