package Application.BE;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private Integer id = null;

    private String name;

    private List<Category> children = new ArrayList<>();

    private Integer parentID = null;

    private CategoryType type;

    public Category(String name) {
        this.name = name;
    }

    public Category(int id, String name, int parent) {
        this.id = id;
        this.name = name;
        this.parentID = parent;
    }


    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType isHealthCondition) {
        this.type = isHealthCondition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }

    public int getParentID() {
        return parentID;
    }

    public List<Category> getChildren() {
        return children;
    }
}
