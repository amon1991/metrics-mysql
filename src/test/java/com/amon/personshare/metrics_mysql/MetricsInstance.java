package com.amon.personshare.metrics_mysql;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;

/**
 * Created by Administrator on 2015/11/4.
 */
public class MetricsInstance {

    /**
     * 实例化一个registry，最核心的一个模块，相当于一个应用程序的metrics系统的容器，维护一个Map
     */
    private static final MetricRegistry metrics = new MetricRegistry();

    /**
     * 将监控指标输出至mysql
     */
    private static MysqlReporter reporter;

    //连接路径
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/metrics";
    //用户名
    private static final String USERNAME = "root";
    //密码
    private static final String PASSWORD = "123456";

    /**
     * 构造私有构造方法，防止产生新对象
     */
    private MetricsInstance(){
    }

    /**
     * 单例，返回MetricRegistry对象
     * @return
     */
    public static final MetricRegistry getMetricRegistryInstance(){
        return metrics;
    }

    /**
     * 单例，返回RedisReporter对象
     * @return
     */
    public static final MysqlReporter getMysqlReporterInstance(){

        if(null == reporter){

            // 植入心跳信息,当不需要监测心跳情况是可不用这么写
            HealthCheckRegistry registry = new HealthCheckRegistry();
            registry.register("database1", new DatabaseHealthCheck());
            registry.register("database2", new DatabaseHealthCheck());

            MysqlReporter.Builder builder = MysqlReporter.forRegistry(metrics,URL,USERNAME,PASSWORD,"tsdata");
            builder.setHealthCheckRegistry(registry);

            reporter = builder.build();

        }

        return  reporter;
    }


}
