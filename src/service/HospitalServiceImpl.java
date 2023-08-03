package service;

import java.util.List;

import exception.DMLException;
import exception.SearchWrongException;
import model.dto.DeptDto;
import model.dto.DiagnosisDto;
import model.dto.DoctorDto;
import model.dto.PatientDto;
import model.dto.ReservationDto;

public class HospitalServiceImpl implements HospitalService {

	@Override
	public List<DeptDto> deptSelectAll() throws SearchWrongException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DoctorDto> doctorSelectAllByDept(int deptCode) throws SearchWrongException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] timeSelectAllByDoctor(int doctorSeq) throws SearchWrongException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertReservation(ReservationDto reservationDto) throws DMLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ReservationDto> reserveSelectAllByPatient() throws SearchWrongException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelReservableReserveNumber(int reservationSeq) throws DMLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateReservation(ReservationDto reservationDto) throws DMLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DiagnosisDto> diagSelectAllByPatient(PatientDto patientDto) throws SearchWrongException {
		// TODO Auto-generated method stub
		return null;
	}

}
