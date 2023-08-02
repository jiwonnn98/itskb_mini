package dto;

public class PatientDto {
	private String patientName;
	private int patientSeq;
	private String patientSSN;
	private String patientAddr;
	private String patientPhone;
	
	public PatientDto() {};
	
	
	
	
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
	
	@Override
	public String toString() {
		return "PatientDto [patientName=" + patientName + ", patientSeq=" + patientSeq + ", patientSSN=" + patientSSN
				+ ", patientAddr=" + patientAddr + ", patientPhone=" + patientPhone + "]";
	}
	
	
}
