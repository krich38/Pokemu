package org.pokemu.io;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.pokemu.Pokemon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Kyle Richards
 * @version 1.0
 *          <p>
 *          Handles the engines cache file. Simply downloads and extracts it if it is not found on the system.
 */
public final class CacheLoader {
    public static boolean loadCache() {
        String temp = System.getProperty("java.io.tmpdir");
        Pokemon.WORKING_DIRECTORY = temp + "pokemu" + File.separator;
        final File cacheFile = new File(temp + "pokemu.cache");
        try {
            if (!cacheFile.exists()) {
                final URL url = new URL("http://104.131.189.82/pokemu/pokemu.cache");
                final HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    int length = httpConn.getContentLength();
                    final InputStream inputStream = httpConn.getInputStream();
                    final FileOutputStream outputStream = new FileOutputStream(cacheFile);
                    int bytesRead, totalBytes = 0;
                    byte[] buffer = new byte[128];
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        totalBytes += bytesRead;
                        printProgBar((totalBytes / length) * 100);
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    outputStream.close();
                    inputStream.close();
                    System.out.println("\nCache downloaded.");
                    final ZipFile zip = new ZipFile(cacheFile);
                    zip.setPassword("mj8bvtevr538");
                    zip.extractAll(Pokemon.WORKING_DIRECTORY);
                } else {
                    throw new RuntimeException("Unable to download cache!");
                }
            }
            return true;
        } catch (IOException | ZipException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void printProgBar(int percent) {
        final StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < 50; i++) {
            if (i < (percent / 2)) {
                bar.append("=");
            } else if (i == (percent / 2)) {
                bar.append(">");
            } else {
                bar.append(" ");
            }
        }
        bar.append("]   ").append(percent).append("%     ");
        System.out.print("\r" + bar.toString());
    }
}
