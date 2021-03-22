package com.example.test;

import org.apache.commons.io.input.ReaderInputStream;
import org.junit.Test;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author hnn
 * @date 2021/02/19
 */
public class IoTest {

    @Test
    public void testFile() throws IOException {
        File f = new File("..");
        System.out.println(f.getPath());
        System.out.println(f.getAbsolutePath());
        //获得规范路径
        System.out.println(f.getCanonicalPath());
    }

    @Test
    public void testZipInputStream() {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(Paths.get("springbootdemo2.zip").toFile()))) {
            ZipEntry zipEntry=null;
            while ((zipEntry=zipInputStream.getNextEntry())!=null){
                if(!zipEntry.isDirectory()){
                    System.out.println(zipEntry.getName());
                    BufferedReader br=new BufferedReader(new InputStreamReader(zipInputStream)) ;
                    String line=null;
                    while ((line= br.readLine())!=null) {
                        System.out.println(line);
                    }
                }
            }
        } catch (Exception e) {

        }
    }
}
