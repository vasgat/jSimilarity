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
 * Jaccard Similarity is a token based String Similarity metric that measures
 * similarity between finite sample sets (token sets), and is defined as the
 * size of the intersection divided by the size of the union of the sample sets.
 *
 * @author vasgat
 */
public class JaccardSimilarity extends Similarity<HashSet> {

    /**
     * JaccardSimilarity constructor
     */
    public JaccardSimilarity() {
    }

    /**
     * Calculates the Jaccard Similarity between to strings
     *
     * @param s1 input string 1
     * @param s2 input string 2
     * @return the calculated Jaccard Similarity (between 0-1)
     */
    @Override
    public double calculate(HashSet tokens_s1, HashSet tokens_s2) {
        HashSet<String> tokens_intersection = findCommonTokens(
                tokens_s1,
                tokens_s2
        );

        HashSet tokens_union = new HashSet();
        tokens_union.addAll(tokens_s1);
        tokens_union.addAll(tokens_s2);
        return tokens_intersection.size() * 1D / tokens_union.size();

    }

    /**
     * returns a small description of the metric
     *
     * @return the small description
     */
    @Override
    public String info() {
        return "Jaccard Similarity is a token based String Similarity metric that measures"
                + " similarity between finite sample sets (token sets), and is defined as the"
                + " size of the intersection divided by the size of the union of the sample sets.";
    }

    /**
     * returns the set of common tokens
     *
     * @param s1_tokens the set of the unique terms of string 1
     * @param s2_tokens the set of the unique terms of string 2
     * @return the set of common tokens between the two strings
     */
    private HashSet<String> findCommonTokens(Set s1_tokens, Set s2_tokens) {
        HashSet<String> common_tokens = new HashSet(s1_tokens);
        common_tokens.retainAll(s2_tokens);
        return common_tokens;
    }

}
