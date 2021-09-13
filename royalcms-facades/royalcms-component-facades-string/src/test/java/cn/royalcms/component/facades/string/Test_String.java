package cn.royalcms.component.facades.string;

import cn.royalcms.component.facades.log.RC_Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test_String {

    @Test
    public void test_random() {
        RC_Log.info(RC_String.random());
        RC_Log.info(RC_String.random(32));
    }

    @Test
    public void test_after() {
        String a = RC_String.after("This is my name", "This is");
        RC_Log.info(a);
        Assertions.assertEquals(" my name", a);

        String b = RC_String.of("This is my name").after("This is").toString();
        RC_Log.info(b);
        Assertions.assertEquals(" my name", b);
    }

    @Test
    public void test_afterLast() {
        String a = RC_String.afterLast("App\\Http\\Controllers\\Controller", "\\");
        RC_Log.info(a);
        Assertions.assertEquals("Controller", a);

        String b = RC_String.of("App\\Http\\Controllers\\Controller").afterLast("\\").toString();
        RC_Log.info(b);
        Assertions.assertEquals("Controller", b);
    }

    @Test
    public void test_before() {
        String a = RC_String.before("This is my name", "my name");
        RC_Log.info(a);
        Assertions.assertEquals("This is ", a);

        String b = RC_String.of("This is my name").before("my name").toString();
        RC_Log.info(b);
        Assertions.assertEquals("This is ", b);
    }

    @Test
    public void test_beforeLast() {
        String a = RC_String.beforeLast("This is my name", "is");
        RC_Log.info(a);
        Assertions.assertEquals("This ", a);

        String b = RC_String.of("This is my name").beforeLast("is").toString();
        RC_Log.info(b);
        Assertions.assertEquals("This ", b);
    }

    @Test
    public void test_studly() {
        String a = RC_String.studly("foo_bar");
        RC_Log.info(a);
        Assertions.assertEquals("FooBar", a);

        String b = RC_String.of("foo_bar").studly().toString();
        RC_Log.info(b);
        Assertions.assertEquals("FooBar", b);
    }

    @Test
    public void test_camel() {
        String a = RC_String.camel("foo_bar");
        RC_Log.info(a);
        Assertions.assertEquals("fooBar", a);

        String b = RC_String.of("foo_bar").camel().toString();
        RC_Log.info(b);
        Assertions.assertEquals("fooBar", b);
    }

    @Test
    public void test_contains() {
        Boolean a = RC_String.contains("This is my name", "my");
        Boolean b = RC_String.contains("This is my name", new String[]{"my", "foo"});
        RC_Log.info(a.toString());
        RC_Log.info(b.toString());
        Assertions.assertEquals(true, a);
        Assertions.assertEquals(true, b);

        Boolean a1 = RC_String.of("This is my name").contains("my");
        Boolean b1 = RC_String.of("This is my name").contains(new String[]{"my", "foo"});
        RC_Log.info(a1.toString());
        RC_Log.info(b1.toString());
        Assertions.assertEquals(true, a1);
        Assertions.assertEquals(true, b1);
    }

    @Test
    public void test_containsAll() {
        Boolean a = RC_String.containsAll("This is my name", new String[]{"my", "name"});
        RC_Log.info(a.toString());
        Assertions.assertEquals(true, a);

        Boolean b = RC_String.of("This is my name").containsAll(new String[]{"my", "name"});
        RC_Log.info(b.toString());
        Assertions.assertEquals(true, b);
    }

    @Test
    public void test_endsWith() {
        Boolean a = RC_String.endsWith("This is my name", "name");
        RC_Log.info(a.toString());
        Assertions.assertEquals(true, a);

        Boolean b = RC_String.endsWith("This is my name", new String[]{"name", "foo"});
        RC_Log.info(b.toString());
        Assertions.assertEquals(true, b);

        Boolean c = RC_String.endsWith("This is my name", new String[]{"this", "foo"});
        RC_Log.info(c.toString());
        Assertions.assertEquals(false, c);

        Boolean a1 = RC_String.of("This is my name").endsWith("name");
        RC_Log.info(a1.toString());
        Assertions.assertEquals(true, a1);

        Boolean b1 = RC_String.of("This is my name").endsWith(new String[]{"name", "foo"});
        RC_Log.info(b1.toString());
        Assertions.assertEquals(true, b1);

        Boolean c1 = RC_String.of("This is my name").endsWith(new String[]{"this", "foo"});
        RC_Log.info(c1.toString());
        Assertions.assertEquals(false, c1);

    }

    @Test
    public void test_finish() {
        String a = RC_String.finish("this/string", "/");
        RC_Log.info(a);
        Assertions.assertEquals("this/string/", a);

        String b = RC_String.finish("this/string/", "/");
        RC_Log.info(b);
        Assertions.assertEquals("this/string/", b);

        String a1 = RC_String.of("this/string").finish("/").toString();
        RC_Log.info(a1);
        Assertions.assertEquals("this/string/", a1);

        String b1 = RC_String.of("this/string/").finish("/").toString();
        RC_Log.info(b1);
        Assertions.assertEquals("this/string/", b1);

    }

    @Test
    public void test_isUuid() {
        Boolean a = RC_String.isUuid("a0a2a2d2-0b87-4a18-83f2-2529882be2de");
        RC_Log.info(a.toString());
        Assertions.assertEquals(true, a);

        Boolean b = RC_String.isUuid("dscmall");
        RC_Log.info(b.toString());
        Assertions.assertEquals(false, b);
    }

    @Test
    public void test_kebab() {
        String a = RC_String.kebab("fooBar");
        RC_Log.info(a);
        Assertions.assertEquals("foo-bar", a);

        String b = RC_String.of("fooBar").kebab().toString();
        RC_Log.info(b);
        Assertions.assertEquals("foo-bar", b);
    }

    @Test
    public void test_length() {
        Integer a = RC_String.length("dscmall");
        RC_Log.info(a.toString());
        Assertions.assertEquals(7, a);

        Integer b = RC_String.of("dscmall").length();
        RC_Log.info(b.toString());
        Assertions.assertEquals(7, b);
    }

    @Test
    public void test_limit() {
        String a = RC_String.limit("The quick brown fox jumps over the lazy dog", 20);
        RC_Log.info(a);
        Assertions.assertEquals("The quick brown fox...", a);

        String b = RC_String.limit("The quick brown fox jumps over the lazy dog", 20, " (...)");
        RC_Log.info(b);
        Assertions.assertEquals("The quick brown fox (...)", b);

        String a1 = RC_String.of("The quick brown fox jumps over the lazy dog").limit(20).toString();
        RC_Log.info(a1);
        Assertions.assertEquals("The quick brown fox...", a1);

        String b1 = RC_String.of("The quick brown fox jumps over the lazy dog").limit(20, " (...)").toString();
        RC_Log.info(b1);
        Assertions.assertEquals("The quick brown fox (...)", b1);

    }

    @Test
    public void test_lower() {
        String a = RC_String.lower("DSCMALL");
        RC_Log.info(a);
        Assertions.assertEquals("dscmall", a);

        String b = RC_String.of("DSCMALL").lower().toString();
        RC_Log.info(b);
        Assertions.assertEquals("dscmall", b);
    }

    @Test
    public void test_padBoth() {
        String a = RC_String.padBoth("James", 10, "_");
        RC_Log.info(a);
        Assertions.assertEquals("__James___", a);

        String b = RC_String.padBoth("James", 10);
        RC_Log.info(b);
        Assertions.assertEquals("  James   ", b);

        String a1 = RC_String.of("James").padBoth(10, "_").toString();
        RC_Log.info(a1);
        Assertions.assertEquals("__James___", a1);

        String b1 = RC_String.of("James").padBoth(10).toString();
        RC_Log.info(b1);
        Assertions.assertEquals("  James   ", b1);
    }

    public void test_padLeft() {
        String a = RC_String.padLeft("James", 10, "-=");
        RC_Log.info(a);
        Assertions.assertEquals("-=-=-James", a);

        String b = RC_String.padLeft("James", 10);
        RC_Log.info(b);
        Assertions.assertEquals("     James", b);

        String a1 = RC_String.of("James").padLeft(10, "-=").toString();
        RC_Log.info(a1);
        Assertions.assertEquals("-=-=-James", a1);

        String b1 = RC_String.of("James").padLeft(10).toString();
        RC_Log.info(b1);
        Assertions.assertEquals("     James", b1);
    }

    @Test
    public void test_padRight() {
        String a = RC_String.padRight("James", 10, "-");
        RC_Log.info(a);
        Assertions.assertEquals("James-----", a);

        String b = RC_String.padRight("James", 10);
        RC_Log.info(b);
        Assertions.assertEquals("James     ", b);

        String a1 = RC_String.of("James").padRight(10, "-").toString();
        RC_Log.info(a1);
        Assertions.assertEquals("James-----", a1);

        String b1 = RC_String.of("James").padRight(10).toString();
        RC_Log.info(b1);
        Assertions.assertEquals("James     ", b1);
    }

    @Test
    public void test_replaceArray() {
        String string = "The event will take place between ? and ?";

        String replaced = RC_String.replaceArray("?", new String[]{"8:30", "9:00"}, string);
        RC_Log.info(replaced);
        Assertions.assertEquals("The event will take place between 8:30 and 9:00", replaced);

        String b = RC_String.of(string).replaceArray("?", new String[]{"8:30", "9:00"}).toString();
        RC_Log.info(b);
        Assertions.assertEquals("The event will take place between 8:30 and 9:00", b);
    }

    @Test
    public void test_replaceFirst() {
        String replaced = RC_String.replaceFirst("the", "a", "the quick brown fox jumps over the lazy dog");
        RC_Log.info(replaced);
        Assertions.assertEquals("a quick brown fox jumps over the lazy dog", replaced);

        String b = RC_String.of("the quick brown fox jumps over the lazy dog").replaceFirst("the", "a").toString();
        RC_Log.info(b);
        Assertions.assertEquals("a quick brown fox jumps over the lazy dog", b);
    }

    @Test
    public void test_replaceLast() {
        String replaced = RC_String.replaceLast("the", "a", "the quick brown fox jumps over the lazy dog");
        RC_Log.info(replaced);
        Assertions.assertEquals("the quick brown fox jumps over a lazy dog", replaced);

        String b = RC_String.of("the quick brown fox jumps over the lazy dog").replaceLast("the", "a").toString();
        RC_Log.info(b);
        Assertions.assertEquals("the quick brown fox jumps over a lazy dog", b);
    }

    @Test
    public void test_startsWith() {
        Boolean a = RC_String.startsWith("This is my name", "This");
        RC_Log.info(a.toString());
        Assertions.assertEquals(true, a);

        Boolean b = RC_String.of("This is my name").startsWith("This");
        RC_Log.info(b.toString());
        Assertions.assertEquals(true, b);
    }

    @Test
    public void test_start() {
        String a = RC_String.start("this/string", "/");
        RC_Log.info(a);
        Assertions.assertEquals("/this/string", a);

        String b = RC_String.start("/this/string", "/");
        RC_Log.info(b);
        Assertions.assertEquals("/this/string", b);

        String a1 = RC_String.of("this/string").start("/").toString();
        RC_Log.info(a1);
        Assertions.assertEquals("/this/string", a1);

        String b1 = RC_String.of("/this/string").start("/").toString();
        RC_Log.info(b1);
        Assertions.assertEquals("/this/string", b1);
    }

    @Test
    public void test_title() {
        String converted = RC_String.title("a nice title uses the correct case");
        RC_Log.info(converted);
        Assertions.assertEquals("A Nice Title Uses The Correct Case", converted);

        String b = RC_String.of("a nice title uses the correct case").title().toString();
        RC_Log.info(b);
        Assertions.assertEquals("A Nice Title Uses The Correct Case", b);
    }

    @Test
    public void test_ucfirst() {
        String a = RC_String.ucfirst("foo bar");
        RC_Log.info(a);
        Assertions.assertEquals("Foo bar", a);

        String b = RC_String.of("foo bar").ucfirst().toString();
        RC_Log.info(b);
        Assertions.assertEquals("Foo bar", b);
    }

    @Test
    public void test_words() {
        String a = RC_String.words("Perfectly balanced, as all things should be.", 3, " >>>");
        RC_Log.info(a);
        Assertions.assertEquals("Perfectly balanced, as >>>", a);

        String b = RC_String.of("Perfectly balanced, as all things should be.").words(3, " >>>").toString();
        RC_Log.info(b);
        Assertions.assertEquals("Perfectly balanced, as >>>", b);
    }

    @Test
    public void test_between() {
        String a = RC_String.between("This is my name", "This", "name");
        RC_Log.info(a);
        Assertions.assertEquals(" is my ", a);

        String b = RC_String.of("This is my name").between("This", "name").toString();
        RC_Log.info(b);
        Assertions.assertEquals(" is my ", b);
    }

    @Test
    public void test_of() {
        String a = RC_String.of("This is my name balanced balanced...")
                .after("This is").trim().words(3, " >>>").upper().lower()
                .replace(" ", "_").camel()
                .toString();
        RC_Log.info(a);
        Assertions.assertEquals("myNameBalanced>>>", a);
    }


}
