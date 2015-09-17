@GrabConfig(systemClassLoader=true)
@Grab(group='com.h2database', module='h2', version='1.3.167')

import groovy.sql.Sql

def sql = Sql.newInstance("jdbc:h2:results-fd", "sa", "sa", "org.h2.Driver")
sql.execute("drop table mobile")
sql.execute("create table mobile (id INT PRIMARY KEY, battery INT, url VARCHAR(255), assessment DOUBLE, bwDownload DOUBLE, bwUpload DOUBLE, timeLocal DOUBLE, timeRemote DOUBLE, energyLocal DOUBLE, energyRemote DOUBLE, utilityRemote DOUBLE, utilityLocal DOUBLE, res VARCHAR(5), laccid VARCHAR(255), time VARCHAR(15))")

String fileContents = new File('offloading.MOBILE.log').getText('UTF-8')

def countId = 1
fileContents.split("--\n").each { entry ->
    final def number = entry.find("BATTERY LEVEL: ([0-9]+)") { fm, num -> num }
    final def url = entry.find("------------- url: ([^\n]*)") { fm, u -> u }
    final def assess = entry.find("interpolateAssessment asses: ([0-9]+)") { fm, assess -> assess }
    final def bwDownload = entry.find("shouldOffload: bandwidthDownload: ([0-9.,]+)") { fm, bw -> bw }
    final def bwUpload = entry.find("shouldOffload: bandwidthUpload: ([0-9.,]+)") { fm, bw -> bw }
    final def timeLocal = entry.find("shouldOffload: timeLocal: ([0-9.,]+)") { fm, tl -> tl }
    final def timeRemote = entry.find("timeRemote: ([0-9.,]+)") { fm, tr -> tr }
    final def energyLocal = entry.find("shouldOffload: energyLocal: ([0-9.,]+)") { fm, el -> el }
    final def energyRemote = entry.find("energyRemote: ([0-9.,]+)") { fm, er -> er }
    final def utilityRemote = entry.find("shouldOffload: utilityRemote: ([0-9.,]+)") { fm, ur -> ur }
    final def utilityLocal = entry.find("utilityLocal: ([0-9.,]+)") { fm, ul -> ul }
    final def res = entry.find("shouldOffload: Res: (true|false)") { fm, res -> res }
    final def laccid = entry.find("lac/cid: ([0-9]+/[0-9]+)") { fm, laccid -> laccid }
    final def newBWs = entry.findAll("LocationBasedBandwidth \\[MOBILE\\] ([0-9.,]+)") { fm, bws -> bws }
    final def time = entry.find("([0-9]{2}:[0-9]{2}:[0-9]{2}),[0-9]{3}") { fm, time -> time }

    sql.execute("insert into mobile values(:id, :battery, :url, :assessment, :bwDownload, :bwUpload, :timeLocal, :timeRemote, :energyLocal, :energyRemote, :utilityRemote, :utilityLocal, :res, :laccid, :time)", [id: countId++, battery: number, url: url, assessment: assess, bwDownload: bwDownload, bwUpload: bwUpload, timeLocal: timeLocal, timeRemote: timeRemote, energyLocal: energyLocal, energyRemote: energyRemote, utilityRemote: utilityRemote, utilityLocal: utilityLocal, res: res, laccid: laccid, time: time])
}
