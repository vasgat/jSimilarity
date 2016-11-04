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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * NormalizedTermFrequency extends TF. It calculates Term Frequency as
 * logarithmically scaled frequency: tf(t,d) = 1 + log(f(t,d)), or zero if
 * f(t,d) is zero.
 *
 * @author vasgat
 */
public class NormalizedTermFrequency extends TF {

    /**
     * NormalizedTermFrequency constructor
     *
     * @param tokenizer
     */
    public NormalizedTermFrequency(Tokenizer tokenizer) {
        super(tokenizer);
    }

    /**
     * Calculates the normalized term frequency (log normalization) given the
     * contents of the document as string.
     *
     * @param contents input string
     * @return the normalized term frequency of a document
     */
    @Override
    public HashMap calculate(String contents) {
        HashMap<String, Integer> raw_tf = super.rawFrequency(contents);
        HashMap<String, Double> log_tf = new HashMap();

        Iterator it = raw_tf.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();

            log_tf.put(entry.getKey(), 1D + Math.log(entry.getValue().doubleValue()));
        }

        return log_tf;
    }

    /**
     * returns a small description of the class
     *
     * @return the small description
     */
    @Override
    public String info() {
        return "Logarithmically scaled term frequency: "
                + "tf(t,d) = 1 + log(f(t,d)), or zero if ft,d is zero";
    }

}
