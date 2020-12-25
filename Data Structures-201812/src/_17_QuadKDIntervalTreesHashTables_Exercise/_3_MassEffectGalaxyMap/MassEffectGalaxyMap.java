package _17_QuadKDIntervalTreesHashTables_Exercise._3_MassEffectGalaxyMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MassEffectGalaxyMap {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int starClustersCount = Integer.parseInt(reader.readLine());
        int reportsCount = Integer.parseInt(reader.readLine());
        int galaxySize = Integer.parseInt(reader.readLine());

        KdTree galaxy = new KdTree();
        for (int i = 0; i < starClustersCount; i++) {
            String[] clusterData = reader.readLine().split(" ");
            int x = Integer.parseInt(clusterData[1]);
            int y = Integer.parseInt(clusterData[2]);
            Point2D starCluster = new Point2D(x, y);
            galaxy.insert(starCluster, galaxySize);
        }

        for (int i = 0; i < reportsCount; i++) {
            String[] reportData = reader.readLine().split(" ");
            int x = Integer.parseInt(reportData[1]);
            int y = Integer.parseInt(reportData[2]);
            int width = Integer.parseInt(reportData[3]);
            int height = Integer.parseInt(reportData[4]);
            GalaxyArea galaxyArea = new GalaxyArea(x, y, width, height);

            System.out.println(galaxy.findPointsInArea(galaxyArea));
        }

    }
}
