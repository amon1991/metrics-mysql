package com.amon.personshare.metrics_mysql.model;

/**
 * Created by Administrator on 2015/11/9.
 */
public class Counter {

    private int id;
    private String appname;
    private long tm;
    private String metricskey;
    private int value;

    public Counter() {
    }

    public Counter(String appname, long tm, String metricskey, int value) {
        this.appname = appname;
        this.tm = tm;
        this.metricskey = metricskey;
        this.value = value;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
