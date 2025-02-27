import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Contagion {

    private final Seat[][] seats;

    /**
     * Loads the 2D array of Seats from a file.
     *
     * @param infile the file to read the 2d array from.
     * @throws FileNotFoundException if the file is not found
     */
    public Contagion(String infile) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(infile));

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        this.seats = new Seat[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int status = scanner.nextInt();
                this.seats[row][col] = new Seat(row, col, status == 0);
            }
        }
    }

    /**
     * Checks if the given row and column are out of the bounds of the seats array.
     *
     * @param row the row to check.
     * @param column the column to check.
     * @return true if the coordinates are invalid, false otherwise.
     */
    private boolean isInvalid(int row, int column) {
        return row < 0 || row >= seats.length || column < 0 || column >= seats[0].length;
    }

    /**
     * Starts a new infection at a given row and column.
     *
     * @param row    the row to start an infection at.
     * @param column the column to start an infection at.
     */
    public void infect(int row, int column) {
        if (isInvalid(row, column)) return;

        Seat carrier = this.seats[row][column];

        if (carrier.isVacant()) return;

        Stack<Seat> infected = new Stack<>();
        infected.push(carrier);

        while (!infected.isEmpty()) {
            Seat seat = infected.pop();

            if (seat.isVacant() || seat.isInfected()) continue;

            seat.infect();

            queueSeat(infected, seat.getRow(), seat.getColumn() - 1); // Up
            queueSeat(infected, seat.getRow(), seat.getColumn() + 1); // Down
            queueSeat(infected, seat.getRow() - 1, seat.getColumn());   // Left
            queueSeat(infected, seat.getRow() + 1, seat.getColumn());   // Right
        }
    }

    /**
     * Helper method for pushing an occupied seat to the stack.
     *
     * @param infected the stack that a seat will be pushed to.
     * @param row      the row of the seat to push.
     * @param column   the column of the seat to push.
     */
    private void queueSeat(Stack<Seat> infected, int row, int column) {
        if (isInvalid(row, column)) return;

        Seat seat = this.seats[row][column];

        if (seat.isVacant()) return;

        infected.push(seat);
    }

    /**
     * Checks if a seat is infected.
     *
     * @param row    the row of the seat.
     * @param column the column of the seat.
     * @return true if the seat is infected, false otherwise.
     */
    public boolean isInfected(int row, int column) {
        if (isInvalid(row, column)) return false;
        return this.seats[row][column].isInfected();
    }

    /**
     * Counts all currently infected seats.
     *
     * @return the total amount of infected seats.
     */
    public int countInfections() {
        int infected = 0;

        for (Seat[] value : this.seats) {
            for (Seat seat : value) {
                if (seat.isInfected()) {
                    infected++;
                }
            }
        }

        return infected;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < this.seats.length; row++) {
            for (int column = 0; column < this.seats[row].length; column++) {
                Seat seat = this.seats[row][column];

                if (seat.isVacant()) {
                    sb.append(0);
                } else if (seat.isOccupied()) {
                    sb.append(1);
                } else {
                    sb.append("X");
                }

                if (column != this.seats[row].length - 1) {
                    sb.append(" ");
                }
            }

            if (row != this.seats.length - 1) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * Write the 2D seats array to a file.
     *
     * @param outfile the file name to write to.
     * @throws IOException if the file failed to save.
     */
    public void write(String outfile) throws IOException {
        try (FileWriter writer = new FileWriter(outfile)) {
            writer.write(toString());
        }
    }

}