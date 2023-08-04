package model.dto;

import java.util.List;

public class DoctorDto {
    private int doctorSeq;
    private String doctorSSN;
    private String doctorName;
    private int deptCode;

    List<ScheduleDto> scheduleDtoList;
    
    public DoctorDto() {}

    // Parameterized constructor
    public DoctorDto(int doctorSeq, String doctorSSN, String doctorName, int deptCode) {
        this.doctorSeq = doctorSeq;
        this.doctorSSN = doctorSSN;
        this.doctorName = doctorName;
        this.deptCode = deptCode;
    }

    // Getter and Setter methods for each field
    public int getDoctorSeq() {
        return doctorSeq;
    }

    public void setDoctorSeq(int doctorSeq) {
        this.doctorSeq = doctorSeq;
    }

    public String getDoctorSSN() {
        return doctorSSN;
    }

    public void setDoctorSSN(String doctorSSN) {
        this.doctorSSN = doctorSSN;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(int deptCode) {
        this.deptCode = deptCode;
    }

    public List<ScheduleDto> getScheduleDtoList() {
        return scheduleDtoList;
    }

    public void setScheduleDtoList(List<ScheduleDto> scheduleDtoList) {
        this.scheduleDtoList = scheduleDtoList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DoctorDto [doctorSeq=");
        builder.append(doctorSeq);
        builder.append(", doctorSSN=");
        builder.append(doctorSSN);
        builder.append(", doctorName=");
        builder.append(doctorName);
        builder.append(", deptCode=");
        builder.append(deptCode);
        builder.append("]");
        return builder.toString();
    }
}
