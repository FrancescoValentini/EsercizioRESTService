package it.FrancescoValentini.unicam.EsercizioRESTService.TESTDATA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.FrancescoValentini.unicam.EsercizioRESTService.Models.Product;
import it.FrancescoValentini.unicam.EsercizioRESTService.Models.Produttore;
import it.FrancescoValentini.unicam.EsercizioRESTService.Repositories.ProductListRepository;
import it.FrancescoValentini.unicam.EsercizioRESTService.Repositories.ProduttoriRepository;



@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProduttoriRepository repoProduttori;

    @Autowired
    private ProductListRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Inizializza i produttori
    	repoProduttori.save(new Produttore("1", "Mario", "Rossi"));
    	repoProduttori.save(new Produttore("2", "Luigi", "Bianchi"));
    	repoProduttori.save(new Produttore("3", "Giovanna", "Verdi"));
    	repoProduttori.save(new Produttore("4", "Anna", "Neri"));
    	repoProduttori.save(new Produttore("5", "Paolo", "Gialli"));
    	repoProduttori.save(new Produttore("6", "Laura", "Marrone"));
    	repoProduttori.save(new Produttore("7", "Francesco", "Blu"));
    	repoProduttori.save(new Produttore("8", "Sara", "Arancioni"));
    	repoProduttori.save(new Produttore("9", "Marco", "Viola"));
    	repoProduttori.save(new Produttore("10", "Elisa", "Rosa"));

        // Inizializza i prodotti
        Produttore p0 = repoProduttori.findById("0")
            .orElseThrow(() -> new RuntimeException("Produttore 0 non trovato"));
        Produttore p1 = repoProduttori.findById("1")
            .orElseThrow(() -> new RuntimeException("Produttore 1 non trovato"));
        Produttore p2 = repoProduttori.findById("2")
                .orElseThrow(() -> new RuntimeException("Produttore 1 non trovato"));
        Produttore p5 = repoProduttori.findById("5")
                .orElseThrow(() -> new RuntimeException("Produttore 1 non trovato"));
        Produttore p6 = repoProduttori.findById("6")
                .orElseThrow(() -> new RuntimeException("Produttore 1 non trovato"));
        
        // Aggiunge i prodotti
        productRepository.save(new Product("1", "Mele", 15.0, p0));
        productRepository.save(new Product("2", "Pere", 10.0, p2));
        productRepository.save(new Product("3", "Sale", 5.0, p5));
        productRepository.save(new Product("4", "Limoni", 28.0, p6));
        productRepository.save(new Product("5", "Limoncello", 43.5, p1));
        productRepository.save(new Product("6", "Grappa", 40.0, p2));
        productRepository.save(new Product("7", "Olio d'Oliva", 12.5, p5));
        productRepository.save(new Product("8", "Pomodori", 8.0, p6));
        productRepository.save(new Product("9", "Vino Rosso", 25.0, p1));
        productRepository.save(new Product("10", "Formaggio", 18.0, p0));
    }
}