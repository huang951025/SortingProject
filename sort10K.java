/**
 * Class: CIS211 Data Structures Author: Wei Wen Huang Date: 11/25/2019
 * Describe: write quick sort, shell sort, select sort, and insert sort. and set
 * siez 10,000 random array to test 10,000 time.
 */
public class sort10K {

	public static void main(String[] args) {
		int testsize = 10000;
		testInsertSort(testsize);
		testQuickSort(testsize);
		testShellSort(testsize);
		testSelectSort(testsize);

	}

	// method swap
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void printArr(int[] arr) {
		for (int anArr : arr) {
			System.out.print(anArr + " ");
		}
	}

	public static int partition(int[] arr, int first, int last) {

		int mid = first + (last - first) / 2;
		// keep left side small
		if (arr[first] > arr[last])
			swap(arr, first, last);
		// keep mid small
		if (arr[mid] > arr[last])
			swap(arr, mid, last);
		// keep mid mall two side large
		if (arr[mid] > arr[first])
			swap(arr, first, mid);
		int pivot = arr[first];
		while (last > first) {

			while (pivot <= arr[last] && first < last) {
				--last;
			} // end while
				// if last bigger than first replace
			if (first < last) {
				arr[first] = arr[last];
				++first;
			} // end if

			while (pivot >= arr[first] && first < last) {
				++first;
			} // end while
			if (first < last) {
				arr[last] = arr[first];
				--last;
			} // end if
		}
		arr[first] = pivot;
		return first;

	}// end
		// method quick sort

	public static void quickSort(int[] arr, int first, int last) {
		if (arr == null || first >= last || arr.length <= 1)
			return;
		int mid = partition(arr, first, last);
		quickSort(arr, first, mid); // recursive left side
		quickSort(arr, mid + 1, last); // recursive right side

	}// end method

	public static void insertSort(int[] arr) {

		for (int i = 1; i < arr.length; i++) {
			int insertValue = arr[i];// get insert Value
			int insertIndex = i - 1; // set position

			// search for position for insert Value
			while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
				arr[insertIndex + 1] = arr[insertIndex];
				insertIndex--;
			} // end while

			// found position for insertValue
			arr[insertIndex + 1] = insertValue;
		} // end for
	}// end method insert sort

	// method select sort
	public static void selectSort(int[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {
			int min = arr[i]; // minimal value
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (min > arr[j]) {
					min = arr[j]; // reset minimal value
					minIndex = j; // reset minimal index
				}
			} // end for found the minimal value

			// swap
			if (minIndex != i) {
				arr[minIndex] = arr[i];
				arr[i] = min;
			} // end swap

		} // end for
	}// end method select sort

	public static void shellSort(int[] arr) {

		// sorting
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {

			for (int i = gap; i < arr.length; i++) {
				int j = i;
				int temp = arr[j]; // temp for save value
				if (arr[j] < arr[j - gap]) {
					while (j - gap >= 0 && temp < arr[j - gap]) {
						// move
						arr[j] = arr[j - gap];
						j -= gap;
					} // end while found position for temp
				}

				arr[j] = temp;

			} // end for
		} // end for
	}// end method

	// test quick sort
	public static void testQuickSort(int size) {

		// set 5000 random array
		int[] arr = new int[size];
		for (int i = 0; i < arr.length; i++) {

			arr[i] = (int) (Math.random() * 2147483647);

		}

		int runTime = 0; // save run time
		double meanTime = 0;
		double standDeviation = 0;

		int[] run = new int[arr.length];// to save runtime

		for (int i = 0; i < run.length; i++) {
			long startTime = System.currentTimeMillis(); // get star time
			quickSort(arr, 0, arr.length - 1);
			long endTime = System.currentTimeMillis(); // get end time
			long spendTime = endTime - startTime;

			runTime += spendTime; // get total run time
			run[i] = (int) spendTime;// save spend time to array
		}
		meanTime = runTime / run.length;// get meanTime

		for (int i = 0; i < run.length; i++) {
			standDeviation += Math.pow(run[i] - meanTime, 2.0);
		} // end for

		System.out.println(run.length + " times Quick Sort total run time is " + runTime + "ms mean time is " + meanTime
				+ "ms stand Deviation is: " + Math.sqrt(standDeviation));
	}// end test quick sort

	public static void testShellSort(int size) {

		// set 5000 random array
		int[] arr = new int[size];
		for (int i = 0; i < arr.length; i++) {

			arr[i] = (int) (Math.random() * 2147483647);

		}

		int runTime = 0; // save run time
		double meanTime = 0;
		double standDeviation = 0;

		int[] run = new int[arr.length];// to save runtime

		for (int i = 0; i < run.length; i++) {
			long startTime = System.currentTimeMillis(); // get star time
			shellSort(arr);
			long endTime = System.currentTimeMillis(); // get end time
			long spendTime = endTime - startTime;

			runTime += spendTime; // get total run time
			run[i] = (int) spendTime;// save spend time to array
		}
		meanTime = runTime / run.length;// get meanTime

		for (int i = 0; i < run.length; i++) {
			standDeviation += Math.pow(run[i] - meanTime, 2.0);
		} // end for

		System.out.println(run.length + " times shell Sort total run time is " + runTime + "ms mean time is " + meanTime
				+ "ms stand Deviation is: " + Math.sqrt(standDeviation));

	}// end test shell sort

	public static void testInsertSort(int size) {

		// set 5000 random array
		int[] arr = new int[size];
		for (int i = 0; i < arr.length; i++) {

			arr[i] = (int) (Math.random() * 2147483647);

		}

		int runTime = 0; // save run time
		double meanTime = 0;
		double standDeviation = 0;

		int[] run = new int[arr.length];// to save runtime

		for (int i = 0; i < run.length; i++) {
			long startTime = System.currentTimeMillis(); // get star time
			insertSort(arr);
			long endTime = System.currentTimeMillis(); // get end time
			long spendTime = endTime - startTime;

			runTime += spendTime; // get total run time
			run[i] = (int) spendTime;// save spend time to array
		}
		meanTime = runTime / run.length;// get meanTime

		for (int i = 0; i < run.length; i++) {
			standDeviation += Math.pow(run[i] - meanTime, 2.0);
		} // end for

		System.out.println(run.length + " times insert Sort total run time is " + runTime + "ms mean time is "
				+ meanTime + "ms stand Deviation is: " + Math.sqrt(standDeviation));

	}// end test insert sort

	public static void testSelectSort(int size) {

		// set 5000 random array
		int[] arr = new int[size];
		for (int i = 0; i < arr.length; i++) {

			arr[i] = (int) (Math.random() * 2147483647);

		}

		int runTime = 0; // save run time
		double meanTime = 0;
		double standDeviation = 0;

		int[] run = new int[arr.length];// to save runtime

		for (int i = 0; i < run.length; i++) {
			long startTime = System.currentTimeMillis(); // get star time
			selectSort(arr);
			long endTime = System.currentTimeMillis(); // get end time
			long spendTime = endTime - startTime;

			runTime += spendTime; // get total run time
			run[i] = (int) spendTime;// save spend time to array
		}
		meanTime = runTime / run.length;// get meanTime

		for (int i = 0; i < run.length; i++) {
			standDeviation += Math.pow(run[i] - meanTime, 2.0);
		} // end for

		System.out.println(run.length + " times select Sort total run time is " + runTime + "ms mean time is "
				+ meanTime + "ms stand Deviation is: " + Math.sqrt(standDeviation));

	}// end test select sort

}// end all}