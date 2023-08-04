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
	 * 환자 예약하기
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
	
	public static List<DoctorDto> docList(int deptSeq){
		List<DoctorDto> list = new ArrayList<>();
		try {
			list = hospitalService.doctorSelectAllByDept(deptSeq);
			
		} catch (SearchWrongException e) {
			FailView.errorMessage(e.getMessage());
		}
		
		return list;
	}
	
	public static int[][] timeList(int doctorSeq){
		int[][] timeArr = null;
		try {
			timeArr = hospitalService.timeSelectAllByDoctor(doctorSeq); 

		}catch(SearchWrongException e) {
			FailView.errorMessage(e.getMessage());
		}
		
		return timeArr;
	}
	
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
	 */
	public static void reservationCancel(int blockSeq) {
		try {
			hospitalService.cancelReservableReserveNumber(blockSeq);
			SuccessView.messagePrint(blockSeq+"번 예약을 취소하였습니다.");
		} catch (DMLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 환자 예약 조회
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
