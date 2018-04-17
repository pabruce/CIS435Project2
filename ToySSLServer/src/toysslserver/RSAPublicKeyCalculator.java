package toysslserver;

import java.math.BigInteger;
import java.util.ArrayList;

public class RSAPublicKeyCalculator {
    int p, q, n, z, e;
    RSAPublicKeyCalculator(){
        this.p = 3;
        this.q = 5;
        this.n = 15;
        this.z = 8;
        this.e = 7;
    }
    RSAPublicKeyCalculator(int p, int q){
        this.p = p;
        this.q = q;
        this.n = (this.p * this.q);
        this.z = (this.p - 1) * (this.q - 1);
        this.e = calcE();
    }
    public int calcE(){
        int result = 0;
        for(int i = 2; i < this.n; i++){
            if(gcdofTwoInts(this.z, i) == 1){
                result = i;
            }
        }
        return result;
    }
    public static int gcdofTwoInts(int x, int y)
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
    public int encryptPublicKey(int n, int e, int masterKey){

        BigInteger nBig = BigInteger.valueOf(n);
        BigInteger eBig = BigInteger.valueOf(e);
        int result;
        String temp;
        BigInteger startBigInteger, resultBigInteger;
        /*byte[] messageBytes = message.getBytes();
        startBigInteger = new BigInteger(messageBytes);
        System.out.println("startBigInteger: " + startBigInteger);
        */
        resultBigInteger = BigInteger.valueOf(masterKey).pow(e);
        resultBigInteger = resultBigInteger.mod(nBig);
        temp = resultBigInteger.toString();
        result = Integer.valueOf(temp);
        System.out.println("result: " + result);
        return result;
    }
}
