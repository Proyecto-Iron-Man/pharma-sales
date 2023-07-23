package com.ironman.pharmasales.shared.string;

import java.text.Normalizer;

public class StringHelper {

    public String normalize(String input) {
        return input == null ? null : Normalizer.normalize(input, Normalizer.Form.NFKD);
    }

    public String removeAccents(String input) {
        return input == null ? null : normalize(input).replaceAll("\\p{M}","");
    }

    public String removePunctuation(String input) {
        return input == null ? null : normalize(input).replaceAll("\\p{Punct}","");
    }

    public String replaceWhitespace(String input, String replacement) {
        return input == null ? null : input.strip().replaceAll("\\s+", replacement);
    }

    public String slugsKeywords(String input) {
        String noAccents = removeAccents(input);
        String noPunctuation = removePunctuation(noAccents);

        return  replaceWhitespace(noPunctuation, "-").toLowerCase();
    }

}
