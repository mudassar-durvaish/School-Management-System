package sms.first;

public class Room {
    private String roomId;
    private int capacity;
    private String roomType;
    private int scheduledPeriods;

    public Room(String roomId, int capacity, String roomType, int scheduledPeriods) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.roomType = roomType;
        this.scheduledPeriods = scheduledPeriods;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getScheduledPeriods() {
        return scheduledPeriods;
    }

    public void setScheduledPeriods(int scheduledPeriods) {
        this.scheduledPeriods = scheduledPeriods;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", capacity=" + capacity +
                ", roomType='" + roomType + '\'' +
                ", scheduledPeriods=" + scheduledPeriods +
                '}';
    }
}
