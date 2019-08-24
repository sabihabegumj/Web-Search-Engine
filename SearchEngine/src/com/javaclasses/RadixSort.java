package com.javaclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class RadixSort {
	public static void radixSortA(String[] arr, int stringLen) {
		final int BUCKETS = 256;

		ArrayList<ArrayList<String>> buckets = new ArrayList<ArrayList<String>>(BUCKETS);
		// ArrayList<String[]> buckets = new ArrayList<String[]>(BUCKETS);

		for (int i = 0; i < BUCKETS; i++)
			// this.get(index).add(element);
			// ArrayList<String> element = new ArrayList<String>();
			buckets.add(new ArrayList<String>());
		// buckets.set(i, new ArrayList<String>());
		// buckets[i] = new ArrayList<String>();

		for (int pos = stringLen - 1; pos >= 0; pos--) {
			for (String s : arr)	//for(int i=0;i<arr.length;i++){
				// buckets[ s.charAt( pos ) ].add( s );
				buckets.get(s.charAt(pos)).add(s);

			int idx = 0;
			for (ArrayList<String> thisBucket : buckets) {
				for (String s : thisBucket) {
					arr[idx++] = s;	
				}
				thisBucket.clear();
			}
		}
		
	}

	/*
	 * Counting radix sort an array of Strings Assume all are all ASCII Assume all
	 * have same length
	 */
	public static void countingRadixSort(String[] arr, int stringLen) {
		final int BUCKETS = 256;

		int N = arr.length;
		String[] buffer = new String[N];

		String[] in = arr;
		String[] out = buffer;

		for (int pos = stringLen - 1; pos >= 0; pos--) {
			int[] count = new int[BUCKETS + 1];

			for (int i = 0; i < N; i++)
				count[in[i].charAt(pos) + 1]++;

			for (int b = 1; b <= BUCKETS; b++)
				count[b] += count[b - 1];

			for (int i = 0; i < N; i++)
				out[count[in[i].charAt(pos)]++] = in[i];

			// swap in and out roles
			String[] tmp = in;
			in = out;
			out = tmp;
		}

		// if odd number of passes, in is buffer, out is arr; so copy back
		if (stringLen % 2 == 1)
			for (int i = 0; i < arr.length; i++)
				out[i] = in[i];
	}

	// Print the array
	private static void print(String[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	public static void main(String[] args) {
		List<String> lst = new ArrayList<>();
		Random r = new Random();

		final int LEN = 4;

		for (int i = 0; i < 10; i++) {  
			String str = "";
			int len = LEN; // 3 + r.nextI  nt( 7 ); // between 3 and 9 characters

			for (int j = 0; j < len; j++)
				str += (char) ('a' + r.nextInt(26));
			System.out.println(str);
			lst.add(str);
		}

		String[] arr1 = new String[lst.size()];
		String[] arr2 = new String[lst.size()];

		lst.toArray(arr1);
		lst.toArray(arr2);

		// Print unsorted array
		print(arr1);
		print(arr2);
		

		long start, end;


		start = System.currentTimeMillis();
		Arrays.sort(arr1);
		end = System.currentTimeMillis();
		System.out.println("Quick sort: " + (end - start));
		
		start = System.currentTimeMillis();
		radixSortA(arr2, LEN);
		end = System.currentTimeMillis();
		System.out.println("Radix sort: " + (end - start));

		for (int i = 0; i < arr1.length; i++)
			if (!arr1[i].equals(arr2[i]))
				System.out.println("OOPS!!");

		// Print sorted array
		print(arr1);
		print(arr2);
	}

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	// Generate random strings
	public static String[] randomAlphaNumeric(int length, int number) {

		// length= random string length
		// number= number of random strings
		int counter = length;
		String random[] = new String[number];
		// System.out.println("Array of random strings: ");
		for (int i = 0; i < number; i++) {
			StringBuilder builder = new StringBuilder();
			while (counter-- != 0) {
				int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
				builder.append(ALPHA_NUMERIC_STRING.charAt(character));
			}
			random[i] = builder.toString();
			// System.out.print(builder.toString() + " ");
			counter = length;
		}

		return random;
	}

	public static Long[] randomLong() {

		// length= random string length
		// number= number of random strings
		Long random[] = new Long[100000];
		Random rand = new Random();

		for (int i = 0; i < 100000; i++) {
			random[i] = rand.nextLong();
		}
		return random;

	}

}
