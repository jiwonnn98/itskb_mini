package controller;

import exception.DMLException;
import exception.SessionException;
import model.dto.PatientDto;
import service.PatientService;
import service.PatientServiceImpl;
import session.Session;
import session.SessionSet;
import view.FailView;
import view.MainView;

public class PatientController {
    static PatientService patientService = new PatientServiceImpl();

    /**
     * 로그인
     */
    public static void login(String patientName, String patientSSN) {
        try {
            PatientDto patientDto = new PatientDto(patientName, patientSSN);
            Session session = patientService.login(patientDto);
            MainView.patientMenu(session.getPatientSeq());
        } catch (Exception e) {
            FailView.errorMessage(e.getMessage());
        }
    }

    /**
     * 환자 등록
     */
    public static void insertPatient(String patientName, String patientSSN, String patientAddr, String patientPhone) {
        try {
            PatientDto patientDto = new PatientDto(patientName, patientSSN, patientAddr, patientPhone);
            patientService.insertPatient(patientDto);
        } catch (Exception e) {
            FailView.errorMessage(e.getMessage());
        }
    }

}
