set output "bars.png"
set terminal png size 1200,550 enhanced font "Helvetica,20"

set style data histogram
set style histogram cluster gap 1
set xtics rotate by 45 offset -5.5,-3.8
set bmargin 4.5
set ylabel "Bandwidth (Mbps)"

set style fill solid border rgb "black"
plot 'laccid-bw.data' using 2:xtic(1) title "Download", \
        '' using 3:xtic(1) title "Upload"
