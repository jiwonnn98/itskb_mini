package controller;

import java.util.List;

import model.dao.HospitalDao;
import model.dao.HospitalDaoImpl;
import model.dto.DiagnosisDto;
import model.dto.ReservationDto;
import model.dto.PatientDto;
import service.HospitalService;
import service.HospitalServiceImpl;
import session.SessionSet;
import view.FailView;
import view.SuccessView;

import java.util.Set;

import exception.DMLException;
import exception.SearchWrongException;
import session.Session;

public class HospitalController {
	private static HospitalService hospitalService = HospitalServiceImpl.getInstance();
	
	/**
	 * 환자 진료 내역 조회
	 */
	public static void diagnosisList() {
		PatientDto patientDto = bringPatientSeq();
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
	public static void reservationList() {
		PatientDto patientDto = bringPatientSeq();
		try {
			List<ReservationDto> list = hospitalService.reserveSelectAllByPatient(patientDto);
			SuccessView.reservationList(list);
		} catch (SearchWrongException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 환자 번호 가져오기
	 */
	public static PatientDto bringPatientSeq() {
		SessionSet sessionSet = SessionSet.getInstance();
		Set<Session> set = sessionSet.getSet();
		Session session = null;
		for(Session s : set) {
			session = s;
		}
		int patientSeq = session.getPatientSeq();
		PatientDto patientDto = new PatientDto(patientSeq);
		return patientDto;
	}
}
