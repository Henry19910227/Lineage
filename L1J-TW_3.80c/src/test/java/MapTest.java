import l1j.server.server.utils.FileUtil;
import l1j.server.server.utils.collections.Lists;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class MapTest {
    private static final String MAP_DIR = "./src/main/resources/maps/";
    private static final String MAP_DIR_JAR = "/maps/";
    @Test
    public void testListMapIds() {
        List<Integer> ids = Lists.newList();
        File mapDir = new File(MAP_DIR);
        for (String name : mapDir.list()) {
            File mapFile = new File(mapDir, name);
            if (!mapFile.exists()) {
                continue;
            }
            int id = 0;
            try {
                String idStr = FileUtil.getNameWithoutExtension(mapFile);
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                continue;
            }
            ids.add(id);
        }
        System.out.println(ids);
    }
}
