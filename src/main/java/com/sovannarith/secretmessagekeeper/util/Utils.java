package com.sovannarith.secretmessagekeeper.util;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Utils {

    private static Map<Character, Character> map = new HashMap<>();
    private static Map<Character, Character> reverse = new HashMap<>();

    @PostConstruct
    public static void sdf(){
        map.put('0', 'a');
        map.put('1', 'b');
        map.put('2', 'c');
        map.put('3', 'd');
        map.put('4', 'e');
        map.put('5', 'f');
        map.put('6', 'g');
        map.put('7', 'h');
        map.put('8', 'i');
        map.put('9', 'j');
        map.put('.', 'k');
        for(Map.Entry<Character, Character> entry : map.entrySet()){
            reverse.put(entry.getValue(), entry.getKey());
        }
    }

    public static String toCharStr(String reference){
        char[] refer = reference.toCharArray();
        StringBuilder result= new StringBuilder();
        for (char c : refer) {
            result.append(map.get(c));
        }
        return result.toString();
    }

    public static String toCharInt(String reference){
        char[] refer = reference.toCharArray();
        StringBuilder result= new StringBuilder();
        for (char c : refer) {
            result.append(reverse.get(c));
        }
        return result.toString();
    }

}
