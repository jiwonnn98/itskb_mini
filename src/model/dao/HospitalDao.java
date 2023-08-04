package model.dao;

import java.util.List;

import model.dto.DeptDto;
import model.dto.DoctorDto;
import model.dto.PatientDto;
import model.dto.ReservationDto;
import exception.DMLException;
import exception.SearchWrongException;

public interface HospitalDao {

    /**
     * 모든 진료과 검색
     *
     * @return List<DeptDto> 병원의 모든 진료과가 들어있는 리스트
     * @throws SearchWrongException SQL쿼리 문제 발생시
     */
    List<DeptDto> deptSelectAll() throws SearchWrongException;

    /**
     * 진료과에 해당하는 모든 의사 검색
     *
     * @param deptcode 진료과 코드
     * @return List<DoctorDto> 병원의 한 진료과에 소속된 모든 의사들의 정보 리스트.
     * @throws SearchWrongException SQL쿼리 문제 발생시
     */
    List<DoctorDto> doctorSelectAllByDept(int deptcode) throws SearchWrongException;

    /**
     * 의사에 해당하는 가능한 진료시간 검색
     *
     * @param doctorSeq 의사 번호
     * @return DoctorDto 의사의 스케줄 정보들을 모두 포함한 의사DTO
     * @throws SearchWrongException SQL쿼리 문제 발생시
     */
    DoctorDto timeSelectAllByDoctor(int doctorSeq) throws SearchWrongException;
    
    /**
     * 예약 추가
     * @param reserv 예약 정보
     * @return int 예약 성공 시 1, 실패시 0 반환
     * @throws DMLException SQL쿼리 문제 발생시
     */
    int insertReservation(ReservationDto reserv) throws DMLException;
    

    /**
     * 환자 자신의 예약 내역 조회
     *
     * @param patientDto 예약을 조회할 환자의 정보
     * @return patientDto 예약리스트가 담겨진 환자 DTO
     * @throws SearchWrongException SQL쿼리 문제 발생시
     */
    PatientDto reserveSelectAllByPatient(PatientDto patientDto) throws SearchWrongException;

    /**
     * 환자 자신의 예약 취소
     *
     * @param reservationSeq 취소하고자 하는 예약 번호
     * @return int 1일 시 성공 0일시 취소한 예약 정보 없음
     * @throws DMLException SQL쿼리 문제 발생시
     */
    int cancleReservByReservNumber(int reservationSeq) throws DMLException;

    /**
     * 환자 자신의 예약 변경
     *
     * @param reservationDto 변경할 예약 정보
     * @return int 1일 시 성공 0일시 취소한 예약 정보 없음
     * @throws DMLException SQL 쿼리 문제 발생시
     */
    int updateReservByReservNumber(ReservationDto reservationDto) throws DMLException;

    /**
     * 환자 자신의 진료 내역 조회
     *
     * @param patientDto 진료내역을 조회할 환자의 정보
     * @return PatientDto 진료내역이 들어있는 환자의 정보 반환
     * @throws SearchWrongException SQL쿼리 문제 발생시
     */
    PatientDto diagSelectAllByPatient(PatientDto patientDto) throws SearchWrongException;

    /**
     * 새로운 환자 추가
     *
     * @param patientDto 추가할 환자의 정보
     * @return int 1일 시 성공 0일시 취소한 예약 정보 없음
     * @throws DMLException
     */
    int insertPatient(PatientDto patientDto) throws DMLException;
    
    /**
     * 의사 예약 현황 검색
     * @param scheduleSeq 해당 날짜의 의사 근무시간을 조회하기 위한 근무식별번호
     * @return List<Integer> -1일 시 근무 없음, 그 외에는 해당 시간에 근무
     * @throws SearchWrongException SQL쿼리 문제 발생시
     */
    public List<Integer> selectReservationByDoctor(int scheduleSeq) throws SearchWrongException;
}
