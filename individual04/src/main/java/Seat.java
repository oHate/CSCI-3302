public class Seat {

    private final int row;
    private final int column;
    private Status status;

    /**
     * Constructs a new seat at the given row and column with the specified vacancy.
     * @param row the row of the seat.
     * @param column the column of the seat.
     * @param vacant the vacancy of the seat.
     */
    public Seat(int row, int column, boolean vacant) {
        this.row = row;
        this.column = column;
        this.status = vacant ? Status.VACANT : Status.OCCUPIED;
    }

    /**
     * Gets the row of the seat.
     * @return the seats row.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gets the column of the seat.
     * @return the seats' column.
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Gets the vacant status of the seat.
     * @return true if the seat is vacant.
     */
    public boolean isVacant() {
        return this.status == Status.VACANT;
    }

    /**
     * Gets the infected status of the seat.
     * @return true if the seat is infected.
     */
    public boolean isInfected() {
        return this.status == Status.INFECTED;
    }

    /**
     * Gets the occupied status of the seat.
     * @return true if the seat is occupied.
     */
    public boolean isOccupied() {
        return this.status == Status.OCCUPIED;
    }

    /**
     * Infects the seat if it's Occupied.
     */
    public void infect() {
        if (isOccupied()) {
            this.status = Status.INFECTED;
        }
    }

    @Override
    public String toString() {
        return switch (this.status) {
            case VACANT -> "0";
            case OCCUPIED -> "1";
            case INFECTED -> "X";
        };
    }

}
