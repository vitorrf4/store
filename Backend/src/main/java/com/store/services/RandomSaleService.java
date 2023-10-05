package com.store.services;

import com.store.models.*;
import com.store.repos.StoreRepository;
import com.store.repos.UserRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RandomSaleService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Autowired
    public RandomSaleService(StoreRepository storeRepository, UserRepository userRepository) {
        this.storeRepository = storeRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Sale generateSale(SaleItem itemBought, Store userStore) {
        Customer customer = new Customer(new Faker().name().firstName());
        Sale sale = userStore.makeSale(List.of(itemBought), customer);
        if (sale == null) return null;

        userStore = storeRepository.save(userStore);
        sale = userStore.getLastSale();

        return sale;
    }

    public Store getUserStore(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty() || user.get().getStore() == null) return null;
        Optional<Store> store = storeRepository.findById(user.get().getStore().getId());

        return store.orElse(null);
    }

    public SaleItem getRandomSaleItem(List<Movie> moviesInStock) {
        int quantityInStock = 0;

        for (int i = 0; i < moviesInStock.size(); i++) {
            quantityInStock += moviesInStock.get(i).getCopiesAmount();

            if (i == moviesInStock.size() - 1 && quantityInStock == 0) {
                return null;
            }
        }

        Movie randomCopy;
        do {
            int randomCopyIndex = getRandomInt(moviesInStock.size());
            randomCopy = moviesInStock.get(randomCopyIndex);
        } while(randomCopy.getCopiesAmount() == 0);

        int randomCopiesAmount = getRandomCopy(randomCopy.getCopiesAmount());

        return new SaleItem(randomCopy,  randomCopiesAmount);
    }

    private int getRandomInt(int max) {
        return (int) (Math.random() * (max));
    }

    private int getRandomCopy(int copiesAmount) {
        int max = Math.min(copiesAmount, 4);

        return (int)(Math.random() * (max - 1)) + 1;
    }
}
