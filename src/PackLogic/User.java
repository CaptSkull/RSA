package PackLogic;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

public class User {
    private BigInteger[] publicKey = new BigInteger[2];
    private BigInteger[] secretKey = new BigInteger[2];

    public User() {
        BigInteger p, q, n, eiler_f, e, d;
        BigInteger one = BigInteger.valueOf(1);
        int bitLeng = 1024;
        SecureRandom rnd = new SecureRandom();
        p=BigInteger.probablePrime(bitLeng,rnd);
        q=BigInteger.probablePrime(bitLeng,rnd);

        n=p.multiply(q);
        eiler_f=p.subtract(one).multiply(q.subtract(one));
        e=BigInteger.valueOf(2);
        while ((e.compareTo(eiler_f)<0)&& !eiler_f.gcd(e).equals(one)){
            e=e.add(one);
        }
        d=e.modInverse(eiler_f);

        publicKey[0]=e;
        publicKey[1]=n;
        secretKey[0]=d;
        secretKey[1]=n;
    }
    public BigInteger[] getPublicKey(){return  publicKey;}

    public BigInteger[] crypt (BigInteger[] publicKey, String telegram){
        char[] t = telegram.toCharArray();
        BigInteger[] crypt_units = new BigInteger[t.length];
        for(int i=0;i<telegram.length();i++){
            crypt_units[i]=(BigInteger.valueOf((int) t[i])).modPow(publicKey[0], publicKey[1]);
        }
        return crypt_units;
    }
    public String decrypt (BigInteger[] telegram) {
        System.out.println("Anya's secret key [d,n] is: "+ Arrays.toString(secretKey));
        char[] t = new char[telegram.length];
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i<telegram.length;i++){
            t[i]=(char) telegram[i].modPow(secretKey[0], secretKey[1]).intValue();
            decrypted.append(t[i]);
        }
        return decrypted.toString();
    }

}
