import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Table {

    double[][] values;

    /**
     * Initializes the table with a specific number of rows and columns.
     *
     * @param rows    the number of rows.
     * @param columns the number of columns.
     */
    public Table(int rows, int columns) {
        if (rows <= 0) {
            throw new IllegalArgumentException("The rows argument was <= 0");
        }

        if (columns <= 0) {
            throw new IllegalArgumentException("The columns argument was <= 0");
        }

        values = new double[rows][columns];
    }

    /**
     * Gets the total number of rows in the table.
     *
     * @return the number of rows.
     */
    public int getNumberOfRows() {
        return values.length;
    }

    /**
     * Gets the total number of columns in the table.
     *
     * @return the number of columns.
     */
    public int getNumberOfColumns() {
        return values[0].length;
    }

    /**
     * Returns the value of a cell at a specific row and column.
     *
     * @param row    the row.
     * @param column the column.
     * @return the value at a specific row and column.
     */
    public double getValue(int row, int column) {
        row--;
        column--;

        checkRow(row);
        checkColumn(column);

        return values[row][column];
    }

    /**
     * Changes the value of a cell at a specific row and column.
     *
     * @param row    the row.
     * @param column the column.
     * @param value  the value to be  .
     */
    public void setValue(int row, int column, double value) {
        row--;
        column--;
        checkRow(row);
        checkColumn(column);

        values[row][column] = value;
    }

    /**
     * Finds the smallest value in a row.
     *
     * @param row the row.
     * @return the smallest value.
     */
    public double getRowMin(int row) {
        row--;
        checkRow(row);

        double minValue = values[row][0];

        for (double value : values[row]) {
            minValue = Math.min(minValue, value);
        }

        return minValue;
    }

    /**
     * Finds the biggest value in a row.
     *
     * @param row the row.
     * @return the biggest value.
     */
    public double getRowMax(int row) {
        row--;
        checkRow(row);

        double maxValue = values[row][0];

        for (double value : values[row]) {
            maxValue = Math.max(maxValue, value);
        }

        return maxValue;
    }

    /**
     * Calculates the average of a row.
     *
     * @param row the row.
     * @return the row average.
     */
    public double getRowAverage(int row) {
        row--;
        checkRow(row);

        double total = 0;

        for (double value : values[row]) {
            total += value;
        }

        return total / getNumberOfColumns();
    }

    /**
     * Finds the smallest value in a column.
     *
     * @param column the column.
     * @return the smallest value.
     */
    public double getColumnMin(int column) {
        column--;
        checkColumn(column);

        double minValue = values[0][column];

        for (int row = 0; row < getNumberOfRows(); row++) {
            minValue = Math.min(minValue, values[row][column]);
        }

        return minValue;
    }

    /**
     * Finds the biggest value in a column.
     *
     * @param column the column.
     * @return the biggest value.
     */
    public double getColumnMax(int column) {
        column--;
        checkColumn(column);

        double maxValue = values[0][column];

        for (int row = 0; row < getNumberOfRows(); row++) {
            maxValue = Math.max(maxValue, values[row][column]);
        }

        return maxValue;
    }

    /**
     * Calculates the average of a column.
     *
     * @param column the column.
     * @return the column average.
     */
    public double getColumnAverage(int column) {
        column--;
        checkColumn(column);

        double total = 0;

        for (int row = 0; row < getNumberOfRows(); row++) {
            total += values[row][column];
        }

        return total / getNumberOfRows();
    }

    /**
     * Finds the smallest value in the entire table.
     *
     * @return the smallest value.
     */
    public double getMin() {
        double minValue = values[0][0];

        for (int row = 0; row < getNumberOfRows(); row++) {
            for (int column = 0; column < getNumberOfColumns(); column++) {
                minValue = Math.min(minValue, values[row][column]);
            }
        }

        return minValue;
    }

    /**
     * Finds the biggest value in the entire table.
     *
     * @return the biggest value.
     */
    public double getMax() {
        double maxValue = values[0][0];

        for (int row = 0; row < getNumberOfRows(); row++) {
            for (int column = 0; column < getNumberOfColumns(); column++) {
                maxValue = Math.max(maxValue, values[row][column]);
            }
        }

        return maxValue;
    }

    /**
     * Calculates the average of every cell within the table.
     *
     * @return the table average.
     */
    public double getAverage() {
        double total = 0;

        for (int row = 0; row < getNumberOfRows(); row++) {
            for (int column = 0; column < getNumberOfColumns(); column++) {
                total += values[row][column];
            }
        }

        return total / (getNumberOfRows() * getNumberOfColumns());
    }

    /**
     * Returns the amount of numbers found within an inclusive range.
     * a is NOT always <= b
     *
     * @param a first range number.
     * @param b second range number.
     * @return the amount of numbers found with an inclusive range.
     */
    public int getNumberInRange(double a, double b) {
        double first = a;
        double second = b;

        // Swap if a is greater than b
        if (first > second) {
            first = b;
            second = a;
        }

        int totalFound = 0;

        for (int row = 0; row < getNumberOfRows(); row++) {
            for (int column = 0; column < getNumberOfColumns(); column++) {
                double value = values[row][column];

                if (value >= first && value <= second) {
                    totalFound++;
                }
            }
        }

        return totalFound;
    }

    /**
     * Loads a table from a file.
     *
     * @param fileName the name of the file to load the table from.
     * @throws FileNotFoundException thrown if the file does not exist.
     */
    public void loadValues(String fileName) throws FileNotFoundException {
        File dataFile = new File(fileName);
        Scanner scanner = new Scanner(dataFile);

        for (int row = 0; row < getNumberOfRows(); row++) {
            for (int column = 0; column < getNumberOfColumns(); column++) {
                values[row][column] = scanner.nextDouble();
            }
        }

        scanner.close();
    }

    /**
     * Validates whether a row is within the allowed row range.
     *
     * @param row the row index that is being validated.
     * @throws IllegalArgumentException if row is outside the min/max range.
     */
    private void checkRow(int row) {
        int maxRow = getNumberOfRows() - 1;

        if (row < 0 || row > maxRow) {
            throw new IllegalArgumentException("The row argument was out of the allowed range of [0-" + maxRow + "] was " + row);
        }
    }

    /**
     * Validates whether a column is within the allowed column range.
     *
     * @param column the column index that is being validated.
     * @throws IllegalArgumentException if column is outside the min/max range.
     */
    private void checkColumn(int column) {
        int maxColumn = getNumberOfColumns() - 1;

        if (column < 0 || column > maxColumn) {
            throw new IllegalArgumentException("The column argument was out of the allowed range of [0-" + maxColumn + "] was " + column);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int row = 0; row < getNumberOfRows(); row++) {
            for (int column = 0; column < getNumberOfColumns(); column++) {
                builder.append(values[row][column]).append(" ");
            }

            builder.append("\n");
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return Objects.deepEquals(values, table.values);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(values);
    }

}