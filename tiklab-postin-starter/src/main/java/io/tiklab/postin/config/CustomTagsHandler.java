package io.tiklab.postin.config;
import xdoclet.XDocletException;
import xdoclet.tagshandler.AbstractProgramElementTagsHandler;


public class CustomTagsHandler extends AbstractProgramElementTagsHandler {

    public CustomTagsHandler() {
        super();
    }

    public void startClass() throws XDocletException {
        // 处理类级别的注释
        String classComment = getCurrentClass().getDoc().toString();
        // 解析自定义注释，例如 @pi.protocol 和 @pi.groupName
        String protocol = getTagValue(classComment, "pi.protocol");
        String groupName = getTagValue(classComment, "pi.groupName");

        System.out.println(protocol);
        System.out.println(groupName);
    }

    public void startMethod() throws XDocletException {
        // 处理方法级别的注释
        String methodComment = getCurrentMethod().getDoc().toString();
        // 解析自定义注释，例如 @pi.name 和 @pi.url
        String name = getTagValue(methodComment, "pi.name");
        String url = getTagValue(methodComment, "pi.url");

        System.out.println(name);
        System.out.println(url);
    }

    private String getTagValue(String comment, String tagName) {
        // 在注释中查找指定标签名的值
        String tag = "@" + tagName + ":";
        int startIndex = comment.indexOf(tag);
        if (startIndex != -1) {
            int endIndex = comment.indexOf('\n', startIndex);
            if (endIndex == -1) {
                endIndex = comment.length();
            }
            return comment.substring(startIndex + tag.length(), endIndex).trim();
        }
        return null;
    }
}
