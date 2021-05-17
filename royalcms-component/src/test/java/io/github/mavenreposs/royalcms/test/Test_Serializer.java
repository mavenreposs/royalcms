package io.github.mavenreposs.royalcms.test;

import io.github.mavenreposs.royalcms.facades.RC_Log;
import io.github.mavenreposs.royalcms.facades.RC_Serializer;
import cn.stackbox.phpserialize.PhpDecoder;
import com.alibaba.fastjson.JSONArray;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_Serializer {

    @Test
    public void testSerializer() {
        List list = new ArrayList();
        list.add("A string");
        list.add(12345);
        list.add(true);

        // Outputs: a:3:{i:0;s:8:"A string";i:1;i:12345;i:2;b:1;}
        String s = RC_Serializer.serialize(list);
        System.out.println(s);
        assertEquals("a:3:{i:0;s:8:\"A string\";i:1;i:12345;i:2;b:1;}", s);
    }

    @Test
    public void test_01() {
        //style=\"list-style-type: decimal;\"
        String body = "a:4:{s:5:\"title\";s:15:\"团长招募令\";s:4:\"link\";s:43:\"http://www.hbf.dscmall.zhuo/admin/index.php\";s:7:\"content\";s:1095:\"<ol class=\" list-paddingleft-2\" style=\"list-style-type: decimal;\"><li><p>什么是团长？想成为团长需要具备什么条件？</p><p>商家通过线下或者线上推广的方式在各个社区招募团长，利用社交软件组成团长群，之后可定时发布团购活动，团长再将活动分享转发到自己维护的社区群中，活动结束之后商家发货给团长，团长安排社区的买家自提或团长配送，过程中团不会接触到买家支付的钱，也不需要囤货，利润会在团购结束后自动结算给团长。</p></li><li><p>什么是团长？想成为团长需要具备什么条件？</p><p>商家通过线下或者线上推广的方式在各个社区招募团长，利用社交软件组成团长群，之后可定时发布团购活动，团长再将活动分享转发到自己维护的社区群中，活动结束之后商家发货给团长，团长安排社区的买家自提或团长配送，过程中团不会接触到买家支付的钱，也不需要囤货，利润会在团购结束后自动结算给团长。</p><p><br/></p></li></ol>\";s:4:\"file\";s:70:\"data/groupbuy_recruit_img/w9RsEysI8x3zJ47Iy4KcA34PY17GsSI4Fxudeex6.png\";}";

        body = body.replaceAll("style=\"([\\s\\S]+);\"", "");
        RC_Log.info(body);

        PhpDecoder decoder = PhpDecoder.newInstance(body);
        JSONArray ret = decoder.decode();

        RC_Log.object(ret.get(0));
    }

    @Test
    public void test_02() {
        String body = "a:4:{s:5:\"title\";s:15:\"团长招募令\";s:4:\"link\";s:43:\"http://www.hbf.dscmall.zhuo/admin/index.php\";s:7:\"content\";s:1095:\"<ol class=\" list-paddingleft-2\" style=\"list-style-type: decimal;\"><li><p>什么是团长？想成为团长需要具备什么条件？</p><p>商家通过线下或者线上推广的方式在各个社区招募团长，利用社交软件组成团长群，之后可定时发布团购活动，团长再将活动分享转发到自己维护的社区群中，活动结束之后商家发货给团长，团长安排社区的买家自提或团长配送，过程中团不会接触到买家支付的钱，也不需要囤货，利润会在团购结束后自动结算给团长。</p></li><li><p>什么是团长？想成为团长需要具备什么条件？</p><p>商家通过线下或者线上推广的方式在各个社区招募团长，利用社交软件组成团长群，之后可定时发布团购活动，团长再将活动分享转发到自己维护的社区群中，活动结束之后商家发货给团长，团长安排社区的买家自提或团长配送，过程中团不会接触到买家支付的钱，也不需要囤货，利润会在团购结束后自动结算给团长。</p><p><br/></p></li></ol>\";s:4:\"file\";s:70:\"data/groupbuy_recruit_img/w9RsEysI8x3zJ47Iy4KcA34PY17GsSI4Fxudeex6.png\";}";

        RC_Log.object(RC_Serializer.unserialize(body));
    }

}
