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
        repoProduttori.save(new Produttore("0", "Produttore00", "Produttore00"));
        repoProduttori.save(new Produttore("1", "Produttore01", "Produttore01"));
        repoProduttori.save(new Produttore("2", "Produttore02", "Produttore02"));
        repoProduttori.save(new Produttore("3", "Produttore03", "Produttore03"));

        // Inizializza i prodotti
        Produttore p1 = repoProduttori.findById("0")
            .orElseThrow(() -> new RuntimeException("Produttore 0 non trovato"));
        Produttore p2 = repoProduttori.findById("1")
            .orElseThrow(() -> new RuntimeException("Produttore 1 non trovato"));

        productRepository.save(new Product("0", "Mele", 15, p1));
        productRepository.save(new Product("1", "Pere", 10, p1));
        productRepository.save(new Product("2", "Sale", 5, p1));
        productRepository.save(new Product("3", "Limoni", 28, p1));

        productRepository.save(new Product("4", "Limoncello", 43.5, p2));
        productRepository.save(new Product("5", "Grappa", 40, p2));
    }
}