package service;


import java.util.ArrayList;
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
import model.dto.ScheduleDto;

public class HospitalServiceImpl implements HospitalService {
	
	private static HospitalService instance = new HospitalServiceImpl();
	private HospitalDao hospitalDao = HospitalDaoImpl.getInstance();
	private HospitalServiceImpl() {}
	public static HospitalService getInstance() {
		return instance;
	}
	
	@Override
	public List<DeptDto> deptSelectAll() throws SearchWrongException {
		List<DeptDto> list = hospitalDao.deptSelectAll();
		if(list.isEmpty()) {
			throw new SearchWrongException("선택할 수 있는 진료과가 존재하지 않습니다.");
		}
		
		return list;

	}

	@Override
	public List<DoctorDto> doctorSelectAllByDept(int deptCode) throws SearchWrongException {
		List<DoctorDto> list = hospitalDao.doctorSelectAllByDept(deptCode);
		if(list.isEmpty()) {
			throw new SearchWrongException("조회가능한 의사가 없습니다.");
		}
		return list;

	}

	@Override
	public int[][] timeSelectAllByDoctor(int doctorSeq) throws SearchWrongException {

		DoctorDto doc = hospitalDao.timeSelectAllByDoctor(doctorSeq);
		int[][] arr = new int[3][18];
		List<Integer>  list;
		for(int i=0;i<3;i++) {
			for(int j=0;j<18;j++) {
				arr[i][j] = -1;
			}
		}
		if(doc.getScheduleDtoList().isEmpty()) {
			throw new SearchWrongException("스케줄이 없는 의사입니다");
		}
		for(ScheduleDto s : doc.getScheduleDtoList()) {
			if(s.dayGap()<3 && s.dayGap()>=0) {
				for(int i=s.getStartBlockSeq();i<=s.getEndBlockSeq();i++) {
					arr[s.dayGap()][i] = i;
					
				}
				list = hospitalDao.selectReservationByDoctor(s.getScheduleSeq());
				
				for(int i : list) {
					arr[s.dayGap()][i] = -1;
				}
			
			}
		}
		
		
		return arr;

	}

	@Override
	public void insertReservation(ReservationDto reservationDto) throws DMLException {
		int result = hospitalDao.insertReservation(reservationDto);
		System.out.println(reservationDto.getPatientSeq());
		if(result==0) throw new DMLException("예약에 실패하셨습니다");
	}

	@Override
	public List<ReservationDto> reserveSelectAllByPatient(PatientDto patientDto) throws SearchWrongException {
		PatientDto pDto = hospitalDao.reserveSelectAllByPatient(patientDto);
		if(pDto.getReservationDtoList() == null)
			throw new SearchWrongException("환자 본인의 예약 내역이 존재하지 않습니다.");
		return pDto.getReservationDtoList();


	}

	@Override
	public void cancelReservableReserveNumber(int reservationSeq) throws DMLException {
		int result = hospitalDao.cancleReservByReservNumber(reservationSeq);
		if(result == 0) {
			throw new DMLException("취소된 예약이 없습니다.");
		}
	}

	@Override
	public void updateReservation(ReservationDto reservationDto) throws DMLException {
		int result = hospitalDao.updateReservByReservNumber(reservationDto);
		if(result == 0) {
			throw new DMLException("변경된 예약이 없습니다.");
		}
	}

	@Override
	public List<DiagnosisDto> diagSelectAllByPatient(PatientDto patientDto) throws SearchWrongException {
		PatientDto pDto = hospitalDao.diagSelectAllByPatient(patientDto);
		if(pDto.getDiagnosisDtoList() == null)
			throw new SearchWrongException("환자 본인의 진료내역이 존재하지 않습니다.");
		return pDto.getDiagnosisDtoList();
	}
	
	
	
}


