package academy.mindswap.utils;

public final class RandomNumbers {

    public static int generateRandom(int min,int max){
        return   (int) (Math.random() * (max - min + 1) + min);
    }

}
