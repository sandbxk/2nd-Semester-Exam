package Application.Utility;

import Application.BE.Category;
import Application.BE.CategoryEntry;
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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

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
        FilteredList<T> filteredData = new FilteredList<>(listView.getItems(), b -> true);

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
            HashMap<Category, Integer> categories = new HashMap<>();
            List<CategoryEntryModel> inserted = new ArrayList<>();

            for (CategoryEntryModel categoryEntryModel : categoryEntryModels) {
                categories.put(categoryEntryModel.getCategoryEntry().getCategory(), categoryEntryModels.indexOf(categoryEntryModel));


            }


            //Get the hierarchy of the categories by their parent category.
            for(CategoryEntryModel categoryEntryModel : categoryEntryModels){
                Category child = categoryEntryModel.getCategoryEntry().getCategory();
                Category parent = child.getParent();

                if (inserted.contains(categoryEntryModel))
                    continue;


                if(parent == null){ //If the parent is null, the category is a super category.
                    parentMap.put(child, new TreeItem<>(categoryEntryModel));
                    continue;
                }

                if (!parentMap.containsKey(parent)){ //If the parent is not in the map, add it, and get the parent category and add the child category to it.
                    for (Category cat : categories.keySet()){
                        if(cat.equals(parent)){
                            parentMap.put(parent, new TreeItem<>(categoryEntryModels.get(categories.get(cat))));
                            parentMap.get(parent).getChildren().add(new TreeItem<>(categoryEntryModel));
                            inserted.add(categoryEntryModels.get(categories.get(cat)));
                        }

                    }
                    //parentMap.put(parent, new TreeItem<>(categoryEntryModel));
                    //parentMap.get(parent).getChildren().add(new TreeItem<>(categoryEntryModel));

                }
                else { //If the table contains the parent category, get the parent category and add the child category to it.
                    parentMap.get(parent).getChildren().add(new TreeItem<>(categoryEntryModel));
                    inserted.add(categoryEntryModel);
                }

                //TODO Fix first real entry being super category.
            }

            //Add the categories to the root.
            for(Category category : parentMap.keySet()){
                if(category.getParent() == null){
                    root.getChildren().add(parentMap.get(category));
                }
                else if (parentMap.containsKey(category.getParent())){
                    parentMap.get(category.getParent())
                            .getChildren().add(parentMap.get(category));


                    parentMap.get(category.getParent()).getChildren().sort(Comparator.comparing(o -> o.getValue().getCategoryName()));
                }
            }

        //});
        //thread.run();

        //while (thread.isAlive()){
            //Wait for the thread to finish
        //}
        return root;
    }



    public static TreeItem<CategoryEntryModel> setCategoryHierachy2(List<CategoryEntryModel> categoryEntryModels){
        TreeItem<CategoryEntryModel> root = new TreeItem<>(new CategoryEntryModel("Tilstande"));


        Thread thread = new Thread(() -> {
        HashMap<Category, TreeItem<CategoryEntryModel>> parentMap = new HashMap<>();



        //Create a set for all the parent categories. Sets do not allow duplicates.
        Set<Category> categoryParents = new HashSet<>();
        categoryParents.addAll(categoryEntryModels.stream() //Add all the parent categories to the set.
                .map(CategoryEntryModel::getCategoryEntry)
                .map(CategoryEntry::getCategory)
                .map(Category::getParent)
                .collect(Collectors.toList()));
        categoryParents.forEach(category -> { parentMap.put(category, null);}); //Put all the parent categories in the map with null as value to be replaced later



        //Get the hierarchy of the categories by their parent category.
        for(CategoryEntryModel categoryEntryModel : categoryEntryModels){
            Category child = categoryEntryModel.getCategoryEntry().getCategory();
            Category parent = child.getParent();


            if(parent == null){ //If the parent is null, the category is the highest level super category.
                parentMap.put(child, new TreeItem<>(categoryEntryModel));
                continue;
            }

            if (!parentMap.containsKey(parent)){ //If the parent is not in the map, add it, and get the parent category and add the child category to it.
                if (parentMap.get(parent) == null)
                    parentMap.put(parent, new TreeItem<>(categoryEntryModel)); //This will not be used, as all parents are present in the map.

            }
            else { //If the table contains the parent category, get the parent category and add the child category to it.
                if (parentMap.get(parent) == null)
                    parentMap.put(parent, new TreeItem<>(categoryEntryModel));
                else {
                    parentMap.put(child, new TreeItem<>(categoryEntryModel));
                }

            }

        }

        //Add the categories to the root.
        for(Category category : parentMap.keySet()){
            if (category == null)
                continue;

            if(category.getParent() == null){
                root.getChildren().add(parentMap.get(category));
            }
            else if (parentMap.containsKey(category.getParent())){
                parentMap.get(category.getParent())
                        .getChildren().add(parentMap.get(category));


                parentMap.get(category.getParent()).getChildren().sort(Comparator.comparing(o -> o.getValue().getCategoryName()));
            }
        }

        });
        thread.run();

        while (thread.isAlive()){
        //Wait for the thread to finish
        }
        return root;
    }


    public static TreeItem<CategoryEntryModel> setCategoryHierachy3(List<CategoryEntryModel> categoryEntryModels){
        TreeItem<CategoryEntryModel> root = new TreeItem<>(new CategoryEntryModel("Tilstande"));


        Thread thread = new Thread(() -> {
            HashMap<Category, TreeItem<CategoryEntryModel>> parentMap = new HashMap<>();



            //Create a set for all the parent categories. Sets do not allow duplicates.
            Set<Category> categoryParents = new HashSet<>();
            categoryParents.addAll(categoryEntryModels.stream() //Add all the parent categories to the set.
                    .map(CategoryEntryModel::getCategoryEntry)
                    .map(CategoryEntry::getCategory)
                    .map(Category::getParent)
                    .collect(Collectors.toList()));
            categoryParents.forEach(category -> { parentMap.put(category, null);}); //Put all the parent categories in the map with null as value to be replaced later



            //Get the hierarchy of the categories by their parent category.
            for(CategoryEntryModel categoryEntryModel : categoryEntryModels){
                Category child = categoryEntryModel.getCategoryEntry().getCategory();
                Category parent = child.getParent();


                if(child.getDepth() == 0){ //If the parent is null, the category is the highest level super category.
                    parentMap.put(child, new TreeItem<>(categoryEntryModel));
                }
                else if (child.getDepth() == 1)
                    parentMap.put(child, new TreeItem<>(categoryEntryModel));
                else if (child.getDepth() == 2) {
                    if (parentMap.get(parent) == null)
                        parentMap.put(parent, new TreeItem<>(categoryEntryModel));
                    else if (parentMap.containsKey(parent)){
                        parentMap.get(parent).getChildren().add(new TreeItem<>(categoryEntryModel));
                    }
                }
                else if (child.getDepth() == 3) {
                    if (parentMap.get(parent) == null)
                        parentMap.put(parent, new TreeItem<>(categoryEntryModel));
                    else if (parentMap.containsKey(parent)){
                        parentMap.get(parent).getChildren().add(new TreeItem<>(categoryEntryModel));
                    }

                }

            }

            //Add the categories to the root.
            for(Category category : parentMap.keySet()){
                if (category == null)
                    continue;

                if(category.getParent() == null){
                    root.getChildren().add(parentMap.get(category));
                }
                else if (parentMap.containsKey(category.getParent())){
                    parentMap.get(category.getParent())
                            .getChildren().add(parentMap.get(category));


                    parentMap.get(category.getParent()).getChildren().sort(Comparator.comparing(o -> o.getValue().getCategoryName()));
                }
            }

        });
        thread.run();

        while (thread.isAlive()){
            //Wait for the thread to finish
        }
        return root;
    }
}
