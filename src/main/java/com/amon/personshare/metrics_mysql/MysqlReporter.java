package com.amon.personshare.metrics_mysql;

import com.amon.personshare.metrics_mysql.model.*;
import com.codahale.metrics.*;
import com.codahale.metrics.Counter;
import com.codahale.metrics.Timer;
import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * metrics report for redis
 * Created by Administrator on 2015/11/5.
 */
public class MysqlReporter extends ScheduledReporter {

    private String appName;              //config the app name，(#{appName}+gauges)is redis key, and value is a json data with gauges metrics

    private final DateFormat dateFormat;

    private HealthCheckRegistry healthCheckRegistry;
    private final Locale locale;
    private final Clock clock;

    private String url;       //mysql连接url
    private String username;  //mysql用户名
    private String passwd;    //mysql密码

    private Connection con =null;
    private PreparedStatement pstmt = null;

    /**
     * 通过build内部类来构建实际的属性值
     * @param registry
     * @param url
     * @param username
     * @param passwd
     * @param appName
     * @return
     */
    public static Builder forRegistry(MetricRegistry registry,
                                                    String url, String username, String passwd,String appName){
        return new Builder(registry,url, username, passwd, appName);
    }

    /**
     * 重写构造函数，初始化redis连接池
     * @param registry
     * @param filter
     * @param rateUnit
     * @param durationUnit
     * @param appName     监控应用名
     */
    protected MysqlReporter(MetricRegistry registry, MetricFilter filter,
                            Locale locale, Clock clock, TimeZone timeZone,
                            TimeUnit rateUnit, TimeUnit durationUnit,
                            String url, String username, String passwd, String appName, HealthCheckRegistry healthCheckRegistry) {
        super(registry, "mysql-reporter", filter, rateUnit, durationUnit);

        this.appName = appName;

        //设置一些时间相关的参数
        this.locale = locale;
        this.clock = clock;
        this.dateFormat = DateFormat.getDateTimeInstance(3, 2, locale);
        this.dateFormat.setTimeZone(timeZone);

        //这个参数比较特殊，不一定会被实例化
        this.healthCheckRegistry = healthCheckRegistry;

        //mysql连接参数
        this.url = url;
        this.username = username;
        this.passwd = passwd;

    }


