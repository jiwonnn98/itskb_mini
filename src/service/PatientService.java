package service;

import model.dto.PatientDto;
import exception.DMLException;
import exception.SessionException;
import session.Session;

public interface PatientService {

    /**
     * 환자 로그인
     *
     * @param patientDto 환자 정보
     * @throws SessionException
     */
    Session login(PatientDto patientDto) throws SessionException;

    /**
     * 새로운 환자 추가
     *
     * @param patient 새로운 환자 정보
     * @throws DMLException
     */
    void insertPatient(PatientDto patient) throws DMLException;
}
