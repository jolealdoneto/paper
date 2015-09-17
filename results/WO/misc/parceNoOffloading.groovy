@GrabConfig(systemClassLoader=true)
@Grab(group='com.h2database', module='h2', version='1.3.167')

import groovy.sql.Sql

def sql = Sql.newInstance("jdbc:h2:results-fd", "sa", "sa", "org.h2.Driver")
sql.execute("drop table no_offloading")
sql.execute("create table no_offloading (id INT PRIMARY KEY, battery INT, url VARCHAR(255), time VARCHAR(15))")

String fileContents = new File('offloading.NOOFF.log').getText('UTF-8')

def countId = 1
fileContents.split(" ---- ").each { entry ->
    final def number = entry.find("BATTERY LEVEL: ([0-9]+)") { fm, num -> num }
    final def url = entry.find("------------- url: ([^\n]*)") { fm, u -> u }
    final def time = entry.find("([0-9]{2}:[0-9]{2}:[0-9]{2}),[0-9]{3}") { fm, time -> time }

    sql.execute("insert into no_offloading values(:id, :battery, :url, :time)", [id: countId++, battery: number, url: url, time: time])

}
