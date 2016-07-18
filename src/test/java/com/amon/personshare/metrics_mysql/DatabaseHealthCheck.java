package com.amon.personshare.metrics_mysql;

import com.codahale.metrics.health.HealthCheck;

/**
 * 使用起来和与上述几种类型的Metrics有点类似，但是需要重新实例化一个Metrics容器HealthCheckRegistry，
 * 待检测模块继承抽象类HealthCheck并实现check()方法即可，然后将该模块注册到HealthCheckRegistry中，
 * 判断的时候通过isHealthy()接口即可。
 * Created by Administrator on 2015/11/5.
 */
public class DatabaseHealthCheck extends HealthCheck {


    public DatabaseHealthCheck() {
    }

    @Override
    protected Result check() throws Exception {
        if (true) {
            return Result.healthy();
        }
        return Result.unhealthy("Can't ping database.");
    }

}
