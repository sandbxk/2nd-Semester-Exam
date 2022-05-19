import Application.BE.Category;
import Application.BE.CategoryEntry;
import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenTemplateModel;
import Application.GUI.Models.ControllerModels.CitizenTemplateControllerModel;
import Application.GUI.Models.FunctionalLevels;
import Application.GUI.Models.HealthLevels;
import Application.Utility.GUIUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TreeHierachyTest {
    /*
  Cannot test classes which contain JavaFX components, such as the CategoryEntryModel. The test needs to
  use a JavaFX thread, for which the below rule is required.
   */
    @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();


    ObservableList<CategoryEntryModel> categoryEntryModels;

    @Before
    public void setUp(){
        //Lists
        categoryEntryModels = FXCollections.observableArrayList();


        //Categories
        Category superSuperCategory = new Category(-1, "All Conditions", null);

        Category superCategory1 = new Category(-1, "Conditions 1", superSuperCategory);
        Category superCategory2 = new Category(-1, "Conditions 2", superSuperCategory);
        Category superCategory3 = new Category(-1, "Conditions 3", superSuperCategory);
        Category superCategory4 = new Category(-1, "Conditions 4", superSuperCategory);

        Category subCategory1 = new Category(-1, "Sub Category 1", superCategory1);
        Category subCategory2 = new Category(-1, "Sub Category 2", superCategory2);
        Category subCategory3 = new Category(-1, "Sub Category 3", superCategory3);
        Category subCategory4 = new Category(-1, "Sub Category 4", superCategory4);

        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, superSuperCategory)));

        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Conditions A", superCategory1))));
        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Condition A.1", subCategory1))));
        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Condition A.2", subCategory1))));
        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Condition A.3", subCategory1))));
        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Condition A.4", subCategory1))));

        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Conditions B", superCategory2))));
        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Condition B.1", subCategory2))));
        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Condition B.2", subCategory2))));
        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Condition B.3", subCategory2))));
        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Condition B.4", subCategory2))));

        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Conditions C", superCategory3))));
        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Condition C.1", subCategory3))));
        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Condition C.2", subCategory3))));
        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Condition C.3", subCategory3))));
        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Condition C.4", subCategory3))));

        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Conditions D", superCategory4))));
        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Condition D.1", subCategory4))));
        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Condition D.2", subCategory4))));
        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Condition D.3", subCategory4))));
        categoryEntryModels.add(new CategoryEntryModel(new CategoryEntry(-1, new Category(-1, "Condition D.4", subCategory4))));

    }

    //assert that the tree structure is correct
    @Test
    public void setTreeStructureHierachy(){
        TreeItem<CategoryEntryModel> root = GUIUtils.setCategoryHierachy(categoryEntryModels);

        CategoryEntryModel rootValue = root.getValue();
        TreeItem<CategoryEntryModel> superSuperCategory = root.getChildren().get(0);
        TreeItem<CategoryEntryModel> superCategory = superSuperCategory.getChildren().get(0);
        TreeItem<CategoryEntryModel> subCategory = superCategory.getChildren().get(0);
        //TreeItem<CategoryEntryModel> subSubCategory = subCategory.getChildren().get(0);


        System.out.println(rootValue.getCategoryName());
        System.out.println(superSuperCategory.getValue().getCategoryName());
        System.out.println(superCategory.getValue().getCategoryName());
        System.out.println(subCategory.getValue().getCategoryName());
        System.out.println(subCategory.getChildren().size());
        //System.out.println(subSubCategory.getChildren().size());

        assertEquals("Tilstande", rootValue.getCategoryName());
        assertEquals("All Conditions", superSuperCategory.getValue().getCategoryName());
        assertEquals("Conditions A", superCategory.getValue().getCategoryName());
        assertEquals("Condition A.1", subCategory.getValue().getCategoryName());
        assertNull(subCategory.getChildren());
    }
}
