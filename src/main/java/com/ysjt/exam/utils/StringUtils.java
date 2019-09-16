package com.ysjt.exam.utils;



public class StringUtils {

    private StringUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean hasText(String str){
        return org.springframework.util.StringUtils.hasText(str);
    }

    public static boolean isNotBlank(CharSequence str){
        return !isBlank(str);
    }

    public static boolean isBlank(CharSequence str){
        return org.apache.commons.lang3.StringUtils.isBlank(str);
    }

    public static boolean isEmpty(CharSequence str){
        return org.springframework.util.StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(CharSequence str){
        return !isEmpty(str);
    }

    public static String uncapitalize(String str){
        return org.apache.commons.lang3.StringUtils.uncapitalize(str);
    }

    public static String left(String str, int len){
        return org.apache.commons.lang3.StringUtils.left(str,len);
    }

    public static String rightPad(String str, int size){
        return org.apache.commons.lang3.StringUtils.rightPad(str,size);
    }

    public static String rightPad(String str, int size,String padChar){
        return org.apache.commons.lang3.StringUtils.rightPad(str,size,padChar);
    }

    public static int length(CharSequence cs){
        return org.apache.commons.lang3.StringUtils.length(cs);
    }

    public static String right(String str, int len){
        return org.apache.commons.lang3.StringUtils.right(str,len);
    }

    public static String leftPad(String str, int size){
        return org.apache.commons.lang3.StringUtils.leftPad(str,size);
    }

    public static String leftPad(String str, int size, String padStr){
        return org.apache.commons.lang3.StringUtils.leftPad(str,size,padStr);
    }

    public static String  removeStart(String str, String remove){
        return org.apache.commons.lang3.StringUtils.removeStart(str,remove);
    }

    public static int indexOf(CharSequence seq, int searchChar){
        return org.apache.commons.lang3.StringUtils.indexOf(seq,searchChar);
    }

    public static int indexOf(CharSequence seq, CharSequence searchSeq){
        return org.apache.commons.lang3.StringUtils.indexOf(seq,searchSeq);
    }

    public static String mid(String str, int pos, int len){
        return org.apache.commons.lang3.StringUtils.mid(str,pos,len);
    }

    public static boolean equals(CharSequence cs1,CharSequence cs2){
        return org.apache.commons.lang3.StringUtils.equals(cs1,cs2);
    }

    public static String substring(String str,int start){
        return org.apache.commons.lang3.StringUtils.substring(str,start);
    }

    public static String substring(String str,int start,int end){
        return org.apache.commons.lang3.StringUtils.substring(str,start,end);
    }

    public static String newStringUtf8(byte[] bytes) {
        return org.apache.commons.codec.binary.StringUtils.newStringUtf8(bytes);
    }

    public static String newStringIso8859_1(byte[] bytes) {
        return org.apache.commons.codec.binary.StringUtils.newStringIso8859_1(bytes);
    }

    public static byte[] getByteUtf8(String s) {
        return org.apache.commons.codec.binary.StringUtils.getBytesUtf8(s);
    }

    public static byte[] getByteIso8859_1(String s) {
        return org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1(s);
    }

    public static boolean isNotEmpty(String s) {
        return s != null && s.length() > 0;
    }

    public static boolean isTrimedEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static boolean isLineBroken(String s) {
        if (s.contains("\n") || s.contains("\r")) {
            return true;
        }
        return false;
    }

}
