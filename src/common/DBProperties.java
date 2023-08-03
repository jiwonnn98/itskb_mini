package common;
/**
 * DB의 설정 정보들 상수로 관리
 * */
public interface DBProperties {
	public static final String DRIVER_NAME ="oracle.jdbc.driver.OracleDriver";
	String URL="jdbc:oracle:thin:@10.3.4.119:1521:XE";
	String USER_ID="team5";
	String USER_PASS="pass";
//	String URL="jdbc:oracle:thin:@localhost:1521:XE";
//	String USER_ID="jonghan";
//	String USER_PASS="0000";
}
