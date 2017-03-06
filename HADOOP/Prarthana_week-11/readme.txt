1) Please remove the first line from original cite75_99.txt file i.e. the heading of columns ("citing" and "cited") before passing it as
an input for job 1 i.e. CiteCount.java as we need to pass its output as an input to job3 i.e. TopNrec1 and in that I have kept output
datatype as IntWritable and also it is irrelevant for required output

2) Name of job2 program (count of counts) is Histogram.java

3) For job 3 program (TopNRec1.java) below mentioned is the sample command line argument to find top 10 and 1000 records:
For top 10: hadoop jar TopN.jar TopNRec1 /user/$USER/Input/job1_output/part-r-00000 /user/$USER/Output/top10 10
For top 1000: hadoop jar TopN.jar TopNRec1 /user/$USER/Input/job1_output/part-r-00000 /user/$USER/Output/top1000 1000
So, similarly you can pass any number as a 3rd argument (i.e. last argument) of command line depending upon how many top records you need
to emit
