package com.example.tutoyauth.support.filestore.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "s3.client")
@Getter
@Setter
public

class S3ClientProperties {

  private String accessKeyId;
  private String secretAccessKey;
  private String endpoint;
}
