import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class TopNRec1 {

  public static class TokenizerMapper
       extends Mapper<Object, Text, IntWritable, IntWritable>{


    private IntWritable output_key= new IntWritable();
private IntWritable output_value=new IntWritable();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
                String[] splittext= value.toString().split("\t",2);
String finaltext=splittext[0];                
String finalcount=splittext[1];

output_key.set(Integer.parseInt(finalcount));
output_value.set(Integer.parseInt(finaltext));
        context.write(output_key, output_value);
      }
    }
  

  public static class IntSumReducer
       extends Reducer<IntWritable,IntWritable,IntWritable,IntWritable> {

public static int count=0;
private final static IntWritable num=new IntWritable();
public int num1;
public void setup(Context context1)
{
Configuration config = context1.getConfiguration();
String topn=config.get("Top.Records");
num1=Integer.parseInt(topn);
num.set(num1);
}
    public void reduce(IntWritable key,Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
    

    for (IntWritable val : values) {

count++;
if(count <= num.get())
{

  context.write(key, val);
}    }
}
}
public static class SortIntComparator extends WritableComparator
{
protected SortIntComparator() {
		super(IntWritable.class, true);
	}
	
	@SuppressWarnings("rawtypes")

	@Override
	public int compare(WritableComparable w1, WritableComparable w2) {
		IntWritable k1 = (IntWritable)w1;
		IntWritable k2 = (IntWritable)w2;
		
		return -1 * k1.compareTo(k2);
	}
}

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
GenericOptionsParser optpar = new GenericOptionsParser(conf,args);
args = optpar.getRemainingArgs();
if (args.length != 3) {
      System.err.println("Usage: Histogram <in> <out> [input_number]");
      System.exit(2);
    }
    Job job = Job.getInstance(conf, "topNrec");
    job.setJarByClass(TopNRec1.class);
    job.setMapperClass(TokenizerMapper.class);


    job.setReducerClass(IntSumReducer.class);
job.setSortComparatorClass(SortIntComparator.class);  
  job.setOutputKeyClass(IntWritable.class);
    job.setOutputValueClass(IntWritable.class);
if(args.length > 2)
{
job.getConfiguration().set("Top.Records",args[2]);
}
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}

