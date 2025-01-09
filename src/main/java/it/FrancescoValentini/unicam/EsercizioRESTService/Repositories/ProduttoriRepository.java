package it.FrancescoValentini.unicam.EsercizioRESTService.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.FrancescoValentini.unicam.EsercizioRESTService.Models.Product;
import it.FrancescoValentini.unicam.EsercizioRESTService.Models.Produttore;



public interface ProduttoriRepository extends CrudRepository<Produttore, String>{

    @Query("SELECT p FROM Product p JOIN p.produttore pr WHERE pr.id = :produttoreId")
    List<Product> productsByProduttoreId(@Param("produttoreId") String produttoreId);
}
