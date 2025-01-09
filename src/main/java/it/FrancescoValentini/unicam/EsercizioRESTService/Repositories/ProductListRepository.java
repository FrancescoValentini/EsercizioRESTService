package it.FrancescoValentini.unicam.EsercizioRESTService.Repositories;

import org.springframework.data.repository.CrudRepository;
import it.FrancescoValentini.unicam.EsercizioRESTService.Models.Product;



public interface ProductListRepository extends CrudRepository<Product, String> {
}

