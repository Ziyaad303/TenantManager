package application;

public class Test {

	public static void main(String[] args) {
		int[] numbers = {1,2,3,4,5,6,7,8,9};
		
		int sum = 0;
		int i = 0;
		do {
			sum += numbers[i];
			System.out.println("Inside: " + sum);
			i++;
		} while (sum != 15);
		
		System.out.println("Outside: " + sum);
	}
}
