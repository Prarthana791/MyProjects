import java.io.IOException;
import java.io.DataInput;
import java.io.DataOutput;
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
import org.apache.hadoop.io.WritableComparable;

public class Pagepermon_JOB {


private static class CombinedKey implements WritableComparable<CombinedKey>
{

        Text date;
        Text page;

        public CombinedKey(Text date, Text page) {
            this.date = date;
            this.page = page;
        }
		public Text getDate()
		{
			return this.date;
		}
		public Text getPage()
		{
			return this.page;
		}

		public void setDate(Text d)
		{
			this.date = d;
		}
		public void setPage(Text p)
		{
			this.page = p;
		}
        public CombinedKey() {
            this.date = new Text();
            this.page = new Text();

        }
		
		public void write(DataOutput out) throws IOException {
            //WritableUtils.writeString(out,date);
			this.date.write(out);
            this.page.write(out);
		}
		public void readFields(DataInput in) throws IOException {

            this.date.readFields(in);
            this.page.readFields(in);
		}
		
		public int compareTo(CombinedKey c) {
            if (c == null)
                return 0;
            int count = date.compareTo(c.date);
            if (count != 0) {
                return count;
            } else {
                return page.compareTo(c.page);

            }
        }
		
		@Override
        public String toString() {

            return date.toString() + ":" + page.toString();
        }

}
		
  public static class PagepermonMapper
       extends Mapper<Object, Text, CombinedKey, IntWritable>{

CombinedKey ckey = new CombinedKey();
Text date_input = new Text();
Text page_input = new Text();
IntWritable one = new IntWritable(1);
	   
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
	  if(!value.toString().trim().startsWith("#"))
	  {
		String[] splittext= value.toString().split(" ");
		String dt = splittext[0];
		String yearmon = dt.substring(0,7);
		date_input.set(new Text(yearmon));
		String page=splittext[4];
		
		if(!page.equals("/"))
		{
        page_input.set(page);
		CombinedKey ckey = new CombinedKey(date_input,page_input);
		context.write(ckey, one);
		}
	 }

    }
  }

  public static class IntSumReducer
       extends Reducer<CombinedKey,IntWritable,CombinedKey,IntWritable> {
    private IntWritable result = new IntWritable();

	 static int sum_max = 0;
	  static CombinedKey key_output = new CombinedKey();
     
    public void reduce(CombinedKey key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		
		int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
	   if(sum > sum_max)
	  {
		  sum_max = sum;
		  key_output.setDate(key.getDate());
		  key_output.setPage(key.getPage());
	  }
	 
       }
@Override
protected void cleanup(Context context) throws IOException, InterruptedException
{
	//result.set(sum_max);
      context.write(key_output, new IntWritable(sum_max));

}
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "Page count per month/year");
    job.setJarByClass(Pagepermon_JOB.class);
    job.setMapperClass(PagepermonMapper.class);
   job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
	
	job.setMapOutputKeyClass(CombinedKey.class);
job.setMapOutputValueClass(IntWritable.class);
    job.setOutputKeyClass(CombinedKey.class);
    job.setOutputValueClass(IntWritable.class);
    
	FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
