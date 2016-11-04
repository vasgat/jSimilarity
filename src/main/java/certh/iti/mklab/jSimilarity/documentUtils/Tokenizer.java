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

/**
 * An abstract tokenizer. Tokenizers extending Tokenizer need only to implement
 * the tokenize(String tokens) method. Classes that extend Tokenizer class allow
 * to break a string into tokens.
 *
 * @author vasgat
 */
public abstract class Tokenizer {

    /**
     * Breaks a string into tokens
     *
     * @param contents string for tokenization
     * @return the array of strings - tokens
     */
    public abstract String[] tokenize(String contents);

}
