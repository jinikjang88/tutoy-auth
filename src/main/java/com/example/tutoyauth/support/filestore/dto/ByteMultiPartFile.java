package com.example.tutoyauth.support.filestore.dto;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import com.example.tutoyauth.support.filestore.util.FileStoreUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.io.IOUtils;

@Getter
public class ByteMultiPartFile {

  @JsonIgnore
  public final byte[] bytes;

  private final String fieldName;
  private final String fileName;
  private final String contentType;
  private final boolean formFiled;

  @Setter
  private String s3Url;
  @Setter
  private String filePath;
  @Setter
  private Long size;

  @Builder
  public ByteMultiPartFile(byte[] bytes, String fieldName, String fileName, String contentType,
      boolean formFiled, String s3Url, String filePath, Long size) {
    this.bytes = bytes;
    this.fieldName = fieldName;
    this.fileName = fileName;
    this.contentType = contentType;
    this.formFiled = formFiled;
    this.s3Url = s3Url;
    this.filePath = filePath;
    this.size = size;
  }

  public static ByteMultiPartFile of(final FileItemStream item) throws IOException {
    final byte[] bytes = toByteArray(item);
    return ByteMultiPartFile.builder()
        .fieldName(item.getFieldName())
        .fileName(item.getName())
        .contentType(item.getContentType())
        .formFiled(item.isFormField())
        .filePath(FileStoreUtil.createTempPath(item.getName()))
        .bytes(bytes)
        .size((long)bytes.length)
        .build();
  }

  public static byte[] toByteArray(FileItemStream item) throws IOException {
    try (InputStream inputStream = item.openStream()) {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      IOUtils.copy(inputStream, outputStream);
      return outputStream.toByteArray();
    }
  }

  public void validate() {
  }

  public static String getFileExtension(String fileName) {
    String[] splitDot = fileName.split("\\.");
    return (splitDot.length > 1) ? splitDot[splitDot.length-1].toLowerCase() : EMPTY;
  }
}
