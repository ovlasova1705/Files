package zip;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class ZipTest {
    ClassLoader classLoader = ZipTest.class.getClassLoader();

    @Test
    void pdfCheck() throws Exception {
        try (InputStream inputStream = classLoader.getResourceAsStream("arch.zip")){

            try (ZipInputStream zipIS = new ZipInputStream(inputStream)) {
                ZipEntry entry;
                while ((entry = zipIS.getNextEntry()) != null) {
                    if (entry.getName().contains("PDF")) {
                        PDF pdfFile = new PDF(zipIS);
                        assertThat(pdfFile.text).contains("for DmitryT");
                    } else if (entry.getName().contains("CSV")) {
                        CSVReader csvReader = new CSVReader(new InputStreamReader(zipIS, UTF_8));
                        List<String[]> csv = csvReader.readAll();
                        assertThat(csv).contains(
                                new String[]{"56789", "1"},
                                new String[]{"67688", "2"},
                                new String[]{"55790", "3"}
                                );
                    } else if (entry.getName().contains("xlsx")) {
                        XLS xls = new XLS(zipIS);
                        assertThat(xls.excel.getSheetAt(0).getRow(2)
                                .getCell(2).getStringCellValue()).contains("Dmitry");
                        assertThat(xls.excel.getSheetAt(0).getRow(1)
                                .getCell(1).getStringCellValue()).contains("xlsx FILE");

                    }
                }}}}}







