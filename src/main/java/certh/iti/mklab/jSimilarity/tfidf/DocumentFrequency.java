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
package certh.iti.mklab.jSimilarity.tfidf;

import certh.iti.mklab.jSimilarity.documentUtils.Corpus;
import certh.iti.mklab.jSimilarity.documentUtils.Document;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * DocumentFrequency class calculates the number of document occurrences of
 * unique tokens within a corpus
 *
 * @author vasgat
 */
public class DocumentFrequency {

    /**
     * Calculates the document frequency of the terms within a corpus.
     *
     * @param corpus input corpus of text documents
     * @return the document frequency of the terms
     */
    public static HashMap<String, Integer> calculate(Corpus corpus) {
        HashMap<String, Integer> df = new HashMap();

        Iterator<Document> it = corpus.iterator();

        while (it.hasNext()) {
            Document current_document = it.next();
            HashSet words = new HashSet(current_document.BagOfWords.keySet());

            Iterator<String> setOfWords = words.iterator();
            while (setOfWords.hasNext()) {
                String current_word = setOfWords.next();

                if (df.containsKey(current_word)) {
                    df.put(current_word, df.get(current_word) + 1);
                } else {
                    df.put(current_word, 1);
                }
            }
        }
        return df;
    }
}
