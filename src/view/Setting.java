package view;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Setting {
    Stage stage = new Stage();
    Label color = new Label("颜色");
    Label volume = new Label("音量");
    Label size = new Label("游戏面板");
    Label save = new Label("自动保存");
    ChoiceBox<String> choiceBox = new ChoiceBox<>();
    ToggleGroup toggleGroup = new ToggleGroup();
    Slider volumeSlider = new Slider(0, 100, 50); // 最小值、最大值、初始值
    RadioButton radioButton1 = new RadioButton("Classic");
    RadioButton radioButton2 = new RadioButton("Green and Blue");
    RadioButton radioButton3 = new RadioButton("Pink");
    RadioButton radioButton4 = new RadioButton("Blue");
    RadioButton radioButton5 = new RadioButton("Yellow and Red");
    CheckBox autoSaveCheckBox = new CheckBox("设置自动保存");
    public Setting(int p) {
        choiceBox.getItems().addAll("3*3", "4*4", "5*5", "6*6", "7*7", "8*8", "9*9", "10*10");
        radioButton1.setToggleGroup(toggleGroup);

        radioButton2.setToggleGroup(toggleGroup);
        radioButton3.setToggleGroup(toggleGroup);

        radioButton4.setToggleGroup(toggleGroup);
        radioButton5.setToggleGroup(toggleGroup);

        // 布局设置
        VBox vbox = new VBox(30); // 垂直布局，元素间隔10像素
        vbox.setPadding(new Insets(10)); // 设置内边距
        vbox.setAlignment(Pos.CENTER_LEFT); // 设置对齐方式为居中
        HBox radioBox = new HBox(10); // 水平布局，存放RadioButton
        radioBox.getChildren().addAll(radioButton1, radioButton2, radioButton3, radioButton4, radioButton5);
        VBox vBoxcolor = new VBox(20);
        vBoxcolor.setAlignment(Pos.CENTER_LEFT);
        vBoxcolor.setPadding(new Insets(20));
        vBoxcolor.getChildren().addAll(color, radioBox);
        VBox vBoxvolume = new VBox();
        vBoxvolume.setAlignment(Pos.CENTER_LEFT);
        vBoxvolume.setPadding(new Insets(20));
        vBoxvolume.setPrefWidth(100);
        vBoxvolume.setMaxWidth(300);
        vBoxvolume.getChildren().addAll(volume, volumeSlider);
        VBox vBoxsize = new VBox(20);
        vBoxsize.setAlignment(Pos.CENTER_LEFT);
        vBoxsize.setPadding(new Insets(20));
        if(p==1)vBoxsize.getChildren().setAll(size, choiceBox);
        VBox vBoxsave = new VBox(20);
        vBoxsave.setAlignment(Pos.CENTER_LEFT);
        vBoxsave.setPadding(new Insets(20));
        vBoxsave.getChildren().addAll(save, autoSaveCheckBox);
        vbox.getChildren().addAll(vBoxsize, vBoxvolume, vBoxcolor, vBoxsave); // 将RadioButton和CheckBox添加到VBox中



        // 创建场景并设置给Stage
        Scene scene = new Scene(vbox, 600, 600); // 场景大小为600x600像素
        stage.setTitle("设置");
        stage.setScene(scene);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Label getColor() {
        return color;
    }

    public void setColor(Label color) {
        this.color = color;
    }

    public Label getVolume() {
        return volume;
    }

    public void setVolume(Label volume) {
        this.volume = volume;
    }

    public Label getSize() {
        return size;
    }

    public void setSize(Label size) {
        this.size = size;
    }

    public Label getSave() {
        return save;
    }

    public void setSave(Label save) {
        this.save = save;
    }

    public ChoiceBox<String> getChoiceBox() {
        return choiceBox;
    }

    public void setChoiceBox(ChoiceBox<String> choiceBox) {
        this.choiceBox = choiceBox;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }

    public Slider getVolumeSlider() {
        return volumeSlider;
    }

    public void setVolumeSlider(Slider volumeSlider) {
        this.volumeSlider = volumeSlider;
    }

    public RadioButton getRadioButton1() {
        return radioButton1;
    }

    public void setRadioButton1(RadioButton radioButton1) {
        this.radioButton1 = radioButton1;
    }

    public CheckBox getAutoSaveCheckBox() {
        return autoSaveCheckBox;
    }

    public void setAutoSaveCheckBox(CheckBox autoSaveCheckBox) {
        this.autoSaveCheckBox = autoSaveCheckBox;
    }

    public RadioButton getRadioButton2() {
        return radioButton2;
    }

    public void setRadioButton2(RadioButton radioButton2) {
        this.radioButton2 = radioButton2;
    }

    public RadioButton getRadioButton3() {
        return radioButton3;
    }

    public void setRadioButton3(RadioButton radioButton3) {
        this.radioButton3 = radioButton3;
    }

    public RadioButton getRadioButton4() {
        return radioButton4;
    }

    public void setRadioButton4(RadioButton radioButton4) {
        this.radioButton4 = radioButton4;
    }

    public RadioButton getRadioButton5() {
        return radioButton5;
    }

    public void setRadioButton5(RadioButton radioButton5) {
        this.radioButton5 = radioButton5;
    }
}
