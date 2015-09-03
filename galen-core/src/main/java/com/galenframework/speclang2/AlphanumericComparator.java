/*******************************************************************************
* Copyright 2015 Ivan Shubin http://galenframework.com
* 
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*   http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
******************************************************************************/
package com.galenframework.speclang2;

import com.galenframework.specs.reader.StringCharReader;

import java.util.Comparator;

public class AlphanumericComparator implements Comparator<String> {


    @Override
    public int compare(String left, String right) {
        StringCharReader leftReader = new StringCharReader(left);

        StringCharReader rightReader = new StringCharReader(right);

        int result;

        while(leftReader.hasMore() && rightReader.hasMore()) {
            Object leftChunk = parseChunk(leftReader);
            Object rightChunk = parseChunk(rightReader);

            if (leftChunk instanceof Number && rightChunk instanceof Number) {
                result = toInt(leftChunk) - toInt(rightChunk);
                if (result != 0) {
                    return result;
                }
            } else {
                result = leftChunk.toString().compareTo(rightChunk.toString());
                if (result != 0) {
                    return result;
                }
            }
        }

        return left.length() - right.length();
    }

    private Integer toInt(Object chunk) {
        return ((Number)chunk).intValue();
    }

    private Object parseChunk(StringCharReader reader) {
        if (isDigit(reader.currentSymbol())) {
            return parseNumber(reader);
        } else {
            return parseNonNumber(reader);
        }
    }

    private String parseNonNumber(StringCharReader reader) {
        StringBuilder builder = new StringBuilder();

        while(reader.hasMore() && !isDigit(reader.currentSymbol())) {
            builder.append(reader.next());
        }
        return builder.toString();
    }

    private Integer parseNumber(StringCharReader reader) {
        StringBuilder builder = new StringBuilder();

        while(reader.hasMore() && isDigit(reader.currentSymbol())) {
            builder.append(reader.next());
        }
        return Integer.parseInt(builder.toString());
    }

    private boolean isDigit(char symbol) {
        return symbol >= 48 && symbol <= 57;
    }

}

