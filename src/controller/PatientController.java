package controller;

import exception.DMLException;
import exception.SessionException;
import model.dto.PatientDto;
import service.PatientService;
import service.PatientServiceImpl;
import session.Session;

public class PatientController {
    static PatientService patientService = new PatientServiceImpl();

    /**
     * 로그인
     */
    public static void login(String patientName, String patientSSN ) {
        try {
            PatientDto patientDto = new PatientDto(patientName, patientSSN);
            Session session = patientService.login(patientDto);
            //MenuView.printUserMenu(patientName);

        } catch (Exception e) {
            //FailView.errorMessage(e.getMessage());
        }
    }
}
