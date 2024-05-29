package backend.sasonptumayense.Controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import backend.sasonptumayense.response.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileController {
    public static boolean hasAllowedExtension(String fileName, Set<String> allowedExtensions) {
        for (String extension : allowedExtensions) {
            if(fileName.toLowerCase().endsWith(extension)) return true;
        }
        return false;
    }

    public ResponseEntity<ApiResponse> uploadFile(MultipartFile file, String name, String path) {
        try{
            String pathServer = "src/main/resources/images/";
            String fileName = name + "-" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
            byte[] bytes = file.getBytes();
            String fileOriginalName = file.getOriginalFilename();

            long fileSize = file.getSize();
            long maxFileSize = 9 * 1024 * 1024;

            if(fileSize > maxFileSize) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "File too large, max 9MB", null), HttpStatus.BAD_REQUEST);
            
            Set<String> allowedExtensions = new HashSet<>();
            allowedExtensions.add(".jpg");
            allowedExtensions.add(".jpeg");
            allowedExtensions.add(".png");
            allowedExtensions.add(".gif");

            if(!hasAllowedExtension(fileOriginalName, allowedExtensions)) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "File extension not allowed, only .jpg, .jpeg, .png, .gif", null), HttpStatus.BAD_REQUEST);

            String extension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
            String newFileName = fileName + extension;


            File folder = new File(pathServer + path);
            if(!folder.exists()) folder.mkdirs();

            Path filePath = Paths.get(pathServer + path + "/" + newFileName);
            Files.write(filePath, bytes);

            return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Saved", path + "/" + newFileName), HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ApiResponse> deleteFile(String path) {
        try{
            String pathServer = "src/main/resources/images/" + path;
            File file = new File(pathServer);
            if(file.exists()) file.delete();
            return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Deleted", null), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
