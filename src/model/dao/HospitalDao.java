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
     * @return
     * @throws SearchWrongException
     */
    List<DeptDto> deptSelectAll() throws SearchWrongException;

    /**
     * 진료과에 해당하는 모든 의사 검색
     *
     * @return
     * @throws SearchWrongException
     */
    List<DoctorDto> doctorSelectAllByDept() throws SearchWrongException;

    /**
     * 의사에 해당하는 가능한 진료시간 검색
     *
     * @return
     * @throws SearchWrongException
     */
    DoctorDto timeSelectAllByDoctor() throws SearchWrongException;

    /**
     * 환자 자신의 예약 내역 조회
     *
     * @param patientDto
     * @return
     * @throws SearchWrongException
     */
    PatientDto reserveSelectAllByPatient(PatientDto patientDto) throws SearchWrongException;

    /**
     * 환자 자신의 예약 취소
     *
     * @param reservationDto
     * @return
     * @throws DMLException
     */
    int cancleReservByReservNumber(ReservationDto reservationDto) throws DMLException;

    /**
     * 환자 자신의 예약 변경
     *
     * @param reservationDto
     * @return
     * @throws DMLException
     */
    int updateReservByReservNumber(ReservationDto reservationDto) throws DMLException;

    /**
     * 환자 자신의 진료 내역 조회
     *
     * @param patientDto
     * @return
     * @throws SearchWrongException
     */
    PatientDto diagSelectAllByPatient(PatientDto patientDto) throws SearchWrongException;

    /**
     * 새로운 환자 추가
     *
     * @param patientDto
     * @return
     * @throws DMLException
     */
    int insertPatient(PatientDto patientDto) throws DMLException;
}
