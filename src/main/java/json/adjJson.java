package json;

import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class adjJson {

    public static void main(String[] args) throws IOException {
        JSONReader reader = new JSONReader(new FileReader("src/main/resources/file/China.json"));
        JSONWriter writer = new JSONWriter(new FileWriter("src/main/resources/file/sichuan.json"));
        writer.startArray();
        reader.startArray();
        while(reader.hasNext()) {
            VO vo = reader.readObject(VO.class);
            if ("四川省".equals(vo.getProvince())) {
                writer.writeObject(vo);
            }
        }

        // 反序列化完毕后调用
        reader.endArray();
        reader.close();
    }
}
