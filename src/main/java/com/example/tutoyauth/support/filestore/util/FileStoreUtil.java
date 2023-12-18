package com.example.tutoyauth.support.filestore.util;

import java.util.UUID;
import org.apache.commons.io.FilenameUtils;

public class FileStoreUtil {

  public static final String TEMP_DIR = "public/temp";

  public static final String FILE_SEPARATOR = "/";


  public static String createTempPath(final String name) {
    String extension = FilenameUtils.getExtension(name);
    return TEMP_DIR + FILE_SEPARATOR + UUID.randomUUID().toString() + "." + extension;
  }

}
