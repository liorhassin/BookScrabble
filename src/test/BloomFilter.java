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
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean contains(String s){
        byte[] bts;
        for(String string : params){
            try {
                MessageDigest md = MessageDigest.getInstance(string);
                bts = md.digest(s.getBytes());
                BigInteger a = new BigInteger(bts);
                int test = a.intValue();
                test = Math.abs(test) % size;
                if(bitSet.get(test)){
                    return true;
                }
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(bitSet.length());
        int i = 0;
        while(i < bitSet.length()){
            if(bitSet.get(i)){
                s.append(1);
            }
            else{
                s.append(0);
            }
            i++;
        }
        return s.toString();
    }
}
