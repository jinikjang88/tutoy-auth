package com.example.tutoyauth.support.filestore.service;

import com.example.tutoyauth.support.filestore.code.Bucket;
import com.example.tutoyauth.support.filestore.dto.ByteMultiPartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3FileService {

  private final S3FileStore s3FileStore;

  public List<ByteMultiPartFile> upload(Bucket bucket, FileItemIterator itemIterator)
      throws IOException, FileUploadException {
    List<ByteMultiPartFile> files = new ArrayList<>();
    while (itemIterator.hasNext()) {
      FileItemStream item = itemIterator.next();
      if (!item.isFormField()) {
        files.add(ByteMultiPartFile.of(item));
      }
    }
    files.forEach(file -> s3FileStore.s3Upload(bucket, file));
    return files;
  }
}
