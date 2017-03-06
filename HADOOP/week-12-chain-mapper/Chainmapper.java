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
import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.lib.chain.ChainReducer;

public class Chainmapper {

  public static class RemovePunctuation
       extends Mapper<Object, Text, IntWritable, Text>{

   private final static IntWritable one = new IntWritable(1);
    private Text words = new Text();
//private Text error = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {

					String words1=value.toString().replaceAll("[^a-zA-Z ]", "");
					words.set(words1);
					context.write(one,words);
					}
	   }
	   
	public static class TokenizerMapper
       extends Mapper<IntWritable, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(IntWritable key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
      while (itr.hasMoreTokens()) {
        word.set(itr.nextToken());
        context.write(word, one);
      }
    }
  }

  public static class IntSumReducer
       extends Reducer<Text,IntWritable,Text,IntWritable> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
      result.set(sum);
      context.write(key, result);
    }
  }

	   
	   
public static void main(String[] args) throws Exception {
    //Configuration conf = new Configuration();
 Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "Remove punctuation");
Configuration removepunct= new Configuration(false);
ChainMapper.addMapper(job, RemovePunctuation.class,Object.class, Text.class, IntWritable.class, Text.class, removepunct);

 Configuration tokenize= new Configuration(false);
ChainMapper.addMapper(job, TokenizerMapper.class,IntWritable.class, Text.class, Text.class, IntWritable.class, tokenize);

Configuration countreducer=new Configuration(false);
ChainReducer.setReducer(job,IntSumReducer.class,Text.class, IntWritable.class, Text.class, IntWritable.class, countreducer);
job.setJarByClass(Chainmapper.class);
//job.setReducerClass(IntSumReducer.class);
job.setOutputKeyClass(Text.class);

job.setOutputValueClass(IntWritable.class);
  
   FileInputFormat.addInputPath(job, new Path(args[0]));
  FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
