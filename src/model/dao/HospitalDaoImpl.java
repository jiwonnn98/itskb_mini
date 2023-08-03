package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBManager;
import exception.DMLException;
import exception.SearchWrongException;
import model.dto.DeptDto;
import model.dto.DiagnosisDto;
import model.dto.DoctorDto;
import model.dto.PatientDto;
import model.dto.ReservationDto;

public class HospitalDaoImpl implements HospitalDao {

	private static HospitalDao instance = new HospitalDaoImpl();
	private HospitalDaoImpl() {};
	public static HospitalDao getInstance() {
		return instance;
	}
	
	@Override
	public List<DeptDto> deptSelectAll() throws SearchWrongException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DoctorDto> doctorSelectAllByDept() throws SearchWrongException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoctorDto timeSelectAllByDoctor() throws SearchWrongException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientDto reservSelectAllByPatient(PatientDto patientDto) throws SearchWrongException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int cancleReservByReservNumber(ReservationDto reservationDto) throws DMLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReservByReservNumber(ReservationDto reservationDto) throws DMLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PatientDto diagSelectAllByPatient(PatientDto patientDto) throws SearchWrongException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from diagnosis where patient_seq = ?";
		List<DiagnosisDto> list = new ArrayList<>();
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, patientDto.getPatientSeq());
			
			rs = ps.executeQuery();
			while(rs.next()) {
				int dignosisSeq = rs.getInt("diagnosis_seq");
				String diagnosisPrescription = rs.getString("diagnosis_prescription");
				String diagnosisDate = rs.getString("diagnosis_date");
				int patientSeq = rs.getInt("patient_seq");
				int doctorSeq = rs.getInt("doctor_seq");
				list.add(new DiagnosisDto(dignosisSeq, diagnosisPrescription, diagnosisDate, patientSeq, doctorSeq));
			}
			patientDto.setDiagnosisDtoList(list);
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new SearchWrongException("진료 내역 검색 중 오류가 발생하였습니다.\n다음에 이용해 주세요 ^^;;");
		} finally {
			DBManager.releaseConnection(con, ps, rs);
		}
		return patientDto;
	}

	@Override
	public int insertPatient(PatientDto patientDto) throws DMLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
