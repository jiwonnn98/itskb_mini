package service;

import exception.DMLException;
import exception.SessionException;
import model.dao.PatientDao;
import model.dao.PatientDaoImpl;
import model.dto.PatientDto;
import session.Session;
import session.SessionSet;

public class PatientServiceImpl  implements PatientService{
    PatientDao patientDao = new PatientDaoImpl();
    @Override
    public Session login(PatientDto patientDto ) throws SessionException {
        PatientDto result = patientDao.login(patientDto);
        if (result == null) {
            throw new SessionException("이름과 주민번호를 확인해주세요."); // result 가 비어있다.
        }
        if (result.getPatientSeq() < 0) {
            throw new SessionException("로그인에 실패했습니다. \n잠시 후 다시 시도해주세요."); // setPatientSeq()가 되지 않았다.
        }
        //로그인 정보 저장
        Session session = new Session(result.getPatientSeq(), result.getPatientName());
        SessionSet sessionSet = SessionSet.getInstance();
        sessionSet.add(session);
        
        return session;
    }

    @Override
    public void insertPatient(PatientDto patient) throws DMLException {
        patientDao.insertPatient(patient);
    }
}
