DNA Benchmark
for (int k = 1; k < all.length; k++) {
	recombined.append(splicee);
	recombined.append(all[k]);
}

The for loop above is the one that has the highest contribution in the function SimpleStrand.cutAndSplice . Analysis of this for loop shows that it runs k number of times. This hence makes its time complexity to b O(n) 

B) The highest string that can be spliced with -Xm512m is 217 .We can determine this value by checking the value of j in the loop below.  

for(int j=8; j <= 32; j++) {
 StringBuilder b = new StringBuilder("");
 int spSize = (1 << j);

in the above loop the spSize which is used to create the spliceee, I.e it will hold the length of the splicee is obtained by shifting 1 to the left by the current value of j. it haPPENS that we run out of heap when j=17, therefore using ecoli.dat, the longest string we can process is 217


for -Xm1024M we can process 218 and for -Xm2048M  we can process a string of length 219 


