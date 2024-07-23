class ParkingSystem {
    int big;
    int medium;
    int small;

    public ParkingSystem(int big, int medium, int small) {
        this.big = big;
        this.medium = medium;
        this.small = small;
    }

    public boolean addCar(int carType) {
        if ((carType == 1) && (big - 1 >= 0)) {
            big--;
            return true;
        } else if ((carType == 2) && (medium - 1 >= 0)) {
            medium--;
            return true;
        } else if ((carType == 3) && (small - 1 >= 0)) {
            small--;
            return true;
        } else {
            return false;
        }
    }
}