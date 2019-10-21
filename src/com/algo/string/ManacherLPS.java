package com.algo.string;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.min;

/** Program to Find the longest palindromic substring in a string */

/**
 *  Input:  abcdcbd
 *  Intermediate String: #a#b#c#d#c#b#d#
 *  Output: bcdcb is longest LPS of length 5
 */
public class ManacherLPS {

	public static String lpsLen(String input){
		int len = input.length();
		if(len == 0){
			return null;
		}
		int lps[] = new int[2*len+1];
		String newString = addDelimiter(input);
		Arrays.fill(lps, 0);
		int right, center, i ;
		int diff = -1;
		center = 1;
		right = 2;
		i = 1;
		int max_len = 1;
		lps[0]=0; lps[1]=1;
		len = newString.length();
		int maxIndex = 1;
		int iMirror;

		for(i=2;i<len-1;i++){
			iMirror = center - (i - center);	//corresponding mirror for ith index
			diff = right - i;	//to check if i exceeds right boundary
			if(diff>0){		//signifies the i lies within right boundary
				lps[i] = min(lps[iMirror], right- i);
			}

			while(i+lps[i]+1<len && i-(lps[i]+1)>=0 && newString.charAt(i+lps[i]+1) == newString.charAt(i-(lps[i]+1))){
				lps[i]++;
			}

			if(max_len<lps[i]){
				max_len = lps[i];
				maxIndex = i;
			}

			if(i+lps[i]>right){
				center = i;
				right = i+lps[i];

			}
		}
		int start = (maxIndex - max_len)/2;
		int end = start+max_len;
		System.out.println("Start = " + start);
		System.out.println("End = " + end);
		return input.substring(start, end);
	}

	//function to add the hash sign in the string
	public static String addDelimiter(String input){
		int len = input.length();
		String result = "";
		for(int i=0; i<len; i++){
			result += "#" + input.charAt(i);
		}
		result += "#";
		return result;
	}

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int tc = scan.nextInt();
		scan.nextLine();
		while(tc>0) {
			String str = scan.nextLine();
			System.out.println("Everyone is waiting..." +  lpsLen(str));
			tc--;
		}
	}
}
