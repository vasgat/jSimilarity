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

import certh.iti.mklab.jSimilarity.documentUtils.Corpus;
import certh.iti.mklab.jsimilarity.tfidf.DocumentFrequency;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * MaxInverseDocumentFrequency extends IDF and it is a variation of Inverse
 * Document Frequency. MaxIDF = log (1+max(n't)/nt)
 *
 * @author vasgat
 */
public class MaxInverseDocumentFrequency extends IDF {

    /**
     * MaxInverseDocumentFrequency constructor
     *
     * @param corpus
     */
    public MaxInverseDocumentFrequency(Corpus corpus) {
        super(corpus);
    }

    /**
     * Calculates Max IDF of each token contained in a corpus of documents.
     *
     * @return the calculated Max IDF per token in the corpus
     */
    @Override
    public HashMap<String, Double> calculate() {
        HashMap<String, Integer> df = DocumentFrequency.calculate(corpus);
        HashMap<String, Double> idf = new HashMap();
        int max = Collections.max(df.values());

        Iterator it = df.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();

            idf.put(
                    entry.getKey(),
                    Math.log(1D + (max / entry.getValue().doubleValue()))
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
                + "common or rare across all documents. Max Inverse Document Frequency "
                + "is a variation of IDF and MaxIDF = log (1+max(n't)/nt).";
    }
}
