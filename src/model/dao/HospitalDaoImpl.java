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
import model.dto.DoctorDto;
import model.dto.PatientDto;
import model.dto.ReservationDto;
import model.dto.ScheduleDto;
import model.dto.DiagnosisDto;

public class HospitalDaoImpl implements HospitalDao {
	
	private static HospitalDao instance = new HospitalDaoImpl();
	private HospitalDaoImpl() {}
	public static HospitalDao getInstance() {
		return instance;
	}

	@Override
	public List<DeptDto> deptSelectAll() throws SearchWrongException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "select * from department";
		ResultSet rs = null;
		
		List<DeptDto> list = new ArrayList<>();
		
		try {
			con = DBManager.getConnection();
			ps= con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				DeptDto dept = new DeptDto(rs.getInt("dept_code"), rs.getString("dept_name"), rs.getString("dept_loc"));
				list.add(dept);
			}
			
		}catch(SQLException e) {
			throw new SearchWrongException("진료과를 찾는 도중 오류가 발생했습니다.");
			
		}finally {
			DBManager.releaseConnection(con, ps, rs);
		}	
		return list;
	}

	@Override
	public List<DoctorDto> doctorSelectAllByDept(int deptcode) throws SearchWrongException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "select * from doctor where dept_code = ?";
		ResultSet rs = null;
		List<DoctorDto> list = new ArrayList<>();
		try {
			con = DBManager.getConnection();
			ps= con.prepareStatement(sql);
			ps.setInt(1, deptcode);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				DoctorDto doc = new DoctorDto(rs.getInt("doctor_seq"), rs.getString("doctor_ssn"), rs.getString("doctor_name"), deptcode);
				list.add(doc);
			}
			
		}catch(SQLException e) {
			throw new SearchWrongException("진료과를 찾는 도중 오류가 발생했습니다.");
			
		}finally {
			DBManager.releaseConnection(con, ps, rs);
		}	
		
		
		return list;
	}

	@Override
	public DoctorDto timeSelectAllByDoctor(int doctorSeq) throws SearchWrongException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "select * from schedule where doctor_seq = ?";
		ResultSet rs = null;
		DoctorDto doc = new DoctorDto();
		List<ScheduleDto> scheduleList = new ArrayList<>();
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, doctorSeq);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				ScheduleDto s = new ScheduleDto(rs.getInt("SCHEDULE_SEQ"), doctorSeq, rs.getInt("start_block_seq"), rs.getInt("end_block_seq"), rs.getString("schedule_Date"));
				scheduleList.add(s);
			}
			doc.setScheduleDtoList(scheduleList);
			
		}catch(SQLException e) {
			throw new SearchWrongException(e.getMessage());
		}finally {
			DBManager.releaseConnection(con, ps, rs);
		}
		
		return doc;
	}
	
	@Override
	public int insertReservation(ReservationDto reserv) throws DMLException{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into reservation values(?, to_date(?, 'YYYY-MM-DD'), ?, ?, ?)";
		int result = 0;
		System.out.println(reserv.getPatientSeq());
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, reserv.getReservationSeq());
			ps.setString(2, reserv.getReservationDate());
			ps.setInt(3, reserv.getPatientSeq());
			ps.setInt(4, reserv.getScheduleSeq());
			ps.setInt(5, reserv.getReservationBlockSeq());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DMLException("예약 과정에서 문제가 생겼습니다");
		}finally {
			DBManager.releaseConnection(con, ps);
		}
		
		
		
		return result;
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
			throw new SearchWrongException("예약 내역 검색 중 오류가 발생하였습니다.\\n다음에 이용해 주세요 ^^;;");
		} finally {
			DBManager.releaseConnection(con, ps, rs);
		}
		return patientDto;
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
	
	public List<Integer> selectReservationByDoctor(int scheduleSeq) throws SearchWrongException {
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "select reservation_block_seq from reservation where schedule_seq = ?";
		ResultSet rs =null;
		List<Integer> list = new ArrayList<>();
		try {
			
			
			con = DBManager.getConnection();
			ps =con.prepareStatement(sql);
			ps.setInt(1, scheduleSeq);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int time = rs.getInt("reservation_block_seq");
				list.add(time);
			}
			
		}catch(SQLException e) {
			throw new SearchWrongException("의사 에약 잘못 찾았음");
		}finally {
			DBManager.releaseConnection(con, ps, rs);
		}
		
		return list;
	}
	
	

}
