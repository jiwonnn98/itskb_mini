package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exception.DMLException;
import exception.SearchWrongException;
import model.dto.DeptDto;
import model.dto.DoctorDto;
import model.dto.ReservationDto;
import service.HospitalService;
import service.HospitalServiceImpl;
import view.FailView;

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
}
