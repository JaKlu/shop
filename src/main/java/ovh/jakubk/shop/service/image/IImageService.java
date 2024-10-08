package ovh.jakubk.shop.service.image;

import org.springframework.web.multipart.MultipartFile;
import ovh.jakubk.shop.dto.ImageDto;
import ovh.jakubk.shop.model.Image;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);

    void deleteImageById(Long id);

    List<ImageDto> saveImages(Long productId, List<MultipartFile> files);

    void updateImage(MultipartFile file, Long imageId);
}
