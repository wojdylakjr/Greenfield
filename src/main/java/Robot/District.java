package Robot;

public enum District {
    FIRST(1, 0, 4, 0, 4),
    SECOND(2, 0, 4, 5, 9),
    THIRD(3, 5, 9, 5, 9),
    FOURTH(4, 5, 9, 0, 4);


    private final int districtNumber;
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;

    District(int districtNumber, int minX, int maxX, int minY, int maxY) {
        this.districtNumber = districtNumber;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public int getDistrictNumber() {
        return districtNumber;
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }


    public int getMaxY() {
        return maxY;
    }
    public static District getEnumByNumber(int districtNumber){
        switch (districtNumber){
            case 1: return District.FIRST;
            case 2: return District.SECOND;
            case 3: return District.THIRD;
            case 4: return District.FOURTH;
            default: return null;

        }
    }

    @Override
    public String toString() {
        return "District{" +
                "districtNumber=" + districtNumber +
                ", minX=" + minX +
                ", maxX=" + maxX +
                ", minY=" + minY +
                ", maxY=" + maxY +
                '}';
    }
}
