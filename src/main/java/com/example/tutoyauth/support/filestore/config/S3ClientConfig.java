package com.example.tutoyauth.support.filestore.config;

import com.example.tutoyauth.support.filestore.props.S3ClientProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class S3ClientConfig {

  public final S3ClientProperties s3ClientProperties;

  @Bean("s3Client")
  @Profile({"default", "local"})
  public S3Client s3Client() {
    return S3Client.builder().region(Region.AP_NORTHEAST_2).build();
  }

}
