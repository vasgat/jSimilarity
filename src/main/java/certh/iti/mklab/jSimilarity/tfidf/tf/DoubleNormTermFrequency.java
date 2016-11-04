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
package certh.iti.mklab.jSimilarity.tfidf.tf;

import certh.iti.mklab.jSimilarity.documentUtils.Tokenizer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * DoubleNormTermFrequency extends TF. Augmented frequency, to prevent a bias
 * towards longer documents, e.g. raw frequency divided by the maximum raw
 * frequency of any term in the document.
 *
 * @author vasgat
 */
public class DoubleNormTermFrequency extends TF {

    /**
     * DoubleNormTermFrequency constructor
     *
     * @param tokenizer
     */
    public DoubleNormTermFrequency(Tokenizer tokenizer) {
        super(tokenizer);
    }

    /**
     * Calculates Double normalization 0.5 of term frequency given the contents
     * of a document as string
     *
     * @param contents input string
     * @return the calculated Double Normalization 0.5 of the Term Frequency
     */
    @Override
    public HashMap calculate(String contents) {
        HashMap<String, Integer> raw_tf = super.rawFrequency(contents);
        HashMap<String, Double> doubleNorm_tf = new HashMap();

        Integer maxFrequency = Collections.max(raw_tf.values());
        Iterator it = raw_tf.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();

            doubleNorm_tf.put(entry.getKey(), 0.5D + 0.5D * (entry.getValue().doubleValue() / maxFrequency));
        }

        return doubleNorm_tf;
    }

    /**
     * returns a small description of the class
     *
     * @return the small description
     */
    @Override
    public String info() {
        return "Augmented frequency, to prevent a bias towards longer documents, "
                + "e.g. raw frequency divided by the maximum raw frequency of "
                + "any term in the document";
    }

}
