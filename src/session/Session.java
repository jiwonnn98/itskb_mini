package session;

public class Session {
    private int patientSeq;
    private String patientName;

    public Session(int patientSeq, String patientName) {
        this.patientSeq = patientSeq;
        this.patientName = patientName;
    }

    public int getPatientSeq() {
        return patientSeq;
    }

    public void setPatientSeq(int patientSeq) {
        this.patientSeq = patientSeq;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Seesion{");
        sb.append("patientSeq=").append(patientSeq);
        sb.append(", patientName='").append(patientName).append('\'');
        sb.append('}');
        return sb.toString();
    }


    @Override
    public int hashCode() {
        return String.valueOf(patientSeq).hashCode(); // hashCode 함수를 사용하기 위해 형변환 (int to String)
    }

    @Override
    public boolean equals(Object obj) {
        Session other = (Session) obj;
        if (patientSeq == other.patientSeq) {
            return true;
        } else {
            return false;
        }
    }


}
