package ovh.jakubk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.jakubk.shop.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
