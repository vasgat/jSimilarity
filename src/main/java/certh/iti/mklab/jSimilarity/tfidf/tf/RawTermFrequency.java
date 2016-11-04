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

/**
 * RawTermFrequency extends abstract class TF.
 *
 * @author vasgat
 */
public class RawTermFrequency extends TF {

    /**
     * RawTermFrequency constructor
     *
     * @param tokenizer
     */
    public RawTermFrequency(Tokenizer tokenizer) {
        super(tokenizer);
    }

    /**
     * Calculates the raw term frequency given the contents of the document as
     * string.
     *
     * @param contents input string
     * @return the raw term frequency
     */
    @Override
    public HashMap calculate(String contents) {
        return super.rawFrequency(contents);
    }

    /**
     * returns a small description of the class
     *
     * @return the small description
     */
    @Override
    public String info() {
        return "Raw term frequency (number of times a token appear in a given"
                + " string - document)";
    }

}
