package lk.ijse.cropinventoryservice.repository;

import lk.ijse.cropinventoryservice.model.Crop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends MongoRepository<Crop, String> {
}