package com.amon.personshare.metrics_mysql.model;

/**
 * Created by Administrator on 2015/11/9.
 */
public class Meters {

    private int id;
    private String appname;
    private long tm;
    private String metricskey;
    private int count;
    private double meanrate;
    private double onemrate;
    private double fivemrate;
    private double fifmrate;

    public Meters() {
    }

    public Meters(String appname, long tm, String metricskey, int count, double meanrate, double onemrate, double fivemrate, double fifmrate) {
        this.appname = appname;
        this.tm = tm;
        this.metricskey = metricskey;
        this.count = count;
        this.meanrate = meanrate;
        this.onemrate = onemrate;
        this.fivemrate = fivemrate;
        this.fifmrate = fifmrate;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getMeanrate() {
        return meanrate;
    }

    public void setMeanrate(double meanrate) {
        this.meanrate = meanrate;
    }

    public double getOnemrate() {
        return onemrate;
    }

    public void setOnemrate(double onemrate) {
        this.onemrate = onemrate;
    }

    public double getFivemrate() {
        return fivemrate;
    }

    public void setFivemrate(double fivemrate) {
        this.fivemrate = fivemrate;
    }

    public double getFifmrate() {
        return fifmrate;
    }

    public void setFifmrate(double fifmrate) {
        this.fifmrate = fifmrate;
    }
}
