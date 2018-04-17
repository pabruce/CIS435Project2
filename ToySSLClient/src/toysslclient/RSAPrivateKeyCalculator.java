package toysslclient;

import java.math.BigInteger;

public class RSAPrivateKeyCalculator {
    int p, q, n, z, e;
    RSAPrivateKeyCalculator(){
        this.p = 3;
        this.q = 5;
        this.n = 15;
        this.z = 8;
        this.e = 7;
    }
    RSAPrivateKeyCalculator(int p, int q){
        this.p = p;
        this.q = q;
        this.n = (this.p * this.q);
        System.out.println("n: " + this.n);
        this.z = (this.p - 1) * (this.q - 1);
        System.out.println("z: " + this.z);
        this.e = calculateE();
        System.out.println("e: " + this.e);
    }
    public int calculateE(){
        int result = 0;
        for(int i = 2; i < this.n; i++){
            if(gcdOfTwoInts(this.z, i) == 1){
                result = i;
            }
        }
        return result;
    }
    public static int gcdOfTwoInts(int x, int y)
    {
        while(x != 0 && y != 0)
        {
            int w = y;
            y = x % y;
            x = w;
        }
        return x + y;
    }
    public int getN(){
        return this.n;
    }
    public int getE(){
        return this.e;
    }
    public int encryptPrivateKey(int n, int e, String message){

        String result;
        BigInteger startBigInteger, resultBigInteger;
        byte[] messageBytes = message.getBytes();
        startBigInteger = new BigInteger(messageBytes);
        System.out.println("startBigInteger: " + startBigInteger);
        resultBigInteger = startBigInteger.pow(getE());
        System.out.println("resultBigInteger: " + resultBigInteger);
        result = resultBigInteger.toString();
        System.out.println("result: " + result);
        return 0;
    }
}
