package com.mijecu25.meme.utils.execute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Executor {
  public static final String USER_HOME = System.getProperty("user.home");
  public static final String PATH_SEPARATOR = System.getProperty("path.separator");
  public static final String CLASS_PATH = "./target/classes";

  private Executor() {}

  public static void processOutput(Process process) throws IOException {
    System.out.println("Output: ");
    BufferedReader inputReader =
        new BufferedReader(new InputStreamReader(process.getInputStream()));
    String string;

    while ((string = inputReader.readLine()) != null) {
      if (!string.isEmpty()) {
        System.out.println(string);
      }
    }

    inputReader.close();
    System.out.println();
  }

  public static void processError(Process process) throws IOException {
    StringBuilder stringBuilder = new StringBuilder();
    System.out.println("Errors: ");
    BufferedReader errorReader =
        new BufferedReader(new InputStreamReader(process.getErrorStream()));
    String string;

    while ((string = errorReader.readLine()) != null) {
      if (!string.isEmpty()) {
        stringBuilder.append(string);
        System.out.println(string);
      }
    }

    if (stringBuilder.length() != 0) {
      String potentialErrors = stringBuilder.toString();
      potentialErrors = potentialErrors.toLowerCase();

      if (potentialErrors.contains("error") || potentialErrors.contains("exception")) {
        throw new RuntimeException("There was an error in the application code");
      }
    }

    errorReader.close();
    System.out.println();
  }
}
