public class Student {
    private int id;
    private String name;
    private String seat;
    private boolean allowed;
    private String room;

    public Student(String name, String seat, boolean allowed, String room) {
        this.name = name;
        this.seat = seat;
        this.allowed = allowed;
        this.room = room;
    }

    public Student(int id, String name, String seat, boolean allowed, String room) {
        this.id = id;
        this.name = name;
        this.seat = seat;
        this.allowed = allowed;
        this.room = room;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSeat() { return seat; }
    public boolean isAllowed() { return allowed; }
    public String getRoom() { return room; }

    public void setSeat(String seat) { this.seat = seat; }
    public void setAllowed(boolean allowed) { this.allowed = allowed; }
}