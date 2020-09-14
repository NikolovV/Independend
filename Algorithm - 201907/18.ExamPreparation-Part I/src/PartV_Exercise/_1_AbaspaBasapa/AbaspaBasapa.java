package PartV_Exercise._1_AbaspaBasapa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AbaspaBasapa {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char[] firstString = reader.readLine().toCharArray();
        char[] secondString = reader.readLine().toCharArray();
        int[][] lcs = new int[firstString.length][secondString.length];
        int max = 0;
        int maxIdxFstStr = 0;
        int maxIdxSecStr = 0;

        for (int idxFstStr = 0; idxFstStr < firstString.length; idxFstStr++) {
            for (int idxSecStr = 0; idxSecStr < secondString.length; idxSecStr++) {
                if (firstString[idxFstStr] == secondString[idxSecStr]) {
                    int result = 1;
                    if (idxFstStr - 1 >= 0 && idxSecStr - 1 >= 0) {
                        result = lcs[idxFstStr - 1][idxSecStr - 1] + 1;
                    }
                    lcs[idxFstStr][idxSecStr] = result;
                } else {
                    lcs[idxFstStr][idxSecStr] = 0;
                }

                if (lcs[idxFstStr][idxSecStr] > max) {
                    max = lcs[idxFstStr][idxSecStr];
                    maxIdxFstStr = idxFstStr;
                    maxIdxSecStr = idxSecStr;
                }
            }
        }

        List<Character> result = new ArrayList<>();

        while (max != 0 && maxIdxFstStr >= 0 && maxIdxSecStr >= 0) {
            result.add(firstString[maxIdxFstStr]);
            maxIdxFstStr--;
            maxIdxSecStr--;
            max--;
        }

        Collections.reverse(result);
        System.out.print(result.stream().map(Object::toString).collect(Collectors.joining("")));
    }
}