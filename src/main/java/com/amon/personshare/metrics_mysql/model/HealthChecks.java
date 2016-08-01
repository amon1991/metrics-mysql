package com.amon.personshare.metrics_mysql.model;

/**
 * Created by Administrator on 2015/11/9.
 */
public class HealthChecks {

    private int id;
    private String appname;
    private long tm;
    private String metricskey;
    private boolean ishealth;
    private String message;

    public HealthChecks() {
    }

    @Override
    public String toString() {
        return "HealthChecks{" +
                "id=" + id +
                ", appname='" + appname + '\'' +
                ", tm=" + tm +
                ", metricskey='" + metricskey + '\'' +
                ", ishealth=" + ishealth +
                ", message='" + message + '\'' +
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

    public boolean ishealth() {
        return ishealth;
    }

    public void setIshealth(boolean ishealth) {
        this.ishealth = ishealth;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
