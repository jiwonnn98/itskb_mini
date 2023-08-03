package model.dto;

import java.util.List;

public class PatientDto {
    private String patientName;
    private int patientSeq;
    private String patientSSN;
    private String patientAddr;
    private String patientPhone;

    List<ReservationDto> reservationDtoList;
    List<DiagnosisDto> diagnosisDtoList;

    /**
     * 환자 번호로 예약, 진료 내역 조회 시 사용
     *
     * @param patientSeq
     */
    public PatientDto(int patientSeq) {
        this.patientSeq = patientSeq;
    }

    /**
     * 환자 로그인 시 사용
     *
     * @param patientName
     * @param patientSSN
     */
    public PatientDto(String patientName, String patientSSN) {
        this.patientName = patientName;
        this.patientSSN = patientSSN;
        this.patientSeq = -1; // setter 오류를 확인하기 위한 초기화
    }

    /**
     * 새로운 환자 등록 시 사용
     *
     * @param patientName
     * @param patientSSN
     * @param patientAddr
     * @param patientPhone
     */
    public PatientDto(String patientName, String patientSSN, String patientAddr, String patientPhone) {
        this(patientName, patientSSN);
        this.patientAddr = patientAddr;
        this.patientPhone = patientPhone;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }


    public String getPatientSSN() {
        return patientSSN;
    }

    public void setPatientSSN(String patientSSN) {
        this.patientSSN = patientSSN;
    }

    public String getPatientAddr() {
        return patientAddr;
    }

    public void setPatientAddr(String patientAddr) {
        this.patientAddr = patientAddr;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public int getPatientSeq() {
        return patientSeq;
    }

    public void setPatientSeq(int patientSeq) {
        this.patientSeq = patientSeq;
    }

    public List<ReservationDto> getReservationDtoList() {
        return reservationDtoList;
    }

    public void setReservationDtoList(List<ReservationDto> reservationDtoList) {
        this.reservationDtoList = reservationDtoList;
    }

    public List<DiagnosisDto> getDiagnosisDtoList() {
        return diagnosisDtoList;
    }

    public void setDiagnosisDtoList(List<DiagnosisDto> diagnosisDtoList) {
        this.diagnosisDtoList = diagnosisDtoList;
    }

    @Override
    public String toString() {
        return "PatientDto [patientName=" + patientName + ", patientSeq=" + patientSeq + ", patientSSN=" + patientSSN
                + ", patientAddr=" + patientAddr + ", patientPhone=" + patientPhone + "]";
    }


}
