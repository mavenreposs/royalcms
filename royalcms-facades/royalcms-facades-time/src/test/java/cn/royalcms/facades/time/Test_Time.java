package cn.royalcms.facades.time;

import org.junit.jupiter.api.Test;

public class Test_Time {

    @Test
    public void testGmtime()
    {
        Integer a = RC_Time.gmtime();
        System.out.println(a);
    }

    @Test
    public void testServerTimezone() {
        String a = RC_Time.server_timezone();
        System.out.println(a);

        RC_Time.set_server_timezone();
        System.out.println(RC_Time.get_server_timezone());
    }

    @Test
    public void testTimeZoneSet() {
        RC_Time.setTimeZoneInteger(8);
        RC_Time.set_server_timezone();
        System.out.println(RC_Time.server_timezone());

        RC_Time.setTimeZoneInteger(-8);
        RC_Time.set_server_timezone();
        System.out.println(RC_Time.server_timezone());

        RC_Time.setTimeZoneInteger(0);
        RC_Time.set_server_timezone();
        System.out.println(RC_Time.server_timezone());
    }

    @Test
    public void testServerTimezoneOffset()
    {
        Integer a = RC_Time.server_timezone_offset();
        System.out.println(a);

        Integer b = RC_Time.server_timezone_hour();
        System.out.println(b);
    }

    @Test
    public void testGmdate()
    {
        String format = "yyyy-MM-dd HH:mm:ss";
        String a = RC_Time.gmdate(format);
        System.out.println(a);
    }

    @Test
    public void testGmStrtotime() {
        int a = RC_Time.gmstrtotime("2021-04-02 15:59:45");
        System.out.println(a);
    }

    @Test
    public void testStrtoTime() {
        int a = RC_Time.strtotime("2021-04-02 15:59:45");
        System.out.println(a);
    }


}
