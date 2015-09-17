set style line 1 lw 3 lc 1
set style line 2 lw 3 lc 2

set boxwidth 0.5
set style fill solid

set xlabel "Execution time in seconds"
set ylabel "Bandwidth (bps)"

set terminal png size 1200,550 enhanced font "Helvetica,20"
set output "executions.png"

set key outside

set arrow from 35,35 to 35,0 nohead lc rgb 'black'
set arrow from 89,35 to 89,0 nohead lc rgb 'black'
set arrow from 123,35 to 123,0 nohead lc rgb 'black'
set arrow from 286,35 to 286,0 nohead lc rgb 'black'
set arrow from 302,35 to 302,0 nohead lc rgb 'black'
set arrow from 402,35 to 402,0 nohead lc rgb 'black'
set arrow from 409,35 to 409,0 nohead lc rgb 'black'
set arrow from 603,35 to 603,0 nohead lc rgb 'black'
set arrow from 653,35 to 653,0 nohead lc rgb 'black'
set arrow from 1029,35 to 1029,0 nohead lc rgb 'black'
set arrow from 1084,35 to 1084,0 nohead lc rgb 'black'

set obj 1 rectangle behind from first 0, graph 0 to first 35, graph 1 back
set obj 1 fillstyle solid 1.0 fillcolor rgb "#f5f5ff"
set obj 2 rectangle behind from first 35, graph 0 to first 89, graph 1 back
set obj 2 fillstyle solid 1.0 fillcolor rgb "#ffd8d8"

set obj 3 rectangle behind from first 89, graph 0 to first 123, graph 1 back
set obj 3 fillstyle solid 1.0 fillcolor rgb "#f5f5ff"
set obj 4 rectangle behind from first 123, graph 0 to first 286, graph 1 back
set obj 4 fillstyle solid 1.0 fillcolor rgb "#ffd8d8"

set obj 5 rectangle behind from first 286, graph 0 to first 302, graph 1 back
set obj 5 fillstyle solid 1.0 fillcolor rgb "#f5f5ff"
set obj 6 rectangle behind from first 302, graph 0 to first 402, graph 1 back
set obj 6 fillstyle solid 1.0 fillcolor rgb "#ffd8d8"

set obj 7 rectangle behind from first 402, graph 0 to first 409, graph 1 back
set obj 7 fillstyle solid 1.0 fillcolor rgb "#f5f5ff"
set obj 8 rectangle behind from first 409, graph 0 to first 603, graph 1 back
set obj 8 fillstyle solid 1.0 fillcolor rgb "#ffd8d8"

set obj 9 rectangle behind from first 603, graph 0 to first 653, graph 1 back
set obj 9 fillstyle solid 1.0 fillcolor rgb "#f5f5ff"
set obj 10 rectangle behind from first 653, graph 0 to first 1029, graph 1 back
set obj 10 fillstyle solid 1.0 fillcolor rgb "#ffd8d8"

set obj 11 rectangle behind from first 1029, graph 0 to first 1084, graph 1 back
set obj 11 fillstyle solid 1.0 fillcolor rgb "#f5f5ff"
set obj 12 rectangle behind from first 1084, graph 0 to first 1200, graph 1 back
set obj 12 fillstyle solid 1.0 fillcolor rgb "#ffd8d8"

plot 'bw-data.data' using 1:2 with lines ls 1 title "Download",\
     'bw-data.data' using 1:3 with lines ls 2 title "Upload"
