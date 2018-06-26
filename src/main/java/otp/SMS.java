package otp;

import org.springframework.web.client.RestTemplate;

public class SMS {
    public boolean send_sms(String mobile, int otp)
    {
        String message = "Thank you for registering with us. Your one time password is " + Integer.toString(otp);
        String url;
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
            System.out.println("OTP SENT");
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
