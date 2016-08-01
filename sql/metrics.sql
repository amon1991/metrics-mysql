/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/11/12 8:23:26                           */
/*==============================================================*/


/*drop index index_counter on counter;

drop table if exists counter;

drop index index_gauges on gauges;

drop table if exists gauges;

drop index index_healthcheck on healthchecks;

drop table if exists healthchecks;

drop index index_histograms on histograms;

drop table if exists histograms;

drop index index_meters on meters;

drop table if exists meters;

drop index index_timers on timers;

drop table if exists timers;*/

/*==============================================================*/
/* Table: counter                                               */
/*==============================================================*/
create table counter
(
   id                   int(11) auto_increment primary key not null,
   appname              varchar(100),
   tm                   datetime,
   metricskey           varchar(200),
   value                bigint(20)
);

alter table counter comment '计数器表';

/*==============================================================*/
/* Index: index_counter                                         */
/*==============================================================*/
create index index_counter on counter
(
   appname,
   tm,
   metricskey
);

/*==============================================================*/
/* Table: gauges                                                */
/*==============================================================*/
create table gauges
(
   id                   int(11) auto_increment primary key not null,
   appname              varchar(100),
   tm                   datetime,
   metricskey           varchar(200),
   value                bigint(20)
);

alter table gauges comment '简单计量表';

/*==============================================================*/
/* Index: index_gauges                                          */
/*==============================================================*/
create index index_gauges on gauges
(
   appname,
   tm,
   metricskey
);

/*==============================================================*/
/* Table: healthchecks                                          */
/*==============================================================*/
create table healthchecks
(
   id                   int(11) auto_increment primary key not null,
   appname              varchar(100),
   tm                   datetime,
   metricskey           varchar(200),
   ishealth             boolean,
   message              varchar(200)
);

alter table healthchecks comment '健康检测';

/*==============================================================*/
/* Index: index_healthcheck                                     */
/*==============================================================*/
create index index_healthcheck on healthchecks
(
   appname,
   tm,
   metricskey
);

/*==============================================================*/
/* Table: histograms                                            */
/*==============================================================*/
create table histograms
(
   id                   int(11) auto_increment primary key not null,
   appname              varchar(100),
   tm                   datetime,
   metricskey           varchar(200),
   count                bigint(20),
   min                  bigint(20),
   max                  bigint(20),
   mean                 double,
   stddev               double,
   median               double,
   sevenfive            double,
   ninefive             double,
   nineeight            double,
   ninenine             double,
   nineninenine         double
);

alter table histograms comment '用于统计数据的分布情况，最大值、最小值、平均值、中位数、百分比';

/*==============================================================*/
/* Index: index_histograms                                      */
/*==============================================================*/
create index index_histograms on histograms
(
   appname,
   tm,
   metricskey
);

/*==============================================================*/
/* Table: meters                                                */
/*==============================================================*/
create table meters
(
   id                   int(11) auto_increment primary key not null,
   appname              varchar(100),
   tm                   datetime,
   metricskey           varchar(200),
   count                bigint(20),
   meanrate             double,
   onemrate             double,
   fivemrate            double,
   fifmrate             double
);

alter table meters comment '度量某个时间段的平均处理次数';

/*==============================================================*/
/* Index: index_meters                                          */
/*==============================================================*/
create index index_meters on meters
(
   appname,
   tm,
   metricskey
);

/*==============================================================*/
/* Table: timers                                                */
/*==============================================================*/
create table timers
(
   id                   int(11) auto_increment primary key not null,
   appname              varchar(100),
   tm                   datetime,
   metricskey           varchar(200),
   count                bigint(20),
   meanrate             double,
   onemrate             double,
   fivemrate            double,
   fifmrate             double,
   min                  double,
   max                  double,
   mean                 double,
   stddev               double,
   median               double,
   sevenfive            double,
   ninefive             double,
   nineeight            double,
   ninenine             double,
   nineninenine         double
);

alter table timers comment '统计某一块代码段的执行时间以及分布情况';

/*==============================================================*/
/* Index: index_timers                                          */
/*==============================================================*/
create index index_timers on timers
(
   appname,
   tm,
   metricskey
);

