package org.algorithm.algorithm.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author lvkai
 * @version 1.0
 * @description FileUtils
 * @date 2022/9/21
 */
public class FileUtils {

    @SuppressWarnings("unused")
    public static class Structure {

        public static void main(String[] args) {
            getTreeStructure(
                    "C:\\Users\\lvkai\\Desktop\\example\\TelementSAP", "TreeOfFiles.txt",
                    true, false, Arrays.asList(".*", "WebRoot", "*.xml"),
                    true, true
            );
        }

        public static void getTreeStructure(String readPath) {
            getTreeStructure(readPath, "", false, false, new ArrayList<>(), true, false);
        }

        public static void getTreeStructure(
                String readPath, String writePath, boolean dirLevelMark, boolean fileDescriptionMark
        ) {
            getTreeStructure(readPath, writePath, dirLevelMark, fileDescriptionMark, new ArrayList<>(), false, true);
        }

        public static void getTreeStructure(
                String readPath, String writePath, boolean dirLevelMark, boolean fileDescriptionMark, List<String> exclusiveFileNames
        ) {
            getTreeStructure(readPath, writePath, dirLevelMark, fileDescriptionMark, exclusiveFileNames, false, true);
        }

        public static void getTreeStructure(
                String readPath, boolean dirLevelMark, boolean fileDescriptionMark, List<String> exclusiveFileNames
        ) {
            getTreeStructure(readPath, "", dirLevelMark, fileDescriptionMark, exclusiveFileNames, true, false);
        }

        public static void getTreeStructure(
                String readPath, String writePath,
                boolean dirLevelMark, boolean fileDescriptionMark, List<String> exclusiveFileNames,
                boolean output2Console, boolean output2File
        ) {
            File root = new File(readPath);
            List<String> lines = new ArrayList<>();
            recursive(
                    root, 0, new ArrayList<>(Collections.singleton(-1)), true, lines,
                    dirLevelMark, fileDescriptionMark, exclusiveFileNames
            );

            if (output2Console) lines.forEach(System.out::println);

            if (output2File) {
                try {
                    BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(new File(writePath).toPath()));
                    bos.write(String.join("\r\n", lines).getBytes(StandardCharsets.UTF_8));
                    bos.flush();
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private static void recursive(
                File root, int level, List<Integer> allBlankIdx, boolean isLastOne, List<String> lines,
                boolean dirLevelMark, boolean fileDescriptionMark, List<String> exclusiveFileNames
        ) {

            if (isLastOne) allBlankIdx.add(level);
            if (root.isDirectory()) {
                lines.add(getPrefix(root.getName(), level, allBlankIdx, isLastOne, true, dirLevelMark, fileDescriptionMark));
                File[] files = Arrays.stream(Optional.ofNullable(root.listFiles()).orElse(new File[0]))
                        .filter(file -> !isExclusiveFile(file, exclusiveFileNames)).toArray(File[]::new);
                for (int i = 0; i < files.length; i++)
                    recursive(
                            files[i], level + 1, allBlankIdx, i == files.length - 1, lines,
                            dirLevelMark, fileDescriptionMark, exclusiveFileNames
                    );
            } else {
                lines.add(getPrefix(root.getName(), level, allBlankIdx, isLastOne, false, dirLevelMark, fileDescriptionMark));
            }
            if (allBlankIdx.get(allBlankIdx.size() - 1) == level) allBlankIdx.remove(allBlankIdx.size() - 1);
        }

        private static String getPrefix(
                String name, int level, List<Integer> allBlankIdx,
                boolean isLastOne, boolean isDir, boolean dirLevelMark, boolean fileDescriptionMark
        ) {
            String prefix = String.format(
                    "%s%s%s",
                    IntStream.rangeClosed(0, level).mapToObj(i -> allBlankIdx.contains(i - 1) ? getSpace("    ", i < 2 ? 0 : 1) : getSpace("┃   ", 1)).collect(Collectors.joining()),
                    isLastOne ? (level == 0 ? "" : "┗") : "┣",
                    level == 0 ? "" : getSpace("━", 3)
            );
            String suffix = "(%s)", dirLevel = "", fileDescription = "";
            if (dirLevelMark && isDir) dirLevel = String.format("level: %d", level);
            if (fileDescriptionMark) fileDescription = String.format("description: %s", "");
            suffix = String.format(suffix, String.join(" ", Arrays.asList(dirLevel, fileDescription)).trim());
            if (suffix.replaceAll("\\(|\\s+|\\)", "").isEmpty()) suffix = "";

            return String.format(
                    "%s%s%s", prefix, isDir ? "*" : "", name.concat(suffix)
            );
        }

        private static String getSpace(String space, int times) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < times; i++) sb.append(space);
            return sb.toString();
        }

        private static boolean isExclusiveFile(File file, List<String> exclusiveFileNames) {
            if (file == null || file.getName().equals("")) return false;
            return exclusiveFileNames.stream().anyMatch(name -> {
                if (name.equals("*.*")) {
                    return file.getName().contains(".");
                } else if (name.startsWith("*")) {
                    return file.getName().endsWith(name.substring(name.indexOf("*") + 1));
                } else if (name.endsWith("*")) {
                    return file.getName().startsWith(name.substring(0, name.indexOf("*")));
                } else {
                    return file.getName().equals(name);
                }
            });
        }
    }
}
