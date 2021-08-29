import java.util.Scanner;
public class Arrays {
    public static void main(String [] args){
        Scanner userInput = new Scanner(System.in);
        int[] x = new int[10];
        int[] reverseX = new int[10];
        int maxNumber = 0;
        int minNumber = 0;
        float avg = 0f;
        for(int i = 0; i <= 9 ; i++){
            x[i] = userInput.nextInt();
        }
        for(int i = 0; i < x.length; i++){
            avg += x[i];
            if(i == 0){
                maxNumber = x[i];
                minNumber = x[i];
            } 
            if(maxNumber < x[i]){
                maxNumber = x[i];
            }
            if(minNumber > x[i]){
                minNumber = x[i];
            }
        }
        for(int i = 0; i < x.length; i++){
            reverseX[i] = x[(x.length - 1) - i];
        }
        
        System.out.println("max number is " + maxNumber + " and min number is " + minNumber);
        System.out.println("the avrage = " + avg / x.length);
        System.out.println("reversed array =  " );
        for(int i =0; i < reverseX.length; i++){
            System.out.print(" " + reverseX[i]);
        }

    }
}