    /**
     * 最关键的一个方法，将指标输出至redis
     * @param gauges
     * @param counters
     * @param histograms
     * @param meters
     * @param timers
     */
    @Override
    public void report(SortedMap<String, Gauge> gauges,
                       SortedMap<String, Counter> counters,
                       SortedMap<String, Histogram> histograms,
                       SortedMap<String, Meter> meters,
                       SortedMap<String, Timer> timers) {

        long tm = System.currentTimeMillis();

        //gauges
        List<Gauges> gaugesList = new ArrayList<Gauges>();
        for (Map.Entry<String, Gauge> entry : gauges.entrySet()) {

            Gauges mygauges = new Gauges();
            mygauges.setAppname(this.appName);
            mygauges.setMetricskey(entry.getKey());
            mygauges.setTm(tm);
            mygauges.setValue(Integer.parseInt(entry.getValue().getValue()+""));

            gaugesList.add(mygauges);

        }
        this.saveGauges2Mysql(gaugesList);

        //counters
        List<com.amon.personshare.metrics_mysql.model.Counter> counterList = new ArrayList<com.amon.personshare.metrics_mysql.model.Counter>();
        for (Map.Entry<String, Counter> entry : counters.entrySet()) {

            com.amon.personshare.metrics_mysql.model.Counter mycounter = new com.amon.personshare.metrics_mysql.model.Counter();
            mycounter.setAppname(this.appName);
            mycounter.setMetricskey(entry.getKey());
            mycounter.setTm(tm);
            mycounter.setValue(Integer.parseInt(entry.getValue()
                    .getCount()+""));

            counterList.add(mycounter);

        }
        this.saveCounter2Mysql(counterList);

        //histograms
        List<Histograms> histogramsList = new ArrayList<Histograms>();
        for (Map.Entry<String, Histogram> entry : histograms.entrySet()) {
            Histogram histogram = entry.getValue();
            Snapshot snapshot = histogram.getSnapshot();

            Histograms myhistograms = new Histograms();
            myhistograms.setAppname(this.appName);
            myhistograms.setTm(tm);
            myhistograms.setMetricskey(entry.getKey());
            myhistograms.setCount(snapshot.size());
            myhistograms.setMax(Integer.parseInt(snapshot.getMax()+""));
            myhistograms.setMin(Integer.parseInt(snapshot.getMin()+""));
            myhistograms.setMean(snapshot.getMean());
            myhistograms.setStddev(snapshot.getStdDev());
            myhistograms.setMean(snapshot.getMean());
            myhistograms.setSenvenfive(snapshot.get75thPercentile());
            myhistograms.setNinefive(snapshot.get95thPercentile());
            myhistograms.setNineeight(snapshot.get98thPercentile());
            myhistograms.setNinenine(snapshot.get99thPercentile());
            myhistograms.setNineninenine(snapshot.get999thPercentile());

            histogramsList.add(myhistograms);

        }
        this.saveHistograms2Mysql(histogramsList);

        //meters
        List<Meters> metersList = new ArrayList<Meters>();
        for (Map.Entry<String, Meter> entry : meters.entrySet()) {

            Meters mymeters = new Meters();
            mymeters.setAppname(this.appName);
            mymeters.setTm(tm);
            mymeters.setMetricskey(entry.getKey());

            mymeters.setCount(Integer.parseInt(entry.getValue().getCount()+""));
            mymeters.setMeanrate(convertRate(entry.getValue().getMeanRate()));
            mymeters.setOnemrate(convertRate(entry.getValue().getOneMinuteRate()));
            mymeters.setFivemrate(convertRate(entry.getValue().getFiveMinuteRate()));
            mymeters.setFifmrate(convertRate(entry.getValue().getFifteenMinuteRate()));

            metersList.add(mymeters);

        }
        this.saveMeters2Mysql(metersList);

        //Timer
        List<Timers> timersList = new ArrayList<Timers>();
        for (Map.Entry<String, Timer> entry : timers.entrySet()) {
            Timer timer = entry.getValue();
            Snapshot snapshot = timer.getSnapshot();

            Timers mytimers = new Timers();

            mytimers.setAppname(this.appName);
            mytimers.setTm(tm);
            mytimers.setMetricskey(entry.getKey());

            //timer
            mytimers.setCount(Integer.parseInt(timer.getCount()+""));
            mytimers.setMeanrate(convertRate(timer.getMeanRate()));
            mytimers.setOnemrate(convertRate(timer.getOneMinuteRate()));
            mytimers.setFivemrate(convertRate(timer.getFiveMinuteRate()));
            mytimers.setFifmrate(convertRate(timer.getFifteenMinuteRate()));

            //snapshot
            mytimers.setMin(convertDuration(snapshot.getMin()));//对结果进行格式化
            mytimers.setMax(convertDuration(snapshot.getMax()));
            mytimers.setMean(convertDuration(snapshot.getMean()));
            mytimers.setStddev(convertDuration(snapshot.getStdDev()));
            mytimers.setMedian(convertDuration(snapshot.getMedian()));
            mytimers.setSenvenfive(convertDuration(snapshot.get75thPercentile()));
            mytimers.setNinefive(convertDuration(snapshot.get95thPercentile()));
            mytimers.setNineeight(convertDuration(snapshot.get98thPercentile()));
            mytimers.setNinenine(convertDuration(snapshot.get99thPercentile()));
            mytimers.setNineninenine(convertDuration(snapshot.get999thPercentile()));

            timersList.add(mytimers);

        }
        this.saveTimers2Mysql(timersList);

        //HealthCheck
        List<HealthChecks> healthChecksList = new ArrayList<HealthChecks>();

        if (null!=healthCheckRegistry){

            for (Map.Entry<String, HealthCheck.Result> entry : healthCheckRegistry.runHealthChecks().entrySet()) {
                if (entry.getValue().isHealthy()) {

                    HealthChecks healthChecks = new HealthChecks();
                    healthChecks.setAppname(this.appName);
                    healthChecks.setTm(tm);
                    healthChecks.setMetricskey(entry.getKey());
                    healthChecks.setIshealth(true);
                    healthChecks.setMessage(entry.getValue().getMessage());

                    healthChecksList.add(healthChecks);

                } else {

                    HealthChecks healthChecks = new HealthChecks();
                    healthChecks.setAppname(this.appName);
                    healthChecks.setTm(tm);
                    healthChecks.setMetricskey(entry.getKey());
                    healthChecks.setIshealth(false);
                    healthChecks.setMessage(entry.getValue().getMessage());

                    healthChecksList.add(healthChecks);

                    final Throwable e = entry.getValue().getError();
                    if (e != null) {
                        e.printStackTrace();
                    }

                }
            }
            this.saveHealthchecks2Mysql(healthChecksList);
        }

    }

