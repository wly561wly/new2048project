package view;


import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;

public class MenuBar {
    //用于设置菜单
    //功能应包括restart,save,调节音量,调节大小,返回,设置,退出
   private Menu setting = new Menu("设置");
   private Menu volume = new Menu("音量");
   private Slider volumeSlider = new Slider(0,100,50);
    private MenuItem volumeItem = new MenuItem();
   private Menu restart =new Menu("重新开始");
   private Menu save = new Menu("保存游戏");
   private Menu back = new Menu("返回");
   private  Menu exit = new Menu("退出");
   private Menu about = new Menu("关于");
   private javafx.scene.control.MenuBar menuBar =new javafx.scene.control.MenuBar();
   public MenuBar(){
       menuBar.getMenus().addAll(setting,volume,restart,save,back,exit,about);
   }

    public Menu getSetting() {
        return setting;
    }

    public void setSetting(Menu setting) {
        this.setting = setting;
    }

    public Menu getVolume() {
        return volume;
    }

    public void setVolume(Menu volume) {
        this.volume = volume;
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

    public Menu getRestart() {
        return restart;
    }

    public void setRestart(Menu restart) {
        this.restart = restart;
    }

    public Menu getSave() {
        return save;
    }

    public void setSave(Menu save) {
        this.save = save;
    }

    public Menu getBack() {
        return back;
    }

    public void setBack(Menu back) {
        this.back = back;
    }

    public Menu getExit() {
        return exit;
    }

    public void setExit(Menu exit) {
        this.exit = exit;
    }

    public Menu getAbout() {
        return about;
    }

    public void setAbout(Menu about) {
        this.about = about;
    }

    public javafx.scene.control.MenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(javafx.scene.control.MenuBar menuBar) {
        this.menuBar = menuBar;
    }
}
