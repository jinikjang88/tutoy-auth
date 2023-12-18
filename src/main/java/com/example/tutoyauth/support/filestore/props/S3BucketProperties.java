package com.example.tutoyauth.support.filestore.props;

import com.example.tutoyauth.support.filestore.code.Bucket;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "s3.bucket")
@Getter
@Setter
@NoArgsConstructor
public class S3BucketProperties {

  private String temp;
  private String main;
  private String thumbnail;

  public String getBucketName(final Bucket bucket) {
    if (Objects.requireNonNull(bucket) == Bucket.THUMBNAIL) {
      return thumbnail;
    } else if (Objects.requireNonNull(bucket) == Bucket.TEMP) {
      return temp;
    }
    return main;
  }
}
