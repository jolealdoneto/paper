set terminal png size 1200,550 enhanced font "Helvetica,20" dl 0.4
set output "overhead.png"

set style line 1 lw 3 lc 0
set style line 2 lw 3 lc 1

set boxwidth 0.5
set style fill solid

set xlabel "URL Identifier"
set ylabel "Time (ms)"


set key left top

plot 'overhead.data' using 1:2 with lines ls 2 title "Overhead",\
    'overhead.data' using 1:3 with lines ls 1 title "Total time"
