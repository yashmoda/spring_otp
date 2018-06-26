package otp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class OTPDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean check(String phone)
    {
        String query = "SELECT COUNT(*) FROM otp WHERE phone = ?";
        int count = jdbcTemplate.queryForObject(query, Integer.class, phone);
        return count == 0;
    }

    public void insert(otp otp)
    {
        boolean flag = check(otp.getPhone());
        int n = otp.generateOtp();
        if (flag)
        {
            String query = "INSERT INTO otp (phone, otp) VALUES (?, ?)";
            jdbcTemplate.update(query, otp.getPhone(), n);
        }
        else
        {
            String query = "UPDATE otp set otp = ? where phone = ?";
            jdbcTemplate.update(query, n, otp.getPhone());
        }
        SMS sms_obj = new SMS();
        boolean flag_sms = sms_obj.send_sms(otp.getPhone(), n);
        if (flag_sms)
        {
            System.out.println("OTP has been sent");
        }
        else
        {
            System.out.println("OTP could not be sent.");
        }
    }

    public int get_otp(otp otp)
    {
        String query = "SELECT otp from otp where phone = ?";
        int db_otp = jdbcTemplate.queryForObject(query, Integer.class, otp.getPhone());
        return db_otp;
    }
}
