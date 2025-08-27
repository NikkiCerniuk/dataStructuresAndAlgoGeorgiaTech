//find all occurances of a pattern in the text 
//return as a list of ints with indecies of the occurances in ascending order

//failure cases to account for:
//if pattern is longer than the text, return an empty list
//n = text length
// m = pattern length 

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;




public class PatternMatching{

    public static List<Integer> boyerMoore(CharSequence pattern, CharSequence text, CharacterComparator comparator){
        List<Integer> matches = new ArrayList<>(); // matches delcaration to hold the list of ints where matches are
                                                   // found
    
        if (pattern.length() > text.length()) {// check if pattern is longer than text and if it is then return empty
                                               // list called matches. cant possibly have a match in this instace
            return matches;
        }
    
        Map<Character, Integer> lastTable = buildLastTable(pattern); // call the lastOccuranceTable helper method,
                                                                         // pass in the string
        int i = 0;// i is the index of the text we are on
        while (i <= text.length() - pattern.length()) {// while we still have enough text to pattern match
            int j = pattern.length() - 1; // j is the index in the pattern we are on (evaluating right to left)
            while (j >= 0 && comparator.compare(text.charAt(i + j), pattern.charAt(j))==0) { // have to use charAt to
                                                                                      // pattern (call the                                                                              // compare method for
                                                                                               // this)
                j--; // j is now j-1 (traverse the pattern to the left)
            }
                if (j == -1) {// if we have gone through the entire pattern
                    matches.add(i);// amend matches to include this text start index as a match
                    i++;

                } else {// text and pattern do not match
                    int shift = lastTable.getOrDefault(text.charAt(i + j), -1);// looking up last occurance index of
                     
                    if (shift == -1) { 
                        i = i + j + 1;
                    }
                    else if (shift < j) {
                        i = i + (j - shift); // new pattern start to align with where text matches pattern
                                             // (preprocessing)
                    } else {// if shift is more than j just shift by one bc we dont want to skip pattern
                        i = i + 1;
                    }
    
                }
            
        }
        return matches; // return list of starting indecies where matches are found
    }
    
    // requires an input that is a string and returns Character,Integer pairs
    // (entire map not one at a time)
    public static Map<Character, Integer> buildLastTable(CharSequence pattern){
        int m = pattern.length(); // figure out the pattern length
        Map<Character, Integer> lastIndex = new HashMap<>(); // initalize the hashmap
        for (int i = 0; i < m; i++) { // loop from 0 to m-1(inclusive)
            lastIndex.put(pattern.charAt(i), i); // first i is what char is in that spot, second i is what index it
                                                 // occurs at
        }
        return lastIndex;
    }
    }
