package json;

import com.alibaba.fastjson.JSONWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author wanghongmiao
 */
public class toJson {
    public static void main(String[] args) throws IOException {
        // 把json数组写入json文件
        JSONWriter writer = new JSONWriter(new FileWriter("src/main/resources/file/data1.json"));
        writer.startArray();
        //设置正则表达式匹配地区名称和坐标集
        String name = "(^<name>)[\u4e00-\u9fa5].+(</name>$)";
        String coor = "(^<Polygon><outerBoundaryIs><LinearRing><coordinates>).+(</coordinates></LinearRing></outerBoundaryIs></Polygon>$)";
        String PROVINCE = "四川省";
        int ID = 1;

        //文件夹路径
        List<File> files = getFiles("src/main/resources/file/data1");
        //按文件遍历
        for (File f : files) {
            //读取文件路径
            String path = f.getPath();
            // 创建path文件的文件字节输入流
            FileInputStream fileInputStream = new FileInputStream(path);
            // 创建从字节流到字符流的桥接器
            InputStreamReader isr = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            // 创建一个使用指定大小输入缓冲区的缓冲字符输入流，用于包装InputStreamReader的read()操作
            BufferedReader reader = new BufferedReader(isr, 20 * 1024 * 1024);

            //设置地级市名称为文件名
            String folder = f.getParent();
            folder = folder.replace("/Users/wanghongmiao/IdeaProjects/Project/Java_Project/JsonHandler/src/main/resources/file/data1/", "");
            String CITY = folder;
            String file = f.getName();
            file = file.replace(".kml", "");
            file = file.replace("_行政边界", "");
            file = file.replace(folder, "");
            String AREA = file;
            String NAME = "";
            String COORDINATES;
            String tem;

            //按行索引文件
            while ((tem = reader.readLine()) != null) {
                //去空格
                tem = tem.trim();

                //匹配地区名 存入变量NAME
                if (Pattern.matches(name, tem)) {
                    tem = tem.replace("<name>", "");
                    tem = tem.replace("</name>", "");
                    NAME = tem;
                }

                //匹配坐标集 存入变量 COORDINATES
                if (Pattern.matches(coor, tem)) {
                    tem = tem.replace("<Polygon><outerBoundaryIs><LinearRing><coordinates>", "");
                    tem = tem.replace("</coordinates></LinearRing></outerBoundaryIs></Polygon>", "");
                    tem = tem.replace(" ", ";");
                    COORDINATES = tem;
                    //写入文件
                    writer.writeValue(new VO(ID, NAME, AREA, CITY, PROVINCE, COORDINATES));
                    ID++;
                }
            }
            reader.close();
            isr.close();
            fileInputStream.close();
        }

        writer.endArray();
        writer.close();
    }

    public static List<File> getFiles(String path) {
        File root = new File(path);
        List<File> files = new ArrayList<File>();
        if (!root.isDirectory()) {
            files.add(root);
        } else {
            File[] subFiles = root.listFiles();
            for (File f : subFiles) {
                files.addAll(getFiles(f.getAbsolutePath()));
            }
        }
        return files;
    }
}
