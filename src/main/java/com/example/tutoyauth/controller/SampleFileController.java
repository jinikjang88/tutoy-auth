package com.example.tutoyauth.controller;

import com.example.tutoyauth.support.filestore.code.Bucket;
import com.example.tutoyauth.support.filestore.dto.ByteMultiPartFile;
import com.example.tutoyauth.support.filestore.service.S3FileService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/file")
@RequiredArgsConstructor
public class SampleFileController {

  private final S3FileService s3Service;

  @PostMapping("/temp/upload")
  public List<ByteMultiPartFile> tempUpload(HttpServletRequest request) throws IOException, FileUploadException {
    return s3Service.upload(Bucket.TEMP, new ServletFileUpload().getItemIterator((RequestContext) request));
  }

  @GetMapping
  public String getTest() {
    return "hello";
  }
}
