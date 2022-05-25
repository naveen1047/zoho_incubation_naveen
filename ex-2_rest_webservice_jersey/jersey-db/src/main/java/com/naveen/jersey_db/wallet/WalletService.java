package com.naveen.jersey_db.wallet;

interface WalletServiceInterface {
    public Wallet getWallet(int id);

    public Wallet setWallet(int id, double balance);
}

public class WalletService implements WalletServiceInterface {
    WalletRepo repo;

    WalletService(WalletRepo repo) {
        this.repo = repo;
    }

    @Override
    public Wallet getWallet(int id) {
        double balance = repo.getBalance(id);

        Wallet wallet = new Wallet(id, balance);
        return wallet;
    }

    @Override
    public Wallet setWallet(int id, double balance) {
        return repo.setBalance(id, balance);
    }
}
