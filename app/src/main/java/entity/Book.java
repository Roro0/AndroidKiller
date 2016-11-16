package entity;

/**
 * xml解析模型
 * Created by Administrator on 2016/11/11.
 */

public class Book {
private String name;
    private String type;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
