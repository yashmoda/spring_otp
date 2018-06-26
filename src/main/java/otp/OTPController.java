package otp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class OTPController {
    @Autowired
    private OTPDAO otpdao;

    @PostMapping(path = "/send_otp")
    public ResponseEntity<Void> addPhone(@RequestBody otp otp)
    {
        otpdao.insert(otp);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/verify_otp")
    public ResponseEntity<Void> verifyOtp(@RequestBody otp otp)
    {
        int db_otp = otpdao.get_otp(otp);
        int en_otp = otp.getOtp();
        if (db_otp == en_otp)
        {
            System.out.println("Otp correct.");
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        else
        {
            System.out.println("Incorrect otp.");
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(path = "/all_phone")
    public ResponseEntity<List<otp>> getAllPhone()
    {
        List<otp> otp = otpdao.getAllPhone();
        return new ResponseEntity<List<otp>>(otp, HttpStatus.OK);
    }
}

