package toysslserver;

import java.math.BigInteger;

public class RSAPrivateKeyCalculator 
{
    int p, q, n, z, e, d;
    RSAPrivateKeyCalculator(){
        this.p = 3;
        this.q = 5;
        this.n = 15;
        this.z = 8;
        this.e = 7;
        this.d = 23;
    }
    RSAPrivateKeyCalculator(int p, int q)
    {
        this.p = p;
        this.q = q;
        this.n = (this.p * this.q);
        System.out.println("n: " + this.n);
        this.z = (this.p - 1) * (this.q - 1);
        System.out.println("z: " + this.z);
        this.e = calculateE();
        System.out.println("e: " + this.e);
        this.d = calcD(this.e, this.z);
        System.out.println("d: " + this.d);
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
    public int calcD(int e, int z){
        int result;
        int d = 1;
        boolean test = false;
        while(test == false){
            if((e * d) % z == 1){
                test = true;
            }else{
                d++;
            }
        }
        result = d;
        return result;
    }
    public int decryptPrivateKey(int encryptedMasterKey){
        int result;
        BigInteger nBig = BigInteger.valueOf(this.n);
        BigInteger bigInteger;
        bigInteger = BigInteger.valueOf(encryptedMasterKey).pow(this.d);
        bigInteger = bigInteger.mod(nBig);
        result = bigInteger.intValue();
        return result;
    }
}
