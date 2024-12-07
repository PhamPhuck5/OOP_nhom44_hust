package file_related;

import java.util.*;
public class MyObject {
	boolean debug = false;
    protected static boolean initIntegers = false;
    public static List<Integer> INTEGERS = new ArrayList<>();

    public MyObject() {
        if (!initIntegers) {
            initIntegers = true;
            for (int i = 0; i < 13; i++) {
            	INTEGERS.add(i);
            }
        }
    }
	
	public Integer getInteger(int i){
		return (Integer)INTEGERS.get(i);
	}
	public Integer getInteger(Integer i){
		return (Integer) INTEGERS.get(i.intValue());
	}
	
	public void debugOut(String text){
		if(debug) System.out.println(text); // out to console
	}
	/**
	 * @return whether or not debuggin is on
	 */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * @param b
	 */
	public void setDebug(boolean b) {
		debug = b;
	}
/*
	  public static <T extends Comparable<T>> void quickSort(ArrayList<T> elements)
	  { if (! elements.isEmpty())
		{ 
			MyObject.quickSort(elements, 0, elements.size()-1);
		}
	  }


	  private static <T extends Comparable<T>> void quickSort(ArrayList<T> elements, int lowIndex, int highIndex){
	        if (elements == null || elements.size() == 0 || lowIndex >= highIndex) {
	            return;
	        }

	        int lowToHighIndex = lowIndex;
	        int highToLowIndex = highIndex;
	        T pivotValue = elements.get((lowToHighIndex + highToLowIndex) / 2);

	        while (lowToHighIndex <= highToLowIndex) {
	            while (elements.get(lowToHighIndex).compareTo(pivotValue) < 0) {
	                lowToHighIndex++;
	            }
	            while (elements.get(highToLowIndex).compareTo(pivotValue) > 0) {
	                highToLowIndex--;
	            }

	            if (lowToHighIndex <= highToLowIndex) {
	                Collections.swap(elements, lowToHighIndex, highToLowIndex);
	                lowToHighIndex++;
	                highToLowIndex--;
	            }
	        }

	        if (lowIndex < highToLowIndex) {
	            quickSort(elements, lowIndex, highToLowIndex);
	        }
	        if (lowToHighIndex < highIndex) {
	            quickSort(elements, lowToHighIndex, highIndex);
	        }
	  }*/
	public static void echo(Object o){
		System.out.println(o);
	}
	public static void main(String args[]){
	
	}
}
