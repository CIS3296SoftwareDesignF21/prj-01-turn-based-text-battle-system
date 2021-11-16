package Attacks;

public class Rates {
    /* Calculate a percentage chance into a boolean
     * rate: a number from 0 to 100 */
    public static boolean percentRateApplied(int rate){
        if(rate > 100) rate = 100;
        else if(rate < 0) rate = 0;
        if(rand(0,100) <= rate) return true;
        return false;
    }
    /* Random function for easy use */
    public static int rand(int min, int max){
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }
    /* Randomly choose a number from -percent to +percent */
    public static double variance(int percent){
        return (double)rand(percent*-1,percent) / 100;
    }
}
