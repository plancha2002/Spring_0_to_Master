package com.example.Repository;

import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//<entidadConLaQueTrabajamos,elTipoDeDatoDeLaID>
public interface ProductRepository extends JpaRepository<Product, Long> {

}
