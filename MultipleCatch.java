
package practicequestion3;

public class MultipleCatch {
    public static void main(String[] args) {
        try {
            int[] numbers = {10, 20, 30};

            int result = 10 / 0;
            System.out.println(result);

            System.out.println(numbers[5]);

        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid array index.");
        }

        System.out.println("Program continues.");
    }
}

    

