package fork_join;

import org.junit.Test;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


/*


Result solve(Problem problem) {

       if (problem is small)
         directly solve problem
       else {
        split problem into independent parts
        fork new subtasks to solve each part
        join all subtasks
        compose result from subresults
    }
}



Critique: http://www.coopsoft.com/ar/CalamityArticle.html

 */


public class ForkAndJoinTest {

    /* 1.  prepare ForkJoinPool */

    private final ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
    static final int SEQUENTIAL_THRESHOLD = 5000;


    @Test
    public void test(){
        int[] x = {0,1,2,3,4,5,6,7,8,9,10};
//        for(int i = 0 ; i < x.length ; i++){
        BigInteger left = BigInteger.valueOf(1);
        BigInteger right = BigInteger.valueOf(10);

            final FactorialTask task = new FactorialTask(left,right);
            System.out.print("factorial(x="+3+")" + "=");

            final BigInteger result = pool.invoke(task);
            System.out.println(result);
//        }

    }

    /* 2. prepare some test data */


}


class FactorialTask extends RecursiveTask<BigInteger>{

    private BigInteger left;
    private BigInteger right;

    public FactorialTask(BigInteger left, BigInteger right){
        this.left = left;
        this.right = right;

    }

    public BigInteger compute(){
        if(right.intValue() - left.intValue() < 4){
            return computeDirectly();
        }else{
            BigInteger middle = (left.add(right)).shiftRight(1);
            FactorialTask firstWorker = new FactorialTask(left,middle);
            firstWorker.fork();
            FactorialTask secondWorker = new FactorialTask(middle,right);
            return secondWorker.compute().multiply(firstWorker.join());
        }
    }

    private BigInteger computeDirectly() {
        BigInteger result=BigInteger.valueOf(1);

        for(int i = 1; i <= right.intValue() ; i++){
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

}

