package com.amon.personshare.metrics_mysql.model;

/**
 * Created by Administrator on 2015/11/9.
 */
public class Timers {

    private int id;
    private String appname;
    private long tm;
    private String metricskey;
    private long count;
    private double meanrate;
    private double onemrate;
    private double fivemrate;
    private double fifmrate;
    private double min;
    private double max;
    private double mean;
    private double stddev;
    private double median;
    private double senvenfive;
    private double ninefive;
    private double nineeight;
    private double ninenine;
    private double nineninenine;

    public Timers() {
    }

    @Override
    public String toString() {
        return "Timers{" +
                "id=" + id +
                ", appname='" + appname + '\'' +
                ", tm=" + tm +
                ", metricskey='" + metricskey + '\'' +
                ", count=" + count +
                ", meanrate=" + meanrate +
                ", onemrate=" + onemrate +
                ", fivemrate=" + fivemrate +
                ", fifmrate=" + fifmrate +
                ", min=" + min +
                ", max=" + max +
                ", mean=" + mean +
                ", stddev=" + stddev +
                ", median=" + median +
                ", senvenfive=" + senvenfive +
                ", ninefive=" + ninefive +
                ", nineeight=" + nineeight +
                ", ninenine=" + ninenine +
                ", nineninenine=" + nineninenine +
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

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getStddev() {
        return stddev;
    }

    public void setStddev(double stddev) {
        this.stddev = stddev;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public double getSenvenfive() {
        return senvenfive;
    }

    public void setSenvenfive(double senvenfive) {
        this.senvenfive = senvenfive;
    }

    public double getNinefive() {
        return ninefive;
    }

    public void setNinefive(double ninefive) {
        this.ninefive = ninefive;
    }

    public double getNineeight() {
        return nineeight;
    }

    public void setNineeight(double nineeight) {
        this.nineeight = nineeight;
    }

    public double getNinenine() {
        return ninenine;
    }

    public void setNinenine(double ninenine) {
        this.ninenine = ninenine;
    }

    public double getNineninenine() {
        return nineninenine;
    }

    public void setNineninenine(double nineninenine) {
        this.nineninenine = nineninenine;
    }
}
