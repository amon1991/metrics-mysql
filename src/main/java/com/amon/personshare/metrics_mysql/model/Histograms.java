package com.amon.personshare.metrics_mysql.model;

/**
 * Created by Administrator on 2015/11/9.
 */
public class Histograms {

    private int id;
    private String appname;
    private long tm;
    private String metricskey;
    private long count;
    private long min;
    private long max;
    private double mean;
    private double stddev;
    private double median;
    private double senvenfive;
    private double ninefive;
    private double nineeight;
    private double ninenine;
    private double nineninenine;

    public Histograms() {
    }

    @Override
    public String toString() {
        return "Histograms{" +
                "id=" + id +
                ", appname='" + appname + '\'' +
                ", tm=" + tm +
                ", metricskey='" + metricskey + '\'' +
                ", count=" + count +
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

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
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

    public void setMax(int max) {
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
