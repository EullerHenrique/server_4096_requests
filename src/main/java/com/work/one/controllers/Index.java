package com.work.one.controllers;

import com.work.one.services.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
class Index {

    @Autowired
    private Thread t;

    private static final String SERVER_LOCATION = "src//main//resources//static//";

    @RequestMapping(path = "/{f}", method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> download(@PathVariable("f") String f) throws Exception {

        File file = new File(SERVER_LOCATION + java.io.File.separator + f);
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+f);
        Path path = Paths.get(file.getAbsolutePath());

        ByteArrayResource resource = (ByteArrayResource) t.create(path).get();

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

}
