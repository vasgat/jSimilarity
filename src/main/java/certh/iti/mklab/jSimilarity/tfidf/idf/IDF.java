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
import java.util.HashMap;

/**
 * An abstract class for the calculation of IDF (Inverse Document Frequency).
 * There are a number of variations of Inverse Document Frequency that extend
 * this class.
 *
 * @author vasgat
 */
public abstract class IDF {

    protected Corpus corpus;

    /**
     * Each IDF constructor needs to define a Corpus of TextDocuments
     *
     * @param corpus
     */
    public IDF(Corpus corpus) {
        this.corpus = corpus;
    }

    /**
     * Calculates the IDF (Inverse Document Frequency) given the contents of a
     * document as string.
     *
     * @return the calculated term frequency.
     */
    public abstract HashMap calculate();

    public abstract double maxIDF();

    /**
     * returns a small description of the metric
     *
     * @return the small description
     */
    public abstract String info();
}
