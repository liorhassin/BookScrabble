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
        byte[] bts;
        for(String string : params){
            try {
                MessageDigest md = MessageDigest.getInstance(string);
                bts = md.digest(s.getBytes());
                BigInteger a = new BigInteger(bts);
                int test = a.intValue();
                test = Math.abs(test) % size;
                bitSet.set(test);
                System.out.println(bitSet);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
