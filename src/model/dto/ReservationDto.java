package model.dto;

public class ReservationDto {
    private int reservationSeq;
    private String reservationDate;
    private int patientSeq;
    private int scheduleSeq;
    private int timeblockSeq;

    /**
     * 환자 예약 변경 시 사용
     *
     * @param reservationSeq
     * @param scheduleSeq
     */
    public ReservationDto(int reservationSeq, int scheduleSeq) {
        this.reservationSeq = reservationSeq;
        this.scheduleSeq = scheduleSeq;
    }

    /**
     * 환자 예약 생성 시 사용
     *
     * @param patientSeq
     * @param scheduleSeq
     * @param timeblockSeq
     */
    public ReservationDto(int patientSeq, int scheduleSeq, int timeblockSeq) {
        this(patientSeq, scheduleSeq);
        this.timeblockSeq = timeblockSeq;
    }

    /**
     * 환자 예약 내역 반환 시 사용
     *
     * @param reservationSeq
     * @param reservationDate
     * @param patientSeq
     * @param scheduleSeq
     * @param timeblockSeq
     */
    public ReservationDto(int reservationSeq, String reservationDate, int patientSeq, int scheduleSeq, int timeblockSeq) {
        this(patientSeq, scheduleSeq, timeblockSeq);
        this.reservationSeq = reservationSeq;
        this.reservationDate = reservationDate;
    }

    // Getter and Setter methods for each field
    public int getReservationSeq() {
        return reservationSeq;
    }

    public void setReservationSeq(int reservationSeq) {
        this.reservationSeq = reservationSeq;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getPatientSeq() {
        return patientSeq;
    }

    public void setPatientSeq(int patientSeq) {
        this.patientSeq = patientSeq;
    }

    public int getScheduleSeq() {
        return scheduleSeq;
    }

    public void setScheduleSeq(int scheduleSeq) {
        this.scheduleSeq = scheduleSeq;
    }

    public int getTimeblockSeq() {
        return timeblockSeq;
    }

    public void setTimeblockSeq(int timeblockSeq) {
        this.timeblockSeq = timeblockSeq;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ReservationDto [reservationSeq=");
        builder.append(reservationSeq);
        builder.append(", reservationDate=");
        builder.append(reservationDate);
        builder.append(", patientSeq=");
        builder.append(patientSeq);
        builder.append(", scheduleSeq=");
        builder.append(scheduleSeq);
        builder.append(", timeblockSeq=");
        builder.append(timeblockSeq);
        builder.append("]");
        return builder.toString();
    }
}
