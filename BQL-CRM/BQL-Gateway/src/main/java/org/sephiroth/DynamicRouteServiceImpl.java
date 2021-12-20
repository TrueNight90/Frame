package org.sephiroth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 增加路由
     * @param routeDefinition
     * @return
     */
    public String add(RouteDefinition routeDefinition){
        routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }

    /**
     * gengx路由
     * @param routeDefinition
     * @return
     */
    public String update(RouteDefinition routeDefinition){
        try{
            routeDefinitionWriter.delete(Mono.just(routeDefinition.getId()));
        }catch (Exception e){
            return "failed routerId:"+routeDefinition.getId();
        }
        try {
            routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
            this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
            return "success";
        }catch (Exception e){
            return "failed";
        }
    }

    /**
     * gengx路由
     * @param id
     * @return
     */
    public String delete(String id){
        try{
            routeDefinitionWriter.delete(Mono.just(id));
            return "success";
        }catch (Exception e){
            return "failed";
        }
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
