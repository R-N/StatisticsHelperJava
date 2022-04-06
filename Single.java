
import java.util.Arrays;
public class Single {
  public class DataTunggal{
    public double[] data;
    public boolean sampel;
    public DataTunggal(double[] data, boolean sampel){
      this.sampel = sampel;
      this.data = data;
    }
    
    public void sort(){
      Double[] data2 = new Double[data.length];
      int n = data.length;
      
      for (int i = 0; i < n; ++i){
        data2[i] = data[i];
      }
      
      Arrays.sort(data2);
      
      this.data = new double[data.length];
      
      for(int i = 0; i < n; ++i){
        this.data[i] = data2[i];
      }
    }
    
    public double mean(){
      int n = data.length;
      double sum = 0;
      for(int i = 0; i < n; ++i){
        sum += data[i];
      }
      return sum/n;
    }
    
    public double median(){
      int n = data.length;
      if(n%2==0){
        return (data[n/2] + data[(n/2)+1])/2;
      }else{
        return data[(n+1)/2];
      }
    }
    
    public double modus(){
      return 0; //todo
    }
    
    public double max(){
      return data[data.length-1];
    }
    public double min(){
      return data[0];
    }
    public double range(){
      return max()-min();
    }
    
    public double deltaXMean(int i, double mean){
      return Math.abs(data[i]-mean);
    }
    
    public double sqDeltaXMean(int i, double mean){
      double deltaXMean = deltaXMean(i, mean);
      return deltaXMean*deltaXMean;
    }
    
    public double sr(){
      double mean = mean();
      int n = data.length;
      double sum = 0;
      for(int i = 0; i < n; ++i){
        sum += deltaXMean(i, mean);
      }
      return sum/n;
    }
    public double v(){
      double mean = mean();
      int n = data.length;
      double sum = 0;
      for(int i = 0; i < n; ++i){
        sum+= sqDeltaXMean(i, mean);
      }
      return sum/n;
    }
    public double s(){
      return Math.sqrt(v());
    }
  }
  public static void main(String[] args) {
   
  }
}
