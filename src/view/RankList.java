package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.VBox;
import model.RankElement;
import model.UserImform;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankList {
    private int num;
    private String mode;
    Scene scene;
    List<RankElement> rank;
    public RankList(String mode)
    {
        this.mode=mode;
        rank=new ArrayList<>();
        ReadFile(mode);
        DoSort();
        RankPrint();
    }
    public void DoSort()
    {
        /* if(mode.equals("classic"))rank.sort(new compSteps());
        else if(mode.equals("challenge")||mode.equals("scores"))*/
            rank.sort(new compScores());
    }

    public void RankPrint()
    {
        for(int i=0;i<rank.size();i++){
            rank.get(i).setRank(i+1);
        }
        // 创建一个ObservableList来绑定到ListView
        ObservableList<RankElement> observableRankList = FXCollections.observableArrayList(rank);

        // 创建ListView并设置cellFactory来显示自定义的字符串
        ListView<RankElement> listView = new ListView<>();
        listView.setCellFactory(lv -> new TextFieldListCell<RankElement>() {
            @Override
            public void updateItem(RankElement item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getRank() + ". " + item.getUserName() + " - 步数: " + item.getStep() + ", 分数: " + item.getScore() + ", 时间: " + item.getTimes());
                }
            }
        });
        listView.setItems(observableRankList);

        // 创建布局容器并添加ListView
        VBox vbox = new VBox(10); // 垂直布局，间距10
        vbox.setPadding(new Insets(10)); // 设置内边距
        vbox.setAlignment(Pos.CENTER); // 设置布局内组件居中对齐
        vbox.getChildren().add(listView); // 添加ListView到VBox中

        // 创建场景并将VBox添加到场景中
        scene = new Scene(vbox, 400, 300); // 场景大小为400x300
    }
    public Scene getScene(){return scene;}
    public void ReadFile(String mode)
    {
        String path="C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\rank\\"+mode+"\\rank.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String username = parts[0].trim();
                    int steps=Integer.parseInt(parts[1].trim());
                    int scores = Integer.parseInt(parts[2].trim());
                    int times = Integer.parseInt(parts[3].trim());
                    rank.add(new RankElement(username,steps,scores,times));
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("读取用户文件出错");
            throw new RuntimeException(e);
        }
    }
    public void WriteFile(String mode,RankElement newUser) {
        String path = "C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\rank\\" + mode + "\\rank.txt";

        // 创建一个临时文件来写入新的内容
        Path tempFile = Paths.get("C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\rank\\" + mode + "\\rank1.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8)) {
            // 如果文件存在，读取现有内容并写入临时文件
            if (Files.exists(Paths.get(path))) {
                try (BufferedReader reader = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine(); // 确保每行后面都有换行符
                    }
                }
            }

            // 追加新用户信息到临时文件
            writer.write(String.format("%s,%d,%d,%d", newUser.getUserName(), newUser.getStep(), newUser.getScore(), newUser.getTimes()));
            writer.newLine(); // 确保新用户信息后面有换行符
        } catch (IOException e) {
            System.err.println("写入用户文件出错");
            throw new RuntimeException(e);
        }

        // 替换原始文件为临时文件
        try {
            Files.move(tempFile, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("替换原始文件时出错");
            throw new RuntimeException(e);
        }
    }
}
class compSteps implements Comparator<RankElement>
{
    @Override
    public int compare(RankElement x,RankElement y)
    {
        if(x.getStep()<y.getStep())return -1;
        else if(x.getStep()>y.getStep())return 1;
        else if(x.getStep()<y.getStep())return -1;
        else return 1;
    }
}
class compScores implements Comparator<RankElement>
{
    @Override
    public int compare(RankElement x,RankElement y)
    {
        if(x.getScore()<y.getScore())return 1;
        else if(x.getScore()>y.getScore())return -1;
        else if(x.getStep()<y.getScore())return -1;
        else return 1;
    }
}