package com.example.tutoyauth.support.filestore.code;

public enum Bucket {

  TEMP("temp"),
  MAIN("main"),
  THUMBNAIL("thumbnail");

  private final String bucketName;

  Bucket(String bucketName) {this.bucketName = bucketName;}

  public String getBucketName() {return this.bucketName;}
}
