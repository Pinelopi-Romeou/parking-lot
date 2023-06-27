import java.util.Arrays;
import java.util.Comparator;
/**
 * This app examines the arrival and departure times of multiple cars in a
 * parking lot and calculates the maximum number of concurrent cars present
 */
public class CarArrivals {
    public static void main(String[] args) {
        int[][] array = {{1023, 1058}, {1008, 1145}, {1037, 1231}, {1147, 1317}, {1240, 1411}};
        int[][] transformed = transform(array);
        sortByTime(transformed);

        System.out.println("Max Arrivals: " + getMaxConcurrentCars(transformed));
    }

    /**
     * Creates an array that contains the transformed representation of the input array.
     * The new array separates the arrival and departure events and assigns an event type
     * (1 for arrival, 0 for departure).
     *
     * @param array    the input array containing arrival and departure time
     * @return         the transformed array
     */
    public static int[][] transform(int[][] array) {
        int[][] transformed = new int[array.length*2][2];

        for (int i = 0; i < array.length; i++) {
            transformed[i*2][0] = array[i][0];
            transformed[i*2][1] = 1;
            transformed[i*2+1][0] = array[i][1];
            transformed[i*2+1][1] = 0;
        }
        return transformed;
    }

    /**
     * Sorts the array that contains arrival and departure times in ascending order
     * @param array    the given array
     */
    public static void sortByTime(int[][] array) {
        Arrays.sort(array, Comparator.comparing(row -> row[0]));
    }

    /**
     *Finds the maximum number of concurrent cars present
     * @param array    the given array
     * @return         the number of concurrent cars
     */
    public static int getMaxConcurrentCars(int[][] array) {
        int count = 0;
        int maxCount = 0;

        for (int[] row : array) {
            if (row[1] == 1) {
                count++;
                if (count > maxCount) maxCount = count;
            } else {
                count--;
            }
        }
        return maxCount;
    }
}
