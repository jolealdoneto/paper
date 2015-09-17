set style line 1 lw 3 lc 1
set style line 2 lw 3 lc 2

set boxwidth 0.5
set style fill solid

set xlabel "Execution time in seconds"
set ylabel "Utility values"

set terminal png size 1200,550 enhanced font "Helvetica,20"
set output "executions.png"

set key outside

plot 'fluctuation-utility.data' using 1:3 with lines ls 1 title "Utility local",\
     'fluctuation-utility.data' using 1:2 with lines ls 2 title "Utility remote"
