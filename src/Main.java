import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class Main {

    /**
     * Main driver for whitespace and comment removing program. Reads in files and
     * assumes the files will be named with a .in extension. Writes same file, with
     * no whitespace and optionally no comments with the same name and a .out extension.
     * @param args The first string arg must be the file name with .in extension. the second
     *             and optional arg can be used, called "no-comments".
     */
    public static void main(String[] args) throws IOException {
	    Path inputPath = Paths.get(args[0]);
        String fileName = inputPath.getFileName().toString();

        // if the filename doesn't have the .in extension we will exit with helpful message
        if (!fileName.endsWith(".in")) {
            System.out.println("Only able to read files with .in extension. Please rename file and try again.");
            System.exit(1);
        }

        // create the output file
        String outputFileName = fileName.replace("in", "out");
        String outputDir = inputPath.toRealPath(NOFOLLOW_LINKS).getParent().toString();
        Path outputPath = Paths.get(outputDir, outputFileName);

        // decide whether or not we will be using the no-comments flag
        boolean isNoComment;
        if (args.length == 2 && args[1].equals("no-comments")) {
            isNoComment = true;
        } else {
            isNoComment = false;
        }

        try {
            List<String> updatedLines = Files.readAllLines(inputPath).stream()
                // remove all whitespace
                .map(whiteful -> whiteful.replaceAll(" ", ""))
                // if the no comment flag is set, remove comments
                .map(commentful -> {
                    if (isNoComment) {
                        return commentful.replaceAll("(//.*)", "");
                    } else {
                        return commentful;
                    }
                })
                // remove tab characters
                .map(tabful -> tabful.replaceAll("\t", ""))
                // remove blank lines after comment removal in case comment was the whole line
                .filter(line -> !line.equals(""))
                .collect(Collectors.toList());
            // write out the file
            Files.write(outputPath, updatedLines, Charset.defaultCharset());
        } catch (IOException e) {
            System.out.println("Unable to read file from " + inputPath.toString());
            System.exit(1);
        }
    }
}
