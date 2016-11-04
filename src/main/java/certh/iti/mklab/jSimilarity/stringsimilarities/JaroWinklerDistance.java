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
 * JaroWinklerDistance is a string similarity metric which extends Similarity
 * class that measures the similarity between two strings and it is a variant of
 * the Jaro distance metric.
 *
 * @author vasgat
 */
public class JaroWinklerDistance extends Similarity<String> {

    /**
     * Constant of default scaling factor that is used in the calculation of
     * JaroWinklerDistance
     */
    private static final double DEFAULT_SCALING_FACTOR = 0.1;

    private double p;

    /**
     * JaroWinklerDistance constructor
     */
    public JaroWinklerDistance() {
        this.p = DEFAULT_SCALING_FACTOR;
    }

    /**
     * JaroWinklerDistance constructor
     *
     * @param scaling_factor
     */
    public JaroWinklerDistance(double scaling_factor) {
        this.p = scaling_factor <= 0.25 ? scaling_factor : 0.25;
    }

    /**
     * Calculates the JaroWinkler similarity between two giver strings
     *
     * @param s1 input string 1
     * @param s2 input string 2
     * @return
     */
    @Override
    public double calculate(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        JaroDistance jaro = new JaroDistance();
        double jaro_distance = jaro.calculate(s1, s2);

        if (jaro_distance < 0.7) {
            return Math.round(jaro_distance * 1000.0D) / 1000.0D;
        }

        StringBuffer prefix = new StringBuffer();
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                break;
            } else {
                prefix.append(s1.charAt(i));
            }
        }
        int minPrefix = Math.min(prefix.length(), 4);
        double similarity = jaro_distance + minPrefix * p * (1D - jaro_distance);

        return Math.round(similarity * 1000.0D) / 1000.0D;
    }

    @Override
    public String info() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
