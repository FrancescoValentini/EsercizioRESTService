package it.FrancescoValentini.unicam.EsercizioRESTService.RESTControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.FrancescoValentini.unicam.EsercizioRESTService.Models.Product;
import it.FrancescoValentini.unicam.EsercizioRESTService.Models.Produttore;
import it.FrancescoValentini.unicam.EsercizioRESTService.Repositories.ProductListRepository;
import it.FrancescoValentini.unicam.EsercizioRESTService.Repositories.ProduttoriRepository;



@RestController
public class RestProdotti {
	
	@Autowired
	private ProductListRepository productRepository;
	
	@Autowired
	private ProduttoriRepository repoProduttori;
	
	@GetMapping(value = "/prodotti")
	public ResponseEntity<Object> getprodotti() {
		return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/prodotti/{id}")
	public ResponseEntity<Object> getProduct(@PathVariable("id") String id) {
		if (!productRepository.existsById(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(productRepository.findById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/prodotti")
	public ResponseEntity<Object> addProduct(@RequestBody Product product) {
		Produttore produttore = repoProduttori.findById(product.getProduttore().getId()).get();
		
		if(produttore != null) { // Verifica che il produttore specificato esista
			if (!productRepository.existsById(product.getId())) { // Verifica che il prodotto non sia già presente
				product.setProduttore(produttore); // Imposta il produttore del prodotto
				productRepository.save(product); // Salva il prodotto nella repository
				return new ResponseEntity<>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	@PutMapping(value = "/prodotti")
	public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
		if (productRepository.existsById(product.getId())) {
			productRepository.save(product);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(value = "/prodotti/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id) {
		productRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
