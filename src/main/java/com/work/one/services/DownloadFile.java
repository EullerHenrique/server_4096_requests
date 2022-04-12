package com.work.one.services;

import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;

public class DownloadFile implements Callable {

    private final Path p;

    public DownloadFile(Path p){
        this.p = p;
    }

    public ByteArrayResource download() throws IOException{
        return new ByteArrayResource(Files.readAllBytes(this.p));
    }

    @Override
    public ByteArrayResource call()  {
        try{
            return this.download();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
