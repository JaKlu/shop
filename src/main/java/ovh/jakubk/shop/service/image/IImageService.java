package ovh.jakubk.shop.service.image;

import org.springframework.web.multipart.MultipartFile;
import ovh.jakubk.shop.model.Image;

public interface IImageService {
    Image getImageById(Long id);

    void deleteImageById(Long id);

    Image saveImage(MultipartFile file, Long productId);

    void updateImage(MultipartFile file, Long imageId);
}
