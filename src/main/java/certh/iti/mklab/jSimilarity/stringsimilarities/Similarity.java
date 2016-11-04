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
package certh.iti.mklab.jSimilarity.stringsimilarities;

/**
 * An abstract class for similarity metrics.
 *
 * @author vasgat
 */
public abstract class Similarity<T> {

    /**
     * calculates the similarity measure of the metric for the given objects.
     *
     * @param s1 input object 1
     * @param s2 input object 2
     * @return returns a value 0-1 of similarity
     */
    public abstract double calculate(T o1, T o2);

    /**
     * returns a small description of the metric
     *
     * @return the small description
     */
    public abstract String info();
}
