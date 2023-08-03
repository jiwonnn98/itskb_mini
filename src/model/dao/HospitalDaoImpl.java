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
			ps.setInt(5, reserv.getTimeblockSeq());
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
		// TODO Auto-generated method stub
		return null;
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
