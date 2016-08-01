package com.amon.personshare.metrics_mysql.model;

/**
 * Created by Administrator on 2015/11/9.
 */
public class Meters {

    private int id;
    private String appname;
    private long tm;
    private String metricskey;
    private long count;
    private double meanrate;
    private double onemrate;
    private double fivemrate;
    private double fifmrate;

    public Meters() {
    }

    @Override
    public String toString() {
        return "Meters{" +
                "id=" + id +
                ", appname='" + appname + '\'' +
                ", tm=" + tm +
                ", metricskey='" + metricskey + '\'' +
                ", count=" + count +
                ", meanrate=" + meanrate +
                ", onemrate=" + onemrate +
                ", fivemrate=" + fivemrate +
                ", fifmrate=" + fifmrate +
                '}';
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
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
