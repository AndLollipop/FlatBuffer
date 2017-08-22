package lypop.com.myflatbuffer;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.flatbuffers.FlatBufferBuilder;
import com.lypop.bean.Action;
import com.lypop.bean.LoveWoman;
import com.lypop.bean.People;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void serialize(View view) {
        //序列化
        FlatBufferBuilder builder = new FlatBufferBuilder();
        //创建People
        int loveWomanName01 = builder.createString("小红");
        int loveWoman01 = LoveWoman.createLoveWoman(builder, loveWomanName01, 21, true);

        int loveWomanName02 = builder.createString("小七");
        int loveWoman02 = LoveWoman.createLoveWoman(builder, loveWomanName02, 19, true);

        int lovePeopleList = Action.createLovePeopleVector(builder, new int[]{loveWoman01, loveWoman02});
        int action = Action.createAction(builder, true, true, false, lovePeopleList);
        int actionOffSet = People.createActionVector(builder,new int[]{action});

        int peopleName = builder.createString("张三");
        int peopleSex = builder.createString("男");
        People.startPeople(builder);
        People.addName(builder, peopleName);
        People.addAge(builder,99);
        People.addSex(builder, peopleSex);
        People.addAction(builder, actionOffSet);
        int rootItems = People.endPeople(builder);
        People.finishPeopleBuffer(builder, rootItems);

//        int peopleName = builder.createString("张三");
//        int peopleSex = builder.createString("男");
//        int people = People.createPeople(builder, peopleName, 22, peopleSex, action);
//        People.finishPeopleBuffer(builder, people);

        //保存到数据到文件中
        File sdcard = Environment.getExternalStorageDirectory();
        //保存的路径
        File file = new File(sdcard, "people.txt");
        if (file.exists()) {
            file.delete();
        }
        ByteBuffer data = builder.dataBuffer();
        FileOutputStream out = null;
        FileChannel channel = null;
        try {
            out = new FileOutputStream(file);
            channel = out.getChannel();
            while (data.hasRemaining()) {
                channel.write(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (channel != null) {
                    channel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deSerialize(View view) {
        FileInputStream fis = null;
        FileChannel readChannel = null;
        try {
            File sdcard = Environment.getExternalStorageDirectory();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            //保存的路径
            File file = new File(sdcard, "people.txt");
            fis = new FileInputStream(file);
            readChannel = fis.getChannel();
            int readBytes = 0;
            while ((readBytes = readChannel.read(byteBuffer)) != -1) {
                Log.i("Lypop", "读取数据的readBytes长度=" + readBytes);
            }
            //把指针回到最初的状态，准备从byteBuffer当中读取数据
            byteBuffer.flip();
            //解析出二进制为item的对象
            People people = People.getRootAsPeople(byteBuffer);
            //读取数据
            Log.i("Lypop", "name=" + people.name() + "  age=" + people.age());

            for (int i = 0; i < people.actionLength(); i++) {
                Action action = people.action(i);
                Log.i("Lypop", "isHead=" + action.isHead() + "isRun=" + action.isRun());
                for (int j = 0; j < action.lovePeopleLength(); j++) {
                    LoveWoman loveWoman = action.lovePeople(j);
                    Log.i("Lypop", "name=" + loveWoman.name() + "  age=" + loveWoman.age());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (readChannel != null) {
                    readChannel.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
