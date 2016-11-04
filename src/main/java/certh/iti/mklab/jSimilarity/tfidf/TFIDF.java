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
import certh.iti.mklab.jSimilarity.documentUtils.BasicTokenizer;
import certh.iti.mklab.jSimilarity.documentUtils.Corpus;
import certh.iti.mklab.jSimilarity.documentUtils.TextDocument;
import certh.iti.mklab.jSimilarity.tfidf.idf.IDF;
import certh.iti.mklab.jSimilarity.tfidf.idf.InverseDocumentFrequency;
import certh.iti.mklab.jSimilarity.tfidf.tf.NormalizedTermFrequency;
import certh.iti.mklab.jSimilarity.tfidf.tf.TF;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * TFIDF class implements Term Frequency Inverse Document Frequency statistic.
 * TF-IDF is a numerical statistic that is intended to reflect how important a
 * word is to a document in a collection or corpus.
 *
 * @author vasgat
 */
public class TFIDF {

    private Corpus corpus;
    private IDF idf;
    private TF tf;
    private HashMap<String, HashMap<String, Double>> tfidf;

    /**
     * TFIDF constructor
     *
     * @param corpus
     */
    public TFIDF(Corpus corpus) {
        this.corpus = corpus;
    }

    /**
     * set preferable variation of IDF
     *
     * @param idf
     */
    public void setIDF(IDF idf) {
        this.idf = idf;
    }

    /**
     * set preferable variation of TF
     *
     * @param tf
     */
    public void setTF(TF tf) {
        this.tf = tf;
    }

    /**
     * Calculates the tf-idf weights in the given corpus
     */
    public void calculate() {
        tfidf = new HashMap();

        if (idf == null) {
            idf = new InverseDocumentFrequency(corpus);
        }

        if (tf == null) {
            tf = new NormalizedTermFrequency(new BasicTokenizer());
        }

        HashMap<String, Double> idf_weights = idf.calculate();

        Iterator<TextDocument> collection = corpus.iterator();

        while (collection.hasNext()) {
            TextDocument document = collection.next();

            if (document.tf == null || document.tf.isEmpty()) {
                document.implementTF(this.tf);
            }

            Iterator tokens = document.tf.entrySet().iterator();

            HashMap<String, Double> tfidf_weights = new HashMap();

            while (tokens.hasNext()) {
                Map.Entry<String, Double> tf_weights
                        = (Map.Entry<String, Double>) tokens.next();

                double tfidf_weight = tf_weights.getValue() * idf_weights.get(tf_weights.getKey());

                tfidf_weights.put(tf_weights.getKey(), tfidf_weight);
            }
            tfidf.put(document.id, tfidf_weights);
        }

    }

    /**
     * returns the tf*idf weights of each token of given document
     *
     * @param document_id document identifier
     * @return the tf*idf weights
     */
    public HashMap<String, Double> tfidf(String document_id) {
        return tfidf.get(document_id);
    }

    /**
     * returns the tf*idf weight of a token in a given document
     *
     * @param document_id document identifier
     * @param token
     * @return the tf*idf weight of the token
     */
    public double tfidf(String document_id, String token) {
        return tfidf.get(document_id).get(token);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return the string representation of the object
     */
    public String toString() {
        return "tfidf weights: " + tfidf;
    }

    /**
     * returns a small description
     *
     * @return the small description
     */
    public String info() {
        return " term frequency–inverse document frequency, is a numerical "
                + "statistic that is intended to reflect how important a word "
                + "is to a document in a collection or corpus";
    }

    /**
     * Calculates tf-idf similarity between two given documents. It is actually
     * the calculated Cosine Similarity by using tf*idf weights.
     *
     * @param doc1_id the identifier of document 1
     * @param doc2_id the identifier of document 2
     * @return the tf-idf similarity as a double (values between 0-1)
     */
    public double similarity(String doc1_id, String doc2_id) {
        CosineSimilarity cosine_similarity = new CosineSimilarity();
        return cosine_similarity.calculate(tfidf.get(doc1_id), tfidf.get(doc2_id));
    }

}
