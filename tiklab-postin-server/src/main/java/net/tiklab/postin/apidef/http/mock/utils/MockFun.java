package net.tiklab.postin.apidef.http.mock.utils;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.http.HttpUtil;
import cn.hutool.script.JavaScriptEngine;
import cn.hutool.script.ScriptUtil;

import javax.script.ScriptException;
import java.util.Map;

public class MockFun {

    private static Cache<String, String> lfuCache = CacheUtil.newLFUCache(1);
    private static JavaScriptEngine scriptEngine = ScriptUtil.getJavaScriptEngine();

    /**
     * 方法描述：调用mockJs生成模拟数据.
     *
     * @param script js脚本
     * @throws ScriptException 脚本书写错误将会发生异常
     */
    public static Object mockRandom(String script) throws ScriptException {
        // 把mockjs读取到缓存
        if (lfuCache.isEmpty()) {
            String scriptString = FileUtil.readUtf8String( "classpath:mock-min.js");
            lfuCache.put("scriptString", scriptString, DateUnit.SECOND.getMillis() * 10);
            scriptEngine.eval(scriptString);
        }
        return scriptEngine.eval("Mock." + script);
    }

    /**
     * 方法描述：返回一个随机的布尔值.
     */
    public static boolean bool() throws ScriptException {
        Object random = mockRandom("Random.boolean()");
        return Convert.toBool(random);
    }

    /**
     * 方法描述：返回一个随机的布尔值.
     * @param min     指示参数 current 出现的概率。概率计算公式为 min / (min + max)。
     *                该参数的默认值为 1，即有 50% 的概率返回参数 current。
     * @param max     指示参数 current 的相反值 !current 出现的概率。概率计算公式为 max / (min + max)。
     *                该参数的默认值为 1，即有 50% 的概率返回参数 !current。
     * @param current 可选值为布尔值 true 或 false。如果未传入任何参数，则返回 true 和 false 的概率各为 50%。
     *                该参数没有默认值。在该方法的内部，依据原生方法 Math.random() 返回的（浮点）数来计算和返回布尔值，
     *                例如在最简单的情况下，返回值是表达式 Math.random() >= 0.5 的执行结果。
     * @return the boolean
     
     */
    public static boolean bool(int min, int max, boolean current) throws ScriptException {
        String format = StrFormatter.format("Random.boolean({}, {}, {})", min, max, current);
        Object random = mockRandom(format);
        return Convert.toBool(random);
    }

    /**
     * 方法描述：返回一个随机的自然数（大于等于 0 的整数）。
     */
    public static long natural() throws ScriptException {
        Object random = mockRandom("Random.natural()");
        return Convert.toLong(random);
    }

    /**
     * 方法描述：返回一个随机的自然数（大于等于 0 的整数）。
     * @param min 指示随机自然数的最小值。默认值为 0。
     */
    public static long natural(long min) throws ScriptException {
        String format = StrFormatter.format("Random.natural({})", min);
        Object random = mockRandom(format);
        return Convert.toLong(random);
    }

    /**
     * 方法描述：返回一个随机的自然数（大于等于 0 的整数）。
     * @param min 指示随机自然数的最小值。默认值为 0。
     * @param max 指示随机自然数的最大值。默认值为 9007199254740992。
     */
    public static long natural(long min, long max) throws ScriptException {
        String format = StrFormatter.format("Random.natural({},{})", min, max);
        Object random = mockRandom(format);
        return Convert.toLong(random);
    }

    /**
     * 方法描述：返回一个随机的浮点数。
     */
    public static float floatNumber() throws ScriptException {
        Object random = mockRandom("Random.float()");
        return Convert.toFloat(random);
    }

    /**
     * 方法描述：返回一个随机的浮点数。
     * @param min  整数部分的最小值。默认值为 -9007199254740992。
     * @param max  整数部分的最大值。默认值为 9007199254740992。
     * @param dmin 小数部分位数的最小值。默认值为 0。
     * @param dmax 小数部分位数的最大值。默认值为 17。
     */
    public static float floatNumber(float min, float max, float dmin, float dmax) throws ScriptException {
        String format = StrFormatter.format("Random.float({},{},{},{})", min, max, dmin, dmax);
        Object random = mockRandom(format);
        return Convert.toFloat(random);
    }

