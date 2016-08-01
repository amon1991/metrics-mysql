package com.amon.personshare.metrics_mysql.model;

/**
 * Created by Administrator on 2015/11/9.
 */
public class Counter {

    private int id;
    private String appname;
    private long tm;
    private String metricskey;
    private long value;

    public Counter() {
    }

    @Override
    public String toString() {
        return "Counter{" +
                "id=" + id +
                ", appname='" + appname + '\'' +
                ", tm=" + tm +
                ", metricskey='" + metricskey + '\'' +
                ", value=" + value +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public long getTm() {
        return tm;
    }

    public void setTm(long tm) {
        this.tm = tm;
    }

    public String getMetricskey() {
        return metricskey;
    }

    public void setMetricskey(String metricskey) {
        this.metricskey = metricskey;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
