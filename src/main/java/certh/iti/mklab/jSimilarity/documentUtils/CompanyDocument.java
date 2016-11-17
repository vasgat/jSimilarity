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

import static certh.iti.mklab.jSimilarity.documentUtils.TextDocument.DEFAULT_TOKENIZER;
import certh.iti.mklab.jSimilarity.tfidf.tf.RawTermFrequency;
import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author vasgat
 */
public class CompanyDocument extends Document {

    public String country;

    private CompanyDocument(Builder builder) {
        if (builder.id == null) {
            super.id = UUID.randomUUID().toString();
        } else {
            super.id = builder.id;
        }
        super.contents = builder.contents;
        if (builder.tokenizer == null) {
            super.tokenizer = new BasicTokenizer();
        } else {
            super.tokenizer = builder.tokenizer;
        }
        super.raw_term_frequency = new RawTermFrequency(tokenizer);
        super.BagOfWords = raw_term_frequency.calculate(contents);
        super.tf = new HashMap();
        this.country = builder.country;
    }

    public static class Builder {

        private String id;
        private final String contents;
        private Tokenizer tokenizer;
        private String country;

        public Builder(String contents) {
            this.contents = contents;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder tokenizer() {
            this.tokenizer = DEFAULT_TOKENIZER;
            return this;
        }

        public Builder tokenizer(Tokenizer tokenizer) {
            this.tokenizer = tokenizer;
            return this;
        }

        public CompanyDocument build() {
            return new CompanyDocument(this);
        }
    }
}
