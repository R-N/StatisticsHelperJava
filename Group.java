
public class Group {
  
  public static class Kelas{
    public double bawah;
    public double atas;
    public double frek;
    
    public Kelas(double bawah, double atas, double frek){
      this.bawah = bawah;
      this.atas = atas;
      this.frek = frek;
    }
    
    public double panjang(){
      return atas-bawah+1;
    }
    
    public double xi(){
      return (atas+bawah)/2;
    }
    
    public double tb(){
      return bawah-0.5;
    }
    
    public double ta(){
      return atas+0.5;
    }
    
    public double fiXi(){
      return frek*xi();
    }
    
    public double deltaMeanXi(double mean){
      return Math.abs(mean-xi());
    }
    
    public double deltaMeanXiFi(double mean){
      return deltaMeanXi(mean)*frek;
    }
    
    public double sqDeltaMeanXi(double mean){
      double deltaMeanXi=deltaMeanXi(mean);
      return deltaMeanXi*deltaMeanXi;
    }
    
    public double sqDeltaMeanXiFi(double mean){
      return sqDeltaMeanXi(mean)*frek;
    }
  }
  
  public static class DataBerkelompok{
    public Kelas[] kelas2;
    public boolean sampel;
    
    public DataBerkelompok(Kelas[] kelas2, boolean sampel){
      this.kelas2 = kelas2;
      this.sampel = sampel;
    }
    
    public double n(){
      int l = kelas2.length;
      double sumFi=0;
      for(int i = 0; i<l; ++i){
        sumFi+=kelas2[i].frek;
      }
      return sumFi;
    }
    
    public double mean(){
      int l = kelas2.length;
      double sumFiXi = 0;
      for(int i = 0; i<l; ++i){
        sumFiXi+=kelas2[i].fiXi();
      }
      return sumFiXi/n();
    }
    
    public double q(int i) throws Exception{
      if (i < 1 || i> 3){
        throw new Exception("q i ccd");
      }
      return x(n()*i/4.0);
      
    }
    
    public double median() throws Exception{
      return q(2);
    }
    
    public double x(double i)throws Exception{
      if(i < 1 || i > n()){
        throw new Exception("x i ccd");
      }
      Kelas k = null;
      int l = kelas2.length;
      double tfk = 0;
      double fk = 0;
      for(int j = 0; j <l; ++j){
        Kelas kj = kelas2[j];
        tfk += kj.frek;
        if(tfk > i){
          break;
        }
        fk += kj.frek;
        k = kj;
      }
      if (tfk < i || k == null || fk > i){
        throw new Exception("wtf i");
      }
      return k.tb() + ((i-fk)/k.frek)*k.panjang();
    }
    
    public double dModus(int j){
      Kelas kj = kelas2[j];
      double d1 = kj.frek;
      double d2 = kj.frek;
      if (j > 0) d1 -= kelas2[j-1].frek;
      if (j < kelas2.length-1) d2 -= kelas2[j+1].frek;
      
      return kj.tb() + (kj.panjang()*d1/(d1+d2));
    }
    
    public double kModus(){
      double f=kelas2[0].frek;
      
      int l = kelas2.length;
      int c = 0;
      for(int j=1; j < l; ++j){
        double fj = kelas2[j].frek;
        if (fj > f){
          f = fj;
        }
      }
      int[] tks = new int[kelas2.length];
      for(int j=0; j < l; ++j){
        Kelas kj = kelas2[j];
        if(kj.frek == f){
          tks[c++]=j;
        }
      }
      int[] ret = new int[c];
      for(int j = 0; j < l; ++j){
        ret[j]=tks[j];
      }
      return ret;
    }
    
    public double[] modus(){
      int[] tks = kModus();
      int c = tks.length;
      double[] ret = new double[c];
      for(int j = 0; j < c; ++j){
        ret[j] = dModus(tks[j]);
      }
      return ret;
    }
    
    public double range(){
      return kelas2[kelas2.length-1].xi() - kelas2[0].xi();
    }
    
    public double sr(){
      double mean = mean();
      double sum = 0;
      int l = kelas2.length;
      for (int j = 0; j < l; ++j){
        sum+=kelas2[j].deltaMeanXiFi(mean);
      }
      return sum/n();
    }
    
    public double v(){
      double mean = mean();
      double sum = 0;
      int l = kelas2.length;
      for(int j = 0; j < l; ++j){
        sum += kelas2[j].sqDeltaMeanXiFi(mean);
      }
      if (sampel){
        return sum/(n()-1);
      }else{
        return sum/n();
      }
    }
    
    public double s(){
      return Math.sqrt(v());
    }
  }

  public static void main(String[] args) {
   
  }
}
