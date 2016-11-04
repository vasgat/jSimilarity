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
package certh.iti.mklab.jSimilarity.tfidf.idf;

import certh.iti.mklab.jsimilarity.tfidf.DocumentFrequency;
import certh.iti.mklab.jSimilarity.documentUtils.Corpus;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * ProbabilisticInverseDocumentFrequency class extends IDF and it is a variation
 * of Inverse Document Frequency. ProbabilisticIDF = log((N-nt)/nt
 *
 * @author vasgat
 */
public class ProbabilisticInverseDocumentFrequency extends IDF {

    /**
     * ProbabilisticInverseDocumentFrequency constructor
     *
     * @param corpus
     */
    public ProbabilisticInverseDocumentFrequency(Corpus corpus) {
        super(corpus);
    }

    /**
     * Calculates the Probabilistic Inverse Document frequency per token
     * contained in a corpus of documents
     *
     * @return the calculated Probabilistic Inverse Document Frequency
     */
    @Override
    public HashMap<String, Double> calculate() {
        HashMap<String, Integer> df = DocumentFrequency.calculate(corpus);
        HashMap<String, Double> idf = new HashMap();

        Iterator it = df.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();

            idf.put(
                    entry.getKey(),
                    Math.log((corpus.size() - entry.getValue().doubleValue())
                            / entry.getValue().doubleValue())
            );
        }
        return idf;
    }

    /**
     * returns a small description of the class
     *
     * @return the small description
     */
    @Override
    public String info() {
        return "The inverse document frequency is a measure of how much "
                + "information the word provides, that is, whether the term is "
                + "common or rare across all documents. Probabilistic Inverse Document Frequency "
                + "is a variation of IDF and ProbabilisticIDF = log((N-nt)/nt";
    }
}
