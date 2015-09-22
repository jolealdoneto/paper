@GrabConfig(systemClassLoader=true)
@Grab(group='com.h2database', module='h2', version='1.3.167')

import groovy.sql.Sql

def sql = Sql.newInstance("jdbc:h2:results-fd", "sa", "sa", "org.h2.Driver")
//sql.execute("drop table overhead")
sql.execute("create table overhead (id INT PRIMARY KEY, url VARCHAR(255), overhead int, totaltime int)")

String fileContents = new File('offloading.overhead.pp.log').getText('UTF-8')

def countId = 1
fileContents.split("-------------").each { entry ->
    final def url = entry.find("url: ([^\n]*)") { fm, u -> u }
    final def total = entry.find("Total time \\(ms\\): ([0-9]+)") { fm, assess -> assess }
    final def overhead = entry.find("OVERHEAD \\(ms\\): ([0-9]+)") { fm, assess -> assess }

    sql.execute("insert into overhead values(:id, :url, :overhead, :totaltime)", [id: countId++, url: url, overhead: overhead, totaltime: total])
}
