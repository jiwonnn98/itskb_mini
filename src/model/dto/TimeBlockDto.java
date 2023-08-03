package model.dto;

public class TimeBlockDto {
	private int timeblockSeq;
    private String timeblockTime;

    // Parameterized constructor
    public TimeBlockDto(int timeblockSeq, String timeblockTime) {
        this.timeblockSeq = timeblockSeq;
        this.timeblockTime = timeblockTime;
    }

    // Getter and Setter methods for each field
    public int getTimeblockSeq() {
        return timeblockSeq;
    }

    public void setTimeblockSeq(int timeblockSeq) {
        this.timeblockSeq = timeblockSeq;
    }

    public String getTimeblockTime() {
        return timeblockTime;
    }

    public void setTimeblockTime(String timeblockTime) {
        this.timeblockTime = timeblockTime;
    }
}
