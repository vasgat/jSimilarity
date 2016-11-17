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

import certh.iti.mklab.jSimilarity.tfidf.tf.RawTermFrequency;
import certh.iti.mklab.jSimilarity.tfidf.tf.TF;
import java.util.HashMap;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author vasgat
 */
public abstract class Document {

    /**
     * the constant of default tokenizer
     */
    public static final Tokenizer DEFAULT_TOKENIZER = new BasicTokenizer();

    /**
     * unique document id
     */
    public String id;

    /**
     * the contents of the document
     */
    public String contents;

    /**
     * The document as BagOfWords - Vector with the raw term frequency of each
     * word
     */
    public HashMap<String, Integer> BagOfWords;

    /**
     * The document as a BagOfWords - Vector with the term frequency calculated
     * as defined by the user
     */
    public HashMap tf;
    protected Tokenizer tokenizer;
    protected TF raw_term_frequency;

    /**
     * Setter for Tokenizer, if user wants to change the tokenizer.
     *
     * @param tokenizer
     */
    public void setTokenizer(Tokenizer tokenizer) {
        raw_term_frequency = new RawTermFrequency(tokenizer);
        this.BagOfWords = raw_term_frequency.calculate(contents);
    }

    /**
     * Calculates the defined TF (term frequency) per token.
     *
     * @param termfrequency
     */
    public void implementTF(TF termfrequency) {
        this.tf = termfrequency.calculate(contents);
    }

    /**
     * returns a small description of the metric
     *
     * @return the small description
     */
    public String info() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * returns a string identifier of the metric
     *
     * @return the string identifier of the metric
     */
    @Override
    public String toString() {
        return "[Document|" + id + "]: " + BagOfWords;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(id)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Document)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Document rhs = (Document) obj;
        return new EqualsBuilder()
                .append(id, rhs.id)
                .isEquals();
    }
}
