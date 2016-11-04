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
import java.util.UUID;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author vasgat
 */
public class TextDocument {

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
    private Tokenizer tokenizer;
    private TF raw_term_frequency;

    /**
     * TextDocument constructor
     *
     * @param contents the text contents of the document
     */
    public TextDocument(String contents) {
        id = UUID.randomUUID().toString();
        this.contents = contents;
        this.tokenizer = DEFAULT_TOKENIZER;
        this.raw_term_frequency = new RawTermFrequency(new BasicTokenizer());
        this.BagOfWords = raw_term_frequency.calculate(contents);
        this.tf = new HashMap();
    }

    /**
     * TextDocument constructor
     *
     * @param id the identifier of the document is defined by the user
     * @param contents the text contents of the document
     */
    public TextDocument(String id, String contents) {
        this.id = id;
        this.contents = contents;
        this.tokenizer = DEFAULT_TOKENIZER;
        this.raw_term_frequency = new RawTermFrequency(new BasicTokenizer());
        this.BagOfWords = raw_term_frequency.calculate(contents);
        this.tf = new HashMap();
    }

    /**
     * TextDocument constructor
     *
     * @param contents the text contents of the document
     * @param tokenizer the preferred Tokenizer for text tokenization
     */
    public TextDocument(String contents, Tokenizer tokenizer) {
        id = UUID.randomUUID().toString();
        this.contents = contents;
        this.tokenizer = tokenizer;
        this.raw_term_frequency = new RawTermFrequency(tokenizer);
        this.BagOfWords = raw_term_frequency.calculate(contents);
        this.tf = new HashMap();
    }

    /**
     * TextDocument constructor
     *
     * @param id the identifier of the document is defined by the user
     * @param contents the text contents of the document
     * @param tokenizer the preferred Tokenizer for text tokenization
     */
    public TextDocument(String id, String contents, Tokenizer tokenizer) {
        this.id = id;
        this.contents = contents;
        this.tokenizer = tokenizer;
        this.raw_term_frequency = new RawTermFrequency(tokenizer);
        this.BagOfWords = raw_term_frequency.calculate(contents);
        this.tf = new HashMap();
    }

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
        return "[TextDocument|" + id + "]: " + BagOfWords;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(id)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TextDocument)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        TextDocument rhs = (TextDocument) obj;
        return new EqualsBuilder()
                .append(id, rhs.id)
                .isEquals();
    }
}
