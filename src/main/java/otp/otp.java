package otp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Controller
public class otp {

    private String phone;
    private int otp;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public String getPhone() {
        return phone;
    }

    public int generateOtp() {
        Random rnd = new Random();
        otp = 100000 + rnd.nextInt(900000);
        return otp;
    }

    public int getOtp() {
        return otp;
    }
}