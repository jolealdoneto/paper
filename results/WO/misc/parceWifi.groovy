@GrabConfig(systemClassLoader=true)
@Grab(group='com.h2database', module='h2', version='1.3.167')

import groovy.sql.Sql

def sql = Sql.newInstance("jdbc:h2:results-fd", "sa", "sa", "org.h2.Driver")
sql.execute("drop table wifi")
sql.execute("create table wifi (id INT PRIMARY KEY, battery INT, url VARCHAR(255), assessment DOUBLE, bwDownload DOUBLE, bwUpload DOUBLE, time varchar(15))")

String fileContents = new File('offloading.WIFI.log').getText('UTF-8')

def countId = 1
fileContents.split("--\n").each { entry ->
    final def number = entry.find("BATTERY LEVEL: ([0-9]+)") { fm, num -> num }
    final def url = entry.find("------------- url: ([^\n]*)") { fm, u -> u }
    final def assess = entry.find("interpolateAssessment asses: ([0-9]+)") { fm, assess -> assess }
    final def newBWs = entry.findAll("LocationBasedBandwidth \\[WIFI\\] ([0-9.,]+)") { fm, bws -> bws }
    final def time = entry.find("([0-9]{2}:[0-9]{2}:[0-9]{2}),[0-9]{3}") { fm, time -> time }

    sql.execute("insert into wifi values(:id, :battery, :url, :assessment, :bwDownload, :bwUpload, :time)", [id: countId++, battery: number, url: url, assessment: assess, bwDownload: newBWs[1], bwUpload: newBWs[0], time: time])
}
