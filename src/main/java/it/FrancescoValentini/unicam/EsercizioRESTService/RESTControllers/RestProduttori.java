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

import it.FrancescoValentini.unicam.EsercizioRESTService.Models.Produttore;
import it.FrancescoValentini.unicam.EsercizioRESTService.Repositories.ProduttoriRepository;



@RestController
public class RestProduttori {
	@Autowired
	private ProduttoriRepository repoProduttori;
	
	@GetMapping(value = "/produttori")
	public ResponseEntity<Object> getProduttori() {
		return new ResponseEntity<>(repoProduttori.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/produttori/{id}")
	public ResponseEntity<Object> getProduttore(@PathVariable("id") String id) {
		if (!repoProduttori.existsById(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(repoProduttori.findById(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/produttori/{id}/prodotti")
	public ResponseEntity<Object> getProdotti(@PathVariable("id") String id) {
		if (!repoProduttori.existsById(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(repoProduttori.productsByProduttoreId(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/produttori")
	public ResponseEntity<Object> addProduttore(@RequestBody Produttore p ) {
		if (!repoProduttori.existsById(p.getId())) {
			repoProduttori.save(p);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(value = "/produttori")
	public ResponseEntity<Object> editProduttore(@RequestBody Produttore p ) {
		if (repoProduttori.existsById(p.getId())) {
			repoProduttori.save(p);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(value = "/produttori/{id}")
	public ResponseEntity<Object> deleteProduttore(@PathVariable("id") String id) {
		if (!repoProduttori.existsById(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		repoProduttori.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
