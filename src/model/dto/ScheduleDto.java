package model.dto;

public class ScheduleDto {
	private int scheduleSeq;
    private int doctorSeq;
    private int startBlockSeq;
    private int endBlockSeq;
    private String scheduleDate;

    // Parameterized constructor
    public ScheduleDto(int scheduleSeq, int doctorSeq, int startBlockSeq, int endBlockSeq, String scheduleDate) {
        this.scheduleSeq = scheduleSeq;
        this.doctorSeq = doctorSeq;
        this.startBlockSeq = startBlockSeq;
        this.endBlockSeq = endBlockSeq;
        this.scheduleDate = scheduleDate;
    }

    // Getter and Setter methods for each field
    public int getScheduleSeq() {
        return scheduleSeq;
    }

    public void setScheduleSeq(int scheduleSeq) {
        this.scheduleSeq = scheduleSeq;
    }

    public int getDoctorSeq() {
        return doctorSeq;
    }

    public void setDoctorSeq(int doctorSeq) {
        this.doctorSeq = doctorSeq;
    }

    public int getStartBlockSeq() {
        return startBlockSeq;
    }

    public void setStartBlockSeq(int startBlockSeq) {
        this.startBlockSeq = startBlockSeq;
    }

    public int getEndBlockSeq() {
        return endBlockSeq;
    }

    public void setEndBlockSeq(int endBlockSeq) {
        this.endBlockSeq = endBlockSeq;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ScheduleDto [scheduleSeq=");
		builder.append(scheduleSeq);
		builder.append(", doctorSeq=");
		builder.append(doctorSeq);
		builder.append(", startBlockSeq=");
		builder.append(startBlockSeq);
		builder.append(", endBlockSeq=");
		builder.append(endBlockSeq);
		builder.append(", scheduleDate=");
		builder.append(scheduleDate);
		builder.append("]");
		return builder.toString();
	}
}
