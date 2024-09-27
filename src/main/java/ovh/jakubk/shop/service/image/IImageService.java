package ovh.jakubk.shop.service.image;

import org.springframework.web.multipart.MultipartFile;
import ovh.jakubk.shop.dto.ImageDto;
import ovh.jakubk.shop.model.Image;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);

    void deleteImageById(Long id);

    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);

    void updateImage(MultipartFile file, Long imageId);
}
