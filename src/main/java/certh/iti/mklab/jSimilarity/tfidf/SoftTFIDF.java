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
package certh.iti.mklab.jsimilarity.tfidf;

import certh.iti.mklab.jSimilarity.stringsimilarities.CosineSimilarity;
import certh.iti.mklab.jSimilarity.documentUtils.Corpus;
import certh.iti.mklab.jSimilarity.stringsimilarities.JaroWinklerDistance;
import certh.iti.mklab.jSimilarity.stringsimilarities.Similarity;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * SoftTFIDF implements CosineSimilarity and overrides the computation of the
 * dotProduct. SoftTFIDF similarity uses TFIDF measure as a higher level of
 * similarity measure. It is TFIDF-based distance metric, extended to use "soft"
 * token-matching. Specifically, tokens are considered a partial match if they
 * get a good score using an inner string comparator.
 *
 * @author vasgat
 */
public class SoftTFIDF extends CosineSimilarity {

    private Corpus corpus;
    private TFIDF tfidf;
    private Similarity<String> string_similarity;
    public static final double DEFAULT_JARO_THRESHOLD = 0.9;
    private double threshold;

    /**
     * Crates a new instance with the defined corpus, token similarity measure
     * (jaro-winkler) and the default token similarity threshold.
     *
     * @param corpus
     */
    public SoftTFIDF(Corpus corpus) {
        this.corpus = corpus;
        this.tfidf = new TFIDF(corpus);
        tfidf.calculate();
        this.string_similarity = new JaroWinklerDistance();
        this.threshold = DEFAULT_JARO_THRESHOLD;
    }

    /**
     * Creates a new instance with the defined corpus and token similarity
     * threshold
     *
     * @param corpus
     * @param threshold
     */
    public SoftTFIDF(Corpus corpus, double threshold) {
        this.corpus = corpus;
        this.tfidf = new TFIDF(corpus);
        tfidf.calculate();
        this.string_similarity = new JaroWinklerDistance();
        this.threshold = DEFAULT_JARO_THRESHOLD;
    }

    /**
     * sets a defined different token similarity threshold
     *
     * @param threshold
     */
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    /**
     * sets a defined different AbstractStringSimilarity measure for calculating
     * the similarity between two tokens.
     *
     * @param similarity
     */
    public void setStringSimilarity(Similarity<String> similarity) {
        this.string_similarity = similarity;
    }

    /**
     * Calculates the SoftTFIDF similarity between two documents
     *
     * @param doc1_id identifier of document 1
     * @param doc2_id identifier of document 2
     * @return the calculated SoftTFIDF similarity between the documents
     */
    public double similarity(String doc1_id, String doc2_id) {
        return super.calculate(tfidf.tfidf(doc1_id), tfidf.tfidf(doc2_id));
    }

    /**
     * Overrides the calculation of the dotProduct in Cosine Similarity and
     * computes weight(x,y) as in traditional TFIDF score but weighing each
     * TFIDF component using s' (max token similarity between close(x,y,k) and
     * s' >= threshold).
     *
     * @param doc1
     * @param doc2
     * @return
     */
    @Override
    protected double dotProduct(HashMap doc1, HashMap doc2) {
        ArrayList<String> terms_doc1 = new ArrayList(doc1.keySet());
        ArrayList<String> terms_doc2 = new ArrayList(doc2.keySet());

        double dot_product = 0D;
        for (int i = 0; i < terms_doc1.size(); i++) {
            double max = 0;
            String token = "";
            for (int j = 0; j < terms_doc2.size(); j++) {
                double word_similarity = string_similarity.calculate(
                        terms_doc1.get(i),
                        terms_doc2.get(j)
                );

                if (word_similarity >= DEFAULT_JARO_THRESHOLD && max < word_similarity) {
                    token = terms_doc2.get(j);
                    max = word_similarity;
                }
            }
            if (!token.equals("")) {
                dot_product += max * ((double) doc1.get(terms_doc1.get(i))) * ((double) doc2.get(token));
            }
        }

        return dot_product;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return the string representation of the object
     */
    @Override
    public String toString() {
        return "[SoftTFIDF]";
    }

    /**
     * returns a small description of the metric
     *
     * @return the small description
     */
    @Override
    public String info() {
        return "SoftTFIDF similarity uses TFIDF measure as a higher level of\n"
                + " similarity measure. It is TFIDF-based distance metric, extended to use \"soft\"\n"
                + " token-matching. Specifically, tokens are considered a partial match if they\n"
                + " get a good score using an inner string comparator.";
    }
}
