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

import certh.iti.mklab.jSimilarity.stringsimilarities.Similarity;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Implements Cosine Similarity between two non zero document vectors and it
 * measures the cosine of the angle between them. CosineSimilarity extends
 * Similarity: an abstract class for similarity metrics
 *
 * @author vasgat
 */
public class CosineSimilarity extends Similarity<HashMap> {

    /**
     * Constructor
     */
    public CosineSimilarity() {
    }

    /**
     * Calculates cosine similarity between two documents. The documents are
     * expressed as vectors of tokens.
     *
     * @param doc1 contains all the unique terms of doc1 and the weight of each
     * term
     * @param doc2 contains all the unique terms of doc2 and the weight of each
     * term.
     * @return the similarity between the documents (a value between 0-1)
     */
    @Override
    public double calculate(HashMap doc1, HashMap doc2) {
        Set<String> tokens = new HashSet();
        tokens.addAll(doc1.keySet());
        tokens.addAll(doc2.keySet());

        return Math.round(dotProduct(doc1, doc2) / (Math.sqrt(d(doc1)) * Math.sqrt(d(doc2))) * 1000.0D) / 1000.0D;
    }

    /**
     * Calculated the dot product between the two document vectors. The dot
     * product is an algebraic operation that takes two equal-length sequences
     * of numbers (usually coordinate vectors) and returns a single number.
     *
     * @param doc1 contains all the unique terms of doc1 and the weight of each
     * term
     * @param doc2 contains all the unique terms of doc2 and the weight of each
     * term
     * @return the calculated dotProduct between the two document vectors
     */
    protected double dotProduct(HashMap doc1, HashMap doc2) {
        HashSet<String> common_tokens = findCommonTokens(doc1.keySet(), doc2.keySet());
        double dot_product = 0D;
        for (String key : common_tokens) {
            dot_product += Double.parseDouble(doc2.get(key).toString()) * Double.parseDouble(doc1.get(key).toString());
        }
        return dot_product;
    }

    /**
     * Calculates the magnitude of a vector document
     *
     * @param doc contains all the unique terms of doc and the weight of each
     * term
     * @return the calculated magnitude of the given vector
     */
    protected double d(HashMap doc) {
        double d = 0D;
        for (String key : new HashSet<String>(doc.keySet())) {
            d += Math.pow(Double.parseDouble(doc.get(key).toString()), 2);
        }
        return d;
    }

    /**
     * returns the set of common tokens between two document vectors
     *
     * @param doc1_tokens the set of the unique terms the doc1 contains
     * @param doc2_tokens the set of the unique terms the doc2 contains
     * @return the set of common tokens between the two document vectors
     */
    protected HashSet<String> findCommonTokens(Set doc1_tokens, Set doc2_tokens) {
        HashSet<String> common_tokens = new HashSet(doc1_tokens);
        common_tokens.retainAll(doc2_tokens);
        return common_tokens;
    }

    /**
     * returns a small description of the metric
     *
     * @return the small description
     */
    @Override
    public String info() {
        return "Implements Cosine Similarity between two non zero vectors and it measures the cosine of the angle between them.\n"
                + "More info about Cosine Similarity can be found: https://en.wikipedia.org/wiki/Cosine_similarity";
    }

    /**
     * returns a string identifier of the metric
     *
     * @return the string identifier of the metric
     */
    @Override
    public String toString() {
        return "[CosineSimilarity]";
    }
}
