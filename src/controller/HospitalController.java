package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import exception.DMLException;
import exception.SearchWrongException;
import model.dao.HospitalDao;
import model.dao.HospitalDaoImpl;
import model.dto.DiagnosisDto;
import model.dto.DeptDto;
import model.dto.DoctorDto;
import model.dto.ReservationDto;
import model.dto.PatientDto;
import service.HospitalService;
import service.HospitalServiceImpl;
import session.Session;
import session.SessionSet;
import view.FailView;
import view.SuccessView;

public class HospitalController {
	
	private static HospitalService hospitalService = HospitalServiceImpl.getInstance();
	
	
	/**
	 * 진료과 조회
	 * @return List<DeptDto> 진료과 정보들이 담긴 리스트
	 */
	public static List<DeptDto> deptList() {
		List<DeptDto> list = new ArrayList<>();
		try {
			list = hospitalService.deptSelectAll();
			
		} catch (SearchWrongException e) {
			FailView.errorMessage(e.getMessage());
		}
		
		return list;
		
	}
	
	/**
	 * 진료과 의사 봔환
	 * @param deptSeq 진료과 식별번호
	 * @return List<DoctorDto> 의사 정보들이 담긴 리스트
	 */
	public static List<DoctorDto> docList(int deptSeq){
		List<DoctorDto> list = new ArrayList<>();
		try {
			list = hospitalService.doctorSelectAllByDept(deptSeq);
			
		} catch (SearchWrongException e) {
			FailView.errorMessage(e.getMessage());
		}
		
		return list;
	}
	
	/**
	 * 의사의 가능한 시간 반환
	 * @param doctorSeq 의사 식별 번호
	 * @return 행: 오늘, 내일, 모래 // 열: 0번-근무식별 번호, 나머지 시간 블록 번호
	 */
	public static int[][] timeList(int doctorSeq){
		int[][] timeArr = null;
		try {
			timeArr = hospitalService.timeSelectAllByDoctor(doctorSeq); 

		}catch(SearchWrongException e) {
			FailView.errorMessage(e.getMessage());
		}
		
		return timeArr;
	}
	
	/**
	 * 환자 예약 생성
	 * @param day 예약일
	 * @param time 예약 시간 번호
	 * @param patient 환자 식별 번호
	 * @param schedule 근무 식별 번호
	 */
	public static void reservation(int day, int time, int patient, int schedule) {
		ReservationDto reserv = new ReservationDto(0, LocalDate.now().plusDays(day).toString(), patient, schedule, time);
		try {
			hospitalService.insertReservation(reserv);
		}catch(DMLException e ) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 환자 진료 내역 조회
	 * @param patientSeq 환자 식별 번호
	 */
	public static void diagnosisList(int patientSeq) {
		PatientDto patientDto = new PatientDto(patientSeq);
		try {
			List<DiagnosisDto> list = hospitalService.diagSelectAllByPatient(patientDto);
			SuccessView.diagnosisList(list);			
		} catch (SearchWrongException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 환자 예약 취소
	 * @param blockSeq 예약식별번호
	 */
	public static void reservationCancel(int reservSeq) {
		try {
			hospitalService.cancelReservableReserveNumber(reservSeq);
			SuccessView.messagePrint(reservSeq+"번 예약을 취소하였습니다.");
		} catch (DMLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 환자 예약 조회
	 * @param patientSeq 환자 식별 번호
	 */
	public static void reservationList(int patientSeq) {
		PatientDto patientDto = new PatientDto(patientSeq);
		try {
			List<ReservationDto> list = hospitalService.reserveSelectAllByPatient(patientDto);
			SuccessView.reservationList(list);
		} catch (SearchWrongException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 환자 예약 변경
	 * @param reservDto 예약 정보
	 */
	public static void reservationModifyDate(ReservationDto reservDto) {
		try {
			hospitalService.updateReservation(reservDto);
			SuccessView.messagePrint("변경성공하였습니다.");
		} catch (DMLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	
	}
}
