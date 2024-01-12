import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = List.of("C:Games/savegames/save.dat",
                "C:Games/savegames/save1.dat",
                "C:Games/savegames/save2.dat");
        GameProgress gameProgress = new GameProgress(94,10,2,254.3);
        GameProgress gameProgress1 = new GameProgress(80,20,30,200);
        GameProgress gameProgress2 = new GameProgress(91,35,10,158);
        saveGames("C:Games/savegames/save.dat",gameProgress);
        saveGames("C:Games/savegames/save1.dat", gameProgress1);
        saveGames("C:Games/savegames/save2.dat", gameProgress2);
        for (int i = 0; i < list.size(); i++) {
            zipFiles(list.get(i));
        }


    }
        public static void saveGames(String way,GameProgress gameProgress) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(way);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(gameProgress);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        public static void zipFiles(String way)  {
            try(ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("zip_out.zip"));
            FileInputStream fis = new FileInputStream(way)){
                ZipEntry entry = new ZipEntry(way);
                zos.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zos.write(buffer);
                zos.closeEntry();
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
}