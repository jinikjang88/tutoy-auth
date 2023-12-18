package com.example.tutoyauth.support.filestore.service;

import com.example.tutoyauth.support.filestore.code.Bucket;
import com.example.tutoyauth.support.filestore.dto.ByteMultiPartFile;
import com.example.tutoyauth.support.filestore.props.S3BucketProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3FileStore {

  public final S3Client s3Client;
  public final S3BucketProperties s3BucketProperties;

  public void upload(final Bucket bucket, final ByteMultiPartFile file) {
    file.validate();
    this.s3Upload(bucket, file);
  }

  public void s3Upload(final Bucket bucket, final ByteMultiPartFile file) {
    try {
      PutObjectRequest request = PutObjectRequest.builder()
          .bucket(s3BucketProperties.getBucketName(bucket))
          .key(file.getFilePath())
          .contentType(file.getContentType())
          .build();
      s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));
    } catch (Exception e) {
      log.error("failed file upload");
      throw new RuntimeException("");
    }
  }

  public void tempUpload(final Bucket bucket) {
    //TODO 최초 파일 업로드 시 임시저장된다.
  }

  public void permanent(final Bucket bucket) {
    //TODO 임시저장된 파일을 최종으로 영구 저장한다.
  }
}
