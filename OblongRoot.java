public class OblongRoot {
    private final int[] temp;

    public static int f(int n, RecCheck rc) {
        rc.checkRec();
        if (n >= 1 && n < 3) {
            return 1;
        } else return f(n - 2 * f(n - f(n - 1, rc), rc), rc) + 1;
    }

    public static int fTailRec(int n, RecCheck rc) {
        rc.checkRec();
       /* if (n >= 1 && n < 3) {
            return 1;
        }*/
        return fTailRecHelper(n, 1, 1,3, rc);
    }

    private static int fTailRecHelper(int n, int a, int counter,int max, RecCheck rc) {
        rc.checkRec();

//        if (n >= 1 && n < 3) {
//            return a;
//        }
        if(a==n){
            return counter;
        }
        if(a+1>=max){
            a++;
            counter++;
            return fTailRecHelper(n,a,counter,max+counter*2,rc) ;
        }else {
            a++;
            return fTailRecHelper(n,a,counter,max,rc);
        }






//        if (counter > 0) {
//
//            return fTailRecHelper(n - 1, a , counter-1, rc);
//
//        }
//        return fTailRecHelper(n - 1, a + 1, a* 2, rc);
    }

    public OblongRoot(int max) {
        temp = new int[max+1];
    }

    public int fMem(int n, RecCheck rc) {
        rc.checkRec();
        int result=1;

                if (n<temp.length && temp[n] != 0) {
                    result=temp[n];
                    //return temp[n];
                }
                 else if (n>=3) {
                    result = fMem(n - 2 * fMem(n - fMem(n - 1, rc), rc), rc) + 1;
                }

                 if(n<temp.length){
                     temp[n] = result;
                 }


           return result;


    }

    public static int fIterative(int n, RecCheck rc) {
        rc.checkRec();
        int temp = (int) Math.sqrt(n);
        if (Math.sqrt(n) - temp > 0.5) {
            return temp + 1;
        }
        return temp;
        /*int[] result=new int[n];
        int a=1;
        int counter=2;
        for(int i=0;i<result.length;i++){
            if(counter>0){
                result[i]=a;
                counter--;
                continue;
            }
            result[i]=a+1;
            counter=(a+1)*2;

        }



        return result[n-1];*/


    }
}
