package com.testing.test.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.apache.tika.Tika;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testing.test.model.FilesEntity;
import com.testing.test.model.MainFileEntity;
import com.testing.test.repository.FilesRepository;
import com.testing.test.repository.MainFileRepository;




@RestController
@RequestMapping("/api/v1/")
public class FilesController {
	
	@Autowired
	private MainFileRepository mainFileRepository;
	
	@Autowired
	private FilesRepository filesRepository;
	
	@PostMapping("upload")
    public ResponseEntity<String> handleFileUpload() throws IOException {
        String directoryPath = "C:\\Users\\murali\\Pictures";

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files == null || files.length == 0) {
            return ResponseEntity.badRequest().body("No Files Found");
        }
        for (File file : files) {
            if (file.isFile()) {
            	try {
            	System.out.println("Processing file: " + file.getName());
                List<FilesEntity> fileEntities = processFileName(file.getName(), Files.readAllBytes(file.toPath()));
                MainFileEntity mainFileEntity = new MainFileEntity();
                
                mainFileEntity.setFiles(fileEntities);
                mainFileRepository.save(mainFileEntity);
                for (FilesEntity filesEntity : fileEntities) {
                    filesRepository.save(filesEntity);
                }
            	}catch (Exception e) {
                    System.err.println("Error processing file " + file.getName() + ": " + e.getMessage());
                }
            }
        }
        return ResponseEntity.ok("Files Saved");
    }
	private List<FilesEntity> processFileName(String originalFileName, byte[] fileContent) throws IOException {
		String[] parts = originalFileName.split("_");

		if (parts.length == 3) {
		    String fileName = parts[0];
		    LocalDate fileDate = LocalDate.parse(parts[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		    // Splitting the time component and excluding the file extension
		    String[] timeAndExtension = parts[2].split("\\.");
		    String timeComponent = timeAndExtension[0];

		    // Adjusted parsing logic for time component with optional colon or hyphen
		    LocalTime fileTime = LocalTime.parse(
		        timeComponent.replaceFirst("^(\\d+)[-:](\\d+)$", "$1:$2"),
		        DateTimeFormatter.ofPattern("HH:mm")
		    );

		    String fileType = detectFileType(fileContent);

		    return Stream.of(new FilesEntity(fileName, fileType, fileDate, fileTime)).collect(Collectors.toList());
		} else {
		    throw new RuntimeException("Invalid file name format");
		}


	}
	 private String detectFileType(byte[] fileContent) throws IOException {
	        Tika tika = new Tika();
			return tika.detect(fileContent);
	    }
}