    /**
     * 方法描述：返回一个随机字符。
     * 范围：abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()[]
     */
    public static Character character() throws ScriptException {
        Object random = mockRandom("Random.character()");
        return Convert.toChar(random);
    }

    /**
     * 方法描述：返回一个随机字符。
     * @param pool 表示字符池，将从中选择一个字符返回
     *             lower: "abcdefghijklmnopqrstuvwxyz",
     *             upper: "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
     *             number: "0123456789",
     *             symbol: "!@#$%^&*()[]"
     *             也可自定义
     */
    public static Character character(String pool) throws ScriptException {
        String format = StrFormatter.format("Random.character('{}')", pool);
        Object random = mockRandom(format);
        return Convert.toChar(random);
    }

    /**
     * 方法描述：返回一个随机字符串.
     * @param pool   表示字符池，将从中选择一个字符返回
     * @param length 返回的长度
     
     */
    public static String string(String pool, int length) throws ScriptException {
        String format = StrFormatter.format("Random.string('{}',{})", pool, length);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：返回一个随机字符串.
     * @param pool 表示字符池，将从中选择一个字符返回
     * @param min  随机字符串的最小长度。默认值为 3。
     * @param max  随机字符串的最大长度。默认值为 7。
     
     */
    public static String string(String pool, int min, int max) throws ScriptException {
        String format = StrFormatter.format("Random.string('{}',{},{})", pool, min, max);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：返回一个整型数组。
     * @param start 数组中整数的起始值。
     * @param stop  数组中整数的结束值（不包含在返回值中）。
     
     */
    public static Integer[] range(int start, int stop) throws ScriptException {
        String format = StrFormatter.format("Random.range({},{})", start, stop);
        //jdk9+ 不再支持，更换为?，zhangzh
        /*
        ScriptObjectMirror random = (ScriptObjectMirror) mockRandom(format);
        return Convert.toIntArray(random.values());
         */
        return null;
    }

    /**
     * 方法描述：返回一个整型数组。
     * @param start 数组中整数的起始值。
     * @param stop  数组中整数的结束值（不包含在返回值中）。
     * @param step  数组中整数之间的步长。默认值为 1。
     
     */
    public static Integer[] range(int start, int stop, int step) throws ScriptException {
        String format = StrFormatter.format("Random.range({},{},{})", start, stop, step);
        //jdk9+ 不再支持，更换为?，zhangzh
        /*
        ScriptObjectMirror random = (ScriptObjectMirror) mockRandom(format);
        return Convert.toIntArray(random.values());
         */
        return null;
    }

    /**
     * 方法描述：返回一个随机的日期字符串.
     */
    public static String date() throws ScriptException {
        String format = StrFormatter.format("Random.date()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：返回一个随机的日期字符串.
     * @param dateFormat 指示生成的日期字符串的格式。默认值为 yyyy-MM-dd。
     */
    public static String date(String dateFormat) throws ScriptException {
        String format = StrFormatter.format("Random.date('{}')", dateFormat);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：返回一个随机的时间字符串。
     */
    public static String time() throws ScriptException {
        String format = StrFormatter.format("Random.time()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：返回一个随机的时间字符串。
     * @param timeFormat 指示生成的时间字符串的格式。默认值为 HH:mm:ss。
     */
    public static String time(String timeFormat) throws ScriptException {
        String format = StrFormatter.format("Random.time('{}')", timeFormat);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：返回一个随机的日期和时间字符串。
     */
    public static String datetime() throws ScriptException {
        String format = StrFormatter.format("Random.datetime()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：返回一个随机的日期和时间字符串。
     * @param datetimeFormat 指示生成的日期和时间字符串的格式。默认值为 yyyy-MM-dd HH:mm:ss。
     */
    public static String datetime(String datetimeFormat) throws ScriptException {
        String format = StrFormatter.format("Random.datetime('{}')", datetimeFormat);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：生成一个随机的图片地址。
     */
    public static String image() throws ScriptException {
        String format = StrFormatter.format("Random.image()");
        Object random = mockRandom(format);
        return HttpUtil.encode(Convert.toStr(random), "UTF-8");
    }

    /**
     * 方法描述：生成一个随机的图片地址。
     * @param size 指示图片的宽高，格式为 '宽x高'。例如：“300x250”，”360x123“
     */
    public static String image(String size) throws ScriptException {
        String format = StrFormatter.format("Random.image('{}')", size);
        Object random = mockRandom(format);
        return HttpUtil.encode(Convert.toStr(random), "UTF-8");
    }

    /**
     * 方法描述：生成一个随机的图片地址。
     * @param size       指示图片的宽高，格式为 '宽x高'。例如：“300x250”，”360x123“
     * @param background 指示图片的背景色。默认值为 '#000000'。
     */
    public static String image(String size, String background) throws ScriptException {
        String format = StrFormatter.format("Random.image('{}','{}')", size, background);
        Object random = mockRandom(format);
        return HttpUtil.encode(Convert.toStr(random), "UTF-8");
    }

    /**
     * 方法描述：生成一个随机的图片地址。
     * @param size       指示图片的宽高，格式为 '宽x高'。例如：“300x250”，”360x123“
     * @param background 指示图片的背景色。默认值为 '#000000'。
     * @param text       指示图片上的文字。默认值为参数 size。
     
     */
    public static String image(String size, String background, String text) throws ScriptException {
        String format = StrFormatter.format("Random.image('{}','{}','{}')", size, background, text);
        Object random = mockRandom(format);
        return HttpUtil.encode(Convert.toStr(random), "UTF-8");
    }

    /**
     * 方法描述：生成一个随机的图片地址。
     * @param size       指示图片的宽高，格式为 '宽x高'。例如：“300x250”，”360x123“
     * @param background 指示图片的背景色。默认值为 '#000000'。
     * @param foreground 指示图片的前景色（文字）。默认值为 '#FFFFFF'。
     * @param text       指示图片上的文字。默认值为参数 size。
     
     */
    public static String image(String size, String background, String foreground, String text) throws ScriptException {
        String format = StrFormatter.format("Random.image('{}','{}','{}','{}')", size, background, foreground, text);
        Object random = mockRandom(format);
        return HttpUtil.encode(Convert.toStr(random), "UTF-8");
    }

    /**
     * 方法描述：生成一个随机的图片地址。
     * @param size       指示图片的宽高，格式为 '宽x高'。例如：“300x250”，”360x123“
     * @param background 指示图片的背景色。默认值为 '#000000'。
     * @param foreground 指示图片的前景色（文字）。默认值为 '#FFFFFF'。
     * @param imgFormat  指示图片的格式。默认值为 'png'，可选值包括：'png'、'gif'、'jpg'。
     * @param text       指示图片上的文字。默认值为参数 size。
     
     */
    public static String image(String size, String background, String foreground, String imgFormat, String text) throws ScriptException {
        String format = StrFormatter.format("Random.image('{}','{}','{}','{}','{}')", size, background, foreground, imgFormat, text);
        Object random = mockRandom(format);
        return HttpUtil.encode(Convert.toStr(random), "UTF-8");
    }

    /**
     * 方法描述：随机生成一个有吸引力的颜色，格式为 '#RRGGBB'。
     */
    public static String color() throws ScriptException {
        String format = StrFormatter.format("Random.color()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个有吸引力的颜色，格式为 '#RRGGBB'。
     */
    public static String hex() throws ScriptException {
        String format = StrFormatter.format("Random.hex()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个有吸引力的颜色，格式为 'rgb(r, g, b)'。
     */
    public static String rgb() throws ScriptException {
        String format = StrFormatter.format("Random.rgb()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个有吸引力的颜色，格式为 'rgba(r, g, b, a)'。
     */
    public static String rgba() throws ScriptException {
        String format = StrFormatter.format("Random.rgba()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个有吸引力的颜色，格式为 'hsl(h, s, l)'。
     */
    public static String hsl() throws ScriptException {
        String format = StrFormatter.format("Random.hsl()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一段英文文本。
     */
    public static String paragraph() throws ScriptException {
        String format = StrFormatter.format("Random.paragraph()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一段英文文本。
     * @param len 指示文本中句子的个数。默认值为 3 到 7 之间的随机数。
     */
    public static String paragraph(int len) throws ScriptException {
        String format = StrFormatter.format("Random.paragraph({})", len);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一段英文文本。
     * @param min 指示文本中句子的最小个数。默认值为 3。
     * @param max 指示文本中句子的最大个数。默认值为 7。
     */
    public static String paragraph(int min, int max) throws ScriptException {
        String format = StrFormatter.format("Random.paragraph({},{})", min, max);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一段中文文本。
     */
    public static String cparagraph() throws ScriptException {
        String format = StrFormatter.format("Random.cparagraph()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一段中文文本。
     * @param len 指示文本中句子的个数。默认值为 3 到 7 之间的随机数。
     */
    public static String cparagraph(int len) throws ScriptException {
        String format = StrFormatter.format("Random.cparagraph({})", len);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一段中文文本。
     * @param min 指示文本中句子的最小个数。默认值为 3。
     * @param max 指示文本中句子的最大个数。默认值为 7。
     */
    public static String cparagraph(int min, int max) throws ScriptException {
        String format = StrFormatter.format("Random.cparagraph({},{})", min, max);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个单词。
     */
    public static String word() throws ScriptException {
        String format = StrFormatter.format("Random.word()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个单词。
     * @param len 指示单词中字符的个数。默认值为 3 到 10 之间的随机数。
     */
    public static String word(int len) throws ScriptException {
        String format = StrFormatter.format("Random.word({})", len);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个单词。
     * @param min 指示单词中字符的最小个数。默认值为 3。
     * @param max 指示单词中字符的最大个数。默认值为 10。
     */
    public static String word(int min, int max) throws ScriptException {
        String format = StrFormatter.format("Random.word({},{})", min, max);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个汉字。
     */
    public static String cword() throws ScriptException {
        String format = StrFormatter.format("Random.cword()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个汉字。
     * @param pool 汉字字符串。表示汉字字符池，将从中选择一个汉字字符返回。
     */
    public static String cword(String pool) throws ScriptException {
        String format = StrFormatter.format("Random.cword('{}')", pool);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成 length 个汉字。
     * @param length 随机字符串的长度
     */
    public static String cword(int length) throws ScriptException {
        String format = StrFormatter.format("Random.cword({})", length);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成汉字。
     * @param min 随机汉字字符串的最小长度。默认值为 1。
     * @param max 随机汉字字符串的最大长度。默认值为 1。
     */
    public static String cword(int min, int max) throws ScriptException {
        String format = StrFormatter.format("Random.cword({},{})", min, max);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成汉字。
     * @param pool 汉字字符串。表示汉字字符池，将从中选择一个汉字字符返回。
     * @param min  随机汉字字符串的最小长度。默认值为 1。
     * @param max  随机汉字字符串的最大长度。默认值为 1。
     */
    public static String cword(String pool, int min, int max) throws ScriptException {
        String format = StrFormatter.format("Random.cword('{}',{},{})", pool, min, max);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一句标题，其中每个单词的首字母大写。
     */
    public static String title() throws ScriptException {
        String format = StrFormatter.format("Random.title()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一句标题，其中每个单词的首字母大写。
     * @param len 指示单词中字符的个数。默认值为 3 到 7 之间的随机数。
     */
    public static String title(int len) throws ScriptException {
        String format = StrFormatter.format("Random.title({})", len);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一句标题，其中每个单词的首字母大写。
     * @param min 指示单词中字符的最小个数。默认值为 3。
     * @param max 指示单词中字符的最大个数。默认值为 7。
     */
    public static String title(int min, int max) throws ScriptException {
        String format = StrFormatter.format("Random.title({},{})", min, max);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一句中文标题。
     */
    public static String ctitle() throws ScriptException {
        String format = StrFormatter.format("Random.ctitle()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一句中文标题。
     * @param len 指示单词中字符的个数。默认值为 3 到 7 之间的随机数。
     */
    public static String ctitle(int len) throws ScriptException {
        String format = StrFormatter.format("Random.ctitle({})", len);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一句中文标题。
     * @param min 指示单词中字符的最小个数。默认值为 3。
     * @param max 指示单词中字符的最大个数。默认值为 7。
     */
    public static String ctitle(int min, int max) throws ScriptException {
        String format = StrFormatter.format("Random.ctitle({},{})", min, max);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个常见的英文名。
     */
    public static String first() throws ScriptException {
        String format = StrFormatter.format("Random.first()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个常见的英文姓。
     */
    public static String last() throws ScriptException {
        String format = StrFormatter.format("Random.last()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个常见的英文姓名。
     */
    public static String name() throws ScriptException {
        String format = StrFormatter.format("Random.name()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个常见的英文姓名。
     * @param middle 指示是否生成中间名。
     */
    public static String name(boolean middle) throws ScriptException {
        String format = StrFormatter.format("Random.name({})", middle);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个常见的中文名。
     */
    public static String cfirst() throws ScriptException {
        String format = StrFormatter.format("Random.cfirst()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个常见的中文姓。
     */
    public static String clast() throws ScriptException {
        String format = StrFormatter.format("Random.clast()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个常见的中文姓名。
     */
    public static String cname() throws ScriptException {
        String format = StrFormatter.format("Random.cname()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个 URL。
     */
    public static String url() throws ScriptException {
        String format = StrFormatter.format("Random.url()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个 URL。
     * 创建时间：2019-06-14 22:10:52
    
     *
     * @param protocol 指定 URL 协议。例如 http。
     * @param host     指定 URL 域名和端口号。例如 nuysoft.com。
     
     */
    public static String url(String protocol, String host) throws ScriptException {
        String format = StrFormatter.format("Random.url('{}','{}')", protocol, host);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个 URL 协议。返回以下值之一：
     * 'http'、'ftp'、'gopher'、'mailto'、'mid'、'cid'、'news'、'nntp'、
     * 'prospero'、'telnet'、'rlogin'、'tn3270'、'wais'。
     */
    public static String protocol() throws ScriptException {
        String format = StrFormatter.format("Random.protocol()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个域名。
     */
    public static String domain() throws ScriptException {
        String format = StrFormatter.format("Random.domain()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个顶级域名（Top Level Domain）。
     */
    public static String tld() throws ScriptException {
        String format = StrFormatter.format("Random.tld()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个邮件地址。
     */
    public static String email() throws ScriptException {
        String format = StrFormatter.format("Random.email()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个邮件地址。
     */
    public static String email(String domain) throws ScriptException {
        String format = StrFormatter.format("Random.email('{}')", domain);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个 IP 地址。
     */
    public static String ip() throws ScriptException {
        String format = StrFormatter.format("Random.ip()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个（中国）大区。
     */
    public static String region() throws ScriptException {
        String format = StrFormatter.format("Random.region()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个（中国）省（或直辖市、自治区、特别行政区）。
     */
    public static String province() throws ScriptException {
        String format = StrFormatter.format("Random.province()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个（中国）市。
     */
    public static String city() throws ScriptException {
        String format = StrFormatter.format("Random.city()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个（中国）市。
     * @param prefix 指示是否生成所属的省。
     
     */
    public static String city(boolean prefix) throws ScriptException {
        String format = StrFormatter.format("Random.city({})", prefix);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个（中国）县。
     */
    public static String county() throws ScriptException {
        String format = StrFormatter.format("Random.county()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个（中国）县。
     * @param prefix 指示是否生成所属的省。
     
     */
    public static String county(boolean prefix) throws ScriptException {
        String format = StrFormatter.format("Random.county({})", prefix);
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个邮政编码（六位数字）。
     */
    public static String zip() throws ScriptException {
        String format = StrFormatter.format("Random.zip()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    public static String guid() throws ScriptException {
        String format = StrFormatter.format("Random.guid()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：随机生成一个 18 位身份证。
     */
    public static String id() throws ScriptException {
        String format = StrFormatter.format("Random.id()");
        Object random = mockRandom(format);
        return Convert.toStr(random);
    }

    /**
     * 方法描述：根据数据模板生成模拟数据。
     * @param template 表示数据模板，可以是对象或字符串。例如 { 'data|1-10':[{}] }、'@EMAIL'。
     
     */
    public static Map mock(String template) throws ScriptException {
        String format = StrFormatter.format("mock({})", template);
        //jdk9+ 不再支持，更换为?，zhangzh
        /*
        ScriptObjectMirror random = (ScriptObjectMirror) mockRandom(format);
        return Convert.convert(Map.class,random);
         */

        return null;
    }

      
    
}


