/*
 * Copyright 2016 vasgat.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package certh.iti.mklab.jSimilarity.stringsimilarities;

import java.util.HashSet;
import java.util.Set;

/**
 * JaroDistance is a string similarity metric which extends Similarity class
 * that measures the similarity between two strings and takes into account the
 * mismatches in two strings by allowing character transpositions.
 *
 * @author vasgat
 */
public class JaroDistance extends Similarity<String> {

    /**
     * JaroDistance constructor
     */
    public JaroDistance() {
    }

    /**
     * Calculates the JaroDistance between two given strings
     *
     * @param s1 input string 1
     * @param s2 input string 2
     * @return the calculated Jaro Similarity
     */
    @Override
    public double calculate(String s1, String s2) {
        double m = 0D;
        double t = 0D;

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        if (str1.length <= 3 && str2.length <= 3) {
            return s1.equals(s2) ? 1D : 0D;
        }

        StringBuffer commonCharacters_s1 = getCommonCharacters(str1, str2);
        StringBuffer commonCharacters_s2 = getCommonCharacters(str2, str1);

        m = commonCharacters_s1.length();

        if (commonCharacters_s1.length() == 0 || commonCharacters_s2.length() == 0) {
            return 0.0D;
        }

        if (commonCharacters_s1.length() != commonCharacters_s2.length()) {
            return 0.0D;
        }

        for (int i = 0; i < commonCharacters_s1.length(); i++) {
            if (commonCharacters_s1.charAt(i) != commonCharacters_s2.charAt(i)) {
                t++;
            }
        }

        t = t / 2;

        double similarity = (m / str1.length + m / str2.length + (m - t) / m) / 3D;

        return Math.round(similarity * 1000.0D) / 1000.0D;
    }

    /**
     * returns a small description of the metric
     *
     * @return the small description
     */
    @Override
    public String info() {
        return "JaroDistance is a string similarity metric that measures the similarity"
                + "between two strings and takes into account the mismatches in two strings by"
                + "allowing character transpositions.";
    }

    /**
     * Finds the common characters between two char arrays (strings as char
     * arrays) if they exist within a calculated distance from the position in
     * str1
     *
     * @param str1 array 1 of chars
     * @param str2 array 2 of chars
     * @return a string buffer of characters from str1 that appear in str2
     * within a calculated distance
     */
    private static StringBuffer getCommonCharacters(char[] str1, char[] str2) {
        double max_length = Math.max(str1.length, str2.length);
        int lookup_thres = (int) (Math.floor(max_length / 2.0) - 1);

        StringBuffer commonChars = new StringBuffer();
        Set matched_chars = new HashSet();

        for (int i = 0; i < str1.length; i++) {
            for (int j = Math.max(0, i - lookup_thres); j < Math.min(str2.length, i + lookup_thres); j++) {
                if (str1[i] == str2[j] && !matched_chars.contains(j)) {
                    commonChars.append(str1[i]);
                    matched_chars.add(j);
                    break;
                }
            }
        }
        return commonChars;
    }

}
