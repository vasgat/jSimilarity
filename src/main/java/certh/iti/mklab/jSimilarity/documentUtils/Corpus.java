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

import java.util.HashSet;
import java.util.Iterator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

/**
 * Corpus is a large collection of elements T. Corpus implements Iterable
 * abstract class to iterate to the set of documents.
 *
 * @author vasgat
 */
public class Corpus<T> implements Iterable<T> {

    private HashSet<T> corpus;

    /**
     * Corpus constructor
     */
    public Corpus() {
        this.corpus = new HashSet();
    }

    /**
     * A defined document is added to the corpus
     *
     * @param document
     */
    public void addDocument(T document) {
        corpus.add(document);
    }

    /**
     * Returns the size of the corpus
     *
     * @return the number of documents the Corpus contains
     */
    public int size() {
        return corpus.size();
    }

    /**
     *
     * @return the iterator of the corpus
     */
    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = corpus.iterator();
        return it;
    }

    /**
     *
     * @param predicate
     * @return
     */
    public Iterator<T> iterator(Predicate<T> predicate) {
        HashSet subset = new HashSet(CollectionUtils.select(corpus, predicate));
        return subset.iterator();
    }
}
