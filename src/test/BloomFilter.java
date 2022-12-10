package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

public class BloomFilter {

    String[] params;
    int size;
    BitSet bitSet;

    public BloomFilter(int s, String...algs){
        size = s;
        params = algs;
        bitSet = new BitSet(s);
    }

    public void add(String s){
        try {
            MessageDigest md = MessageDigest.getInstance(params[0]);
            byte[] bts = md.digest(s.getBytes());
            BigInteger a = new BigInteger(bts);
            int test = a.intValue();
            test = Math.abs(test) % size;
            String b = Integer.toBinaryString(test);
            b = String.format("%"+ size + "s", b).replaceAll(" ", "0");
            for(int i = 0; i < size; i++){
                if(b.indexOf(i) == 1) {
                    bitSet.set(i, true);
                }
                else{
                    bitSet.set(i,false);
                }
            }
            System.out.println(bitSet);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
