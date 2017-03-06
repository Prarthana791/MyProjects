import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Page_JOB {

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(Object key, Text value, Context context)
	throws IOException, InterruptedException {
		if(!value.toString().trim().startsWith("#"))				
						
	   {
		String[] splittext= value.toString().split(" ");
		String finaltext=splittext[4];
		if(!finaltext.equals("/"))
		{
        word.set(finaltext);
        context.write(word, one);
		}
		}

    }
  }

  public static class IntSumReducer
       extends Reducer<Text,IntWritable,Text,IntWritable> {
    

	 static int sum_max = 0;
	  static Text key_output = new Text();
     
    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
		
		int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
	  if(sum > sum_max)
	  {
		  sum_max = sum;
		  key_output.set(key);
	  }
	   }
@Override
protected void cleanup(Context context) throws IOException, InterruptedException
{
	
      context.write(key_output, new IntWritable(sum_max));

}
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "Max Page count");
    job.setJarByClass(Page_JOB.class);
    job.setMapperClass(TokenizerMapper.class);
   job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
