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
package certh.iti.mklab.jSimilarity;

import certh.iti.mklab.jSimilarity.documentUtils.Corpus;
import certh.iti.mklab.jSimilarity.documentUtils.TextDocument;
import certh.iti.mklab.jSimilarity.tfidf.SoftTFIDF;
import certh.iti.mklab.jSimilarity.tfidf.TFIDF;

/**
 *
 * @author vasgat
 */
public class Main {

    public static void main(String[] args) {
        TextDocument document1 = new TextDocument.Builder("Bank of China").build();
        TextDocument document2 = new TextDocument.Builder("Bank of China Limited").build();
        TextDocument document3 = new TextDocument.Builder("Adidas AG").build();
        TextDocument document4 = new TextDocument.Builder("Adidas Group").build();
        TextDocument document5 = new TextDocument.Builder("Adidas").build();
        TextDocument document6 = new TextDocument.Builder("BMW Group").build();
        TextDocument document7 = new TextDocument.Builder("Nike Inc").build();
        TextDocument document8 = new TextDocument.Builder("PetroChina Company Limited").build();
        TextDocument document9 = new TextDocument.Builder("PetroChina Comany").build();

        Corpus corpus = new Corpus();
        corpus.addDocument(document1);
        corpus.addDocument(document2);
        corpus.addDocument(document3);
        corpus.addDocument(document4);
        corpus.addDocument(document5);
        corpus.addDocument(document6);
        corpus.addDocument(document7);
        corpus.addDocument(document8);
        corpus.addDocument(document9);

        TFIDF tfidf = new TFIDF(corpus);
        tfidf.calculate();

        double similarity = tfidf.similarity(document8.id, document9.id);

        System.out.println("TFIDF similarity between documents:" + document8.toString() + " and " + document9.toString());
        System.out.println("TFIDFSimilarity = " + similarity);

        SoftTFIDF softTFIDF = new SoftTFIDF(corpus);
        double softSim = softTFIDF.similarity(document8.id, document9.id);

        System.out.println("SoftTFIDF similarity between documents:" + document8.toString() + " and " + document9.toString());
        System.out.println("SoftTFIDFSimilarity = " + softSim);
    }
}
