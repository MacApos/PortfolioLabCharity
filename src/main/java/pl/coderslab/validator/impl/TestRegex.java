package pl.coderslab.validator.impl;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TestRegex {
    public static void main(String[] args) {
//        Street
        Pattern streetPattern = Pattern.compile(
                "\\d{1,4} \\p{Lu}*\\p{Ll}+" +   //np. 3 Maja
                        "|" +
                        "(\\p{Lu}\\p{Ll}+((( )|-)\\p{Lu}\\p{Ll}+)*)" +    //np. Jana Nowaka-Jeziorańskiego
                        " \\d{1,4}(/\\d{1,4})*(\\p{Lu}|\\p{Ll})*( m\\.\\d{1,4})*");   //np. Długa 8/9A m.11

        String street = "Poznańska 1";

        String formattedStreet = street.replaceAll(" {2,}", " ");
        String[] split = formattedStreet.split(" ");
        formattedStreet = Arrays.stream(split)
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining(" "));
        String pattern = "[^\\p{Lu}\\p{Ll}\\d ]";
        for (String s : formattedStreet.split("")) {
            if (s.matches(pattern)) {
                s = String.format("\\%s", s);
                formattedStreet = formattedStreet.replaceAll(" *" + s + " *", s);
            }
        }
        Matcher streetMatcher = streetPattern.matcher(street);
        System.out.println(street + "\n" + streetMatcher.matches());

//        City
        Pattern cityPattern = Pattern.compile("\\p{Lu}\\p{Ll}+((( )|-)\\p{Lu}\\p{Ll}+)*");

        String city = "Zielona Góra";
        Matcher cityMatcher = cityPattern.matcher(city);
        System.out.println(city + "\n" + cityMatcher.matches());
    }
}
