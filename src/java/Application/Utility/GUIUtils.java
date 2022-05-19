package Application.Utility;

import Application.BE.Category;
import Application.GUI.Models.AccountModel;
import Application.GUI.Models.CategoryEntryModel;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TreeItem;

import java.util.*;
import java.util.function.UnaryOperator;

public final class GUIUtils {

    private static AccountModel selectedStudent;
    private static AccountModel selectedTeacher;


    private GUIUtils() {
        // Private constructor to prevent instantiation
    }


    /**
     * Utility method for unwrapping the CategoryEntryModel from a TreeItem.
     * @param root
     * @return a list of CategoryEntryModels present in the TreeItem root.
     */
    public static List<CategoryEntryModel> getTreeItemsFromRoot(TreeItem<CategoryEntryModel> root) {
        List<CategoryEntryModel> catList = new ArrayList<>(); //List to store the categories
        Thread thread = new Thread(() -> {

            ObservableList<TreeItem<CategoryEntryModel>> treeItems = root.getChildren(); //Get the children of the root
            for (TreeItem<CategoryEntryModel> treeItem : treeItems) { //For each child
                if (treeItem.getChildren().size() > 0) { //If the child has children
                    catList.addAll(getTreeItemsFromRoot(treeItem)); //Get the children of the child
                } else {
                    CategoryEntryModel categoryEntryModel = treeItem.getValue(); //Get the value of the child
                    catList.add(categoryEntryModel); //Add the value to the list
                }

            }
        });
        thread.run();
        return catList;
    }


    /**
     * And interger TextFormatter to only allow numbers to be entered.
     * @return
     */
    public static UnaryOperator<TextFormatter.Change> getIntegerFilter(){
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            //if (newText.matches("-?([1-9][0-9]*)?")) {
            if (newText.matches("-?([1-9][0-9]*)?")) {

                return change;
            }
            return null;
        };

        return integerFilter;
    }

    /**
     * Applies a filter to a list view through a listener on the text field.
     * The list of items is filtered based on the text in the text field.
     * Items are automatically updated by a listener on the ListViews itemsProperty.
     * @param searchField
     * @param listView
     * @param <T>
     */
    public static <T> void searchListener(TextField searchField, ListView<T> listView) {
        //Wrap ObservableList of UserInfo in a FilteredList.
        FilteredList<T> filteredData = new FilteredList<T>(listView.getItems(), b -> true);

        //Make sure the filtered list always contains the same elements as the original list.
        listView.itemsProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.clear();
            filteredData.addAll(newValue);
                });

        //Sets the filter predict when filter changes.
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(data -> {

                //If filter is empty, display all accounts.
                if (newValue == null || newValue.isEmpty())
                {
                    return true;
                }

                //Compare Account name with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (data.toString().toLowerCase().indexOf(lowerCaseFilter) != -1)
                {
                    return true;
                } else return false;
            });
        });

        SortedList<T> sortedUsers = new SortedList<>(filteredData);

        listView.setItems(sortedUsers);
    }

    public static TreeItem<CategoryEntryModel> setCategoryHierachy(List<CategoryEntryModel> categoryEntryModels){
        TreeItem<CategoryEntryModel> root = new TreeItem<>(new CategoryEntryModel("Tilstande"));

        //Thread thread = new Thread(() -> {
            HashMap<Category, TreeItem<CategoryEntryModel>> parentMap = new HashMap<>();

            //Get the hierarchy of the categories by their parent category.
            for(CategoryEntryModel categoryEntryModel : categoryEntryModels){
                Category child = categoryEntryModel.getCategoryEntry().getCategory();
                Category parent = child.getParent();

                if(parent == null){
                    parentMap.put(child, new TreeItem<>(categoryEntryModel));
                    continue;
                }
                if (!parentMap.containsKey(parent)){
                    parentMap.put(parent, new TreeItem<>(categoryEntryModel));
                }
                else if (parentMap.containsKey(parent)){
                    parentMap.get(parent).getChildren().add(new TreeItem<>(categoryEntryModel));
                }
            }

            //Sort alphabetically
            for(TreeItem<CategoryEntryModel> treeCategoryEntryModel : parentMap.values()){
                treeCategoryEntryModel.getChildren().sort(Comparator.comparing(o -> o.getValue().getCategoryName()));
            }

            //Add the categories to the root.
            for(Category parents : parentMap.keySet()){
                if(parents.getParent() == null){
                    root.getChildren().add(parentMap.get(parents));
                }
                else if (parentMap.containsKey(parents.getParent())){
                    parentMap.get(parents.getParent()).getChildren().add(parentMap.get(parents));
                }
            }

        //});
        //thread.run();

        //while (thread.isAlive()){
            //Wait for the thread to finish
        //}
        return root;
    }
}
