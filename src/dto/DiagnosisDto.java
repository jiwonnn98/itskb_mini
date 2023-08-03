package dto;

public class DiagnosisDto {
	private int diagnosisSeq;
    private String diagnosisPrescription;
    private String diagnosisDate;
    private int patientSeq;
    private int doctorSeq;

    // Default constructor
    public DiagnosisDto() {
    }

    // Parameterized constructor
    public DiagnosisDto(int diagnosisSeq, String diagnosisPrescription, String diagnosisDate, int patientSeq, int doctorSeq) {
        this.diagnosisSeq = diagnosisSeq;
        this.diagnosisPrescription = diagnosisPrescription;
        this.diagnosisDate = diagnosisDate;
        this.patientSeq = patientSeq;
        this.doctorSeq = doctorSeq;
    }

    // Getter and Setter methods for each field
    public int getDiagnosisSeq() {
        return diagnosisSeq;
    }

    public void setDiagnosisSeq(int diagnosisSeq) {
        this.diagnosisSeq = diagnosisSeq;
    }

    public String getDiagnosisPrescription() {
        return diagnosisPrescription;
    }

    public void setDiagnosisPrescription(String diagnosisPrescription) {
        this.diagnosisPrescription = diagnosisPrescription;
    }

    public String getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(String diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public int getPatientSeq() {
        return patientSeq;
    }

    public void setPatientSeq(int patientSeq) {
        this.patientSeq = patientSeq;
    }

    public int getDoctorSeq() {
        return doctorSeq;
    }

    public void setDoctorSeq(int doctorSeq) {
        this.doctorSeq = doctorSeq;
    }
}
