package service;

import java.util.List;

import model.dto.*;
import exception.DMLException;
import exception.SearchWrongException;

public interface HospitalService {
	
    /**
     * 모든 진료과 검색
     *
     * @return DeptDto List 진료과 정보들이 담긴 리스트
     * @throws SearchWrongException
     */
    List<DeptDto> deptSelectAll() throws SearchWrongException;

    /**
     * 진료과에 해당하는 모든 의사 검색
     *
     * @param deptCode 부서 코드
     * @return 의사 리스트
     * @throws SearchWrongException
     */
    List<DoctorDto> doctorSelectAllByDept(int deptCode) throws SearchWrongException;

    /**
     * 의사에 해당하는 가능한 진료시간 검색
     *
     * @param doctorSeq 의사 식별번호
     * @return 일하지 않는 시간에는 -1, 일하는 시간에는 근무 식별번호가 배열에 들어 있다.
     * @throws SearchWrongException
     */
    int[][] timeSelectAllByDoctor(int doctorSeq) throws SearchWrongException;

    /**
     * 환자 자신의 예약 생성
     *
     * @param reservationDto 예약 정보
     * @throws DMLException
     */
    void insertReservation(ReservationDto reservationDto) throws DMLException;

    /**
     * 환자 자신의 예약 내역 조회
     *
     * @param patientDto 환자정보
     * @return 전달된 patient_seq에 해당하는 예약 정보가 담긴 ReservationDto List가 반환
     * @throws SearchWrongException
     */
    List<ReservationDto> reserveSelectAllByPatient(PatientDto patientDto) throws SearchWrongException;

    /**
     * 환자 자신의 예약 취소
     *
     * @param reservationSeq 예약 식별 번호
     * @throw DMLException
     */
    void cancelReservableReserveNumber(int reservationSeq) throws DMLException;

    /**
     * 환자 자신의 예약 변경
     *
     * @param reservationDto 예약 정보
     * @throws DMLException
     */
    void updateReservation(ReservationDto reservationDto) throws DMLException;

    /**
     * 환자 자신의 진료 내역 조회
     *
     * @param patientDto 환자 정보
     * @return 진료 내역 리스트
     * @throws SearchWrongException
     */
    List<DiagnosisDto> diagSelectAllByPatient(PatientDto patientDto) throws SearchWrongException;
}
