package net.common.build.freemarker.image;

import freemarker.cache.FileTemplateLoader;
import freemarker.core.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import net.common.build.freemarker.FreeMarkerHelper;

/**
 *
 * @author Heather
 */
public class ImageDimensionReaderForFreeMarker {
    //The following method will only work on templates loaded with a file loader
    public Dimension2DIntegerPrecision readFile(String file) throws IOException {
        Environment currentEnvironment = Environment.getCurrentEnvironment();
        FileTemplateLoader templateLoader = (FileTemplateLoader)currentEnvironment.getConfiguration().getTemplateLoader();
        File toRead = new File(templateLoader.baseDir, FreeMarkerHelper.getTemplatePathFromRoot(currentEnvironment, file));
        return readInputStream(new FileInputStream(toRead));
    }
    
    public Dimension2DIntegerPrecision readUrl(String file) throws IOException {
        return readInputStream(new URL(file).openStream());
    }
    
    private Dimension2DIntegerPrecision readInputStream(InputStream input) throws IOException {
        ImageInputStream in = ImageIO.createImageInputStream(input);
        try {
            final Iterator readers = ImageIO.getImageReaders(in);
            if (readers.hasNext()) {
                ImageReader reader = (ImageReader) readers.next();
                try {
                    reader.setInput(in);
                    return new Dimension2DIntegerPrecision(reader.getWidth(0), reader.getHeight(0));
                } finally {
                    reader.dispose();
                }
            }
            return null;
        } finally {
            if (in != null)
                in.close();
        }
    }
}
