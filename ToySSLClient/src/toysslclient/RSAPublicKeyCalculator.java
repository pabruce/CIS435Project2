package toysslclient; 

import java.math.BigInteger;

public class RSAPublicKeyCalculator 
{
    int p;
    int q;
    int n;
    int z;
    int e;
    
    RSAPublicKeyCalculator()
    {
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
        System.out.println("N: " + this.n);
        this.z = (this.p - 1) * (this.q - 1);
        System.out.println("Z: " + this.z);
        this.e = calcE();
        System.out.println("E: " + this.e);
    }
    public int calcE(){
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
    public int encryptPublicKey(int n, int e, int masterKey){

        BigInteger nBig = BigInteger.valueOf(n);
        BigInteger masterKeyBig = BigInteger.valueOf(masterKey);
        int result;
        BigInteger resultBigInteger;
        resultBigInteger = masterKeyBig.pow(e);
        resultBigInteger = resultBigInteger.mod(nBig);
        result = resultBigInteger.intValue();
        return result;
    }
}
