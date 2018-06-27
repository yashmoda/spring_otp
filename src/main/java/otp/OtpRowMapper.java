package otp;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class OtpRowMapper implements RowMapper<otp> {
    @Override
    public otp mapRow(ResultSet row, int rowNum) throws SQLException
    {
        otp obj = new otp();
        obj.setPhone(row.getString("phone"));
        obj.setOtp(row.getInt("otp"));
        return obj;
    }
}
