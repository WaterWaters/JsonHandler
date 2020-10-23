import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonTest {


    public static void main(String[] args) {
        List<File> files = getFiles("src/main/resources/file/data1");

        for (File f : files) {
            String folder = f.getParent();
            folder = folder.replace("/Users/wanghongmiao/IdeaProjects/Project/Java_Project/JsonHandler/src/main/resources/file/data1/", "");

            String file = f.getName();
            file = file.replace(".kml", "");
            file = file.replace("_行政边界", "");
            file = file.replace(folder, "");
            System.out.println(file);



//            System.out.println(f.getParent().substring("data1/") + ": " + f.getName());
        }



    }

    public static List<File> getFiles(String path){
        File root = new File(path);
        List<File> files = new ArrayList<File>();
        if(!root.isDirectory()){
            files.add(root);
        }else{
            File[] subFiles = root.listFiles();
            for(File f : subFiles){
                files.addAll(getFiles(f.getAbsolutePath()));
            }
        }
        return files;
    }



}