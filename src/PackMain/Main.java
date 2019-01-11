package PackMain;

import PackLogic.User;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        User anya = new User();
        User borys = new User();

        String telegram= "Hello friend";
        System.out.println("Borys telegram: "+telegram);
        System.out.println("Borys get Anya's public key [e,n]: "+ Arrays.toString(anya.getPublicKey()));
        BigInteger[] cryptedInt = borys.crypt(anya.getPublicKey(), telegram);
        StringBuilder sb = new StringBuilder();
        for (BigInteger value : cryptedInt){
            sb.append(value);
        }
        String cryptedStr=sb.toString();
        System.out.println("Crypted telegram is: "+cryptedStr);
        System.out.println();
        String decrypted = anya.decrypt(cryptedInt);
        System.out.println("Decrypted telegram is: "+ decrypted);
    }
}
