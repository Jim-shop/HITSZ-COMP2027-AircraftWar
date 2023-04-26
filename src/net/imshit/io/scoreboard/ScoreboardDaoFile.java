package net.imshit.io.scoreboard;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 计分板DAO的文件实现
 *
 * @author Jim
 */
public class ScoreboardDaoFile implements ScoreboardDao, Closeable {

    private final File f;
    private final List<ScoreInfo> buffer;

    public ScoreboardDaoFile(String path) {
        this.f = new File(path);
        try {
            if (!f.exists()) {
                Files.createFile(Path.of(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = new ArrayList<>();
        try (var ois = new ObjectInputStream(new FileInputStream(f))) {
            for (Object elem : (ArrayList<?>) ois.readObject()) {
                if (elem instanceof ScoreInfo e) {
                    buffer.add(e);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        buffer.sort(Comparator.comparingInt(ScoreInfo::score).reversed());
    }

    @Override
    public List<ScoreInfo> getTopK(int topK) {
        if (topK == -1) {
            return new ArrayList<>(buffer);
        } else {
            return new ArrayList<>(buffer.subList(0, topK));
        }
    }

    @Override
    public void addItem(ScoreInfo item) {
        if (!buffer.contains(item)) {
            buffer.add(item);
            buffer.sort(Comparator.comparingInt(ScoreInfo::score).reversed());
        }
    }

    @Override
    public void deleteItem(ScoreInfo item) {
        buffer.remove(item);
    }

    @Override
    public void deleteItem(int[] indices) {
        Arrays.stream(indices).boxed().sorted(Comparator.reverseOrder()).forEach(index -> buffer.remove(index.intValue()));
    }

    @Override
    public void close() {
        try (var oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
