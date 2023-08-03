package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
	public PatientDto reserveSelectAllByPatient(PatientDto patientDto) throws SearchWrongException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from reservation where patient_seq = ?";
		List<ReservationDto> list = new ArrayList<>();
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, patientDto.getPatientSeq());
			
			rs = ps.executeQuery();
			while(rs.next()) {
				int reservationSeq = rs.getInt("reservation_seq");
			    String reservationDate = rs.getString("reservation_date");
			    int patientSeq = rs.getInt("patient_seq");
			    int scheduleSeq = rs.getInt("schedule_seq");
			    int reservationBlockSeq = rs.getInt("reservation_block_seq");
			    list.add(new ReservationDto(reservationSeq, reservationDate, patientSeq, scheduleSeq, reservationBlockSeq));
			}
			patientDto.setReservationDtoList(list);
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new SearchWrongException("예약 내역 검색 중 오류가 발생하였습니다.\\n다음에 이용해 주세요 ^^;;");
		} finally {
			DBManager.releaseConnection(con, ps, rs);
		}
		return patientDto;
	}

	@Override
	public int cancleReservByReservNumber(int reservationSeq) throws DMLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from reservation where reservation_seq = ?";
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, reservationSeq);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				int today=LocalDate.now().getDayOfMonth();
				if(Integer.parseInt(rs.getString("reservation_date").substring(8,10))- today == 0) {
					throw new DMLException("당일 예약은 취소할 수 없습니다.");
				}
			}
		} catch (SQLException e) {
			throw new DMLException("취소 도중 오류가 발생했습니다!!");
		}finally {
			DBManager.releaseConnection(con, ps, rs);
		}
		int result = 0;
		sql = "delete from reservation where reservation_seq = ?";
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, reservationSeq);
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new DMLException("취소 도중 오류가 발생했습니다.");
		}
		return result;
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
