package service;

import java.util.List;

import exception.DMLException;
import exception.SearchWrongException;
import model.dao.HospitalDao;
import model.dao.HospitalDaoImpl;
import model.dto.DeptDto;
import model.dto.DiagnosisDto;
import model.dto.DoctorDto;
import model.dto.PatientDto;
import model.dto.ReservationDto;

public class HospitalServiceImpl implements HospitalService {

	private static HospitalServiceImpl instance = new HospitalServiceImpl();
	private HospitalDao hospitalDao = HospitalDaoImpl.getInstance();
	private HospitalServiceImpl() {}
	public static HospitalServiceImpl getInstance() {
		return instance;
	}
	
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
		PatientDto pDto = hospitalDao.diagSelectAllByPatient(patientDto);
		if(pDto.getDiagnosisDtoList() == null)
			throw new SearchWrongException("환자 본인의 진료내역이 존재하지 않습니다.");
		return pDto.getDiagnosisDtoList();
	}

}