package otp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class otp {
    @GetMapping(path = "/send_otp")
    public String send_otp(@RequestParam String mobile)
    {

        String url;
        String message = "Thank you for registering with us.";
        url = "http://control.msg91.com/api/sendhttp.php?";
        url += "sender=YAMODA&";
        url += "route=4&";
        url += "country=91&";
        url += "mobiles=" + mobile;
        url += "&authkey=" + "";
        url += "&message=" + message;
        System.out.println(url);
        try
        {
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(url, String.class);
            System.out.println(result);
            System.out.println("OTP SENT");
            return "OTP Sent";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e);
            return "OTP not sent.";
        }
    }
}
