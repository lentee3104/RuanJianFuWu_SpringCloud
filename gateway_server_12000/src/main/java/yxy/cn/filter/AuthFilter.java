package yxy.cn.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter {
    @Override  //全局的过滤器
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) { //这里是 责任链 设计模式。
        String token = exchange.getRequest().getQueryParams().getFirst("token"); //一般token放在 请求的头部，当为了方便就放在请求里面。
        //然后可以对token进行各种处理，这个token携带的信息 可以代表 该用户的角色、权限。
        if(token == null){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED); //如果没有token，告诉他无权限 401code
            return exchange.getResponse().setComplete(); //无token，就直接截断他的请求，会返回401
        }

        return chain.filter(exchange); //继续执行过滤器链的下一个步骤：也就是我们的请求可以发给服务器了
    }
}
