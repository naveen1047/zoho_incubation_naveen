package com.naveen.jersey_db.wallet;

public class DependenciesFactory {

    private static WalletService walletService;

    private static WalletRepo walletRepo;

    private static WalletRepo getWalletRepo() {
        if (walletRepo == null) walletRepo = new WalletRepo();
        return walletRepo;
    }

    public static WalletService getWalletService() {
        if (walletService == null) {
            walletService = new WalletService(getWalletRepo());
        }
        return walletService;
    }
}
