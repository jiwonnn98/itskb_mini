package dto;

public class DeptDto {
	private int deptCode;
    private String deptName;
    private String deptLoc;

    // Default constructor
    public DeptDto() {
    }

    // Parameterized constructor
    public DeptDto(int deptCode, String deptName, String deptLoc) {
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.deptLoc = deptLoc;
    }

    // Getter and Setter methods for each field
    public int getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(int deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptLoc() {
        return deptLoc;
    }

    public void setDeptLoc(String deptLoc) {
        this.deptLoc = deptLoc;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeptDto [deptCode=");
		builder.append(deptCode);
		builder.append(", deptName=");
		builder.append(deptName);
		builder.append(", deptLoc=");
		builder.append(deptLoc);
		builder.append("]");
		return builder.toString();
	}
}