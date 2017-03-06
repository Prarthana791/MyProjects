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

public class Punctuation {

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, Text>{

    private Text words = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {

					String words1=value.toString().replaceAll("[^a-zA-Z ]", "");
					words.set(words1);
					context.write(words,null);
					}
	   }
	   
public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "Remove punctuation");
    job.setJarByClass(Punctuation.class);
	job.setNumReduceTasks(0);
    job.setMapperClass(TokenizerMapper.class);
	job.setMapOutputKeyClass(Text.class);
job.setMapOutputValueClass(Text.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
