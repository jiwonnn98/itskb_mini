package model.dao;

import common.DBManager;
import exception.DMLException;
import exception.SessionException;
import model.dto.PatientDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDaoImpl implements PatientDao {
    @Override
    public PatientDto login(PatientDto patientDto) throws SessionException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select patient_seq from patient where patient_name = ? and patient_ssn = ?";
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, patientDto.getPatientName());
            ps.setString(2, patientDto.getPatientSSN());

            rs = ps.executeQuery();
            if (rs.next()) {
                patientDto.setPatientSeq(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new SessionException("로그인을 다시 시도해주세요.");
        }finally {
            DBManager.releaseConnection(con, ps, rs);
        }
        return patientDto;

    }


//    insert into PATIENT (PATIENT_SEQ , PATIENT_NAME, PATIENT_SSN , PATIENT_ADDRESS, PATIENT_PHONE )
//     VALUES (PATIENT_auto_seq.nextval , ? ,? ,? ,?);

    @Override
    public void insertPatient(PatientDto patientDto) throws DMLException {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "insert into PATIENT (PATIENT_SEQ , PATIENT_NAME, PATIENT_SSN , PATIENT_ADDRESS, PATIENT_PHONE ) "
                + "VALUES (PATIENT_SEQ.nextval , ? ,? ,? ,?)";
        int result = 0;
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, patientDto.getPatientName());
            ps.setString(2, patientDto.getPatientSSN());
            ps.setString(3, patientDto.getPatientAddr());
            ps.setString(4, patientDto.getPatientPhone());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw new DMLException("회원 등록에 실패했습니다. 다시 시도하세요.");
        }finally {
            DBManager.releaseConnection(con, ps);
        }
    }
}
