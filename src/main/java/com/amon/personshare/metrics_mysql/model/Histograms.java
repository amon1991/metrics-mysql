package com.amon.personshare.metrics_mysql.model;

/**
 * Created by Administrator on 2015/11/9.
 */
public class Histograms {

    private int id;
    private String appname;
    private long tm;
    private String metricskey;
    private int count;
    private int min;
    private int max;
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

    public Histograms(String appname, long tm, String metricskey, int count, int min, int max, double mean, double stddev, double median, double senvenfive, double ninefive, double nineeight, double ninenine, double nineninenine) {
        this.appname = appname;
        this.tm = tm;
        this.metricskey = metricskey;
        this.count = count;
        this.min = min;
        this.max = max;
        this.mean = mean;
        this.stddev = stddev;
        this.median = median;
        this.senvenfive = senvenfive;
        this.ninefive = ninefive;
        this.nineeight = nineeight;
        this.ninenine = ninenine;
        this.nineninenine = nineninenine;
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

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
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
