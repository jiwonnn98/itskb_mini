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
            throw new SessionException("다시 로그인 시도하십쇼.");
        }finally {
            DBManager.releaseConnection(con, ps, rs);
        }
        return patientDto;

    }

    @Override
    public void insertPatient(PatientDto patientDto) throws DMLException {

    }
}
