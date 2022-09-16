package net.tiklab.postin.apimock.http.utils;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

public class MockProcess {

    //express例： @id  @natural(1,10)
    public static String mock(String express) throws ScriptException {
        //TODO 1,解析表达式 ：操作方法，操作参数
        //TODO 2,调对应的实现方法，
        //TODO 3,构造数据返回

        String funcationName = null;
        List<String> paramList = new ArrayList();
        boolean status = express.contains("(");
        if(status){
            funcationName = express.substring(1,express.indexOf("("));
            String params =  express.substring(express.indexOf("(")+1,express.indexOf(")"));
            for(String param:params.split(",")){
                paramList.add(param);
            }
        }else {
            funcationName = express.substring(1);
        }

        String data = switchFun(funcationName,paramList);
        return data;
    }

    //根据方法名称找到对应的方法获取值
    public static String switchFun (String funcationName,List<String> paramList) throws ScriptException {
        switch (funcationName){
            case "float":
                return floatNumber(paramList) ;
            case "boolean":
                return bool();
            case "character":
                return character(paramList);
            case "email":
                return email();
            case "name":
                return name();
            case "cname":
                return cname();
            case "naturel":
                return naturel(paramList);
            case "time":
                return time();
            case "date":
                return date();
            case "datetime":
                return datetime();
            case "color":
                return color();
            case "hex":
                return hex();
            case "rgb":
                return rgb();
            case "rgba":
                return rgba();
            case "hsl":
                return hsl();
            case "title":
                return title();
            case "paragraph":
                return paragraph();
            case "word":
                return word();
            case "cword":
                return cword();
            case "ctitle":
                return ctitle();
            case "cparagraph":
                return cparagraph();
            case "first":
                return first();
            case "cfirst":
                return cfirst();
            case "last":
                return last();
            case "clast":
                return clast();
            case "protocol":
                return protocol();
            case "url":
                return url();
            case "domain":
                return domain();
            case "ip":
                return ip();
            case "regin":
                return regin();
            case "province":
                return province();
            case "city":
                return city();
            case "country":
                return country();
            case "zip":
                return zip();
            case "id":
                return id();
            case "guid":
                return guid();
        }
        return null;
    }

    public static String bool() throws ScriptException {
        String bool = String.valueOf(MockFun.bool());
        return bool;
    }


    public static String character(List<String> paramList) throws ScriptException {
        char cha;
        String chaval = null;
        if(paramList.size()==0){
            cha = MockFun.character();
            chaval = Character.toString(cha);
        }else if(paramList.size()==1){
            String one = paramList.get(0);
            cha = MockFun.character(one);
            chaval = Character.toString(cha);
        }
        return chaval;
    }

    public static String email() throws ScriptException {
        return MockFun.email();
    }

    public static String name() throws ScriptException {
        return MockFun.name();
    }

    public static String cname() throws ScriptException {
        return MockFun.cname();
    }

    public static String naturel(List<String> paramList) throws ScriptException {
        long natural;
        String na=null;
        if(paramList.size()==0){
            natural = MockFun.natural();
            na = String.valueOf(natural);
        }else if(paramList.size()==1){
            long one =Long.parseLong (paramList.get(0));
            natural = MockFun.natural(one);
            na = String.valueOf(natural);
        }else if(paramList.size()==2){
            long one = Long.parseLong (paramList.get(0));
            long two = Long.parseLong (paramList.get(1));
            natural = MockFun.natural(one,two);
            na = String.valueOf(natural);
        }
        return na;
    }

    public static String time() throws ScriptException {
        return MockFun.time();
    }

    public static String date() throws ScriptException {
        return MockFun.date();
    }

    public static String datetime () throws ScriptException {
        return MockFun.datetime();
    }

    public static String color() throws ScriptException {
        return MockFun.color();
    }

    public static String hex() throws ScriptException {
        return MockFun.hex();
    }

    public static String rgb() throws ScriptException {
        return MockFun.rgb();
    }

    public static String rgba() throws ScriptException {
        return MockFun.rgba();
    }

    public static String hsl() throws ScriptException {
        return MockFun.hsl();
    }

    public static String title() throws ScriptException {
        return MockFun.title();
    }

    public static String paragraph() throws ScriptException {
        return MockFun.paragraph();
    }

    public static String word() throws ScriptException {
        return MockFun.word();
    }

    public static String cword() throws ScriptException {
        return MockFun.cword();
    }

    public static String ctitle() throws ScriptException {
        return MockFun.ctitle();
    }

    public static String cparagraph() throws ScriptException {
        return MockFun.cparagraph();
    }

    public static String first() throws ScriptException {
        return MockFun.first();
    }

    public static String floatNumber(List<String> paramList) throws ScriptException {
        float floatNum =0;
        String flonum =null;
        if(paramList.size()==0){
            floatNum = MockFun.floatNumber();
            flonum = Float.toString(floatNum);
        }else {
            if (paramList.size()>0){
                float s1=0;
                float s2=0;
                float s3=0;
                float s0 = Float.valueOf(paramList.get(0));
                if (paramList.size()>1){
                    s1 = Float.valueOf(paramList.get(1));
                    if (paramList.size()>2){
                        s2 = Float.valueOf(paramList.get(2));
                        if (paramList.size()>3){
                            s3 = Float.valueOf(paramList.get(3));
                        }
                    }
                }
                floatNum = MockFun.floatNumber(s0,s1,s2,s3);
            }

            flonum = Float.toString(floatNum);
        }
        return flonum;
    }

    public static String cfirst() throws ScriptException {
        return MockFun.cfirst();
    }

    public static String last() throws ScriptException {
        return MockFun.last();
    }

    public static String clast() throws ScriptException {
        return MockFun.clast();
    }

    public static String protocol() throws ScriptException {
        return MockFun.protocol();
    }

    public static String url() throws ScriptException {
        return MockFun.url();
    }

    public static String domain() throws ScriptException {
        return MockFun.domain();
    }

    public static String ip() throws ScriptException {
        return MockFun.ip();
    }

    public static String regin() throws ScriptException {
        return MockFun.region();
    }

    public static String province() throws ScriptException {
        return MockFun.province();
    }

    public static String city() throws ScriptException {
        return MockFun.city();
    }

    public static String country() throws ScriptException {
        return MockFun.county();
    }

    public static String zip() throws ScriptException {
        return MockFun.zip();
    }

    public static String id() throws ScriptException {
        return MockFun.id();
    }

    public static String guid() throws ScriptException {
        return MockFun.guid();
    }

}
