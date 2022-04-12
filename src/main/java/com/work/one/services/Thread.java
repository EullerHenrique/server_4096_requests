package com.work.one.services;

import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.concurrent.*;

@Service
public class Thread {

    public Future<?> create(Path p) {
        ExecutorService es = Executors.newSingleThreadExecutor();
        return es.submit(new DownloadFile(p));
    }


}
