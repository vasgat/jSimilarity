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
package certh.iti.mklab.jSimilarity.documentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * BasicTokenizer breaks given strings to a set of tokens. As tokens are
 * regarded the words and the sequences of the numbers the string contains.
 *
 * @author vasgat
 */
public class BasicTokenizer extends Tokenizer {

    /**
     * Constant for ignoring punctuation in the string
     */
    public static boolean IGNORE_PUNCTUATION = true;

    /**
     * Constant for ignoring case in the string
     */
    public static boolean IGNORE_CASE = true;

    private boolean ignorePunctuation;
    private boolean ignoreCase;

    /**
     * Constructor of BasicTokenizer. The default behavior is to ignore
     * punctuation and casing to the strings that are going to be tokenized
     */
    public BasicTokenizer() {
        ignorePunctuation = IGNORE_PUNCTUATION;
        ignoreCase = IGNORE_PUNCTUATION;
    }

    /**
     * Constructor of BasicTokenizer. Users can define values other than
     * defaults for ignoring punctuation and casing in the strings.
     *
     * @param ignorePunctuation
     * @param ignoreCase
     */
    public BasicTokenizer(boolean ignorePunctuation, boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        this.ignorePunctuation = ignorePunctuation;
    }

    /**
     * Setter for ignoring punctuation in strings
     *
     * @param preference
     */
    public void setIgnorePunctuation(boolean preference) {
        this.ignorePunctuation = preference;
    }

    /**
     * Setter for ignoring casing in strings
     *
     * @param preference
     */
    public void setIgnoreCase(boolean preference) {
        this.ignoreCase = preference;
    }

    /**
     * Performs tokenization of an input string
     *
     * @param contents input string
     * @return an array of tokens (words)
     */
    @Override
    public String[] tokenize(String contents) {
        List<String> tokens = new ArrayList();
        int cursor = 0;
        while (cursor < contents.length()) {
            char ch = contents.charAt(cursor);
            if (Character.isWhitespace(ch)) {
                cursor++;
            } else if (Character.isLetter(ch) || Character.isDigit(ch)) {
                StringBuffer buf = new StringBuffer("");
                while ((cursor < contents.length()) && (Character.isLetter(contents.charAt(cursor)) || Character.isDigit(contents.charAt(cursor)))) {
                    buf.append(contents.charAt(cursor));
                    cursor++;
                }
                tokens.add(transform(buf.toString()));
            } else {
                if (!this.ignorePunctuation) {
                    StringBuffer buf = new StringBuffer("");
                    buf.append(ch);
                    String str = buf.toString();
                    tokens.add(transform(str));
                }
                cursor++;
            }
        }
        return tokens.toArray(new String[tokens.size()]);
    }

    /**
     * Transforms a string to lowercase.
     *
     * @param s input text
     * @return the tranformed string
     */
    private String transform(String s) {
        return ignoreCase ? s.toLowerCase() : s;
    }

}
