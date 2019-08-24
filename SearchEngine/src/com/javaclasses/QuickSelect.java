package com.javaclasses;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@SuppressWarnings("unused")
public class QuickSelect {
    private static final int CUTOFF = 3;
    public static <AnyType> void swapReferences( AnyType [ ] a, int index1, int index2 )
    {
        AnyType tmp = a[ index1 ];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
    }
    
    private static <AnyType extends Comparable<? super AnyType>>
    AnyType median3( AnyType [ ] a, int left, int right )
    {
        int center = ( left + right ) / 2;
        if( a[ center ].compareTo( a[ left ] ) < 0 )
            swapReferences( a, left, center );
        if( a[ right ].compareTo( a[ left ] ) < 0 )
            swapReferences( a, left, right );
        if( a[ right ].compareTo( a[ center ] ) < 0 )
            swapReferences( a, center, right );

            // Place pivot at position right - 1
        swapReferences( a, center, right - 1 );
        return a[ right - 1 ];
    }
    
    private static <AnyType extends Comparable<? super AnyType>>
    void insertionSort( AnyType [ ] a, int left, int right )
    {
        for( int p = left + 1; p <= right; p++ )
        {
            AnyType tmp = a[ p ];
            int j;

            for( j = p; j > left && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )
                a[ j ] = a[ j - 1 ];
            a[ j ] = tmp;
        }
    }
    
    public static <AnyType extends Comparable<? super AnyType>>
    void quickSelect( Integer [ ] a, int k )
    {
        quickSelect( a, 0, a.length - 1, k );
    }

    
    private static <AnyType extends Comparable<? super AnyType>>
    void quickSelect( AnyType [ ] a, int left, int right, int k )
    {
        if( left + CUTOFF <= right )
        {
            AnyType pivot = median3( a, left, right );

                // Begin partitioning
            int i = left, j = right - 1;
            for( ; ; )
            {
                while( a[ ++i ].compareTo( pivot ) < 0 ) { }
                while( a[ --j ].compareTo( pivot ) > 0 ) { }
                if( i < j )
                    swapReferences( a, i, j );
                else
                    break;
            }

            swapReferences( a, i, right - 1 );   // Restore pivot

            if( k <= i )
                quickSelect( a, left, i - 1, k );
            else if( k > i + 1 )
                quickSelect( a, i + 1, right, k );
        }
        else  // Do an insertion sort on the subarray
            insertionSort( a, left, right );
    }
	    private static final int NUM_ITEMS = 12;
	    private static final int NUM_ITEMS1 = 12;

	    public static <AnyType extends Comparable<? super AnyType>>
	    void quickSelect1( Integer [ ] a, int k )
	    {
	    	 int p=0;
		        @SuppressWarnings("unused")
				Integer [ ] a1 = new Integer[ NUM_ITEMS ];
		        Integer [ ] b1 = new Integer[ NUM_ITEMS1 ];
		       // Random rand = new Random(System.currentTimeMillis());
		        // Fill array a with random numbers
		      // for( int i = 0; i < a1.length; i++ ) {
		        //   a1[i] = rand.nextInt(NUM_ITEMS * 5);
		       // }
		        // Quick select
		        //int k1 = 1; // k must be greater than 0 and less than n
		        System.out.println("Top 5 biggest");
		        for( int i = 0; i < a.length; i++ ) {
		       // System.out.print(a[i]+"  ");
		        }
		        for (k=1;k<11;k++) {
		        quickSelect( a, k); //NUM_ITEMS / 2 );        
		        b1[p]=a[k-1];
		        p++;
		        }
		        for(int y = 9;y>=5;y--) {
		        	 System.out.println(b1[y]);
			        }
	    }
	    
	 public static void main(String args[]) {
		 
 
		    int p=0;
	        Integer [ ] a = new Integer[ NUM_ITEMS ];
	        Integer [ ] b = new Integer[ NUM_ITEMS1 ];
	        Random rand = new Random(System.currentTimeMillis());
	        // Fill array a with random numbers
	       for( int i = 0; i < a.length; i++ ) {
	           a[i] = rand.nextInt(NUM_ITEMS * 5);
	        }
	        // Quick select
	        int k = 1; // k must be greater than 0 and less than n
	        System.out.println("Top 5 biggest");
	        for( int i = 0; i < a.length; i++ ) {
	       // System.out.print(a[i]+"  ");
	        }
	        for (k=1;k<13;k++) {
	        quickSelect( a, k); //NUM_ITEMS / 2 );        
	        b[p]=a[k-1];
	        p++;
	        }
	        for(int y = 9;y>=5;y--) {
	        	 System.out.println(b[y]);
		        }
	 }

}

