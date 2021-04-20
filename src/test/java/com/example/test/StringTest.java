package com.example.test;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.HammingDistance;
import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.apache.commons.text.similarity.LevenshteinDetailedDistance;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * @author hnn
 * @date 2021/01/25
 */
public class StringTest {

    @Test
    public void testStringJoiner() {
        String[] names = {"jack", "bob", "lina"};
        StringJoiner sj = new StringJoiner(",", "Hello ", "!");
        Arrays.stream(names).forEach(sj::add);
        System.out.println(sj.toString());
    }

    @Test
    public void testBigDecimal() {
        BigDecimal bd1 = new BigDecimal("123.23");
        BigDecimal bd2 = new BigDecimal("0.22");
        BigDecimal bd3 = bd1.divide(bd2, 5, RoundingMode.HALF_UP);
        System.out.println(bd3);
        BigDecimal[] bb = bd1.divideAndRemainder(bd2);
        System.out.println(Arrays.toString(bb));
    }

    @Test
    public void testMath() {
        System.out.println(Math.log10(100000));
        System.out.println(Math.log(100000));
        System.out.println(Math.asin(1.0 / 2) / Math.PI);
    }

    @Test
    public void testRandom() {
        System.out.println(new SecureRandom().nextInt());
    }

    @Test
    public void testDistance() {
        String left = "abc地方v并非官方公布";
        String right = "abcdf范德萨v";

        System.out.println(1 - (double) LevenshteinDistance.getDefaultInstance().apply(left, right) / Math.max(left.length(), right.length()));
    }

    @Test
    public void testStringFormat(){
            String hex = Integer.toHexString(0x000000FF);
            String sHex = String.format("%8s", hex).replace(" ", "0");
            String color = "蓝色";
            switch (sHex) {
                case "0000FF":
                    color = "蓝色";
                    break;
                case "FFFFFF":
                    color = "白色";
                    break;
                default:
                    color = "蓝色";
                    break;
            }
    }
}
