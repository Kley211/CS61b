package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static void main(String[] args) {
        // 琴键映射字符串，左到右依次对应37个琴键
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        int size = keyboard.length();
        GuitarString[] strings = new GuitarString[size];

        // 初始化每个琴键的频率
        for (int i = 0; i < size; i++) {
            double frequency = 440.0 * Math.pow(2, (i - 24) / 12.0);
            strings[i] = new GuitarString(frequency);
        }

        while (true) {
            // 检查是否有按键被按下
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) {
                    strings[index].pluck();
                }
            }

            // 叠加所有琴键的 sample
            double sample = 0;
            for (int i = 0; i < size; i++) {
                sample += strings[i].sample();
            }

            // 播放音频
            StdAudio.play(sample);

            // 推进所有琴键的物理模拟
            for (int i = 0; i < size; i++) {
                strings[i].tic();
            }
        }
    }
}