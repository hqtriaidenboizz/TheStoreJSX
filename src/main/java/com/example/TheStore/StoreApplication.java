package com.example.TheStore;


import com.example.TheStore.Database.DataConnect;
import com.example.TheStore.Models.Products;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

public class StoreApplication extends Application {

    private Scene scene;
    private static final String EMPTY = "";
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        GridPane grid = new GridPane();
        grid.setBackground(Background.fill(Color.web("#7D7C77")));
        grid.setVgap(10);
        grid.setHgap(10);
        DataConnect DB = new DataConnect();
        ArrayList<Products> productsList = DB.getProducts();
        Label label = new Label("QUỐC TRÍ STORE");
        grid.getChildren().add(label);
        label.setStyle("-fx-text-fill: red; -fx-font-size: 30px; -fx-font-weight: 900 ");
//
//        search
        TextField search = new TextField();
        grid.add(search,2,0);
        Button btnSearch = new Button("SEARCH");
        grid.add(btnSearch,3,0);


        Label NameCL = new Label("Product Name");
        NameCL.setStyle("-fx-text-fill: black; -fx-background-color: yellow; -fx-font-size: 15px; -fx-font-weight: 500 ");
        grid.add(NameCL, 2,1);
        Label PriceCL = new Label("Product Price");
        PriceCL.setStyle("-fx-text-fill: black; -fx-background-color: yellow; -fx-font-size: 15px; -fx-font-weight: 500 ");
        grid.add(PriceCL, 3,1);
        Label QuantityCL = new Label("Product Quantity");
        QuantityCL.setStyle("-fx-text-fill: black; -fx-background-color: yellow; -fx-font-size: 15px; -fx-font-weight: 500 ");
        grid.add(QuantityCL, 4,1);

//        input name


        var tfName = new TextField();
        tfName.setPromptText("Name");
        grid.add(tfName, 0, 2);


//        input price
        var tfPrice = new TextField();
        tfPrice.setPromptText("Price");
        grid.add(tfPrice, 0, 3);

//        input Quantity
        var tfQuantity = new TextField();
        tfQuantity.setPromptText("Quantity");
        grid.add(tfQuantity, 0, 4);
        //

        // add

        var btnAdd = new Button("SUBMIT");
        btnAdd.setPadding(new Insets(5, 50, 5, 50));
        btnAdd.setOnAction(e -> {
            String name = tfName.getText();
            Integer price = Integer.valueOf(tfPrice.getText());
            Integer quantity = Integer.valueOf(tfQuantity.getText());
            if (!name.equals(EMPTY) && !price.equals(EMPTY) && !quantity.equals(EMPTY)) {
                DB.insertProducts(new Products(name, price, quantity));
                try {
                    start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                return;
            }
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank!");
            alert.showAndWait();
        });
        grid.add(btnAdd, 0, 5);
        btnAdd.setBackground(Background.fill(Color.GREEN));


        //show products
        for(int i = 0; i < productsList.size(); i++){
            Label lbName = new Label(productsList.get(i).getName());
            grid.add(lbName, 2, i+2);
            Label lbPrice = new Label ((productsList.get(i).getPrice()) + " VND");
            grid.add(lbPrice, 3, i+2);
            Label lbQuantity = new Label(String.valueOf(productsList.get(i).getQuantity()));
            grid.add(lbQuantity, 4, i+2);


            // Update
            var btnUpdate = new Button("Update");
            btnUpdate.setId(String.valueOf(i));
            btnUpdate.setOnAction(e -> {
                btnAdd.setVisible(false);
                int id1 = Integer.parseInt(btnUpdate.getId());
                TextField tfname = (TextField) grid.getChildren().get(6);
                tfname.setText("" + productsList.get(id1).getName());
                TextField tfprice = (TextField) grid.getChildren().get(7);
                tfprice.setText("" + productsList.get(id1).getPrice());
                TextField tfquantity = (TextField) grid.getChildren().get(8);
                tfquantity.setText("" + productsList.get(id1).getQuantity());
                var newbtnAdd = new Button("SUBMIT");
                newbtnAdd.setPadding(new Insets(5, 15, 5, 15));
                newbtnAdd.setOnAction(newe -> {
                    Integer Newid = productsList.get(id1).id;
                    String Newname = tfname.getText();
                    Integer Newprice = Integer.valueOf(tfprice.getText());
                    Integer Newquantity = Integer.valueOf(tfquantity.getText());
                    if (!Newname.equals(EMPTY) && !Newprice.equals(EMPTY) && !Newquantity.equals(EMPTY)) {
                        DB.updateProducts(new Products(Newid, Newname, Newprice, Newquantity));
                        try {
                            start(stage);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        return;
                    }
                    var alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank!");
                    alert.showAndWait();
                });
                grid.add(newbtnAdd, 0, 5);
                newbtnAdd.setBackground(Background.fill(Color.GREEN));

            });
            grid.add(btnUpdate, 5, i+2);
            btnUpdate.setBackground(Background.fill(Color.web("#1987DA")));


            // Delete
            var btnDelete = new Button("Delete");
            btnDelete.setId(String.valueOf(productsList.get(i).id));
            btnDelete.setOnAction(e -> {
                int id3 = Integer.parseInt(btnDelete.getId());
                DB.deleteProducts(id3);
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Deleted!");
                alert.showAndWait();
                try {
                    start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            grid.add(btnDelete, 6, i+2);
            btnDelete.setBackground(Background.fill(Color.RED));


        }

        scene = new Scene(grid, 800, 500);
        stage.setTitle("products");
        stage.setScene(scene);
        stage.show();
    }
}
