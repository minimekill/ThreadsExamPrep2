

package com.mycompany;


public class Group {
private String Authors;
private String className;
private String groupNumber;

    public Group(String Authors, String className, String groupNumber) {
        this.Authors = Authors;
        this.className = className;
        this.groupNumber = groupNumber;
    }

    public Group() {
    }

    public String getAuthors() {
        return Authors;
    }

    public void setAuthors(String Authors) {
        this.Authors = Authors;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }



}
