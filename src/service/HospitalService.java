package service;

import java.util.List;

import model.dto.*;
import exception.DMLException;
import exception.SearchWrongException;

public interface HospitalService {

    /**
     * 모든 진료과 검색
     *
     * @return
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
     */
    int[][] timeSelectAllByDoctor(int doctorSeq) throws SearchWrongException;

    /**
     * 환자 자신의 예약 생성
     *
     * @param reservationDto
     * @throws DMLException
     */
    void insertReservation(ReservationDto reservationDto) throws DMLException;

    /**
     * 환자 자신의 예약 내역 조회
     *
     * @return
     * @throws SearchWrongException
     */
    List<ReservationDto> reserveSelectAllByPatient() throws SearchWrongException;

    /**
     * 환자 자신의 예약 취소
     *
     * @param reservationSeq
     */
    void cancelReservableReserveNumber(int reservationSeq) throws DMLException;

    /**
     * 환자 자신의 예약 변경
     *
     * @param reservationDto
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