    /**
     * 将Gauges指标数据保存至mysql数据库
     * @param gaugesList
     */
    private void saveGauges2Mysql(List<Gauges> gaugesList){

        BaseDao dao = new BaseDao();
        String sql = "insert into gauges (appname,tm,metricskey,`value`) values(?,?,?,?)";

        pstmt = null;

        try {

            con = dao.getConnection(this.url,this.username,this.passwd);
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(sql);

            for (Gauges gauges : gaugesList) {
                pstmt.setString(1, gauges.getAppname());
                pstmt.setTimestamp(2,new Timestamp(gauges.getTm()));
                pstmt.setString(3,gauges.getMetricskey());
                pstmt.setInt(4, gauges.getValue());
                pstmt.addBatch();//加入批量处理
            }

            pstmt.executeBatch(); //执行批量处理
            con.commit();         //提交

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            gaugesList = null;
            dao.close(pstmt,con);
        }

    }

    /**
     * 将Counter指标数据保存至mysql数据库
     * @param countersList
     */
    private void saveCounter2Mysql(List<com.amon.personshare.metrics_mysql.model.Counter> countersList){

        BaseDao dao = new BaseDao();
        String sql = "insert into counter (appname,tm,metricskey,`value`) values(?,?,?,?)";

        pstmt = null;

        try {

            con = dao.getConnection(this.url,this.username,this.passwd);
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(sql);

            for (com.amon.personshare.metrics_mysql.model.Counter counter : countersList) {
                pstmt.setString(1, counter.getAppname());
                pstmt.setTimestamp(2,new Timestamp(counter.getTm()));
                pstmt.setString(3,counter.getMetricskey());
                pstmt.setInt(4, counter.getValue());
                pstmt.addBatch();//加入批量处理
            }

            pstmt.executeBatch(); //执行批量处理
            con.commit();         //提交

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            countersList = null;
            dao.close(pstmt,con);
        }

    }

    /**
     * 将Histograms指标数据保存至mysql数据库
     * @param histogramsList
     */
    private void saveHistograms2Mysql(List<Histograms> histogramsList){

        BaseDao dao = new BaseDao();
        String sql = "insert into histograms (appname,tm,metricskey,count,min,max,mean," +
                "stddev,median,sevenfive,ninefive,nineeight,ninenine,nineninenine) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        pstmt = null;

        try {

            con = dao.getConnection(this.url,this.username,this.passwd);
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(sql);

            for (Histograms histograms : histogramsList) {
                pstmt.setString(1, histograms.getAppname());
                pstmt.setTimestamp(2,new Timestamp(histograms.getTm()));
                pstmt.setString(3,histograms.getMetricskey());
                pstmt.setInt(4, histograms.getCount());
                pstmt.setInt(5, histograms.getMin());
                pstmt.setInt(6, histograms.getMax());
                pstmt.setDouble(7,histograms.getMean());
                pstmt.setDouble(8,histograms.getStddev());
                pstmt.setDouble(9,histograms.getMedian());
                pstmt.setDouble(10,histograms.getSenvenfive());
                pstmt.setDouble(11,histograms.getNinefive());
                pstmt.setDouble(12,histograms.getNineeight());
                pstmt.setDouble(13,histograms.getNinenine());
                pstmt.setDouble(14,histograms.getNineninenine());

                pstmt.addBatch();//加入批量处理
            }

            pstmt.executeBatch(); //执行批量处理
            con.commit();         //提交

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            histogramsList = null;
            dao.close(pstmt,con);
        }

    }

    /**
     * 将Meters指标数据保存至mysql数据库
     * @param metersList
     */
    private void saveMeters2Mysql(List<Meters> metersList){

        BaseDao dao = new BaseDao();
        String sql = "insert into meters (appname,tm,metricskey,count,meanrate,onemrate,fivemrate,fifmrate) " +
                "values(?,?,?,?,?,?,?,?)";

        pstmt = null;

        try {

            con = dao.getConnection(this.url,this.username,this.passwd);
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(sql);

            for (Meters meters : metersList) {
                pstmt.setString(1, meters.getAppname());
                pstmt.setTimestamp(2,new Timestamp(meters.getTm()));
                pstmt.setString(3,meters.getMetricskey());
                pstmt.setInt(4, meters.getCount());
                pstmt.setDouble(5,meters.getMeanrate());
                pstmt.setDouble(6,meters.getOnemrate());
                pstmt.setDouble(7,meters.getFivemrate());
                pstmt.setDouble(8,meters.getFifmrate());

                pstmt.addBatch();//加入批量处理
            }

            pstmt.executeBatch(); //执行批量处理
            con.commit();         //提交

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            metersList = null;
            dao.close(pstmt,con);
        }

    }

