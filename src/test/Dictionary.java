package test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Dictionary {

    String[] fileNames;
    CacheManager lru;
    CacheManager lfu;
    BloomFilter bf;
    public Dictionary(String...algs){
        fileNames = algs;
        lru = new CacheManager(400, new LRU());
        lfu = new CacheManager(100, new LFU());
        bf = new BloomFilter(256,"MD5","SHA1");
        for(String fileName: algs){
            loadFile(fileName);
        }
    }

    public void loadFile(String fileName) {
        try{
            Stream<String> stringStream = Files.lines(Paths.get(fileName));
            stringStream.forEach(line->{
                Stream.of(line.split("\\s+")).forEach(word->bf.add(word));
            });
            stringStream.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public boolean query(String s){
        if(lru.query(s)){
            return true;
        }
        if(lfu.query(s)){
            return false;
        }
        boolean bloomFilterCheck = bf.contains(s);
        if(bloomFilterCheck){
            lru.add(s);
        }
        else{
            lfu.add(s);
        }
        return bloomFilterCheck;
    }

    public boolean challenge(String s){
        boolean searchTest;
        try{
            searchTest = IOSearcher.search(s, fileNames);
        }catch(RuntimeException e){
            return false;
        }
        if(searchTest){
            lru.add(s);
        }
        else{
            lfu.add(s);
        }
        return searchTest;
    }
}
