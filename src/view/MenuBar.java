package view;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuBar {
    //用于设置菜单
    //功能应包括restart,save,调节音量,调节大小,返回,设置,退出
    private Menu menuFile=new Menu("文件");
    private MenuItem restartItem =new Menu("重新开始");
    private MenuItem save = new Menu("保存游戏");
    private Menu menuOther=new Menu("其他");
    private MenuItem settingItem = new Menu("设置");
    private MenuItem helpItem = new Menu("帮助");
    private Slider volumeSlider = new Slider(0,100,50);
    private MenuItem volumeItem = new MenuItem();
   private MenuItem back = new Menu("返回");
   private MenuItem about = new Menu("关于");
    Stage aboutStage = new Stage();
   private javafx.scene.control.MenuBar menuBar =new javafx.scene.control.MenuBar();
   public MenuBar(){

       menuFile.getItems().addAll(restartItem,save);
       menuOther.getItems().addAll(settingItem,helpItem,back,about);
       menuBar.getMenus().addAll(menuFile,menuOther);
       helpItem.setOnAction(event -> {
           Help help1 = new Help();
       });
       this.getAbout().setOnAction(event -> {
           TextArea name = new TextArea("玩得开心\n" +
                   "————吴鎏亿  钟庸");
           VBox vBox = new VBox();
           vBox.setAlignment(Pos.CENTER);
           vBox.getChildren().add(name);
           Scene abputScene = new Scene(vBox,300,200);
           aboutStage.setScene(abputScene);
           aboutStage.setTitle("关于");
           aboutStage.show();
       });
   }

    public MenuItem getSettingItem() {
        return settingItem;
    }

    public void setSettingItem(Menu settingItem) {
        this.settingItem = settingItem;
    }

    public MenuItem getHelpItem() {
        return helpItem;
    }

    public void setVolume(Menu volume) {
        this.helpItem = volume;
    }

    public Slider getVolumeSlider() {
        return volumeSlider;
    }

    public void setVolumeSlider(Slider volumeSlider) {
        this.volumeSlider = volumeSlider;
    }

    public MenuItem getVolumeItem() {
        return volumeItem;
    }

    public void setVolumeItem(MenuItem volumeItem) {
        this.volumeItem = volumeItem;
    }

    public MenuItem getRestartItem() {
        return restartItem;
    }

    public void setRestartItem(Menu restartItem) {
        this.restartItem = restartItem;
    }

    public MenuItem getSave() {
        return save;
    }

    public void setSave(Menu save) {
        this.save = save;
    }

    public MenuItem getBack() {
        return back;
    }

    public void setBack(Menu back) {
        this.back = back;
    }


    public MenuItem getAbout() {
        return about;
    }

    public void setAbout(Menu about) {
        this.about = about;
    }
    public javafx.scene.control.MenuBar getMenuBar(){return menuBar;}
}
