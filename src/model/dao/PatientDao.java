package model.dao;

import model.dto.PatientDto;
import exception.DMLException;
import exception.SessionException;

public interface PatientDao {
    /**
     * 환자 로그인
     *
     * @param patientDto
     */
    int login(PatientDto patientDto) throws SessionException;

    /**
     * 새로운 환자 추가
     *
     * @param patientDto 입력된 환자 정보
     * @throws DMLException
     */
    void insertPatient(PatientDto patientDto) throws DMLException;
}
