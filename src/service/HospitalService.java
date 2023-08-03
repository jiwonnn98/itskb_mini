package service;

import java.util.List;

import dto.DeptDto;
import dto.DiagnosisDto;
import dto.DoctorDto;
import dto.PatientDto;
import dto.ReservationDto;
import exception.DMLException;
import exception.SearchWrongException;

public interface HospitalService {

	/**
	 * 모든 진료과 검색
	 * *
	 */
	List<DeptDto> deptSelectAll() throws SearchWrongException;
	
	
	/**
	 * 진료과에 해당하는 모든 의사 검색
	 */
	List<DoctorDto> doctorSelectAllByDept() throws SearchWrongException;
	
	/**
	 *  의사에 해당하는 가능한 진료시간 검색
	 */
	List<String> timeSelectAllByDoctor() throws SearchWrongException;
	
	/**
	 * 환자 자신의 예약 내역 조회
	 */
	List<ReservationDto> reservSelectAllByPatient(/*   .////////todo   */) throws SearchWrongException;
	
	/**
	 * 환자 자신의 예약 취소	
	 *
	 */
	int cancleReservByReservNumber(/*   .////////todo   */) throws DMLException;
	
	/**
	 * 환자 자신의 예약 변경
	 * @return
	 * @throws DMLException
	 */
	int updateReservByReservNumber(/*todo*/) throws DMLException;
	
	/**
	 * 환자 자신의 진료 내역 조회
	 * @return
	 * @throws SearchWrongException
	 */
	List<DiagnosisDto> diagSelectAllByPatient(/*   .////////todo   */) throws SearchWrongException;
	
	/**
	 * 새로운 환자 추가
	 * @param patient
	 * @return
	 * @throws DMLException
	 */
	int insertPatient(PatientDto patient) throws DMLException;
	
}