    /**
     * 将Timers指标数据保存至mysql数据库
     * @param timersList
     */
    private void saveTimers2Mysql(List<Timers> timersList){

        BaseDao dao = new BaseDao();
        String sql = "insert into timers (appname,tm,metricskey,count,meanrate,onemrate,fivemrate,fifmrate, min,max,mean,stddev,median,sevenfive,ninefive,nineeight,ninenine,nineninenine) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        pstmt = null;

        try {

            con = dao.getConnection(this.url,this.username,this.passwd);
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(sql);

            for (Timers timers : timersList) {
                pstmt.setString(1, timers.getAppname());
                pstmt.setTimestamp(2,new Timestamp(timers.getTm()));
                pstmt.setString(3,timers.getMetricskey());
                pstmt.setInt(4, timers.getCount());
                pstmt.setDouble(5,timers.getMeanrate());
                pstmt.setDouble(6,timers.getOnemrate());
                pstmt.setDouble(7,timers.getFivemrate());
                pstmt.setDouble(8,timers.getFifmrate());

                pstmt.setDouble(9, timers.getMin());
                pstmt.setDouble(10, timers.getMax());
                pstmt.setDouble(11,timers.getMean());
                pstmt.setDouble(12,timers.getStddev());
                pstmt.setDouble(13,timers.getMedian());
                pstmt.setDouble(14,timers.getSenvenfive());
                pstmt.setDouble(15,timers.getNinefive());
                pstmt.setDouble(16,timers.getNineeight());
                pstmt.setDouble(17,timers.getNinenine());
                pstmt.setDouble(18,timers.getNineninenine());

                pstmt.addBatch();//加入批量处理
            }

            pstmt.executeBatch(); //执行批量处理
            con.commit();         //提交

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            timersList = null;
            dao.close(pstmt,con);
        }

    }

    /**
     * 将Healthchecks指标数据保存至mysql数据库
     * @param healthChecksList
     */
    private void saveHealthchecks2Mysql(List<HealthChecks> healthChecksList){

        BaseDao dao = new BaseDao();
        String sql = "insert into healthchecks (appname, tm, metricskey, ishealth, message) " +
                "values(?,?,?,?,?)";

        pstmt = null;

        try {

            con = dao.getConnection(this.url,this.username,this.passwd);
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(sql);

            for (HealthChecks healthChecks : healthChecksList) {
                pstmt.setString(1, healthChecks.getAppname());
                pstmt.setTimestamp(2,new Timestamp(healthChecks.getTm()));
                pstmt.setString(3,healthChecks.getMetricskey());
                pstmt.setBoolean(4, healthChecks.ishealth());
                pstmt.setString(5,healthChecks.getMessage());
                pstmt.addBatch();//加入批量处理
            }

            pstmt.executeBatch(); //执行批量处理
            con.commit();         //提交

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            healthChecksList = null;
            dao.close(pstmt,con);
        }

    }

    /**
     * 内部类，用于初始化一些默认参数，并传入外部传入的设置参数
     */
    public static class Builder {

        private final MetricRegistry registry;
        private Locale locale;
        private Clock clock;
        private TimeZone timeZone;
        private TimeUnit rateUnit;
        private TimeUnit durationUnit;
        private MetricFilter filter;

        private String url;       //mysql连接url
        private String username;  //mysql用户名
        private String passwd;    //mysql密码

        private String appName;
        private HealthCheckRegistry healthCheckRegistry;

        private Builder(MetricRegistry registry,String url, String username, String passwd,String appName) {

            this.registry = registry;
            this.url = url;
            this.username = username;
            this.passwd = passwd;
            this.appName = appName;

            this.locale = Locale.getDefault();
            this.clock = Clock.defaultClock();
            this.timeZone = TimeZone.getDefault();
            this.rateUnit = TimeUnit.SECONDS;
            this.durationUnit = TimeUnit.MILLISECONDS;
            this.filter = MetricFilter.ALL;

        }

        public void setHealthCheckRegistry(HealthCheckRegistry healthCheckRegistry){
            this.healthCheckRegistry = healthCheckRegistry;
        }

        /**
         * 下面的这些方法可以修改默认的属性值，方便今后扩展
         * @param locale
         * @return
         */
        public Builder formattedFor(Locale locale) {
            this.locale = locale;
            return this;
        }

        public Builder withClock(Clock clock) {
            this.clock = clock;
            return this;
        }

        public Builder formattedFor(TimeZone timeZone) {
            this.timeZone = timeZone;
            return this;
        }

        public Builder convertRatesTo(TimeUnit rateUnit) {
            this.rateUnit = rateUnit;
            return this;
        }

        public Builder convertDurationsTo(TimeUnit durationUnit) {
            this.durationUnit = durationUnit;
            return this;
        }

        public Builder filter(MetricFilter filter) {
            this.filter = filter;
            return this;
        }

        public MysqlReporter build() {
            return new MysqlReporter(this.registry, this.filter ,this.locale, this.clock, this.timeZone, this.rateUnit, this.durationUnit, this.url, this
            .username, this.passwd, this.appName , this.healthCheckRegistry);
        }
    }

}
